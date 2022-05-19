package com.study.dto;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class CustomUser extends User {
	
	private SpUser spUser;
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	
	public CustomUser(SpUser spUser) {
		super(spUser.getUserid(), spUser.getPassword(), spUser.getAuthorities() //list구조
															  .stream()
															  .map(auth -> new SimpleGrantedAuthority(auth.getAuthority())) // SimpleGrantedAuthority 로만들어서 넘겨줘야한다
															  .collect(Collectors.toList()));
		System.out.println(spUser.getUserid());
		System.out.println(spUser.getPassword());
		System.out.println(spUser.getAuthorities());
		this.spUser = spUser;
	}
}
