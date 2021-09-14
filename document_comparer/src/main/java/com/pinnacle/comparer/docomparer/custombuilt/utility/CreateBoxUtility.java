package com.pinnacle.comparer.docomparer.custombuilt.utility;

import com.pinnacle.comparer.docomparer.custombuilt.dto.PageInfoDto;

public class CreateBoxUtility
{

	static int[][] createUnderLine(int[][] temparry, PageInfoDto pdfInfo)
	{
		int[][] temparray1 = temparry;
		// System.out.println(pdfInfo.getPdfCordinateInfo().get(0).cordinates.get("End").get("x"));
		for (int count = 0; count < pdfInfo.getPdfCordinateInfo().size(); count++)
		{
			int x = pdfInfo.getPdfCordinateInfo().get(count).cordinates.get("End").get("x");
			int y = pdfInfo.getPdfCordinateInfo().get(count).cordinates.get("End").get("y");
			int Sy = pdfInfo.getPdfCordinateInfo().get(count).cordinates.get("Start").get("y");
			for (int tx = x; tx < x + 2; tx++)
			{
				for (int i = Sy; i < y; i++)
				{

					temparray1[tx][i] = pdfInfo.getPdfCordinateInfo().get(count).getColorCode();

				}
			}
		}
		return createTopLine(temparray1, pdfInfo);

	}

	static int[][] createTopLine(int[][] temparry, PageInfoDto pdfInfo)
	{
		int[][] temparray1 = temparry;
		// System.out.println(pdfInfo.getPdfCordinateInfo().get(0).cordinates.get("End").get("x"));
		for (int count = 0; count < pdfInfo.getPdfCordinateInfo().size(); count++)
		{
			int xs = pdfInfo.getPdfCordinateInfo().get(count).cordinates.get("Start").get("x");
			int y = pdfInfo.getPdfCordinateInfo().get(count).cordinates.get("End").get("y");
			int Sy = pdfInfo.getPdfCordinateInfo().get(count).cordinates.get("Start").get("y");
			for (int tx = xs; tx < xs + 2; tx++)
			{
				for (int i = Sy; i < y; i++)
				{

					temparray1[tx][i] = pdfInfo.getPdfCordinateInfo().get(count).getColorCode();

				}
			}
		}
		return createLeftLine(temparray1, pdfInfo);

	}

	static int[][] createLeftLine(int[][] temparry, PageInfoDto pdfInfo)
	{

		int[][] temparray1 = temparry;
		// System.out.println(pdfInfo.getPdfCordinateInfo().get(0).cordinates.get("End").get("x"));
		for (int count = 0; count < pdfInfo.getPdfCordinateInfo().size(); count++)
		{
			int xe = pdfInfo.getPdfCordinateInfo().get(count).cordinates.get("End").get("x");
			int ys = pdfInfo.getPdfCordinateInfo().get(count).cordinates.get("Start").get("y");
			int xs = pdfInfo.getPdfCordinateInfo().get(count).cordinates.get("Start").get("x");

			for (int i = xs; i < xe; i++)
			{
				for (int tk = ys; tk < ys + 2; tk++)
				{

					temparray1[i][tk] = pdfInfo.getPdfCordinateInfo().get(count).getColorCode();

				}

			}
		}
		return createRightLine(temparray1, pdfInfo);

	}

	static int[][] createRightLine(int[][] temparry, PageInfoDto pdfInfo)
	{

		int[][] temparray1 = temparry;
		// System.out.println(pdfInfo.getPdfCordinateInfo().get(0).cordinates.get("End").get("x"));
		for (int count = 0; count < pdfInfo.getPdfCordinateInfo().size(); count++)
		{
			int xe = pdfInfo.getPdfCordinateInfo().get(count).cordinates.get("End").get("x");
			int y = pdfInfo.getPdfCordinateInfo().get(count).cordinates.get("End").get("y");
			int xs = pdfInfo.getPdfCordinateInfo().get(count).cordinates.get("Start").get("x");

			for (int i = xs; i < xe; i++)
			{
				for (int tk = y; tk < y + 2; tk++)
				{
					try
					{
						temparray1[i][tk] = pdfInfo.getPdfCordinateInfo().get(count).getColorCode();

					} catch (Exception e)
					{
						// TODO: handle exception
					}

				}

			}
		}
		return temparray1;

	}

}
