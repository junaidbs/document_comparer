package com.pinnacle.comparer.docomparer.custombuilt.utility;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pinnacle.comparer.docomparer.custombuilt.dto.PageCordinateInfoDto;
import com.pinnacle.comparer.docomparer.custombuilt.dto.PageInfoDto;

public class ComparepdfUtility
{
	private ComparepdfUtility()
	{
		throw new IllegalStateException("Utility class");
	}

	public static PageInfoDto compareAndHighlight(final BufferedImage img1, final BufferedImage img2, String fileName,
			boolean highlight, int colorCode, PdfContentInspectUtility inspectUtility) throws IOException
	{

		PageInfoDto pageInfo = new PageInfoDto();
		List<PageCordinateInfoDto> pageCordinatesStorage = new ArrayList<>();
		int firstRow = -1;
		int lastRow = -1;
		int startColumn = -1;
		int lastColumn = -1;
		final int width = img1.getWidth();
		final int height = img1.getHeight();
		final int[] p1 = img1.getRGB(0, 0, width, height, null, 0, width);
		final int[] p2 = img2.getRGB(0, 0, width, height, null, 0, width);
		MatrixManipulationUtility manipulation = new MatrixManipulationUtility();
		manipulation.setWidth(width);
		manipulation.setHeight(height);
		// Compare Two pixel array length same or not
		if (!(java.util.Arrays.equals(p1, p2)))
		{
			// convert 1D Array to 2D array for getting row and column
			int[][] p2d1 = manipulation.convertTo2D(p1);
			int[][] p2d2 = manipulation.convertTo2D(p2);
			if (highlight)
			{

				for (int row = 0; row < height; row++)
				{
					for (int column = 0; column < width; column++)
					{
						// check pixel from two image are different
						if (p2d1[row][column] != p2d2[row][column])
						{
							// StartColumn store the left most column index
							startColumn = sColumnIsEmptyOrIsSmall(startColumn, column);
							// firstRow store the start most array row index
							firstRow = isfRowEmpty(firstRow, row);
							// lastRow have last most row index
							lastRow = row;
							// change offset is used to break the loop after a fixed hardocoded value if no
							// continues column change occure
							int changeOffset = -1;
							int tcolumn = 0;
							/*
							 * this loop to find the right most column change index. loop start from where
							 * the changes in column start. lastColumn store right most column changes
							 * 
							 */
							for (tcolumn = column; tcolumn < width; tcolumn++)
							{

								lastColumn = islcolumnEmptyOrlarge(lastColumn, tcolumn);
								/*
								 * if two pixel are same then check changeOffset reaches 23(23 continues pixel
								 * in column) if reaches break the loop and we got an lastcolumn
								 */
								if (p2d1[row][tcolumn] == p2d2[row][tcolumn])
								{
									if (changeOffset == 23)
									{
										column = tcolumn;
										break;
									}

									else
									{
										changeOffset = changeOffset + 1;
									}

								}

								else
								{
									changeOffset = 0;

								}

							}

						} else
						{
							// if two image have 3 continues non change rows(end of changes in rows)
							if (row - lastRow > 3 && lastRow != -1)
							{

								findCoordinates(inspectUtility, pageCordinatesStorage, firstRow, lastRow, startColumn,
										lastColumn, width, height, row);

								// resetting values to initial for next iteration
								startColumn = -1;
								lastColumn = -1;
								lastRow = -1;
								firstRow = -1;
							}

						}

					}
					// end of image compare
				}

				pageInfo.setPdfCordinateInfo(pageCordinatesStorage);
				// creation of Box
				boxCreationAndSaveImage(fileName, pageInfo, width, height, manipulation, p2d1);
				return pageInfo;
			}
			return pageInfo;

		}
		return pageInfo;
	}

	private static void boxCreationAndSaveImage(String fileName, PageInfoDto pageInfo, final int w, final int h,
			MatrixManipulationUtility manipulation, int[][] _2dp1)
	{
		int[][] temparray = CreateBoxUtility.createUnderLine(_2dp1, pageInfo);
		int[] array = manipulation.conertTo1D(temparray);
		System.out.println(array.length + "arr" + w + "height" + h);
		final BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		out.setRGB(0, 0, w, h, array, 0, w);
		ConvertToImageUtility.saveImage(out, fileName);
	}

	private static int isfRowEmpty(int firstRow, int row)
	{
		if (firstRow == -1)
		{
			firstRow = row;
		}
		return firstRow;
	}

	private static int sColumnIsEmptyOrIsSmall(int startColumn, int column)
	{
		// this ensure StartColumn having left most column
		if (startColumn == -1 || (column < startColumn))
		{
			startColumn = column;

		}

		return startColumn;
	}

	private static int islcolumnEmptyOrlarge(int lastColumn, int tcolumn)
	{
		/*
		 * this condition ensure endColumn having the right most column index having
		 * changes
		 */
		if (lastColumn == -1 || (tcolumn > lastColumn))
		{
			lastColumn = tcolumn;

		}

		return lastColumn;
	}

	private static void findCoordinates(PdfContentInspectUtility inspectUtility,
			List<PageCordinateInfoDto> pageCordinatesStorage, int firstRow, int lastRow, int startColumn,
			int lastColumn, final int w, final int h, int row)
	{

		HashMap<String, Map<String, Integer>> map = new HashMap<>();
		map.put("Start", insertCordinates((firstRow), startColumn));
		map.put("End", insertCordinates(lastRow, lastColumn));
		PageCordinateInfoDto cordinateInfo = new PageCordinateInfoDto();
		// inspect content of the coordinate portion
		if (!inspectUtility.isContentsame(map, w, h, 1, cordinateInfo))
		{
			cordinateInfo.setCordinates(map);
			pageCordinatesStorage.add(cordinateInfo);
		}
	}

	static Map<String, Integer> insertCordinates(int x, int y)
	{
		Map<String, Integer> cordinates = new HashMap<>();
		cordinates.put("x", x);
		cordinates.put("y", y);
		return cordinates;
	}

}
