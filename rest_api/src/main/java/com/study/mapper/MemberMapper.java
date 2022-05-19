package com.study.mapper;

import org.apache.ibatis.annotations.Param;

import com.study.dto.AuthDTO;
import com.study.dto.ChangeDTO;
import com.study.dto.MemberDTO;

public interface MemberMapper {
	//CRUD 메소드 정의
	
	//받을게 없다면 int
	//     있다면 그에 맞는 변수 타입
	
	//C - 회원가입
	public int insert(MemberDTO register);
	//R - 로그인
	public AuthDTO login(@Param("userid") String userid, @Param("password") String password);
	//R - 아이디 중복 확인
	public String dupId(String userid);
	//U - 비밀번호 요청
	public int update(ChangeDTO changepwd);
	//D - 회원탈퇴, @Param("이름") 에 들어오는 값의 이름이 name과 같아야한다.
	public int delete(@Param("userid") String userid, @Param("current_password") String password);
	
}
