package com.github.justinclagg.pdflibrary.keywords;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

@RobotKeywords
public class Parse {

	private PdfReader reader;
	private Map<Integer, String> data;

	@RobotKeyword("Parse a PDF")
	@ArgumentNames({ "filename" })
	public void parsePdf(String filename) throws IOException {
		reader = new PdfReader(filename);

		data = new HashMap<Integer, String>();
		int numberOfPages = reader.getNumberOfPages();
		for (int page = 1; page <= numberOfPages; page++) {
			System.out.println("Reading page " + page);
			String textFromPage = PdfTextExtractor.getTextFromPage(reader, page);
			data.put(page, textFromPage);
		}
	}

	@RobotKeyword("Verify that the given value exists in the parsed PDF.")
	@ArgumentNames({ "expectedValue" })
	public void pdfShouldContain(String expectedValue) {
		Collection<String> values = data.values();
		collectionShouldContain(expectedValue, values, true);
	}

	private void collectionShouldContain(String expectedValue, Collection<String> values, boolean ignoreCase) {
		for (String content : values) {
			if (ignoreCase ? StringUtils.containsIgnoreCase(content, expectedValue)
					: StringUtils.contains(content, expectedValue)) {
				return;
			}
		}
		throw new RuntimeException("could not find " + expectedValue + " in " + data);
	}

}
