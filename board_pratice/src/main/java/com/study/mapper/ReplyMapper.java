package com.study.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.study.dto.Criteria;
import com.study.dto.ReplyDTO;

public interface ReplyMapper {
	
	//댓글 삽입
	public int insert(ReplyDTO insertDto);
	
	//모달창에 특정 댓글 가져오기
	public ReplyDTO read(int rno);
	
	//댓글 수정
	public int update(ReplyDTO updateDto);
	
	//댓글 삭제
	public int delete(int rno);
	
	//댓글 리스트 가져오기
	public List<ReplyDTO> select(@Param("cri")Criteria cri, @Param("bno") int bno);
	
	//댓글 총 개수
	public int getCountBno(int bno);
}
