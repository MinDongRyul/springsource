package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StreamEx5 {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("바둑","바나나","포도","딸기","바질","강아지");
		
		//'바'로 시작하는 단어만 출력
		for(String li : list) {
			if(li.startsWith("바")) {
				System.out.println(li);
			}
		}
		
		//stream 이용
//		list.stream().filter(s -> s.startsWith("바")).forEach(s -> System.out.println(s));
		list.stream().filter(s -> s.startsWith("바")).forEach(System.out::println);
		
		List<Student> stuList = new ArrayList<Student>();
		stuList.add(new Student("홍길동", 70));
		stuList.add(new Student("송혜교", 75));
		stuList.add(new Student("ㄱㅈㅇ", 88));
		stuList.add(new Student("ㅈㅇㅅ", 92));
		stuList.add(new Student("송ㅈㄱ", 93));
		
		System.out.println();
		
		//송으로 시작하는 학생 이름 출력
		//stuList.stream().filter(s -> s.getName().startsWith("송")).forEach(System.out::println);
		stuList.stream().map(n -> n.getName())
		                .filter(s-> s.startsWith("송")).forEach(s -> System.out.println(s));
		
		System.out.println();
		
		//점수를 모아서 80점 이상인 점수만 출력
		stuList.stream().map(n -> n.getJumsu())
						.filter(s -> s >= 80).forEach(s -> System.out.println(s));
	}
}
