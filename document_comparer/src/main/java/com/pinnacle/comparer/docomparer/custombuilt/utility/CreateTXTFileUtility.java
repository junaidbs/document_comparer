package com.pinnacle.comparer.docomparer.custombuilt.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateTXTFileUtility
{
	static void createTXTfile(String dir)
	{
		try
		{
			File myObj = new File(dir);
			if (myObj.createNewFile())
			{
				System.out.println("File created: " + myObj.getName());
			} else
			{
				System.out.println("File already exists.");
			}
		} catch (IOException e)
		{
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	static void writeTXTfile(String content, String dir)
	{
		try
		{
			FileWriter myWriter = new FileWriter(dir);
			myWriter.write(content);
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		} catch (IOException e)
		{
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}
}
