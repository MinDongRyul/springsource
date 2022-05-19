package com.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.dto.SpUser;
import com.study.dto.SpUserAuthority;
import com.study.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Transactional
	@Override
	public boolean register(SpUser user) {
		
		//사용자가 입력한 비밀번호를 암호화
		user.setPassword(encoder.encode(user.getPassword()));
		
		//회원가입
		boolean result = mapper.register(user) == 1;
		
		//권한부여
		SpUserAuthority auth = new SpUserAuthority(user.getUserid(), "ROLE_USER");
		mapper.registerAuth(auth);
		
		auth = new SpUserAuthority(user.getUserid(), "ROLE_ADMIN");
		mapper.registerAuth(auth);
		
		return result;
	}

	@Override
	public boolean registerAuth(SpUserAuthority auth) {
		return false;
	}

}
