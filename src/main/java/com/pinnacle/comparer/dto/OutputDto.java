package com.pinnacle.comparer.dto;

import java.io.Serializable;
import java.util.List;

import com.pinnacle.comparer.docomparer.custombuilt.dto.PageInfoDto;

import lombok.Data;

@Data
public class OutputDto implements Serializable
{
	private static final long serialVersionUID = -7909326572742712743L;
	List<PageInfoDto> Cordinates;
	String outPutDir;

}
