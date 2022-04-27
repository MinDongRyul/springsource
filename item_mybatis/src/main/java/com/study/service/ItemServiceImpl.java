package com.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.dto.ItemDTO;
import com.study.mapper.ItemMapper;

@Service("service") //객체 생성
public class ItemServiceImpl implements ItemSerivce {

	@Autowired //객체 주입
	private ItemMapper mapper;
	
	@Override
	public List<ItemDTO> getlist() {
		return mapper.select();
	}

	@Override
	public boolean itemInsert(ItemDTO itemDto) {
		return mapper.insert(itemDto) == 1? true:false;
	}

	@Override
	public boolean itemUpdate(int num, String psize, int price) {
		return mapper.update(num, psize, price) == 1 ? true:false;
	}

	@Override
	public boolean itemDelete(int num) {
		return mapper.delete(num) == 1 ? true:false;
	}

	@Override
	public ItemDTO getRow(int num) {
		return mapper.selectOne(num);
	}

}
