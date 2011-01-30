package com.bitstructures.substructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class LineTokenizer {

	private BufferedReader bufferedReader;
	private boolean isBeforeStart;
	private boolean isAtEnd;
	private String currentLine;
	private String lookAheadLine;
	
	public LineTokenizer(Reader reader) {
		this.bufferedReader = new BufferedReader(reader);
		this.currentLine = null;
		this.lookAheadLine = null;
		this.isBeforeStart = true;
		this.isAtEnd = false;
	}
	
	public void readNext() throws IOException {
		if (isBeforeStart) {
			currentLine = bufferedReader.readLine();
			isBeforeStart = false;
		} else {
			currentLine = lookAheadLine;
		}
		lookAheadLine = bufferedReader.readLine();
		if (currentLine == null) {
			isAtEnd = true;
		}
	}
	
	public String getCurrentLine() {
		return currentLine;
	}
	
	public String lookAhead() {
		return lookAheadLine;
	}
	
	public boolean isAtEnd() {
		return isAtEnd;
	}
}
