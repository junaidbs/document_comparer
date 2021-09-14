package com.pinnacle.comparer.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CheckTtime {
	
	
	public static void printTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		   LocalDateTime now = LocalDateTime.now();  
		   System.out.println(dtf.format(now));  
		
	}

}
