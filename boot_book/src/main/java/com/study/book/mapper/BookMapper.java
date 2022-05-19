package com.study.book.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.book.dto.BookDTO;

@Mapper
public interface BookMapper {
	//bookmapper.xml과 이름 맞춰주는 곳
	//DAO에서 메소드의 앞부분 ex)public List<BookDTO> select()만 선언
	
	//C - 삽입
	public int insert(BookDTO insertDto);
	//R - 전체 조회, 하나 조회, 검색
	public List<BookDTO> list();
	public BookDTO select(int code);
	public List<BookDTO> search(@Param("criteria") String criteria,@Param("keyword") String keyword);	
	//U - 수정
	public int update(@Param("code") int code,@Param("price") int price);
	//D - 삭제
	public int delete(int code);
}
