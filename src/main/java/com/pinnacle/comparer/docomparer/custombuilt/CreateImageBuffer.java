package com.pinnacle.comparer.docomparer.custombuilt;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.pinnacle.comparer.docomparer.custombuilt.dto.PageInfoDto;
import com.pinnacle.comparer.docomparer.custombuilt.utility.ComparepdfUtility;
import com.pinnacle.comparer.docomparer.custombuilt.utility.ConvertToPdfUtility;
import com.pinnacle.comparer.docomparer.custombuilt.utility.PdfContentInspectUtility;

public class CreateImageBuffer
{

	public List<PageInfoDto> convertToImageAndCompare(String srcFile1, String srcFile2, int startPage, int endPage,
			String destDir, int index, String destTXTDir, String pdfDestDir) throws IOException
	{

		// parameter index is for testing purpose

		PDDocument doc1 = null;
		PDDocument doc2 = null;

		PDFRenderer pdfRenderer1 = null;
		PDFRenderer pdfRenderer2 = null;
		List<PageInfoDto> destPdInfo = new ArrayList<>();

		try
		{

			doc1 = PDDocument.load(new File(srcFile1));
			doc2 = PDDocument.load(new File(srcFile2));
			pdfRenderer1 = new PDFRenderer(doc1);
			pdfRenderer2 = new PDFRenderer(doc2);

			ConvertToPdfUtility convertToPdf = new ConvertToPdfUtility();

			convertToPdf.intConversion(pdfDestDir, getImageBuffer(pdfRenderer1, 0).getWidth(),
					getImageBuffer(pdfRenderer1, 0).getHeight());
			for (int iPage = startPage - 1; iPage < doc1.getNumberOfPages(); iPage++)
			{
				PdfContentInspectUtility manipulation = new PdfContentInspectUtility();
				manipulation.init(doc1, doc2, iPage);
				String imgDestDir = new File(srcFile1).getName().replace(".pdf", "_") + (iPage);
				imgDestDir = destDir + "/" + imgDestDir + index + "_diff.png";

				BufferedImage srcImage1 = getImageBuffer(pdfRenderer1, iPage);

				BufferedImage srcImage2 = getImageBuffer(pdfRenderer2, iPage);
				PageInfoDto info = ComparepdfUtility.compareAndHighlight(srcImage1, srcImage2, imgDestDir, true,
						Color.MAGENTA.getRGB(), manipulation);

				convertToPdf.startConversion(new File(imgDestDir).toURI().toURL());
				// manipulation.extractString(info,
				// doc1.getPage(iPage),doc2.getPage(iPage),doc1,srcImage1,destTXTDir +
				// index+"_diff.txt");
				// System.out.println(info.getPdfCordinateInfo());
				destPdInfo.add(info);

			}

			convertToPdf.stopConversion();

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			doc1.close();
			doc2.close();
		}
		return destPdInfo;
	}

	private BufferedImage getImageBuffer(PDFRenderer pdfRenderer1, int iPage) throws IOException
	{
		BufferedImage srcImage1 = pdfRenderer1.renderImageWithDPI(iPage, 300, ImageType.RGB);
		return srcImage1;
	}

}
