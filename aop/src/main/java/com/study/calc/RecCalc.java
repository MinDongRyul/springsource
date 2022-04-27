package com.study.calc;

import org.springframework.stereotype.Component;

// 재귀호출로 factorial 구현
//       : 기본단계와 재귀단계로 나뉨

@Component("rec")
public class RecCalc implements Calc{

	@Override
	public long factorial(long num) {
		if(num == 0) {
			return 1;
		}else {			
			return num * factorial(num - 1);
		}
	}
}
