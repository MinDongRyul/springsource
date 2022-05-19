package com.study.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SpUser {
	private String userid;
	private String email;
	private boolean enabled;
	private String password;
	
	//권한 정보
	private List<SpUserAuthority> authorities;
}
