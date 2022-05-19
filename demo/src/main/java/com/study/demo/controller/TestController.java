package com.study.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TestController {

	@GetMapping("/")
	public String home() {
		log.info("index 요청");
		
		return "index";
	}
	
	@GetMapping("/sub/test2")
	public void test() {
		log.info("test 요청");
	}
		
}
