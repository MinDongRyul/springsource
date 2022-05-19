package lambda;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class StreamEx10 {
	public static void main(String[] args) {
		IntStream stream = Arrays.stream(new int[] {1,2,3,4,5});
		
		//2의 배수 개수
		long cnt = stream.filter(n -> n % 2 == 0).count();
		System.out.println("2의 배수 개수 : "+cnt);
		
		//2의 배수 합
		long sum = Arrays.stream(new int[] {1,2,3,4,5}).filter(n -> n % 2 == 0).sum();
		System.out.println("2의 배수 합 : "+sum);
		
		//2의 배수 평균
		OptionalDouble avg = Arrays.stream(new int[] {1,2,3,4,5}).filter(n -> n % 2 == 0).average();
		System.out.println("2의 배수 평균 : "+avg);
		
		//2의 배수 최대값
		OptionalInt max = Arrays.stream(new int[] {1,2,3,4,5}).filter(n -> n % 2 == 0).max();
		System.out.println("2의 배수 최대값 : "+max);
		
		//2의 배수 최소값
		OptionalInt min = Arrays.stream(new int[] {1,2,3,4,5}).filter(n -> n % 2 == 0).min();
		System.out.println("2의 배수 최소값 : "+min);
		
		//2의 배수 첫번째 값
		OptionalInt first = Arrays.stream(new int[] {1,2,3,4,5}).filter(n -> n % 2 == 0).findFirst();
		System.out.println("2의 배수 첫번째 값 : "+first);
	}
}
