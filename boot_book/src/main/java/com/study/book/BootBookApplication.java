package com.study.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 얘가 이 패키지를 쓰는 모든애들에게 component scan을 해준다
public class BootBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootBookApplication.class, args);
	}
}
