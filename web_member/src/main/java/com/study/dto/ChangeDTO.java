package com.study.dto;

import lombok.Data;

@Data
public class ChangeDTO {
	private String userid;
	private String password;
	private String new_password;
	private String confirm_password;
}
