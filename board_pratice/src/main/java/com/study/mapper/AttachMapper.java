package com.study.mapper;

import java.util.List;

import com.study.dto.AttachDTO;

public interface AttachMapper {

	//첨부 파일 삽입
	public int insert(AttachDTO attach);
	
	//첨부 파일 목록 추출
	public List<AttachDTO> list(int bno);
}
