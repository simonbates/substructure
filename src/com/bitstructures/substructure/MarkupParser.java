package com.bitstructures.substructure;

import java.io.IOException;
import java.io.Reader;

public class MarkupParser {
	
	private static final String PARAGRAPH_START = "<p>";
	private static final String PARAGRAPH_END = "</p>";
	private static final String CODE_BLOCK_START = "<pre><code>";
	private static final String CODE_BLOCK_END = "</code></pre>";

	LineTokenizer lineTokenizer;
	StringBuffer html;
	
	public void setInput(Reader input) {
		lineTokenizer = new LineTokenizer(input);
	}

	public String parse() throws IOException {
		html = new StringBuffer();
		while (!lookAhead().isEnd()) {
			if (lookAhead().isParagraphLine()) {
				doParagraph();
			} else if (lookAhead().isBlankLine()) {
				doBlankLine();
			} else if (lookAhead().isCodeLine()) {
				doCodeBlock();
			}
		}
		return html.toString();
	}
	
	private Token lookAhead() throws IOException {
		return lineTokenizer.lookAhead();
	}
	
	private Token getNextToken() throws IOException{
		return lineTokenizer.getNextToken();
	}
	
	private void doParagraph() throws IOException {
		html.append(PARAGRAPH_START);
		html.append(getNextToken().getText());
		while (lookAhead().isParagraphLine()) {
			html.append("\n");
			html.append(getNextToken().getText());
		}
		html.append(PARAGRAPH_END + "\n");
	}
	
	private void doBlankLine() throws IOException {
		html.append("\n");
		getNextToken();
	}
	
	private void doCodeBlock() throws IOException {
		html.append(CODE_BLOCK_START);
		html.append(processCodeLine(getNextToken()));
		while (lookAhead().isCodeLine()) {
			html.append("\n");
			html.append(processCodeLine(getNextToken()));
		}
		html.append(CODE_BLOCK_END + "\n");
	}

	private String processCodeLine(Token token) {
		// TODO escape HTML characters
		return token.getText();
	}

}
