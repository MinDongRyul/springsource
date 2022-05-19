package com.study.dto;

import lombok.Data;

@Data
public class MemberDTO {
	//Dto에 값을 넣어려는 페이지와 name들이 동일해야한다.
	private String userid;
	private String password;
	private String confirm_password;
	private String name;
	private String gender;
	private String email;
}
