package com.study.myapp.di.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component("ID역할")
@Component("lg")
public class LgTV implements TV {

	@Autowired
	@Qualifier("sony")
	private Speaker speaker; // speaker객체 AppleSpeaker, SonySpeaker 두개를 가지고 있음

	@Override
	public void powerOn() {
		System.out.println("LgTV 전원 on");
	}

	@Override
	public void powerOff() {
		System.out.println("LgTV 전원 off");
	}

	@Override
	public void volumeUp() {
		//System.out.println("LgTV Volume Up");
		speaker.volumeUp();
	}

	@Override
	public void volumeDown() {
		//System.out.println("LgTV Volume Down");
		speaker.volumeDown();
	}
}
