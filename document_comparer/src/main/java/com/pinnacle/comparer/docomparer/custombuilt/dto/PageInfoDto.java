package com.pinnacle.comparer.docomparer.custombuilt.dto;

import java.util.List;

import lombok.Data;

@Data
public class PageInfoDto
{

	List<PageCordinateInfoDto> pdfCordinateInfo;

	@Override
	public String toString()
	{
		return "PdfInfo= [pdfCordinateInfo=" + pdfCordinateInfo + "]";
	}

}
