package com.pinnacle.comparer.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class InputDto implements Serializable
{

	private static final long serialVersionUID = -1698415534390925501L;
	String filePath1;
	String filePath2;

}
