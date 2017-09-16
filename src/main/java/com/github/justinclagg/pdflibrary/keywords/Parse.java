package com.github.justinclagg.pdflibrary.keywords;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywordOverload;
import org.robotframework.javalib.annotation.RobotKeywords;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

@RobotKeywords
public class Parse {

	private PdfReader reader;
	private Map<Integer, String> data;
	private int numberOfPages;
	
	@RobotKeyword("Parses a PDF and stores its text.")
	@ArgumentNames({ "file" })
	public void parsePdf(String file) throws IOException {
		reader = new PdfReader(file);
		data = new HashMap<Integer, String>();
		numberOfPages = getNumberOfPdfPages();
		
		for (int page = 1; page <= numberOfPages; page++) {
			System.out.println("Reading page " + page);
			String textFromPage = PdfTextExtractor.getTextFromPage(reader, page);
			data.put(page, textFromPage);
		}
	}
	
	@RobotKeyword("Returns the number of pages in the parsed PDF.")
	public int getNumberOfPdfPages() {
		return reader.getNumberOfPages();
	}
	
	@RobotKeyword("Verifies that the given text exists in the parsed PDF.")
	@ArgumentNames({ "expectedText", "ignoreCase=false" })
	public void pdfShouldContain(String expectedText, boolean ignoreCase) {
		Collection<String> values = data.values();
		collectionShouldContain(expectedText, values, ignoreCase);
	}
	
	@RobotKeywordOverload
	public void pdfShouldContain(String expectedText) {
		pdfShouldContain(expectedText, false);
	}

	private void collectionShouldContain(String expectedText, Collection<String> values, boolean ignoreCase) {
		for (String content : values) {
			if (ignoreCase ? StringUtils.containsIgnoreCase(content, expectedText)
					: StringUtils.contains(content, expectedText)) {
				return;
			}
		}
		throw new RuntimeException("could not find " + expectedText + " in " + data);
	}

}
