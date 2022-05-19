package com.study.guestbook.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.guestbook.dto.GuestbookDTO;
import com.study.guestbook.dto.PageRequestDTO;
import com.study.guestbook.dto.PageResultDTO;
import com.study.guestbook.entity.Guestbook;

@SpringBootTest
public class GuestbookServiceTest {
	
	@Autowired
	private GuestbookService service;
	
//	@Test
//	public void testRegister() {
//		
//		GuestbookDTO dto = GuestbookDTO.builder()
//									   .title("안녕하세요")
//									   .content("스프링 부트에 대해서 궁금합니다")
//									   .writer("홍길동")
//									   .build();
//		
//		System.out.println(service.register(dto));
//	}
	
	@Test
	public void testList() {
	
		PageRequestDTO requestDTO = PageRequestDTO.builder()
												  .page(1)
												  .size(10)
												  .type("tcw")
												  .keyword("작성")
												  .build();
		
		PageResultDTO<GuestbookDTO, Guestbook> result = service.getList(requestDTO);
	
		result.getDtoList().forEach(guestbook -> {
			System.out.println(guestbook);
		});
		
		System.out.println("=======================================");
		System.out.println("prev : "+result.isPrev());
		System.out.println("next : "+result.isNext());
   		System.out.println("total : "+result.getTotalPage());
   		System.out.println("=======================================");
	   
	    result.getDtoList().forEach(guestbook -> {
		    System.out.println(guestbook);
	    });
	    System.out.println("=======================================");
	    result.getPageList().forEach(i -> System.out.println(i));
	}
	
	
}
