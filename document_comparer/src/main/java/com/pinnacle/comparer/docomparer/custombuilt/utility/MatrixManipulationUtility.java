package com.pinnacle.comparer.docomparer.custombuilt.utility;

public class MatrixManipulationUtility
{

	/*
	 * this function is mainly for 1d to 2d and 2d to 1d array conversion
	 */
	public int width;
	public int height;

	public int[][] convertTo2D(int[] _1d)
	{

		int array_index = -1;

		int[][] temp_metrix = new int[height][width];
		for (int row = 0; row < height; row++)
		{

			for (int column = 0; column < width; column++)
			{

				array_index = array_index + 1;
				temp_metrix[row][column] = _1d[array_index];

			}

		}
		System.out.println(_1d[width * height - 1]);
		System.out.println(temp_metrix[height - 1][width - 1]);

		return temp_metrix;

	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public int[] conertTo1D(int[][] _2d)
	{

		int[] _1DArray = new int[width * height];
		int array_index = -1;

		for (int row = 0; row < height; row++)
		{

			for (int column = 0; column < width; column++)
			{

				array_index = array_index + 1;
				_1DArray[array_index] = _2d[row][column];

			}

		}

		return _1DArray;
	}

}
