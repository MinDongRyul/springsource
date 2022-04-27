package com.study.myapp.di;

public class SonySpeaker implements Speaker {
	
	public SonySpeaker() {
		System.out.println("SonySpeaker -- 객체 생성");
	}
	
	@Override
	public void volumeUp() {
		System.out.println("SonySpeaker -- 볼륨업");
	}
	
	@Override
	public void volumeDown() {
		System.out.println("SonySpeaker -- 볼륨다운");
	}
}
