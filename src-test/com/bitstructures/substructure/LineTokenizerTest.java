package com.bitstructures.substructure;

import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;

public class LineTokenizerTest extends Assert {

	@Test
	public void testTokenStream() throws Exception {
		String lines = "1\n2\n3";
		LineTokenizer lineTokenizer = new LineTokenizer(new StringReader(lines));
		assertEquals("1", lineTokenizer.lookAhead().getText());
		assertEquals("1", lineTokenizer.lookAhead().getText());
		assertEquals("1", lineTokenizer.getNextToken().getText());
		assertFalse(lineTokenizer.lookAhead().isEnd());
		assertEquals("2", lineTokenizer.lookAhead().getText());
		assertEquals("2", lineTokenizer.getNextToken().getText());
		assertFalse(lineTokenizer.lookAhead().isEnd());
		assertEquals("3", lineTokenizer.getNextToken().getText());
		assertTrue(lineTokenizer.lookAhead().isEnd());
		assertTrue(lineTokenizer.getNextToken().isEnd());
		assertTrue(lineTokenizer.lookAhead().isEnd());
		assertTrue(lineTokenizer.getNextToken().isEnd());
	}

	@Test
	public void testParagraphLine() throws Exception {
		LineTokenizer lineTokenizer = new LineTokenizer(new StringReader("hello, world"));
		Token token = lineTokenizer.getNextToken();
		assertTrue(token.isParagraphLine());
		assertFalse(token.isBlankLine());
		assertFalse(token.isCodeLine());
		assertFalse(token.isEnd());
		assertEquals("hello, world", token.getText());
	}

	@Test
	public void testBlankLine() throws Exception {
		LineTokenizer lineTokenizer = new LineTokenizer(new StringReader("\n"));
		Token token = lineTokenizer.getNextToken();
		assertFalse(token.isParagraphLine());
		assertTrue(token.isBlankLine());
		assertFalse(token.isCodeLine());
		assertFalse(token.isEnd());
		assertEquals("", token.getText());
	}

	@Test
	public void testCodeLine() throws Exception {
		String lines = "    \n    a";
		LineTokenizer lineTokenizer = new LineTokenizer(new StringReader(lines));
		
		Token token1 = lineTokenizer.getNextToken();
		assertFalse(token1.isParagraphLine());
		assertFalse(token1.isBlankLine());
		assertTrue(token1.isCodeLine());
		assertFalse(token1.isEnd());
		assertEquals("", token1.getText());
		
		Token token2 = lineTokenizer.getNextToken();
		assertFalse(token2.isParagraphLine());
		assertFalse(token2.isBlankLine());
		assertTrue(token2.isCodeLine());
		assertFalse(token2.isEnd());
		assertEquals("a", token2.getText());
	}

}
