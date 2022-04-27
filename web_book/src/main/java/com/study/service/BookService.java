package com.study.service;

import java.util.List;

import com.study.dto.BookDTO;

public interface BookService {
	//BookMapper 메소드 호출
	public boolean bookInsert(BookDTO insertDto);
	public List<BookDTO> getList();
	public BookDTO getRow(int code);
	public List<BookDTO> getSearchList(String criteria, String keyword);
	public boolean bookUpdate(int code, int price); 
	public boolean bookDelete(int code);
}
