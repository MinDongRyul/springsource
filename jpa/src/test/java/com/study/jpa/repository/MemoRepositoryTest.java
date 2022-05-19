package com.study.jpa.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.study.jpa.entity.Memo;

//Spring Data JPA는 여러 종류의 인터페이스 기능을 통해서 JPA관련작업을 처리
//CRUD,페이징,정렬을 인터페이스 메소드만 호출하면 처리됨

//JpaRepository 인터페이스

//MemoRepository 테스트

//테스트 클래스 안에 작성하는 메소드는 void이고 하나만 존재해야 함
@SpringBootTest
public class MemoRepositoryTest {
	
	@Autowired
	private MemoRepository repository;
	
	// insert : save(엔티티 객체)
	// select : findById(키 타입), getOne(키 타입)
	// update : save(엔티티 객체)
	// delete : deleteById(키 타입), delete(엔티티 객체)
	
//	@Test
//	public void testInsert() {
//		
//		IntStream.rangeClosed(1, 101).forEach(i -> {
//			Memo memo = new Memo();
//			memo.setMemoText("Sample..."+i);
//			
//			//@Builder 사용 시 
//			//Memo memo = new Memo(1,2,3,4,5,6,7.....);
//			Memo memo = Memo.builder()
//							.memoText("Sample..."+i)
//							.build();	
//			
//			//Entity Manager가 엔티티를 관리해서 객체를 비교한 후 없으면 insert작업
//			//있으면 update 실행해 줌
//			repository.save(memo);
//		});
//		
//	}
	
//	//read
//	@Test 
//	public void testRead() {
//		
//		// Optional<> : 
//		Optional<Memo> result = repository.findById(90L);
//		
//		// 레코드를 읽어왔다면 true
//		if(result.isPresent()) {
//			System.out.println(result.get());
//		}
//	}
	
//	//update
//	@Test
//	public void testUpdate() {
//		//수정 내용
//		Memo memo = Memo.builder()
//						.mno(90L)
//						.memoText("업데이트 메모")
//						.build();
//		
//		System.out.println(repository.save(memo));
//	}
	
//	//delete
//	@Test
//	public void testDelete() {
//		repository.deleteById(90L);
//		
//	}

//	@Test
//	public void testPaging() {
//		
		// 페이징 처리 : findAll(Pageable)
		// Pageable : 페이지 처리에 필요한 정보를 전달하는 객체
		
		// 페이지 번호는 0번부터 시작
		// 1페이지 데이터 10개 가져오기
//		Pageable pageable = PageRequest.of(0, 10);
//		Page<Memo> result = repository.findAll(pageable);
		
		//정렬 기준 설정
//		Sort sort = Sort.by("mno").descending();
//		Pageable pageable = PageRequest.of(0, 10, sort);
//		
//		Page<Memo> result = repository.findAll(pageable); 
//		
//		System.out.println(result);
//		
//		System.out.println("-----------------------------");
//		
//		System.out.println("총 페이지 수 : "+result.getTotalPages());
//		System.out.println("전체 게시물 수 : "+result.getTotalElements());
//		System.out.println("현재 페이지 번호(0부터 시작) : "+result.getNumber());
//		System.out.println("페이지 당 데이터 개수 : "+result.getSize());
//		System.out.println("다음 페이지 존재 여부 : "+result.hasNext());
//		System.out.println("시작 페이지 여부 : "+result.isFirst());
//		
//		System.out.println("-----------------------------");
//		
//		for(Memo memo : result.getContent()) {
//			System.out.println(memo);
//		}
//		
//	}
	
//	@Test
//	public void testQuery() {
//		
//		//By : 기준 , Between : between, OrderbyByMnoDesc(기준1, 기준2) -> Mno를 40에서 80사이로 기준잡음
//		List<Memo> list = repository.findByMnoBetweenOrderByMnoDesc(40L, 80L);
//		
//		list.forEach(memo -> {
//			System.out.println(memo);
//		});
//	}

	
	@Test
	public void testQuery() {
		
		Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
		
		Page<Memo> result = repository.findByMnoBetween(40L, 80L, pageable);
		
		result.getContent().forEach(memo -> {
			System.out.println(memo);
		});
	}
}






















