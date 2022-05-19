package com.study.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SecurityController {
	
	@GetMapping("/login")
	public void login() {
		log.info("로그인 폼 요청");
	}
	
	@GetMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", "아이디나 비밀번호를 확인해 주세요");
		return "login";
	}
	
	@PreAuthorize("hasAnyAuthority('ROLE_USER')")
	@GetMapping("/user-page")
	public String userPage() {
		log.info("user page 요청");
		return "userpage";
	}
	
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
	@GetMapping("/admin-page")
	public String adminPage() {
		log.info("admin page 요청");
		return "adminpage";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied() {
		log.info("접근제한");
		return "accessdenied";
	}
}
