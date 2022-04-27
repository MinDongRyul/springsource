package com.study.myapp.di;

public class MessageEn implements Message {

	@Override
	public void sayHello(String name) {
		System.out.println("Hello "+name+" !!");
	}
}
