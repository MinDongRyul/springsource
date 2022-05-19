package lambda;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

// 대부분의 추상 메소드는 타입이 비슷하기 때문에 자주 쓰이는 형식의 메소드를 함수형
// 인터페이스로 제공함
// Supplier 인터페이스 T get()
// Consumer 

public class LambdaEx2 {
	public static void main(String[] args) {
		Supplier<Integer> s = () -> (int)(Math.random() * 100) + 1;
		System.out.println(s.get());
	
		Consumer<Integer> c = k -> System.out.println(k);
		c.accept(15);
		
		Predicate<Integer> p = i -> i > 10;
		System.out.println(p.test(12));
	}
}
