package com.bitstructures.substructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
		Reader markupReader = getReader(MARKUP_FILE_NAME_TEMPLATE, n);
		while (markupReader != null) {
			try {
				String expected = getContents(EXPECTED_FILE_NAME_TEMPLATE, n);
				assertEquals(expected, markupParser.parse(markupReader));
			} finally {
				markupReader.close();
			}
			n++;
			markupReader = getReader(MARKUP_FILE_NAME_TEMPLATE, n);
		}
		assertEquals(expectedNumberOfTestCases, n - 1);
	}
	
	private Reader getReader(String template, int n) {
		String fileName = template.replace("{n}", Integer.toString(n));
		InputStream in = getClass().getResourceAsStream(fileName);
		if (in == null) {
			return null;
		}
		return new InputStreamReader(in);
	}
	
	private String getContents(String template, int n) throws IOException {
		Reader reader = getReader(template, n);
		if (reader == null) {
			return null;
		}
		
		// TODO switch over to IOUtils.toString(Reader)
		
		BufferedReader bufferedReader = new BufferedReader(reader);
		StringBuffer contents = new StringBuffer();
		try {
			String line = bufferedReader.readLine();
			while (line != null) {
				contents.append(line);
				contents.append("\n");
				line = bufferedReader.readLine();
			}
		} finally {
			reader.close();
		}
		return contents.toString();
	}
}
