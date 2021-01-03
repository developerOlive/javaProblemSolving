package 단계5_배열문제;

import java.util.Random;
import java.util.Scanner;

/*
 * 
 * [ 문제 ] 영어 단어 맞추기 게임 
 * 1) 영어 단어가 전부 * 로 표시된다.
 * 2) 사용자로부터 영어 단어를 입력 받은 후, 틀릴 때마다 랜덤으로 한 글자씩 공개된다. 
 *    단, 같은 철자가 여러개이면 한 번에 공개 된다.
 * 3) 단어가 전부 공개되거나, 사용자가 맞추면 게임이 종료된다.
 * 4) 점수는 처음 100점으로 세팅하고, 틀릴 때마다 -5점씩 감점시킨다.
 * 
 * 
 * [ 생각해보기 ]
 * ( 세팅 ) 
 * - 처음에 세팅해야 할 것이 무엇인가? 
 *   스캐너, 랜덤, 점수, 문제(영어), 의미(한글), check 배열
 * - 세팅 후에는 while(true)문 시작되고, 
 * - 그 안에서 단어의 의미와 문제를 출력한다.
 *   문제를 출력할 때는 for문을 돌리는데 
 *   check[i] == 1일때는 그 인덱스에 해당하는 철자는 charAt으로 공개하고
 *   check[i] != 1일 때는 그 인덱스에 해당하는 철자를 "*"로 출력한다. 
 * 
 * ( *로 표시되던 단어철자가 전부 공개된 것인지 확인하는 코드 -> 전부 공개되면 맞추기 실패 )
 * -  먼저 boolean 형식의 end를 true로 초기화 시킨다. 
 * -  만약 check 배열 중 하나라도 0이면,
 *    단어가 다 아직 공개된 것이 아니 때문에 end를 false로 처리 
 * -  그러다 true로 초기화시킨 end가 if문에 걸리지 않고 그대로 true로 남아있으면 
 *    check변수의 데이터가 모두 1이되어 단어철자가 전부 공개되었다는 의미기 때문에
 *    맞추기 실패라는 문구를 출력한다. 
 * 
 * ( 입력 받는 부분 ) 
 * - 영어 단어 입력 받은 후, 
 *   if문) 해당 내용이 정답이 아니면
 *        1) 점수를 -5점 처리
 *        2) while(true)문 안에서 랜덤 숫자 하나를 뽑아
 *         check[r] == 0이면 check[r] = 1로 저장 
 *         (check[r] == 0 이라는 의미는 아직 공개되지 않은 철자라는 의미) 
 *        3) for문을 돌려 word.charAt(i) == word.charAt(r)이면 
 *        	 check[i] = 1로 저장함 
 *  else문) 사용자가 입력한 단어가 정답이면, 점수 출력 후 break 처리
 * 
 */

public class 영어단어맞추기 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		Random ran = new Random();

		int score = 100;
		
		String word = "scanner";
		String meaning = "스캐너";
		
		int size = word.length();
		int check[] = new int[size]; // *로 표시되던 단어가 공개될 때 -> 단어에 해당되는 인덱스가 숫자 1로 바뀌는 배열 

		while (true) {

			//// 단어 출력 부분
			System.out.println("뜻 : " + meaning);
			System.out.print("문제 : ");

			for (int i = 0; i < size; i++) {
				if (check[i] == 1) {
					System.out.print(word.charAt(i)); // 공개될 글자 출력
				} else {
					System.out.print("*");
				}
			}

			System.out.println();
			System.out.println("====================");

			
			//// *로 표시되던 단어가 전부 공개된 것인지 확인하는 코드
			boolean end = true;
			for (int i = 0; i < size; i++) {
				if (check[i] == 0) { // 0이 하나라도 있으면 단어가 아직 다 안 벗겨졌다는 의미 
					end = false;
					break; // 이 break는 이 for반복문에 대한 break
				}
			}

			if (end == true) { // 단어를 못 맞췄다는 의미
				System.out.println("맞추기 실패");
				break; // 이 break는 while(true)문에 대한 break
			}

			//// 입력받는 부분 / 정답이 아닌 경우 글자를 하나씩 벗김 / 정답처리 
			System.out.println("영어단어를 입력하세요. : ");
			String userReply = scanner.next();

			if (userReply.equals(word) == false) { // 사용자가 입력한 값이 정답이 아니면,
				score -= 5;

				while (true) {
					int r = ran.nextInt(size);
					if (check[r] == 0) {
						check[r] = 1;
						// 똑같은 글자는 다 공개해야 하기 때문에 for문 돌림
						for (int i = 0; i < size; i++) {
							// 질문 : 왜 equals가 아닌 == 으로 비교하는가? 
							// 답변 : String 형식의 홍길동이 2개 있다고 할 때, 실제 값은 같으나 주소값 비교시 서로 다르다고 출력함.
							// 그래서 String 형식을 비교할 때는 주소값을 비교하는 == 이 아닌 equals를 쓰는게 맞음
							// char처럼 하나에 담기는 것은 주소값을 비교하는 == 을 사용 
							if (word.charAt(i) == word.charAt(r)) {
								check[i] = 1; 
							}
						}
						break; // 중복까지 확인 후 break
					}
				}

			} else if (userReply.equals(word) == true) {
				System.out.println("정답입니다!");
				System.out.println("점수 : " + score);
				break;
			}

		}

	}

}
