package com.study.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j // log관리
@RequestMapping("/board/*") // http://localhost:9090/board/**
public class BoardController {
	
	// servlet-context.xml 여기에서 해주는 것 같음
	// 앞쪽 고정 : /WEB-INF/views
	// 뒤쪽 고정 : .jsp
	
	@GetMapping("/insert") // http://localhost:9090/board/insert
	public void insert() {
		log.info("insert.."); // WEB-INF/views/board/insert.jsp
	}
	
//	@GetMapping("/modify") // http://localhost:9090/board/modify
//	public void modify() {
//		log.info("modify.."); // WEB-INF/views/board/modify.jsp
//	}
//	
//	@GetMapping("/read")  // http://localhost:9090/board/read
//	public void read() {
//		log.info("read.."); // WEB-INF/views/board/read.jsp
//	}
	
	@GetMapping(path = {"/modify", "/read"}) // http://localhost:9090/board/modify
	public void read() {
		log.info("board read or modify 요청"); // WEB-INF/views/board/modify.jsp
	}
	
	@GetMapping("/list")  // http://localhost:9090/board/list
	public void list() {
		log.info("list.."); // WEB-INF/views/board/list.jsp
	} 
}
