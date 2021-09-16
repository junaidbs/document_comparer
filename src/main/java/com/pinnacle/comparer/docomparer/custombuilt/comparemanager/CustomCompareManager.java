package com.pinnacle.comparer.docomparer.custombuilt.comparemanager;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pinnacle.comparer.docomparer.DocumentComparer;
import com.pinnacle.comparer.docomparer.custombuilt.CreateImageBuffer;
import com.pinnacle.comparer.docomparer.custombuilt.dto.PageInfoDto;
import com.pinnacle.comparer.dto.InputDto;
import com.pinnacle.comparer.dto.OutputDto;
import com.pinnacle.comparer.utils.CheckTtime;

@Component
public class CustomCompareManager implements DocumentComparer
{

	@Override
	public OutputDto compareDocument(InputDto inputDto)
	{
		// hardcodesd image dest directory
		String imgdestDir = "G:/smplpinnacle";
		String destTXTDir = "G:\\smplpinnacle\\";
		// ending index should change
		int startingIndex = 1;
		int endingIndex = 6;
		try
		{
			List<PageInfoDto> info = new CreateImageBuffer().convertToImageAndCompare(inputDto.getFilePath1(),
					inputDto.getFilePath2(), startingIndex, endingIndex, imgdestDir, 0, destTXTDir, "G:/out.pdf");

			CheckTtime.printTime();
			OutputDto outdto = new OutputDto();
			outdto.setCordinates(info);
			outdto.setOutPutDir("G:/out.pdf");
			return outdto;

		} catch (Exception e)
		{

		}
		return null;
	}

}
