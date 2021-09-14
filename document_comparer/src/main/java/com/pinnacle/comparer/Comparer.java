package com.pinnacle.comparer;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pinnacle.comparer.docomparer.custombuilt.CreateImageBuffer;
import com.pinnacle.comparer.docomparer.custombuilt.dto.PageInfoDto;
import com.pinnacle.comparer.docomparer.custombuilt.utility.HardcodedPathsUtitily;
import com.pinnacle.comparer.utils.CheckTtime;

@SpringBootApplication
public class Comparer
{

	public static void main(String[] args) throws IOException
	{
		SpringApplication.run(Comparer.class, args);

		CheckTtime.printTime();

		// using pdfutil
		// PixelComparePdfUtil utilPdfExtract=new PixelComparePdfUtil();
		// utilPdfExtract.usingUtil(file1,file2);

		// using string compare
		// StringComparePdfUtil cp=new StringComparePdfUtil();
		// cp.extractContent();
		// cp.extractpdf();
		// new StringExtractionPdfUtil().extractpdf();

		// using pdfcomparator
		// new PixelComparePdfComparator().extractpdf(file1,file2);

		// using custome convertor
		// GroupCompareUtility.groupComparison();
		String destDir = "G:/smplpinnacle";
		String destTXTDir = "G:\\smplpinnacle\\";

		// test code
		int from = 1;
		List<PageInfoDto> outContent = new CreateImageBuffer().convertToImageAndCompare(HardcodedPathsUtitily.file20,
				HardcodedPathsUtitily.file21, from, 1, destDir, 0, destTXTDir, "G:/out.pdf");
		System.out.println(outContent);

		CheckTtime.printTime();
	}

}
