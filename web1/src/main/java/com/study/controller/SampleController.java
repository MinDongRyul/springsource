package com.study.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.dto.UserDTO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/sample/*") // http://localhost:9090/sample/**
public class SampleController {
	
	// servlet-context.xml 여기에서 해주는 것 같음
	// 앞쪽 고정 : /WEB-INF/views
	// 뒤쪽 고정 : .jsp
	
	// 컨트롤러의 리텁 타입이 void 인 경우 
	// http://localhost:9090/ 다음 부분이 jsp 페이지를 찾는데 사용됨
	
	// 컨트롤러의 리턴 타입이 String인 경우
	// return값 만을 가지고 jsp페이지를 찾음
	
	//@RequestMapping(path = "/basic", method=RequestMethod.GET) // http://localhost:9090/sample/basic
	@GetMapping("/basic")
	public void basic() {
		log.info("basic...."); // WEB-INF/views/sample/basic.jsp
	}
	
	// @RequestMapping("/login") : GET + POST 둘 다 허용
	// @RequestMapping(path = "/login", method = RequestMethod.GET) : GET 허용
	//@RequestMapping(path = "/login", method = RequestMethod.GET) // http://localhost:9090/sample/login
	@GetMapping("/login")
	public void login() {
		log.info("login...");  // WEB-INF/views/sample/login.jsp
	}
	
	// Controller 파라미터 수집
	// 1) 변수명 사용
	// 2) ~DTO 객체 사용
	// 3) HttpServletRequest 객체 사용(필요한 경우만 사용 - 대부분 사용 잘 안함)
	
	// @RequestParam("이름") : 파라미터로 사용된 변수의 이름과 전달되는 파라미터의 이름이 다른 경우 사용
	
//	@RequestMapping(path = "/login", method = RequestMethod.POST) // http://localhost:9090/sample/login
//	@PostMapping("/login")
//	login.jsp에서의 name 과 변수명을 맞춰준다.
//	public void loginPost(String userid, String password, String addr, int age) {
//		log.info("login Post..."+userid+" "+password+" "+addr+" "+age);  // WEB-INF/views/sample/login.jsp
//	}
	
//	@PostMapping("/login")
//	public void loginPost(UserDTO userDto) {
//		log.info("login Post..."+userDto.getUserid()+" "+userDto.getPassword()+" "+userDto.getAddr()+" "+userDto.getAge());  // WEB-INF/views/sample/login.jsp
//	}
	
	@PostMapping("/login")
	public void loginPost(HttpServletRequest request) {
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		String addr = request.getParameter("addr");
		String age = request.getParameter("age");
		log.info("login Post..."+userid+" "+password+" "+addr+" "+age);  // WEB-INF/views/sample/login.jsp
	}
	
	//@RequestMapping(path = "/doA", method = RequestMethod.GET)  // http://localhost:9090/sample/doA
	@GetMapping("/doA")
	public void doA(String userid) {
		log.info("doA..."+userid); // WEB-INF/views/sample/doA.jsp
	}
	
	//@RequestMapping(value = "/insert", method = RequestMethod.GET)  // http://localhost:9090/sample/insert
	@GetMapping("/insert")
	public String insert(@RequestParam("ids") ArrayList<String> ids) {
		log.info("insert..."+ids);  //  /WEB-INF/views/insert.jsp
		return "insert";
	}
}
