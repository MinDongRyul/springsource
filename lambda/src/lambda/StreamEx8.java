package lambda;

import java.util.Arrays;
import java.util.List;

public class StreamEx8 {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("사과","딸기","배","바나나","수박","참외","바나나");
		
		list.stream()
			.skip(2)
			.limit(3)
			.forEach(s -> System.out.println(s));
	}

}
