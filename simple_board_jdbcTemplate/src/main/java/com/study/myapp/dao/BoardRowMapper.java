package com.study.myapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.study.myapp.dto.BoardDTO;

public class BoardRowMapper implements RowMapper<BoardDTO> {

	@Override
	public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardDTO dto = new BoardDTO();
		dto.setBno(rs.getInt("bno"));
		dto.setContent(rs.getString("content"));
		dto.setTitle(rs.getString("title"));
		dto.setWriter(rs.getString("writer"));
		dto.setRegdate(rs.getDate("regdate"));
		dto.setUpdatedate(rs.getDate("updatedate"));
		return dto;
	}
}
