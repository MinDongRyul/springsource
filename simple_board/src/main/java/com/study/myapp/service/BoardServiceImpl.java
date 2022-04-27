package com.study.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.myapp.dao.BoardDAO;
import com.study.myapp.dto.BoardDTO;

@Service("service")
//에러시 : Exception in thread "main" org.springframework.beans.factory.
//        NoSuchBeanDefinitionException: No bean named 'service' available
//dao작업
public class BoardServiceImpl implements BoardService {

	@Autowired
	//에러시 : Exception in thread "main" java.lang.NullPointerException
	private BoardDAO dao;
	
	@Override
	public List<BoardDTO> getList() {
		return dao.getList();
	}

	@Override
	public BoardDTO getRow(int bno) {
		return dao.selectOne(bno);
	}

	@Override
	public boolean boardInsert(BoardDTO insertDto) {
		return dao.insert(insertDto)==1 ? true:false;
	}

	@Override
	public boolean boardUpdate(BoardDTO updateDto) {
		return dao.update(updateDto)==1 ? true:false;
	}

	@Override
	public boolean boardDelete(int bno) {
		return dao.delete(bno)==1 ? true:false;
	}

}
