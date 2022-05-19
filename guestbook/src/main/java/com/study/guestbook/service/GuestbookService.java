package com.study.guestbook.service;

import com.study.guestbook.dto.GuestbookDTO;
import com.study.guestbook.dto.PageRequestDTO;
import com.study.guestbook.dto.PageResultDTO;
import com.study.guestbook.entity.Guestbook;

public interface GuestbookService {
	
	//등록
	Long register(GuestbookDTO insertDto);
	
	//목록
	PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);
	
	//조회
	GuestbookDTO read(Long gno);
	
	//수정
	void update(GuestbookDTO updateDto);
	
	//삭제
	void remove(Long gno);
	
	//Entity(Guestbook) -> DTO(GuestbookDTO) 로 변환
	default GuestbookDTO entityToDto(Guestbook entity) {
		GuestbookDTO dto = GuestbookDTO.builder()
									   .gno(entity.getGno())
									   .title(entity.getTitle())
									   .content(entity.getContent())
									   .writer(entity.getWriter())
									   .regDate(entity.getRegDate())
									   .updatedate(entity.getUpdatedate())
									   .build();
		return dto;
	}
	
	//DTO(GuestbookDTO) -> Entity(Guestbook) 로 변환
	default Guestbook dtoToEntity(GuestbookDTO dto) {
		Guestbook entity = Guestbook.builder()
									.title(dto.getTitle())
									.content(dto.getContent())
									.writer(dto.getWriter())
									.build();
		return entity;
	}
	
	
	
	
	
}
