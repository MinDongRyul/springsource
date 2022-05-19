package com.study.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SampleController {
	
	@GetMapping("/hello")
	public String[] hello() {
		log.info("hello 요청");
		return new String[] {"Hello","World"};
	}
	
}
