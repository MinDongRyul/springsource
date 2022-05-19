package com.study.service;

import com.study.dto.SpUser;
import com.study.dto.SpUserAuthority;

public interface UserService {
	public boolean register(SpUser user);
	public boolean registerAuth(SpUserAuthority auth);
	
}
