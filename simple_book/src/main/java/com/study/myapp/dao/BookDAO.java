package com.study.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.study.myapp.dto.BookDTO;

import static com.study.myapp.dao.JdbcUtil.*;

@Repository // (저장소)객체 생성
public class BookDAO {
	
	private Connection con;
	
	public List<BookDTO> select(){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookDTO> list = new ArrayList<BookDTO>();
		String sql = "select * from booktbl";
		
		try {
			con = getConnection();
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BookDTO dto = new BookDTO();
				dto.setCode(rs.getInt("code"));
				dto.setTitle(rs.getString("title"));
				dto.setWriter(rs.getString("writer"));
				dto.setPrice(rs.getInt("price"));
				
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
			close(con);
		}
		return list;
	}
	
	public int insert(BookDTO insertDto) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String sql = "insert into booktbl values(?,?,?,?)";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, insertDto.getCode());
			pstmt.setString(2, insertDto.getTitle());
			pstmt.setString(3, insertDto.getWriter());
			pstmt.setInt(4, insertDto.getPrice());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(con);
		}
		return result;
	}
}
