package com.study.mapper;

import com.study.dto.SpUser;
import com.study.dto.SpUserAuthority;

public interface UserMapper {
	public int register(SpUser user);
	public int registerAuth(SpUserAuthority auth);
	public SpUser read(String userid);
}
