package com.study.myapp.di.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// @Component : 객체 생성 => 설정 파일에 <bean id="" class=""/>와 같은 역할 
//                      => 클래스 이름을 사용(앞자리를 소문자로 바꾼게 아이디)
//                      => @Service, @Repository, @Controller : 객체 생성 + 의미 부여
@Component("samsung")
public class SamsungTV implements TV {

//	@Autowired : 생성된 객체를 주입
	@Autowired
//	@Qualifier : 단독으로 사용 불가 (AutoWired와 같이 사용) - 이름 부여
	@Qualifier("apple")
//  ==> 어노테이션을 활성화 해주는 작업 필요
//	private SonySpeaker speaker; // 초기화(생성자, setter 메소드)
//	private AppleSpeaker speaker;
	private Speaker speaker;

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
