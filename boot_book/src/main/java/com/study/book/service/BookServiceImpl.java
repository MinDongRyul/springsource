package com.study.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.book.dto.BookDTO;
import com.study.book.mapper.BookMapper;

@Service // 객체 선언
public class BookServiceImpl implements BookService {
	
	@Autowired // 객체 주입
	private BookMapper mapper;
	
	@Override
	public boolean bookInsert(BookDTO insertDto) {
		return mapper.insert(insertDto) == 1? true : false;
	}

	@Override
	public List<BookDTO> getList() {
		return mapper.list();
	}

	@Override
	public BookDTO getRow(int code) {
		return mapper.select(code);
	}

	@Override
	public List<BookDTO> getSearchList(String criteria, String keyword) {
		return mapper.search(criteria, keyword);
	}

	@Override
	public boolean bookUpdate(int code, int price) {
		return mapper.update(code, price) == 1? true : false;
	}

	@Override
	public boolean bookDelete(int code) {
		return mapper.delete(code) == 1? true : false;
	}
	
}
