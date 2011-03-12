package com.bitstructures.substructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class LineTokenizer {
	
	private static final String CODE_BLOCK_LINE_PREFIX = "    ";

	private BufferedReader bufferedReader;
	private boolean isBeforeStart;
	private String lookAheadLine;
	
	public LineTokenizer(Reader reader) {
		bufferedReader = new BufferedReader(reader);
		isBeforeStart = true;
		lookAheadLine = null;
	}
	
	public Token getNextToken() throws IOException {
		Token token = lookAhead();
		readNextLine();
		return token;
	}
	
	public Token lookAhead() throws IOException {
		readNextLineIfBeforeStart();
		return buildTokenForLookAheadLine();
	}
	
	private void readNextLine() throws IOException {
		lookAheadLine = bufferedReader.readLine();
	}

	private void readNextLineIfBeforeStart() throws IOException {
		if (isBeforeStart) {
			readNextLine();
			isBeforeStart = false;
		}
	}
	
	private Token buildTokenForLookAheadLine() {
		if (lookAheadLine == null) {
			return new Token(TokenType.END, null);
		} else if (lookAheadLine.length() == 0) {
			return new Token(TokenType.BLANK_LINE, lookAheadLine);
		} else if (lookAheadLine.startsWith(CODE_BLOCK_LINE_PREFIX)) {
			String codeLine = lookAheadLine.substring(CODE_BLOCK_LINE_PREFIX.length());
			return new Token(TokenType.CODE_LINE, codeLine);
		} else {
			return new Token(TokenType.PARAGRAPH_LINE, lookAheadLine);
		}
	}
	
}
