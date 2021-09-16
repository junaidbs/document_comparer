package com.pinnacle.comparer.docomparer.custombuilt.utility;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ConvertToImageUtility
{
	static void saveImage(BufferedImage image, String file)
	{
		try
		{
			File outputfile = new File(file);

			ImageIO.write(image, "png", outputfile);

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
