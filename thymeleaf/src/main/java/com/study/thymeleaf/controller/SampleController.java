package com.study.thymeleaf.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.thymeleaf.dto.MemberDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/sample") // templates.sample의 .sample와도 맞춰준다.
public class SampleController {
	
	@GetMapping("/ex1") // templates.sample/ex1.html의 ex1과 이름을 맞춰준다.
	public void ex1(Model model) {
		log.info("ex1 요청");
		model.addAttribute("data","thymeleaf");
	}
	
	@GetMapping("/ex2") // templates.sample/ex1.html의 ex2과 이름을 맞춰준다.
	public void ex2(Model model) {
		log.info("ex2 요청");
		model.addAttribute("greeting","안녕하세용");
	}
	
	@GetMapping("/ex3") // templates.sample/ex1.html의 ex3과 이름을 맞춰준다.
	public void ex3(Model model) {
		log.info("ex3 요청");
		
		MemberDTO dto = new MemberDTO(100, "mem01", "mem01", "홍길동", LocalDateTime.now());
		
		model.addAttribute("dto",dto);
	}
}
