package com.pinnacle.comparer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinnacle.comparer.dto.InputDto;
import com.pinnacle.comparer.dto.OutputDto;
import com.pinnacle.comparer.service.ComparerService;

@RestController
@CrossOrigin(origins = { "http://localhost:3000" })
@RequestMapping("/api/comparer/")
public class ComparerRestController
{
	@Autowired
	ComparerService compareservice;

	@PostMapping("/getChanges")
	public OutputDto getChanges(@RequestBody InputDto dto)
	{
		return compareservice.getChanges(dto);

	}

}
