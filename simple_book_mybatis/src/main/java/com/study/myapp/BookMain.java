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
		
//		도서 정보 삽입
//		BookDTO insertDto = new BookDTO(1004, "스프링프레임워크", "프레임작가", 29000);
//		System.out.println(service.bookInsert(insertDto)?"삽입성공":"삽입실패");
		
//		도서 정보 수정
//		System.out.println(service.bookUpdate(1004, 19000)?"수정성공":"수정실패");
		
		
//		도서 정보 삭제
//		System.out.println(service.bookDelete(1004) ? "삭제성공":"삭제실패");
		
//		도서 정보 검색
		List<BookDTO> list = service.searchList("code","1001");
		for(BookDTO dto : list) {
			System.out.println(dto);
		}
		
		
//		도서 정보 전체 조회
//		List<BookDTO> list = service.getlist();
//		for(BookDTO dto : list) {
//			System.out.println(dto);
//		}
	}
}
