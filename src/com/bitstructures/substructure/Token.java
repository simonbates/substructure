package com.bitstructures.substructure;

public class Token {
	
	private TokenType tokenType;
	private String text;
	
	public Token(TokenType tokenType, String text) {
		this.tokenType = tokenType;
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public boolean isEnd() {
		return TokenType.END.equals(tokenType);
	}
	
	public boolean isParagraphLine() {
		return TokenType.PARAGRAPH_LINE.equals(tokenType);
	}
	
	public boolean isBlankLine() {
		return TokenType.BLANK_LINE.equals(tokenType);
	}
	
	public boolean isCodeLine() {
		return TokenType.CODE_LINE.equals(tokenType);
	}
	
}
