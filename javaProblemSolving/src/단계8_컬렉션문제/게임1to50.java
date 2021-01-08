package 단계8_컬렉션문제;

/*
 * [ class : Node1to50 ] 
 * - int front, int back, printNode() 메서드  
 * 
 * [ class : Manager1to50 ]
 * - 스캐너 생성, Vector<Node1to50[]> nodeList 초기화, 2차원 배열의 frontData와 backData 선언, 
 *   size = 5로 초기화, num = 1로 초기화 
 * 
 * - ( void dataInit() 메서드 )
 * 	  : size크기만큼 frontData를 new로 생성
 * 	  : 2중 for문을 돌려 frontData[i][j]에 num을 대입하고, num += 1 처리 (앞면에 1~25까지 숫자 저장)  
 * 	  : size크기만큼 backData를 new로 생성
 * 	  : 2중 for문을 돌려 backData[i][j]에 num을 대입하고, num += 1 처리 (뒷면에 26~50까지 숫자 저장) 
 * 
 * - ( void dataShuffle() 메서드 ) 
 *    : 랜덤 클래스 생성 
 * 	  : 0부터 size까지 2중 for문 돌려 yR, xR에 랜덤숫자(0~9까지 중 하나) 대입 후
 *      temp를 활용하여 frontData[0][0]과 data[yR][xR]을 스위치하는 형식으로 진행함 
 *      backData 배열도 마찬가지로 스위치 
 *
 * - ( void nodeInit() 메서드 ) 
 *   : 선언해 두었던 nodeList를 new로 생성함 
 *   : for문을 돌려 Node1to50[] temp = new Node1to50[size] 만들고 
 *     node를 하나씩 객체화 시켜서 
 *     node.front = frontData[i][j] 대입하고 node.back = backData[i][j] 대입 
 *     그리고 생성한 node를 temp에 넣은 후
 *     nodeList에 temp를 add함 
 * 
 * - ( void printNodeList() 메서드 ) 
 *   : 2중 for문 돌려 nodeList.get(i)[j].printNode() 출력 
 * 
 * 
 *  - ( void init() 메서드 ) 
 *    : dataInit(), dataShuffle(), nodeInit() 실행 
 *    
 *  - ( void selectCard() 메서드 ) 
 *    : num = 1 대입, 
 *    : while - true문 안에서
 *      printNodeList() 메서드 실행 
 *      인덱스 1과 인덱스 2 입력 받음 
 *      if문) num == nodeList.get(sel1)[sel2].front일 때 
 *           num <= 25면 뒷면으로 뒤집고 num > 25면 0을 대입함 
 *      num += 1 처리 
 *    : num == 51이면 break 처리 
 *     
 * 
 *  [ 메인 메서드 ]
 *   Manager1to50 객체화 시킨 후, init() 메서드와 selectCard() 메서드 실행 
 *    
 */

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

class Node1to50 {

	int front;
	int back;

	void printNode() {
		System.out.print(front + "\t");
//		System.out.print("b" + back + "\t");
	}
}

class Manager1to50 {

	Scanner scan = new Scanner(System.in);
	Vector<Node1to50[]> nodeList;
	int frontData[][];
	int backData[][];
	int size = 5;
	int num = 1;

	void dataInit() {
		frontData = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				frontData[i][j] = num;
				num += 1;
			}
		}

		backData = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				backData[i][j] = num;
				num += 1;
			}
		}
	}

	void dataShuffle() {
		Random ran = new Random();

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {

				int yR = ran.nextInt(size);
				int xR = ran.nextInt(size);

				int tmp = frontData[0][0];
				frontData[0][0] = frontData[yR][xR];
				frontData[yR][xR] = tmp;

				int yR2 = ran.nextInt(size);
				int xR2 = ran.nextInt(size);

				tmp = backData[0][0];
				backData[0][0] = backData[yR][xR];
				backData[yR][xR] = tmp;
			}
		}

	}

	void nodeInit() {
		nodeList = new Vector<>();

		for (int i = 0; i < size; i++) {
			Node1to50[] temp = new Node1to50[size];

			for (int j = 0; j < size; j++) {
				Node1to50 node = new Node1to50();
				node.front = frontData[i][j];
				node.back = backData[i][j];
				temp[j] = node;
			}
			// 질문) 왜 temp를 사용해서 nodeList에 add 하는지? (왜 node로 바로 넣지 않는지?)
			// 답변) nodeList는 1차원 배열 여러 개로 구성되어있는데, nodeList는 객체 하나임
			// nodeList에 add하려면 1차원 형식을 add 할 수 있음
			nodeList.add(temp);
		}
	}

	void printNodeList() {
		System.out.println();
		System.out.println("-----------------------------------------");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				nodeList.get(i)[j].printNode();
			}
			System.out.println();
		}
		System.out.println("-----------------------------------------");
		System.out.println();
	}

	void init() {
		dataInit();
		dataShuffle();
		nodeInit();
	}

	void selectCard() {

		num = 1;

		while (true) {

			printNodeList();

			System.out.println("인덱스 1 입력 : ");
			int sel1 = scan.nextInt();
			System.out.println("인덱스 2 입력 : ");
			int sel2 = scan.nextInt();

			if (num == nodeList.get(sel1)[sel2].front) {
				if (num <= 25) {
					nodeList.get(sel1)[sel2].front = nodeList.get(sel1)[sel2].back;
				} else {
					nodeList.get(sel1)[sel2].front = 0;
				}
				num += 1;
			}

			if (num == 51) {
				break;
			}
		}

	}

}

public class 게임1to50 {

	public static void main(String[] args) {

		Manager1to50 manager1to50 = new Manager1to50();
		manager1to50.init();
		manager1to50.selectCard();

	}

}
