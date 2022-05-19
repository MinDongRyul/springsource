package lambda;

/*  함수형 프로그래밍
 * 	
 * 	람다식(8버전부터 추가됨)
 *  메소드를 하나의 식으로 표현한 개념
 *  메소드를 람다식으로 표현하면 메소드의 이름과 값이 없어지므로, 람다식을 익명함수라고도 한다.
 */

public class Lambda1 {
	
//	int max(int a, int b) {
//		return a > b ? a : b;
//	}

//	(int a, int b) -> {return a > b ? a : b;}
//	(int a, int b) -> a > b ? a : b;
//	(a, b) -> a > b ? a : b;

	void print(String name, int i) {
		System.out.println(name + "=" + i);
	}
	
//	(name, i) -> System.out.println(name + "=" + i);
	
//	() -> {return (int)(Math.random()*6);}
//	() -> (int)(Math.random()*6);
	
	int square(int x) {
		return x * x;
	}
	
//	(int x) -> {return x * x;}
//	x -> x * x;
	
	
}
