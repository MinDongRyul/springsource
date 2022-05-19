package com.study.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.study.board.dto.MemberDTO;

@Mapper
public interface MemberMapper {
	public MemberDTO read(String userid);
	
}
