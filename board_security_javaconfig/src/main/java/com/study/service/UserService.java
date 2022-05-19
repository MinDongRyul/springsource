package com.study.service;

import com.study.dto.AuthDTO;
import com.study.dto.MemberDTO;

public interface UserService {
	public boolean register(MemberDTO user);
	public boolean registerAuth(AuthDTO auth);
}
