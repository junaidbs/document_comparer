package com.pinnacle.comparer.docomparer.pdfutil;

import java.io.IOException;

import com.pinnacle.comparer.utils.CheckTtime;
import com.testautomationguru.utility.CompareMode;
import com.testautomationguru.utility.PDFUtil;

public class PixelComparePdfUtil {
	
	String file1="c:/VoucherPrintNew.pdf";
	String file2="c:/VoucherPrintNew2.pdf";
	
	public void usingUtil(String file1,String file2) throws IOException {
		PDFUtil pdfUtil = new PDFUtil();
		// compares the pdf documents and returns a boolean
		// true if both files have same content. false otherwise.
		// Default is CompareMode.TEXT_MODE
		pdfUtil.setCompareMode(CompareMode.VISUAL_MODE);
		//pdfUtil.compare(file1, file2);

		// compare the 3rd page alone
		//pdfUtil.compare(file1, file2, 3, 3);

		// compare the pages from 1 to 5
		//pdfUtil.compare(file1, file2, 1, 5);

		//if you need to store the result
		pdfUtil.highlightPdfDifference(true);
		pdfUtil.setImageDestinationPath("G:/");
		pdfUtil.compare(file1, file2);
		CheckTtime.printTime();
	}

	

}
