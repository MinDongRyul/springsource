package com.study.myapp;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.dto.ItemDTO;
import com.study.service.ItemSerivce;

public class ItemApp {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		
		ItemSerivce service = (ItemSerivce)ctx.getBean("service");
		
		boolean flag = true;
		Scanner sc = new Scanner(System.in);
		
		while(flag) {
			System.out.println("----------------------");
			System.out.println("1. 아이템 추가");
			System.out.println("2. 아이템 조회");
			System.out.println("3. 아이템 삭제");
			System.out.println("4. 아이템 수정");
			System.out.println("5. 아이템 전체 조회");
			System.out.println("6. 종료");
			System.out.println("----------------------");
			
			System.out.print("숫자를 입력해 주세요 : ");
			int no = Integer.parseInt(sc.nextLine());
			
			switch(no) {
			case 1:
				
				ItemDTO insertDto = new ItemDTO();
				
				System.out.print("카테고리를 입력해 주세요 : ");
				String category = sc.nextLine();
				insertDto.setCategory(category);
				
				System.out.print("상품 명을 입력해 주세요 : ");
				String name = sc.nextLine();
				insertDto.setName(name);
				
				System.out.print("상품 내용을 입력해 주세요 : ");
				String content = sc.nextLine();
				insertDto.setContent(content);
				
				System.out.print("사이즈를 입력해 주세요 : ");
				String psize= sc.nextLine();
				insertDto.setPsize(psize);
				
				System.out.print("가격을 입력해 주세요 : ");
				int price = Integer.parseInt(sc.nextLine());
				insertDto.setPrice(price);
				
				System.out.println(service.itemInsert(insertDto)? "입력 성공":"입력 실패");
				
				break;
			case 2:
				
				System.out.print("조회할 번호를 입력해 주세요 : ");
				int num1 = Integer.parseInt(sc.nextLine());
				
				System.out.println(service.getRow(num1));
				
				break;
			case 3:
				
				System.out.print("삭제할 번호를 입력해 주세요 : ");
				int num2 = Integer.parseInt(sc.nextLine());
				
				System.out.println(service.itemDelete(num2)? "삭제 성공":"삭제 실패");
				
				break;
			case 4:
				
				System.out.print("수정할 번호를 입력해 주세요 : ");
				int num3 = Integer.parseInt(sc.nextLine());
				
				System.out.print("수정할 사이즈를 입력해 주세요 : ");
				String psize2= sc.nextLine();
				
				System.out.print("수정할 가격을 입력해 주세요 : ");
				int price2 = Integer.parseInt(sc.nextLine());
				
				System.out.println(service.itemUpdate(num3, psize2, price2)?"수정 성공":"수정 실패");
				
				break;
			case 5:
				
				System.out.println(service.getlist());
				
				break;
			case 6:
				
				System.out.println("프로그램 종료");
				flag = false;
				
				break;
			default:
				
				System.out.println("잘못된 번호 입니다.");
				
				break;
			}
			
		}
	}
}
