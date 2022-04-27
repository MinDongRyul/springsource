package com.study.myapp;

public class TVMain {
	public static void main(String[] args) {
		
		//TV tv = new SamsungTV(new SonySpeaker());
		//값 주입?
		
		SamsungTV tv = new SamsungTV();
		
		//tv.setSpeaker(new SonySpeaker());
		tv.setSpeaker(new AppleSpeaker());
		
	    tv.powerOn(); 
		tv.volumeUp(); 
		tv.powerOff();
		 
//		String str = null;
//		System.out.println(str);
//		System.out.println(str.toString());
	}
}
