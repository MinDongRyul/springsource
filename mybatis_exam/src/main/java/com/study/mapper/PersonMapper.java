package com.study.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.study.dto.PersonDTO;

public interface PersonMapper {
	
//	#{id}, #{name}  ==> ? 로 변경되는 부분
//	insert into person(id, name) values(?, ?) ?, ? => mybatis에서는 #{id}, #{name}
//	@Insert("insert into person(id, name) values(#{id}, #{name})")
//	2개부터는 에러가 발생하기에 @Param("") 필수.
//	public int insertPerson(@Param("id") String id, @Param("name") String name);
	
	public int insert(@Param("id") String id, @Param("name") String name);
	public int update(@Param("id") String id, @Param("name") String name);
	public int delete(String id);
	public PersonDTO select(String id);
}
