package com.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.dto.SpUser;
import com.study.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/register")
	public void registerGet() {
		log.info("회원가입 폼 요청");
	}
	
	@PostMapping("/register")
	public String registerPost(SpUser user) {
		log.info("회원가입 요청 "+user);
		
		//회원가입 및 권한부여
		service.register(user);
		
		return "redirect:/login";
	}
}
