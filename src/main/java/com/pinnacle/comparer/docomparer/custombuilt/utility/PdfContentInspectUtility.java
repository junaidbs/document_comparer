package com.pinnacle.comparer.docomparer.custombuilt.utility;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import com.github.difflib.DiffUtils;
import com.github.difflib.patch.Patch;
import com.pinnacle.comparer.docomparer.custombuilt.dto.PageCordinateInfoDto;
import com.pinnacle.comparer.docomparer.custombuilt.dto.PageInfoDto;

public class PdfContentInspectUtility
{

	PDPage page;
	PDPage page1;
	PDDocument doc1;

	float findXPixelPoint(float x_pdf, PDPage page, int width_image)
	{

		float x_image;
		float width_page = page.getMediaBox().getWidth();

		x_image = x_pdf * width_image / width_page;
		return x_image;

	}

	float findYPixelPoint(float y_pdf, PDPage page, int height_image)
	{
		float y_image;
		float height_page = page.getMediaBox().getHeight();
		y_image = (height_page - y_pdf) * height_image / height_page;
		return y_image;
	}

	public float findXPoint(float x_image, PDPage page, int width_image)
	{
		float x_pdf;
		float width_page = page.getMediaBox().getWidth();
		x_pdf = x_image * width_page / width_image;
		return x_pdf;
	}

	public float findYPoint(float y_image, PDPage page, int height_image)
	{
		float y_pdf;
		float height_page = page.getMediaBox().getHeight();
		y_pdf = height_page - (y_image * height_page / height_image);
		return y_pdf;

	}

	// this function for testing purpose not use in production
	public void extractString(PageInfoDto info, PDPage page, PDPage page1, PDDocument doc1, BufferedImage image1,
			String dir)
	{
		int width_image = image1.getWidth() + 10;
		int height_image = image1.getHeight();
		// here x=row and y=column

		for (int i = 0; i < info.getPdfCordinateInfo().size(); i++)
		{
			int sx = info.getPdfCordinateInfo().get(i).cordinates.get("Start").get("y");
			int sy = info.getPdfCordinateInfo().get(i).cordinates.get("Start").get("x");
			int ex = info.getPdfCordinateInfo().get(i).cordinates.get("End").get("y");
			int ey = info.getPdfCordinateInfo().get(i).cordinates.get("End").get("x");

			float x_pdf = findXPoint(sx, page, width_image);
			float x2_pdf = findXPoint(ex, page, width_image);
			float y_pdf = findYPoint(sy, page, height_image);
			float y2_pdf = findYPoint(ey, page, height_image);
			// probelm in finding exact y cordinates

			page.setCropBox(new PDRectangle(x_pdf, y2_pdf, (x2_pdf - x_pdf), (y_pdf - (y2_pdf))));
			page1.setCropBox(new PDRectangle(x_pdf, y2_pdf, (x2_pdf - x_pdf), (y_pdf - (y2_pdf))));

			// System.out.println(x_pdf);
			// System.out.println(y_pdf);
			try
			{
				doc1.save(new File("G:/quatssnaps/" + i + ".pdf"));
				PDFTextStripperByArea pArea = new PDFTextStripperByArea();
				PDFTextStripperByArea p1Area = new PDFTextStripperByArea();
				Rectangle2D.Float rectangle2d = new Rectangle2D.Float(0f, 0f, (x2_pdf - x_pdf), (y_pdf - (y2_pdf - 5)));
				pArea.addRegion("reg1", rectangle2d);
				p1Area.addRegion("reg2", rectangle2d);

				pArea.extractRegions(page);
				p1Area.extractRegions(page1);
				if (StringUtils.normalizeSpace(pArea.getTextForRegion("reg1"))
						.equals(StringUtils.normalizeSpace(p1Area.getTextForRegion("reg2"))) || isPage2Empty(p1Area))
				{

					System.out.println("region excluded");
					info.getPdfCordinateInfo()
							.get(i)
							.setText("excluded................." + pArea.getTextForRegion("reg1"));
				} else
				{
					System.out.println("************************" + pArea.getTextForRegion("reg1") + "***********+"
							+ p1Area.getTextForRegion("reg2") + "000000" + isPage2Empty(p1Area));
					info.getPdfCordinateInfo()
							.get(i)
							.setText(StringUtils.normalizeSpace(pArea.getTextForRegion("reg1")));

				}
				// System.out.println(area.getTextForRegion("reg1"));

			} catch (IOException e)
			{

				e.printStackTrace();
			}
		}
		System.out.println(info.getPdfCordinateInfo());
		CreateTXTFileUtility.createTXTfile(dir);

		CreateTXTFileUtility.writeTXTfile(info.getPdfCordinateInfo().toString(), dir);
	}

	public void init(PDDocument doc1, PDDocument doc2, int iPage)
	{

		this.page = doc1.getPage(iPage);
		this.page1 = doc2.getPage(iPage);
		this.doc1 = doc1;

	}

	// return true if the content of passing coordinates are same
	public boolean isContentsame(Map<String, Map<String, Integer>> info, int width_image, int height_image,
			int chosePdf, PageCordinateInfoDto cordinateInfo)
	{

		width_image = width_image + 10;
		// optaining cordinates
		int sx = info.get("Start").get("y");
		int sy = info.get("Start").get("x");
		int ex = info.get("End").get("y");
		int ey = info.get("End").get("x");
		// convert to pdf cordinates
		float x_pdf = findXPoint(sx, page, width_image);
		float x2_pdf = findXPoint(ex, page, width_image);
		float y_pdf = findYPoint(sy, page, height_image);
		float y2_pdf = findYPoint(ey, page, height_image);
		// creating a virtual Box
		setPageCropox(x_pdf, x2_pdf, y_pdf, y2_pdf);
		setPage1CropBox(x_pdf, x2_pdf, y_pdf, y2_pdf);

		try
		{

			PDFTextStripperByArea pArea = new PDFTextStripperByArea();
			PDFTextStripperByArea p1Area = new PDFTextStripperByArea();
			Rectangle2D.Float rectangle2d = new Rectangle2D.Float(0f, 0f, (x2_pdf - x_pdf), (y_pdf - (y2_pdf - 5)));
			pArea.addRegion("reg1", rectangle2d);
			p1Area.addRegion("reg2", rectangle2d);
			// extract the region
			pArea.extractRegions(page);
			p1Area.extractRegions(page1);
			if (isContentSameOrEmpty(pArea, p1Area))
			{

				if (!isBothContentEmpty(pArea, p1Area))
				{
					if (isPage1Empty(pArea))
					{
						cordinateInfo.setColorCode(Color.RED.getRGB());

						return false;
					} else if (isPage2Empty(p1Area))
					{
						cordinateInfo.setColorCode(Color.GREEN.getRGB());
						return false;
					}
				}

				return true;
			} else
			{

				if (ey - sy < 23)
				{
					return true;
				}

				cordinateInfo.setText(StringUtils.normalizeSpace(pArea.getTextForRegion("reg1")));
				cordinateInfo.setColorCode(Color.BLUE.getRGB());
				return wordByWordCompare(pArea, p1Area);
			}

		} catch (IOException e)
		{

			e.printStackTrace();
		}

		return false;

	}

	private boolean isPage2Empty(PDFTextStripperByArea p1Area)
	{
		return p1Area.getTextForRegion("reg2").trim().isEmpty();
	}

	private boolean isPage1Empty(PDFTextStripperByArea pArea)
	{
		return pArea.getTextForRegion("reg1").trim().isEmpty();
	}

	private boolean isBothContentEmpty(PDFTextStripperByArea pArea, PDFTextStripperByArea p1Area)
	{
		return isPage1Empty(pArea) && isPage2Empty(p1Area);
	}

	private boolean isContentSameOrEmpty(PDFTextStripperByArea pArea, PDFTextStripperByArea p1Area)
	{
		return StringUtils.normalizeSpace(pArea.getTextForRegion("reg1"))
				.equals(StringUtils.normalizeSpace(p1Area.getTextForRegion("reg2"))) || isPage2Empty(p1Area)
				|| isPage1Empty(pArea);
	}

	private void setPage1CropBox(float x_pdf, float x2_pdf, float y_pdf, float y2_pdf)
	{
		page1.setCropBox(new PDRectangle(x_pdf, y2_pdf, (x2_pdf - x_pdf), (y_pdf - (y2_pdf))));
	}

	private void setPageCropox(float x_pdf, float x2_pdf, float y_pdf, float y2_pdf)
	{
		page.setCropBox(new PDRectangle(x_pdf, y2_pdf, (x2_pdf - x_pdf), (y_pdf - (y2_pdf))));
	}

	// test function not directly connected using diff utill lirary text compare
	public List<float[]> getStChanges(PDFTextStripperByArea pArea, PDFTextStripperByArea p1Area)
	{
		List<String> l1 = Arrays.asList(StringUtils.normalizeSpace(pArea.getTextForRegion("reg1")).split("\\s+"));
		List<String> l2 = new ArrayList<String>(
				Arrays.asList(StringUtils.normalizeSpace(p1Area.getTextForRegion("reg2")).split("\\s+")));

		Patch<String> ps = DiffUtils.diff(l1, l2);

		System.out.println(ps.getDeltas().get(0).getSource().getLines());
		ArrayList<float[]> arrayList = new ArrayList<>();

		for (String string : ps.getDeltas().get(0).getSource().getLines())
		{
			arrayList.add(getpostion(string));

		}
		return arrayList;

	}

//convert text into list of words and compare words By words in case of extracted string order is different
	@SuppressWarnings("unused")
	private boolean wordByWordCompare(PDFTextStripperByArea pArea, PDFTextStripperByArea p1Area)
	{
		List<String> l1 = Arrays.asList(StringUtils.normalizeSpace(pArea.getTextForRegion("reg1")).split("\\s+"));
		List<String> l2 = new ArrayList<String>(
				Arrays.asList(StringUtils.normalizeSpace(p1Area.getTextForRegion("reg2")).split("\\s+")));

		System.out.println(l1);
		System.out.println(l2);
		int count = 0;
		List<Integer> blacklist = new ArrayList<>();
		for (int i = 0; i < l1.size(); i++)
		{
			for (int j = 0; j < l2.size(); j++)
			{
				int found = 0;
				for (int k : blacklist)
				{
					if (k == j)
					{
						found = 1;
						break;
					}
				}
				if (found == 1)
				{
					continue;
				}

				if (l1.get(i).equals(l2.get(j)))
				{
					blacklist.add(j);
					count = count + 1;
					break;
				}

			}

		}

		if (count == l1.size())
		{
			System.out.println("no change" + count + "hehe" + l1.size());
			return true;
		} else
		{
			System.out.println(" change" + count + "hehe" + l1.size());
			return false;
		}
	}

	// test function
	private float[] getpostion(String string)
	{
		try
		{
			float[] f = WordPositioningUtility.getCoordiantes(doc1, string, 1);
			System.out.println(f[0] + f[1]);
			return f;

		} catch (IOException e)
		{
			e.printStackTrace();
			return null;

		}
	}

}
