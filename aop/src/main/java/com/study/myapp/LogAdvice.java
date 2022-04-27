package com.study.myapp;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component("log")
public class LogAdvice {
	
//	@Before(value="execution(* com.study.myapp.Product.getInfo())")
//	public void beforeLog() {
//		System.out.println("[공통로그] 비즈니스 로직 수행 전 호출");
//	}
	
//	익셉션 여부와 상관없이 메소드 실행 후 호출	
//	@After(value="execution(* com.study.myapp.Product.getInfo())")
//	public void afterLog() {
//		System.out.println("[공통로그] 비즈니스 로직 수행 후 호출");
//	}
	
//	@AfterReturning(value="execution(* com.study.myapp.Product.getInfo())")
//	public void afterReturnLog() {
//		System.out.println("[공통로그] 비즈니스 로직 수행시 익셉션 없이 실행 후 호출");
//	}
//	
//	@AfterThrowing(value="execution(* com.study.myapp.Product.getInfo())")
//	public void afterThrowLog() {
//		System.out.println("[공통로그] 비즈니스 로직 수행시 익셉션 발생 후 호출");
//	}
	
	@Around(value="execution(* com.study.myapp.Product.getInfo())")
	public void aroundLog(ProceedingJoinPoint pjp) {
		System.out.println("[공통로그] 비즈니스 로직 수행시 전 호출");
		
		try {
			//비즈니스 메소드 호출
			pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		System.out.println("[공통로그] 비즈니스 로직 수행시 후 호출");
	}
}









