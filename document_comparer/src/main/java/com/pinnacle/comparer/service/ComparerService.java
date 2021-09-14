package com.pinnacle.comparer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pinnacle.comparer.docomparer.custombuilt.comparemanager.CustomCompareManager;
import com.pinnacle.comparer.dto.InputDto;
import com.pinnacle.comparer.dto.OutputDto;

@Component
public class ComparerService
{
	@Autowired
	CustomCompareManager compareManager;

	public OutputDto getChanges(InputDto inputDto)
	{

		inputDto.setFilePath1(inputDto.getFilePath1());
		inputDto.setFilePath2(inputDto.getFilePath2());
		return compareManager.compareDocument(inputDto);
	}
}
