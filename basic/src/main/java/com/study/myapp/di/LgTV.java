package com.study.myapp.di;

public class LgTV implements TV {

	private Speaker speaker;

	public LgTV(Speaker speaker) {
		super();
		this.speaker = speaker;
	}

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
