package com.study.myapp.di.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TVMain {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config2.xml");
		
//		No bean named 'lgTV' available => bean이 만들어지지 않음
//		TV tv = (TV)ctx.getBean("lg");
		TV tv = (TV)ctx.getBean("samsung");
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
//		.NoUniqueBeanDefinitionException: 
//		No qualifying bean of type 'com.study.myapp.di.annotation.Speaker' 
//		available: expected single matching bean 
//		but found 2: appleSpeaker,sonySpeaker
		
	}
}
