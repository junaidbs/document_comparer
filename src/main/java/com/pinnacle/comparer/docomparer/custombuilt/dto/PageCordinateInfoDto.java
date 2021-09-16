package com.pinnacle.comparer.docomparer.custombuilt.dto;

import java.util.Map;

import lombok.Data;

@Data
public class PageCordinateInfoDto
{

	public Map<String, Map<String, Integer>> cordinates;
	String Text;
	int colorCode;

	@Override
	public String toString()
	{
		return "{" + cordinates + ", Text=" + Text + "}";
	}

}
