package com.study.test;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
public class SpringBoradTest {
	
	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private DataSource ds;
	
	@Test
	public void test() {
		//String sql = "insert into spring_member(userid, userpw, username) values(?, ?, ?)";
		String sql = "update spring_board set content = ? where bno = ?";
		
		for(int i=0; i<664; i++) {
			try(Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
				
				//pstmt.setString(2, encoder.encode("pw"+i)); // user0, pw0, 일반회원0
				
				if(i % 3 == 0) {
					pstmt.setString(1, "자바 게시글");
					pstmt.setInt(2, i);
				}else if(i % 3 == 1) {
					pstmt.setString(1, "스프링 게시글");
					pstmt.setInt(2, i);
				}else {
					pstmt.setString(1, "스크립트 게시글");
					pstmt.setInt(2, i);
				}
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}














