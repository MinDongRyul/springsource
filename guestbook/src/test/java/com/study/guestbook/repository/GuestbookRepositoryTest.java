package com.study.guestbook.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.study.guestbook.entity.Guestbook;
import com.study.guestbook.entity.QGuestbook;

@SpringBootTest // test용
public class GuestbookRepositoryTest {
	
	@Autowired
	private GuestbookRepository repository;

//	@Test
//	public void insert() {
//
//		IntStream.rangeClosed(1, 300).forEach(i -> {
//			
//			Guestbook guestbook = Guestbook.builder()
//										   .title("Guest Title..."+i)
//										   .content("Guest Content..."+i)
//										   .writer("user..."+(i%10))
//										   .build();
//			System.out.println(repository.save(guestbook));
//		});
//	}
	
	@Test
	public void update() {
		//301번 찾은 후 수정
		repository.findById(301L).ifPresent(guest -> {
			guest.setTitle("Changed Title...");
			guest.setContent("Changed Content...");
			
			System.out.println(repository.save(guest));
		});			  
	}
	
	//querydsl 테스트 예제
	//검색
	//단일 항목으로 검색하는 경우
	//혼합 항목으록 검색하는 경우(제목 + 내용, 내용 + 작성자, 제목 + 작성자)
//	@Test
//	public void selectQuery() {
//		
//		//페이지 나누기
//		Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
//		
//		//Q도메인 사용
//		QGuestbook qGuestbook = QGuestbook.guestbook;
//		
//		//title에 1이 들어가 있는 게시물을 검색 where title like '%1%'
//		String keyword = "1";
//		
//		//where절에 들어가는 조건들을 넣어주는 컨테이너
//		BooleanBuilder builder = new BooleanBuilder();
//		
//		//title like '%1%'
//		BooleanExpression expression = qGuestbook.title.contains(keyword);
//		
//		//where + title like '%1%' 역할
//		builder.and(expression);
//		
//		//페이지 나누기 + 생성해 낸 where
//		Page<Guestbook> result = repository.findAll(builder, pageable);
//		
//		result.stream().forEach(guestbook -> {
//			System.out.println(guestbook);
//		});
//		
//		
//	}
	
//	@Test
//	public void selectQuery() {
//		
//		//페이지 나누기
//		Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
//		
//		//Q도메인 사용
//		QGuestbook qGuestbook = QGuestbook.guestbook;
//		
//		//제목 혹은 내용에 1이라는 키워드가 있고, gno가 0보다 크다
//		//where gno > 0 and (title like '%1%' or content like '%1%')
//		String keyword = "1";
//		
//		//where절에 들어가는 조건들을 넣어주는 컨테이너
//		BooleanBuilder builder = new BooleanBuilder();
//		
//		//title like '%1%' 
//		BooleanExpression expTitle = qGuestbook.title.contains(keyword);
//		//content like '%1%'
//		BooleanExpression expContent = qGuestbook.content.contains(keyword);
//		
//		//title like '%1%' or content like '%1%'
//		BooleanExpression expAll = expTitle.or(expContent);
//		
//		//where + title like '%1%' or content like '%1%' 역할
//		builder.and(expAll);
//		
//		//gno.gt(0L) : gno > 0
//		builder.and(qGuestbook.gno.gt(0L));
//		
//		//페이지 나누기 + 생성해 낸 where
//		Page<Guestbook> result = repository.findAll(builder, pageable);
//		
//		result.stream().forEach(guestbook -> {
//			System.out.println(guestbook);
//		});
		
		
//	}
}











