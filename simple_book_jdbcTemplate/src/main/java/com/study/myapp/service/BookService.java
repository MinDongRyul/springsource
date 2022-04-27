package com.study.myapp.service;

import java.util.List;

import com.study.myapp.dto.BookDTO;

public interface BookService {
	public List<BookDTO> getlist();
	public boolean bookInsert(BookDTO insertDto);
	public boolean bookUpdate(int code, int price);
	public boolean bookDelete(int code);
	
}
