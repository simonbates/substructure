package com.bitstructures.substructure;

import java.io.StringReader;

import org.junit.Assert;
import org.junit.Test;

public class LineTokenizerTest extends Assert {

	@Test
	public void testLineTokenizer() throws Exception {
		String lines = "1\n2\n3";
		LineTokenizer lineTokenizer = new LineTokenizer(new StringReader(lines));
		assertFalse(lineTokenizer.isAtEnd());
		assertEquals(null, lineTokenizer.getCurrentLine());
		assertEquals(null, lineTokenizer.lookAhead());
		lineTokenizer.readNext();
		assertFalse(lineTokenizer.isAtEnd());
		assertEquals("1", lineTokenizer.getCurrentLine());
		assertEquals("2", lineTokenizer.lookAhead());
		assertEquals("1", lineTokenizer.getCurrentLine());
		assertEquals("2", lineTokenizer.lookAhead());
		lineTokenizer.readNext();
		assertFalse(lineTokenizer.isAtEnd());
		assertEquals("2", lineTokenizer.getCurrentLine());
		assertEquals("3", lineTokenizer.lookAhead());
		lineTokenizer.readNext();
		assertFalse(lineTokenizer.isAtEnd());
		assertEquals("3", lineTokenizer.getCurrentLine());
		assertEquals(null, lineTokenizer.lookAhead());
		lineTokenizer.readNext();
		assertTrue(lineTokenizer.isAtEnd());
		assertEquals(null, lineTokenizer.getCurrentLine());
		assertEquals(null, lineTokenizer.lookAhead());
	}
}
