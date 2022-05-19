package lambda;

import java.util.ArrayList;
import java.util.List;

public class CustomerEx1 {
	public static void main(String[] args) {
		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer("이순신", 40, 100));
		customerList.add(new Customer("ㄱㅇㅅ", 20, 100));
		customerList.add(new Customer("ㅎㄱㄷ", 13, 50));
		customerList.add(new Customer("ㅅㅊㅎ", 18, 70));
		
		//고객 명단 출력(map)
		customerList.stream().map(n -> n.getName()).forEach(n -> System.out.println(n));
		//총 여행 경비 계산(map, sum)
		long total = customerList.stream().mapToInt(n -> n.getPrice()).sum();
		System.out.println("여행 경비 : "+total);
		//고객 중 20세 이상인 사람의 이름을 정렬하여 출력
		System.out.println("--- 20세 이상 고객 명단 ---");
		customerList.stream().filter(n -> n.getAge() >= 20)
							 .map(n -> n.getName())
							 .sorted()
							 .forEach(n -> System.out.println(n));
	}
}
