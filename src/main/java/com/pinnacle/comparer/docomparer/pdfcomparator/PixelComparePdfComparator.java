package com.pinnacle.comparer.docomparer.pdfcomparator;

import java.io.IOException;

import com.pinnacle.comparer.utils.CheckTtime;

import de.redsix.pdfcompare.CompareResult;
import de.redsix.pdfcompare.PageArea;
import de.redsix.pdfcompare.PdfComparator;
import de.redsix.pdfcompare.RenderingException;

public class PixelComparePdfComparator {
	//String file1="c:/VoucherPrintNew.pdf";
	//String file2="c:/VoucherPrintNew2.pdf";
	public void extractpdf(String file1,String file2) {
		try {
			CompareResult result = new PdfComparator(file1, file2).withIgnore(new PageArea(1, 0, 0, 200, 500))
					.compare();
			System.out.println(result.writeTo("G:/sample.pdf"));
			
			
			if(result.isEqual()) {
				System.out.println("two pdf are equel");
				CheckTtime.printTime();
			}
			else {
				System.out.println("two pdf are not equel");
				CheckTtime.printTime();
				

			}
			
		} catch (RenderingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
