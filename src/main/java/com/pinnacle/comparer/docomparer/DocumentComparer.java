package com.pinnacle.comparer.docomparer;

import com.pinnacle.comparer.dto.InputDto;
import com.pinnacle.comparer.dto.OutputDto;

public interface DocumentComparer
{
	public OutputDto compareDocument(InputDto compareInput);

}
