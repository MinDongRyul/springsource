package com.study.myapp.dto;

import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//spring이 관리하지 않음

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
	private int code;
	private String title;
	private String writer;
	private int price;
}
