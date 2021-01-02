package 단계5_배열문제;

public class 주민등록번호검사 {

	public static void main(String[] args) {

		String jumin = "930101-2012932";

		// 나이 출력

		String strAge = jumin.substring(0, 2);
		int intAge = Integer.parseInt(strAge);
		int age = 2021 - (1900 + intAge);
		System.out.println(age + "세");

		// 성별 출력
		char key = jumin.charAt(7);
		if (key == '1' || key == '3') {
			System.out.println("남");
		} else if (key == '2' || key == '4') {
			System.out.println("여");
		}

	}

}
