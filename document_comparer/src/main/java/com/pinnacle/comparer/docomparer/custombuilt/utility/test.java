package com.pinnacle.comparer.docomparer.custombuilt.utility;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pinnacle.comparer.docomparer.custombuilt.dto.PageCordinateInfoDto;
import com.pinnacle.comparer.docomparer.custombuilt.dto.PageInfoDto;

public class test
{
	static int column = 0;
	static int row = 0;

	public static void compareAndHighlight(final BufferedImage img1, final BufferedImage img2, String fileName,
			boolean highlight, int colorCode, PdfContentInspectUtility inspectUtility) throws IOException
	{

		PageInfoDto pageInfo = new PageInfoDto();
		List<PageCordinateInfoDto> pageCordinatesStorage = new ArrayList<>();
		int firstRow = -1;
		int lastRow = -1;
		int startColumn = -1;
		int lastColumn = -1;

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		final int w = img1.getWidth();
		final int h = img1.getHeight();
		final int[] p1 = img1.getRGB(0, 0, w, h, null, 0, w);
		final int[] p2 = img2.getRGB(0, 0, w, h, null, 0, w);
		MatrixManipulationUtility manipulation = new MatrixManipulationUtility();
		int swap = 0;
		manipulation.setWidth(w);
		manipulation.setHeight(h);
		// Compare Two pixel array length same or not
		if (!(java.util.Arrays.equals(p1, p2)))
		{
			// convert 1D Array to 2D array for getting row and column
			int[][] p2d1 = manipulation.convertTo2D(p1);
			int[][] p2d2 = manipulation.convertTo2D(p2);

			for (int i = 0; i < p2d1.length; i++)
			{
				if (i % w == 0 && i != 0)
				{
					map.put(-1, -1);

				} else
				{
					map.put(p1[i], p2[i]);
				}

			}

			for (Map.Entry<Integer, Integer> entry : map.entrySet())
			{

				if (!entry.getKey().equals(entry.getValue()) && !entry.getKey().equals(-1) && swap == 0)
				{

				} else if (!entry.getKey().equals(entry.getValue()) && !entry.getKey().equals(-1) && swap == 1)
				{

				} else if (swap == 0)
				{
					row = row + 1;
				} else
				{
					column = column + 1;
				}
			}

		}

	}

}
