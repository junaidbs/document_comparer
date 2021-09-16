package com.pinnacle.comparer.docomparer.custombuilt.utility;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.RectangleReadOnly;
import com.itextpdf.text.pdf.PdfWriter;

public class ConvertToPdfUtility
{
	Document document;

	public void startConversion(URL uri) throws MalformedURLException, IOException, DocumentException
	{
		Image image = Image.getInstance(uri);
		document.add(image);
	}

	// initintConversion should call to specify output directory
	public void intConversion(String destdir, int width, int height)
			throws DocumentException, MalformedURLException, IOException
	{
		new RectangleReadOnly(595, 842);
		document = new Document(new RectangleReadOnly(2550, 3299), 0, 0, 0, 0);
		PdfWriter.getInstance(document, new FileOutputStream(destdir));
		document.open();

	}

	public void stopConversion()
	{
		document.close();
	}

}
