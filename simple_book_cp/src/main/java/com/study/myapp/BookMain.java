package com.study.myapp;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.myapp.dto.BookDTO;
import com.study.myapp.service.BookService;

public class BookMain {
	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		BookService service = (BookService) ctx.getBean("service");
		
//		자바코드였다면
//		BookService service = new BookServiceImpl();
//		List<BookDTO> list = service.getlist();
		
		//도서 정보 삽입
		BookDTO insertDto = new BookDTO(1005, "스프링프레임워크2", "프레임작가2", 39000);
		System.out.println(service.bookInsert(insertDto)?"삽입성공":"삽입실패");
		
		List<BookDTO> list = service.getlist();
		for(BookDTO dto : list) {
			System.out.println(dto);
		}
	}
}
