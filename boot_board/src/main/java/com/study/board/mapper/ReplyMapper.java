package com.study.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.board.dto.Criteria;
import com.study.board.dto.ReplyDTO;

@Mapper
public interface ReplyMapper {
	
	//댓글 삽입
	public int insert(ReplyDTO insertDto);
	
	//모달창에 특정 댓글 가져오기
	public ReplyDTO read(int rno);
	
	//댓글 수정
	public int update(ReplyDTO updateDto);
	
	//특정 댓글 삭제
	public int delete(int rno);
	
	//특정 게시물 댓글 전부 삭제
	public int deleteAll(int bno);

	//댓글 리스트 가져오기
	public List<ReplyDTO> select(@Param("cri")Criteria cri, @Param("bno") int bno);
	
	//댓글 총 개수
	public int getCountBno(int bno);
}
