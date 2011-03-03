package com.bitstructures.substructure;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class MarkupParserTest extends Assert {
	
	private static final String MARKUP_FILE_NAME_TEMPLATE = "markup{n}.txt";
	private static final String EXPECTED_FILE_NAME_TEMPLATE = "expected{n}.txt";
	
	private MarkupParser markupParser;

	@Before
	public void setUp() {
		markupParser = new MarkupParser();
	}

	@Test
	public void testParse() throws Exception {
		int expectedNumberOfTestCases = 3;
		
		int n = 1;
		boolean moreToDo = true;
		while (moreToDo) {
			String markup = getContents(MARKUP_FILE_NAME_TEMPLATE, n);
			if (markup == null) {
				moreToDo = false;
			} else {
				StringReader markupReader = new StringReader(markup);
				String expected = getContents(EXPECTED_FILE_NAME_TEMPLATE, n);
				markupParser.setInput(markupReader);
				assertEquals(expected, markupParser.parse());
				n++;
			}
		}
		assertEquals(expectedNumberOfTestCases, n - 1);
	}
	
	private String getContents(String template, int n) throws IOException {
		String fileName = template.replace("{n}", Integer.toString(n));
		URL url = getClass().getResource(fileName);
		if (url == null) {
			return null;
		}
		return Resources.toString(url, Charsets.UTF_8);
	}
	
}
