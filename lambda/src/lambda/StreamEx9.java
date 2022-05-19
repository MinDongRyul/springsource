package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* 스트림 최종 연산
 * forEach() 
 * collect()  : 요소를 그룹화하거나 분할한 결과를 컬렉션에 담아 변환하는데 사용
 * count()    :
 * sum()
 * average()
 * max()
 * min()
 * findFirst()
 * 
 */
public class StreamEx9 {
	public static void main(String[] args) {
		List<Student> stuList = new ArrayList<Student>();
		stuList.add(new Student("홍길동", 70));
		stuList.add(new Student("송혜교", 75));
		stuList.add(new Student("ㄱㅈㅇ", 88));
		stuList.add(new Student("ㅈㅇㅅ", 92));
		stuList.add(new Student("송ㅈㄱ", 93));
		
		//기존의 방식
		List<Integer> stuNum = new ArrayList<Integer>();
		for(Student stu : stuList) {
			stuNum.add(stu.getJumsu());
		}
		
		//학생들의 점수만 모아서 새로운 리스트 생성(스트림)
		//Stream<Student> stream = stuList.stream();
		List<Integer> jumsu = stuList.stream().map(n -> n.getJumsu()).collect(Collectors.toList());
		System.out.println(jumsu);
	}
}
