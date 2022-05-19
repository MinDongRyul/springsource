package com.study.service;

import java.util.List;

import com.study.dto.BookDTO;

public interface BookService {
	public List<BookDTO> getlist();
	public boolean bookInsert(BookDTO insertDto);
	public boolean bookUpdate(int code, int price);
	public boolean bookDelete(int code);
	public List<BookDTO> searchList(String criteria, String keyword);
	public BookDTO getRow(int code);
}
