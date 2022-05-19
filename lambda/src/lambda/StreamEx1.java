package lambda;

import java.util.Arrays;
import java.util.stream.Stream;

// 스트림 : 배열이나 컬렉션의 저장 요소를 하나씩 참조해서 람다식으로 처리할 수 있도록 해줌
//        코드의 간결성 유지, 매핑, 필터링과 같은 중간 처리 가능

public class StreamEx1 {
	public static void main(String[] args) {
		String strArr[] = {"사과","바나나","딸기","포도","메론"};
		
		//배열 => 스트림 변환
//		Stream<String> stream = Arrays.stream(strArr);
//		stream.forEach(s -> System.out.print(s));
		
		Arrays.stream(strArr).forEach(s -> System.out.print(s));
	}
}
