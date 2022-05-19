package com.study.guestbook.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.study.guestbook.dto.GuestbookDTO;
import com.study.guestbook.dto.PageRequestDTO;
import com.study.guestbook.dto.PageResultDTO;
import com.study.guestbook.entity.Guestbook;
import com.study.guestbook.entity.QGuestbook;
import com.study.guestbook.repository.GuestbookRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GuestbookServiceImpl implements GuestbookService {

	@Autowired
	private GuestbookRepository repository;
	
	@Override
	public Long register(GuestbookDTO insertDto) {
		
		log.info("service register "+insertDto);
		
		//entity변환
		Guestbook entity = dtoToEntity(insertDto);
		
		log.info("entity "+entity);
		
		//실질적인 DB작업
		repository.save(entity);
		
		return entity.getGno();
	}
	
	@Override
	public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO) {
		
		//Sort 기준 
		Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
		
		//검색기능
		BooleanBuilder booleanBuilder = getSearch(requestDTO);
		
		//findAll 호출 + 검색 기능
		Page<Guestbook> result = repository.findAll(booleanBuilder, pageable);
		
		//함수형 인터페이스 => 추상메소드가 하나만 존재(default, static과 상관 없이)
		//             => 인터페이스를 사용하려면 구현 클래스 생성
		//             => 중간 단계를 생략한다면 기본적인 방식은 익명구현클래스
		//             => 함수형 인터페이스 라면 람다식으로 사용
		//Test test = x -> System.out.print(x);
		//java.util.Function패키지 안에 자주 사용하는 형식의 추상메소드를 함수형 인터페이스로 정의해 놓음
		//Function<T,R> : T => type(매개변수), R => return type(출력)
		//Guestbook 타입의 매개변수를 받아 GuestbookDTO로 리턴
		Function<Guestbook, GuestbookDTO> fn = (entity -> entityToDto(entity)); // fn => 2개가 담겨져있음?
		
		return new PageResultDTO<GuestbookDTO, Guestbook>(result, fn);
	}

	@Override
	public GuestbookDTO read(Long gno) {
		
		//Guestbook : Entity
		Optional<Guestbook> result =  repository.findById(gno);
		
		// 화면단에서 사용 DTO
		// 서버단에서 사용 Entity
		
		return result.isPresent() ? entityToDto(result.get()) : null;
	}

	@Override
	public void remove(Long gno) {
		repository.deleteById(gno);
	}

	@Override
	public void update(GuestbookDTO updateDto) {
		
		Optional<Guestbook> result = repository.findById(updateDto.getGno());
		
		//result.isPresent() : 값이 있으면 true
		if(result.isPresent()) {
			Guestbook entity = result.get();
			
			entity.setTitle(updateDto.getTitle());
			entity.setContent(updateDto.getContent());
			
			repository.save(entity);
		}
	}
	
	private BooleanBuilder getSearch(PageRequestDTO requestDto) {
		
		//검색 조건 가져오기
		String type = requestDto.getType();
		//검색어 가져오기
		String keyword = requestDto.getKeyword();
		
		//where절에 들어가는 조건들을 넣어주는 컨테이너(조건을 붙혀주기만 하는 곳)
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		
		//Q도메인 클래스 이용(qGuestbook를 통해서 각 컬럼들을 부르고 조건을 걸어줄 수있음)
		QGuestbook qGuestbook = QGuestbook.guestbook;
		
		//질문 해봐야할거같음
		BooleanExpression expression = qGuestbook.gno.gt(0L);
		booleanBuilder.and(expression);
		
		if(type == null || type.trim().length() == 0) {
			return booleanBuilder;
		}
		
		BooleanBuilder conditionBuilder = new BooleanBuilder();
		
		if(type.contains("t")) {
			conditionBuilder.or(qGuestbook.title.contains(keyword));
		}
		if(type.contains("c")) {
			conditionBuilder.or(qGuestbook.content.contains(keyword));
		}
		if(type.contains("w")) {
			conditionBuilder.or(qGuestbook.writer.contains(keyword));
		}
		
		booleanBuilder.and(conditionBuilder);
		
		return booleanBuilder ;
	}

}
