package com.study.mapper;

import java.util.List;

import com.study.dto.AttachDTO;

public interface AttachMapper {

	//첨부 파일 삽입
	public int insert(AttachDTO attach);
	
	//첨부 파일 목록 추출
	public List<AttachDTO> list(int bno);
	
	//bno가 일치하는 첨부파일 모두 삭제
	public int deleteAll(int bno);
	
	//어제 날짜로 등록된 첨부파일 목록 가져오기
	public List<AttachDTO> getOldFiles();
	
}
