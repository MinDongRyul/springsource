package lambda;

import java.util.stream.IntStream;

public class StreamEx6 {

	public static void main(String[] args) {
		// 정수 1~10 숫자를 Stream으로 생성
		IntStream intStream = IntStream.rangeClosed(1, 10);
		
		// 짝수만 출력
		//intStream.filter(n -> n % 2 == 0).forEach(n -> System.out.println(n));
		
		//i를 2로 나눈 나머지가 0이 아니고 3 으로 나눈 나머지가 0이 아닌 요소 출력
		intStream.filter(n -> n % 2 != 0 && n % 3 != 0).forEach(n -> System.out.println(n));
	}
}
