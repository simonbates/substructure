package com.bitstructures.substructure;

import java.io.IOException;
import java.io.Reader;

public class MarkupParser {
	
	private static final String CODE_BLOCK_LINE_PREFIX = "    ";
	private static final String PARAGRAPH_START = "<p>";
	private static final String PARAGRAPH_END = "</p>";
	private static final String CODE_BLOCK_START = "<pre><code>";
	private static final String CODE_BLOCK_END = "</code></pre>";

	public String parse(Reader markupReader) throws IOException {
		LineTokenizer lineTokenizer = new LineTokenizer(markupReader);
		MarkupParserState markupParserState = new MarkupParserState();
		markupParserState.setInParagraph(false);
		markupParserState.setInCodeBlock(false);
		StringBuffer html = new StringBuffer();
		lineTokenizer.readNext();
		while (!lineTokenizer.isAtEnd()) {
			String currentLine = lineTokenizer.getCurrentLine();
			String lookAheadLine = lineTokenizer.lookAhead();
			if (isBlank(currentLine)) {
				doBlank(html, currentLine);
			} else if (isCodeBlockLine(currentLine)) {
				doCodeBlockLine(markupParserState, html, currentLine, lookAheadLine);
			} else {
				doNonBlank(markupParserState, html, currentLine, lookAheadLine);
			}
			lineTokenizer.readNext();
		}
		return html.toString();
	}
	
	private boolean isBlank(String line) {
		return line == null || line.trim().isEmpty();
	}
	
	private boolean isCodeBlockLine(String line) {
		return line.startsWith(CODE_BLOCK_LINE_PREFIX) && !line.trim().isEmpty();
	}

	private void doBlank(StringBuffer html, String currentLine) {
		html.append(currentLine + "\n");
	}
	
	private void doCodeBlockLine(MarkupParserState markupParserState, StringBuffer html, String currentLine, String lookAheadLine) {
		String processedLine = processCodeBlockLine(currentLine);
		if (markupParserState.isInCodeBlock()) {
			if (isBlank(lookAheadLine)) {
				html.append(processedLine + CODE_BLOCK_END + "\n");
				markupParserState.setInCodeBlock(false);
			} else {
				html.append(processedLine + "\n");
			}
		} else {
			if (isBlank(lookAheadLine)) {
				html.append(CODE_BLOCK_START + processedLine + CODE_BLOCK_END + "\n");
			} else {
				html.append(CODE_BLOCK_START + processedLine + "\n");
				markupParserState.setInCodeBlock(true);
			}
		}
	}

	private String processCodeBlockLine(String currentLine) {
		// TODO escape HTML characters
		return currentLine.substring(CODE_BLOCK_LINE_PREFIX.length());
	}

	private void doNonBlank(MarkupParserState markupParserState, StringBuffer html, String currentLine, String lookAheadLine) {
		if (markupParserState.isInParagraph()) {
			if (isBlank(lookAheadLine)) {
				html.append(currentLine + PARAGRAPH_END + "\n");
				markupParserState.setInParagraph(false);
			} else {
				html.append(currentLine + "\n");
			}
		} else {
			if (isBlank(lookAheadLine)) {
				html.append(PARAGRAPH_START + currentLine + PARAGRAPH_END + "\n");
			} else {
				html.append(PARAGRAPH_START + currentLine + "\n");
				markupParserState.setInParagraph(true);
			}
		}
	}
	
}
