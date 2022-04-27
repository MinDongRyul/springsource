package com.study.service;

import java.util.List;

import com.study.dto.ItemDTO;

public interface ItemSerivce {
	//CRUD
	//그저 mapper로 거쳐가는 부분? 
	public List<ItemDTO> getlist();
	public boolean itemInsert(ItemDTO insertDto);
	public boolean itemUpdate(int num, String psize, int price);
	public boolean itemDelete(int num);
	public ItemDTO getRow(int num);
}
