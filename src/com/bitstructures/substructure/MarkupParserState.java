package com.bitstructures.substructure;

public class MarkupParserState {
	
	private boolean isInParagraph;
	private boolean isInCodeBlock;

	public boolean isInParagraph() {
		return isInParagraph;
	}

	public void setInParagraph(boolean isInParagraph) {
		this.isInParagraph = isInParagraph;
	}

	public boolean isInCodeBlock() {
		return isInCodeBlock;
	}

	public void setInCodeBlock(boolean isInCodeBlock) {
		this.isInCodeBlock = isInCodeBlock;
	}

}
