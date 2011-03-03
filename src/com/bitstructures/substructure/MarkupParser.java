package com.bitstructures.substructure;

import java.io.IOException;
import java.io.Reader;

public class MarkupParser {
	
	private static final String CODE_BLOCK_LINE_PREFIX = "    ";
	private static final String PARAGRAPH_START = "<p>";
	private static final String PARAGRAPH_END = "</p>";
	private static final String CODE_BLOCK_START = "<pre><code>";
	private static final String CODE_BLOCK_END = "</code></pre>";
	
	private Reader input;
	private boolean isInParagraph;
	private boolean isInCodeBlock;
	
	public void setInput(Reader input) {
		this.input = input;
	}

	public String parse() throws IOException {
		LineTokenizer lineTokenizer = new LineTokenizer(input);
		isInParagraph = false;
		isInCodeBlock = false;
		StringBuffer html = new StringBuffer();
		lineTokenizer.readNext();
		while (!lineTokenizer.isAtEnd()) {
			String currentLine = lineTokenizer.getCurrentLine();
			String lookAheadLine = lineTokenizer.lookAhead();
			if (isBlank(currentLine)) {
				doBlank(html, currentLine);
			} else if (isCodeBlockLine(currentLine)) {
				doCodeBlockLine(html, currentLine, lookAheadLine);
			} else {
				doNonBlank(html, currentLine, lookAheadLine);
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
	
	private void doCodeBlockLine(StringBuffer html, String currentLine, String lookAheadLine) {
		String processedLine = processCodeBlockLine(currentLine);
		if (isInCodeBlock) {
			if (isBlank(lookAheadLine)) {
				html.append(processedLine + CODE_BLOCK_END + "\n");
				isInCodeBlock = false;
			} else {
				html.append(processedLine + "\n");
			}
		} else {
			if (isBlank(lookAheadLine)) {
				html.append(CODE_BLOCK_START + processedLine + CODE_BLOCK_END + "\n");
			} else {
				html.append(CODE_BLOCK_START + processedLine + "\n");
				isInCodeBlock = true;
			}
		}
	}

	private String processCodeBlockLine(String currentLine) {
		// TODO escape HTML characters
		return currentLine.substring(CODE_BLOCK_LINE_PREFIX.length());
	}

	private void doNonBlank(StringBuffer html, String currentLine, String lookAheadLine) {
		if (isInParagraph) {
			if (isBlank(lookAheadLine)) {
				html.append(currentLine + PARAGRAPH_END + "\n");
				isInParagraph = false;
			} else {
				html.append(currentLine + "\n");
			}
		} else {
			if (isBlank(lookAheadLine)) {
				html.append(PARAGRAPH_START + currentLine + PARAGRAPH_END + "\n");
			} else {
				html.append(PARAGRAPH_START + currentLine + "\n");
				isInParagraph = true;
			}
		}
	}
	
}
