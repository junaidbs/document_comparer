package com.pinnacle.comparer.docomparer.pdfstringcompare;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.testautomationguru.utility.PDFUtil;

import de.redsix.pdfcompare.CompareResult;
import de.redsix.pdfcompare.PdfComparator;
import de.redsix.pdfcompare.RenderingException;

public class StringComparePdfUtil {
	PDFUtil pdfUtil = new PDFUtil();
	String file1="c:/sample.pdf";
	String file2="c:/sample - Copy.pdf";
	String file3="c:/VoucherPrintNew.pdf";
	String file4="c:/VoucherPrintNew1.pdf";
	String file5="c:/VoucherPrintNew2.pdf";
	void extractContent(){
		
		try {
			String string1=pdfUtil.getText(file3);
			String string2=pdfUtil.getText(file5); 
			
			List<String> stringList1 = new ArrayList<String>(Arrays.asList(string1.split(" ")));
			List<String> stringList2 = new ArrayList<String>(Arrays.asList(string2.split(" ")));
			System.out.println(stringList1 );
			System.out.println(stringList2 );
			 Map<String,String> tempmap=new HashMap<String, String>();
			for(int i=0;i<stringList2.size();i++) {
				if(!stringList1.get(i).equals(stringList2.get(i))) {
					//System.out.println(stringList1.get(i)+""+stringList2.get(i));
					tempmap.put(stringList1.get(i), stringList2.get(i));
				}
				
			}
			
			System.out.println(pdfUtil.compare(file3, file4) );
			System.out.println(tempmap) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	
	
	
 
}
