package com.study.myapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.study.myapp.dto.BookDTO;

public class BookRowMapper implements RowMapper<BookDTO> {

	@Override
	public BookDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BookDTO dto = new BookDTO();
		dto.setCode(rs.getInt("code"));
		dto.setTitle(rs.getString("title"));
		dto.setWriter(rs.getString("writer"));
		dto.setPrice(rs.getInt("price"));
		return dto;
	}

}
