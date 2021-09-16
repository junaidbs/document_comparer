package com.pinnacle.comparer.utils;

import java.io.File;
import java.io.IOException;

import com.pinnacle.comparer.docomparer.custombuilt.CreateImageBuffer;

public class GroupCompareUtility
{

	public static void groupComparison() throws IOException
	{
		for (int i = 1; i <= 33; i++)
		{
			String iname = "C:/Users/JUNAID B S/Downloads/for junaid/for junaid/" + "i (" + i + ").pdf";
			String outputdir = "G:/invoice/" + "i(" + i + ")";
			String outTXTdir = "G:\\invoice\\" + "i(" + i + ")\\";
			File f1 = new File(outputdir);
			f1.mkdir();

			for (int j = i + 1; j <= 33; j++)
			{
				String iname1 = "C:/Users/JUNAID B S/Downloads/for junaid/for junaid/" + "i (" + j + ").pdf";
				new CreateImageBuffer().convertToImageAndCompare(iname1, iname, 1, 1, outputdir, j, outTXTdir,
						"G:/out.pdf");
			}

		}
	}

}
