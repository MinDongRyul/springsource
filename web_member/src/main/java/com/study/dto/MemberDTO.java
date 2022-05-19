package com.study.dto;

import lombok.Data;

@Data
public class MemberDTO {
	private String userid;
	private String password;
	private String confirm_password;
	private String name;
	private String gender;
	private String email;
}
