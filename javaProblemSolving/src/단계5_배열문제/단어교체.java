package 단계5_배열문제;

import java.util.Scanner;

/*
 * # 단어 교체하기 문제 (replace)
 * 1. text변수 문장 속에서 변경하고 싶은 단어를 입력받아,
 * 2. 교체해주는 기능을 구현한다.
 * 예)
 * 		Life is too short.
 * 		변경하고 싶은 단어입력 : Life
 * 		바꿀 단어입력 : Time
 * 
 * 		Time is too short.
 * 
 * # 생각해보기
 * - 변경을 하려면? 먼저 그 단어가 어디있는지 찾아야 한다. 
 * - 단어가 어디있는지 찾으려면? 텍스트를 다 배열에 넣은 후, '바꿀 단어'가 어디있는지 인덱스로 찾아 저장한다.
 * - 그 다음? 그 인덱스를 활용하여 문장을 이어 붙인다. 
 * (ex) '인덱스 기준 앞 단어들' + '바뀐 단어' + '인덱스 기준 뒤 단어들 
 * 
 */

public class 단어교체 {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		String text = "Life is too short.";
		System.out.println(text);

		System.out.print("변경하고 싶은 단어를 입력하세요 : ");
		String inputWord = scan.nextLine();

		//// [ 바꿀 단어를 찾는 코드 ]
		// 주어진 문장의 길이만큼 char형식의 배열 생성하고, 그 배열에 text(주어진 문장) 문자 하나씩 저장함
		// 검색할 단어 길이를 size 변수에 저장하고, check 변수 -1로 초기화 시킴
		// 2중 for문을 돌리면서 일치하는 단어가 있는지 비교함 (이 때 count 변수 활용)
		// for문 돌릴 때 for문의 길이를 어디까지 설정할지가 중요 (text 배열의 길이를 넘어가지 않게 설정)

		char[] textChar = new char[text.length()];

		for (int i = 0; i < textChar.length; i++) {
			textChar[i] = text.charAt(i);
		}

		int size = inputWord.length();
		int check = -1;
		int idx = 0;

		for (int i = 0; i < textChar.length - size + 1; i++) {
			int count = 0;
			for (int j = 0; j < size; j++) {
				if (textChar[i + j] == inputWord.charAt(j)) {
					count += 1;
				}
			}

			if (count == size) {
				idx = i;
				check = 1;
			}
		}

		//// [ 바꿀 단어를 찾은 후, 바뀔 단어로 교체하여 출력해주는 코드 ] 
		// 만약 check == 1이라면, firstIdx와 lastIdx 변수 생성 후 값 저장
		// 바꿀 단어 입력 받음
		// char 형식의 temp 배열 만들어서 textChar 배열 저장함
		// String 형식의 front 변수 생성한 후, 0부터 firstIdx까지 for문 돌려 temp 배열 데이터를 front 배열에 저장함
		// String 형식의 back 변수 생성한 후, lastIdx부터 temp.length까지 for문 돌려 temp 배열 데이터를 back
		// 배열에 저장함
		
		if (check == 1) {
			int firstIdx = idx;
			int lastIdx = idx + size;
			
			System.out.println("바꿀 단어를 입력하세요. : ");
			String change = scan.next();
			
			char[] temp = textChar;
			
			String front = "";
			for (int i=0; i<firstIdx; i++) {
				front += temp[i];
			}
			
			String back = "";
			for (int i=lastIdx; i<temp.length; i++) {
				back += temp[i];
			}
			
			String result = front + change + back;
			System.out.println(result);
		} else {
			System.out.println("해당 단어는 존재하지 않습니다.");
		}

	}

}
