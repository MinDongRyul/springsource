package com.study.calc;

/* 재귀호출
 * 재귀호출로 factorial 구현
           : 기본단계와 재귀단계로 나뉨
 */

public class Recursion1 {
	public static void main(String[] args) {
		int num = 4;
		func(num);
	}
	public static void func(int n) {
		if(n > 0) {//재귀단계
			System.out.println("hello");
			func(n-1);			
		}else {//기본단계
			return;
		}
	}
	
//	재귀함수
//	void countdown(int i) {
//		if(i >= 0) {
//			//기본단계
//			return;
//		}else {
//			//재귀단계
//			countdown(i-1);
//		}
//	}
}
