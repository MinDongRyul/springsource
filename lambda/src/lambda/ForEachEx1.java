package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForEachEx1 {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		
		for(String s:list) {
			System.out.print(s+"\t");
		}
		
		System.out.println();
		
		list.forEach(i -> System.out.print(i+"\t"));
	
		System.out.println();
		
		List<String> items = Arrays.asList("사과","포도","딸기","바나나","수박");
		items.forEach(item -> System.out.print(item));
		
		System.out.println();
		
		Map<String, Integer> items1 = new HashMap<String, Integer>();
		items1.put("A", 10);
		items1.put("B", 20);
		items1.put("C", 30);
		items1.put("D", 40);
		items1.put("E", 50);
		
		items1.forEach((k, v) -> {
			System.out.println("Item key : "+k+", Count : "+v);
			if("E".equals(k)) {
				System.out.println("Hello E");
			}
		});
		
	}
}
