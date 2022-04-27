package com.study.myapp;

public class SamsungTV implements TV {

	//private SonySpeaker speaker; // 초기화(생성자, setter 메소드)
	//private AppleSpeaker speaker;
	private Speaker speaker;
	
	public SamsungTV() {
		
	}
	
	//값을 주입 해줘야한다?
	public SamsungTV(Speaker speaker) {
		super();
		this.speaker = speaker;
	}
	
	//초기화, 기존값 변경
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	@Override
	public void powerOn() {
		System.out.println("SamsungTV 전원 on");
	}

	@Override
	public void powerOff() {
		System.out.println("SamsungTV 전원 off");
	}

	@Override
	public void volumeUp() {
		//System.out.println("SamsungTV Volume Up");
		speaker.volumeUp();
	}

	@Override
	public void volumeDown() {
		//System.out.println("SamsungTV Volume Down");
		speaker.volumeDown();
	}
}
