package lambda;

public class LambdaEx1 {
	public static void main(String[] args) {
		
//		Lambda2 lambda = x -> System.out.println(x);
//		lambda.method(10);
//	
//		lambda = x -> System.out.println(x * x);
//		lambda.method(100);
		
		//익명 구현 클래스
		Lambda2 lambda2 = new Lambda2() {
			
			@Override
			public int max(int num1, int num2) {
				return num1 > num2 ? num1 : num2;
			}
		};
		lambda2.max(10, 5);
		
		//The type new Lambda2(){} must implement the inherited abstract method Lambda2.method(int)
		Lambda2 lambda = (a,b) -> a > b ? a : b;
		System.out.println(lambda.max(20, 30));
		
	}
}
