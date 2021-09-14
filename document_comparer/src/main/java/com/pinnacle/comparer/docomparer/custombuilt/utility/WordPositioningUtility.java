package com.pinnacle.comparer.docomparer.custombuilt.utility;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

public class WordPositioningUtility extends PDFTextStripper
{

	private static String key_string;
	private static float x;
	private static float y;
	static PDDocument documents;

//test class not get the cordinates of changed charecter  as  expected
	public WordPositioningUtility() throws IOException
	{
		x = -1;
		y = -1;
	}

	/**
	 * Takes in a PDF Document, phrase to find, and page to search and returns the
	 * x,y in float array
	 * 
	 * @param document
	 * @param phrase
	 * @param page
	 * @return
	 * @throws IOException
	 */
	public static float[] getCoordiantes(PDDocument document, String phrase, int page) throws IOException
	{
		documents = document;
		key_string = phrase;
		PDFTextStripper stripper = new WordPositioningUtility();
		stripper.setSortByPosition(true);
		stripper.setStartPage(page);
		stripper.setEndPage(page);
		stripper.writeText(document, new OutputStreamWriter(new ByteArrayOutputStream()));
		System.out.println(y);
		y = document.getPage(0).getMediaBox().getHeight() - y;
		return new float[] { x, y };
	}

	/**
	 * Override the default functionality of PDFTextStripper.writeString()
	 */
	@Override
	protected void writeString(String string, List<TextPosition> textPositions) throws IOException
	{
		PDRectangle cropBox = documents.getPage(0).getCropBox();
		if (string.contains(key_string))
		{
			System.out.println(textPositions);
			TextPosition text = textPositions.get(0);

			if (x == -1)
			{

				x = text.getTextMatrix().getTranslateX() + cropBox.getLowerLeftX();

				y = text.getTextMatrix().getTranslateY() + cropBox.getLowerLeftY();
			}
		}
	}
}
