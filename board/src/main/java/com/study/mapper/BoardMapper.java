package com.study.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.study.dto.BoardDTO;
import com.study.dto.Criteria;


public interface BoardMapper {
	
	//게시물 리스트
	public List<BoardDTO> select(Criteria cri);
	
	//게시물 삽입
	public int insert(BoardDTO insertDto);
	
	//특정 게시물 읽기
	public BoardDTO read(int bno);

	//게시물 수정
	public int update(BoardDTO updateDto);
	
	//게시물 삭제
	public int delete(int bno);
	
	//게시물 리스트 페이지 나누기시 필요한 게시물 총 개수
	public int totalCnt(Criteria cri);
	
	//게시물의 댓글 수 최신화
	public int updateReplyCnt(@Param("bno") int bno, @Param("amount") int amount);
}
