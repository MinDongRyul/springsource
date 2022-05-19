package com.study.board.service;

import com.study.board.dto.AuthDTO;
import com.study.board.dto.MemberDTO;

public interface UserService {
	public boolean register(MemberDTO user);
	public boolean registerAuth(AuthDTO auth);
}
