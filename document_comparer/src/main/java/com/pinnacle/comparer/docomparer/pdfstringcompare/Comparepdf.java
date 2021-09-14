package com.pinnacle.comparer.docomparer.pdfstringcompare;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Comparepdf {
	public static boolean compareAndHighlight(final BufferedImage img1, final BufferedImage img2, String fileName, boolean highlight, int colorCode) throws IOException {

	    final int w = img1.getWidth();
	    final int h = img1.getHeight();
	    final int[] p1 = img1.getRGB(0, 0, w, h, null, 0, w);
	    final int[] p2 = img2.getRGB(0, 0, w, h, null, 0, w);
	    	ArrayList<Integer> arr=new ArrayList<Integer>();

	    if(!(java.util.Arrays.equals(p1, p2))){
	    	//logger.warning("Image compared - does not match");
	    	if(highlight){
	    		System.out.println(w +"and"+p1.length);
	    	    for (int i = 0; i < p1.length; i++) {
	    	        if (p1[i] != p2[i]){
	    	        	//System.out.println(i+"inside change"+w);
	    	        	for(int j=i;j<=i+w;j++) {
	    	        		arr.add(p1[j]);
	    	        		
	    	        	}
	    	        	i=i+w;
	    	        	//arr.add(p1[i]);
	    	            //p1[i] = colorCode;
	    	            //arr.add(p1[i]);
	    	        }
	    	        else {
	    	        	//System.out.println(i+"inside NO change"+w);
	    	        	arr.add(p1[i]=Color.WHITE.getRGB());
	    	        }
	    	    }
	    	   
	    	     int[] array = arr.stream().mapToInt(i -> i).toArray();
	    	     System.out.println(array.length+"arr"+w+"height"+h);
	    	   
	    	    final BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    	    out.setRGB(0, 0, w, h, array, 0, w);
	    	    saveImage(out, fileName);
	    	}
	    	return false;
	    }
	    return true;
	}

	static void saveImage(BufferedImage image, String file){
		try{
			File outputfile = new File(file);
			ImageIO.write(image, "png", outputfile);	
		}catch(Exception e){
			e.printStackTrace();
		}
	}	
}
