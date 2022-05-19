package com.study.guestbook.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Data;

// 페이지 결과처리
// 페이지 처리 결과 넘어오는 객체는 Page<Entity> 형태임
// 서비스 계층에서 넘어오는 형태의 변경을 위해서 사용(html에서 사용하기 쉬운 형태로 변환하기 위해서 사용)

@Data
//PageResultDTO<GuestbookDTO, Guestbook>
public class PageResultDTO<DTO,EN> {
	
	//게시물 리스트
	private List<DTO> dtoList;
	
	//현재 페이지 번호
	private int page;
	
	//목록 사이즈
	private int size;
	
	//시작 페이지 번호
	private int start;
		
	//끝 페이지 번호
	private int end;
	
	//이전, 다음
	private boolean prev, next;
	
	//페이지 번호 목록
	private List<Integer> pageList;
	
	//총 페이지 번호
	private int totalPage;
	
	//result : serviceimpl에서 repository.findAll(booleanBuilder, pageable) 통해 받은 entity정보
	//fn : serviceimpl에서 entity인 result를 dto객체로 바꿔서 받은 것
	public PageResultDTO(Page<EN> result, Function<EN, DTO> fn) {
		
		//내가 원하는 list형태로 뽑아줌
		//entity에는 토탈페이지의 수도 들어가 있는지? 질문
		dtoList = result.stream().map(fn).collect(Collectors.toList());
		totalPage = result.getTotalPages();
		makePageList(result.getPageable());
	}
	
	private void makePageList(Pageable pageable) {
		//getPageable 에서 -1 해준걸 다시 +1
		this.page = pageable.getPageNumber() + 1;
		this.size = pageable.getPageSize();
		
		int tempEnd = (int)(Math.ceil(page/10.0)) * 10;
		start = tempEnd - 9;
		
		prev = start > 1;
		end = totalPage > tempEnd ? tempEnd : totalPage;
		next = totalPage > tempEnd;
		
		// .boxed() => 객체화
		pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());	
	}
}
