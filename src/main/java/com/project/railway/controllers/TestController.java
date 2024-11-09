package com.project.railway.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/testing")
public class TestController {
	
	@GetMapping("/")
	public String getTester()
	{
		return "this was the testing";
	}

}
