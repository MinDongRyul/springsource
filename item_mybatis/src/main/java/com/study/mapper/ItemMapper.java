package com.study.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.study.dto.ItemDTO;

public interface ItemMapper {
	//method와 return타입 정하기
	public int insert(ItemDTO insertDto);
	public int update(@Param("num") int num, @Param("psize") String psize, @Param("price") int price );
	public int delete(int num);
	
	public List<ItemDTO> select();
	public ItemDTO selectOne(int num);
}
