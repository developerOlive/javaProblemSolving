package 단계8_컬렉션문제;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

class Node {

	int front;
	int back;
	boolean open = false;

}

class MemoryGame {

	// 여기서 new를 하면 정확히 언제 new가 만들어지는지 개발자가 알기 어렵기 때문에
	// new는 메서드 안에서 진행함
	Vector<Node> nodeList;
	int[] data; // 숫자를 저장할 배열
	int size = 10; // 숫자의 개수
	Scanner scan = new Scanner(System.in);

	// 1~5까지의 숫자가 2개씩 있으며 그 숫자들을 data 배열에 저장함
	void dataInit() {
//		int a; // 이 경우처럼 이 위치에서 선언 되어야 변수 a가 스택에 있을 수 있음 
		data = new int[size];
		for (int i = 0; i < size; i++) {
			data[i] = (i + 2) / 2;
		}
	}

	// 배열에 저장된 숫자들을 셔플함
	void shuffle() {
		Random ran = new Random();
		for (int i = 0; i < 1000; i++) {
			int r = ran.nextInt(data.length);
			int temp = data[r];
			data[r] = data[0];
			data[0] = temp;
		}
	}

	// node : 진짜 데이터
	void nodeInit() {
		nodeList = new Vector<>();

		for (int i = 0; i < size; i++) {
			Node node = new Node(); // front와 back을 가지고 있는 클래스를 하나하나 객체화 시킨 것
			node.front = data[i];
			nodeList.add(node);
		}
	}

	void init() {
		dataInit();
		shuffle();
		nodeInit();
	}

	void print() {
		System.out.println();
		System.out.println("-------------------------");
		for (int i = 0; i < size; i++) {
			if (nodeList.get(i).back == 0) {
				System.out.print("[ ]");
			} else {
				System.out.print("[" + nodeList.get(i).back + "]"); // back을 보여주는 것
			}
		}
		System.out.println();
		System.out.println("-------------------------");
		System.out.println();
	}

	void update() {

		while (true) {

			// 처음에 노드 뒷면 출력
			print();

			System.out.println("인덱스 1 입력 : ");
			int sel1 = scan.nextInt();
			System.out.println("인덱스 2 입력 : ");
			int sel2 = scan.nextInt();

			// 사용자가 입력한 인덱스에 해당하는 카드가 둘 다 뒤집어진 상태가 아닌 경우에만 카드를 뒤집음
			// (이미 뒤집어진 카드를 또 뒤집을 경우 예외처리를 하기 위해 이 조건문을 사용함)
			if (nodeList.get(sel1).open == false && nodeList.get(sel2).open == false) {
//				System.out.println("체킹 : " + nodeList.get(sel1).front );
				nodeList.get(sel1).back = nodeList.get(sel1).front; // 카드를 뒤집음
				nodeList.get(sel2).back = nodeList.get(sel2).front; // 카드를 뒤집음
			} else {
				continue;
			}

			// 사용자로부터 인덱스 입력 받은 후 상황을 출력
			print();

			// 1초 지연 시킨 후 다시 while문 처음으로 돌아감
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 뒤집은 카드의 숫자가 서로 다른 경우
			if (nodeList.get(sel1).back != nodeList.get(sel2).back) {
				nodeList.get(sel1).back = 0; // 카드 뒷면으로 원상복귀
				nodeList.get(sel2).back = 0; // 카드 뒷면으로 원상복귀
				nodeList.get(sel1).open = false;
				nodeList.get(sel2).open = false;

			} else {
				nodeList.get(sel1).open = true;
				nodeList.get(sel2).open = true;
			}

		}
	}

}

public class 기억력게임 {

	public static void main(String[] args) {

		MemoryGame mg = new MemoryGame();
		mg.init();
		mg.update();

	}

}
