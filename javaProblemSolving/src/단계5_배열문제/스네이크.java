package 단계5_배열문제;

import java.util.Scanner;

/* 
[ 문제 ] 
1. 10x10 배열을 0으로 채운다.
2. 스네이크는 1234로 표시한다.
3. 상하좌우로 이동이 가능하며, 꼬리가 따라온다.
4. 자기 몸과 부딪히지 않도록 예외처리 한다.

[변수 생성] 
스캐너 생성, size 변수에 10 저장 (map 크기), 2차원 배열 map 생성
x와 y배열 각각 크기 4만큼 생성, snake 배열도 크기 4만큼 생성 

[첫 세팅] 1) 0부터 4까지 for문 돌려 x[i] = i로 뱀(1,2,3,4) 생성하고 
2) map[y[i]][x[i]] = i + 1로 맵에 실질적으로 뱀을 넣어줌 

[맵 출력] while-true 반복문 돌리는데, 그 안에서 2중 for문 돌려 map 출력함 

[방향 입력받기] left, right, up , down 사용자가 선택할 수 있도록 스캐너 처리

[y[0], x[0]이동 처리] 
1) nexty, nextx 변수 0으로 초기화 
2) left, right, up, down을 선택했을 때의 상황 처리해주기 

[예외처리] 1) nexty < 0  2) nexty >= size  3) 자기 몸통을 밟았을 때

[꼬리 지우기]

[몸통 이동 (머리 제외)]

[머리 이동]

[뱀 그리기] 
 */

public class 스네이크 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int size = 10; // map 크기
		int[][] map = new int[size][size];

		int[] y = new int[4];
		int[] x = new int[4];
		int[] snake = new int[4];

		// 첫세팅
		for (int i = 0; i < 4; i++) {
			x[i] = i; // 이 부분에서 스네이크 번호가 생성된 것
			map[y[i]][x[i]] = i + 1;
		}

		// 맵 그려서 출력
		while (true) {
			
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println("y[0] : " + y[0]);
			System.out.println("x[0] : " + x[0]);

			System.out.print("[이동] 1)left 2)right 3)up 4)down : ");
			int move = scan.nextInt();

			int nexty = 0; // 다음으로 이동할 자리
			int nextx = 0; // 다음으로 이동할 자리

			// y[0], x[0] 이동 처리
			if (move == 1) {
				nexty = y[0];
				nextx = x[0] - 1;
			} else if (move == 2) {
				nexty = y[0];
				nextx = x[0] + 1;
			} else if (move == 3) {
				nexty = y[0] - 1;
				nextx = x[0];
			} else if (move == 4) {
				nexty = y[0] + 1;
				nextx = x[0];
			}

			// 예외처리
			if (nexty < 0 || nextx < 0) {
				continue;
			}
			if (nexty >= size || nextx >= size) {
				continue;
			}
			// 0이 아니라는 의미 => 자기 몸통을 밟았다는 의미
			if (map[nexty][nextx] != 0) {
				continue;
			}

			// 꼬리 지우기
			int taily = y[y.length - 1];
			int tailx = x[x.length - 1];
			map[taily][tailx] = 0;

			// 몸통 이동하기 (머리 제외)
			for (int i = snake.length - 1; i > 0; i--) {
				y[i] = y[i - 1];
				x[i] = x[i - 1];
			}

			// 머리 이동
			y[0] = nexty;
			x[0] = nextx;

			// 뱀 그리기
			for (int i = 0; i < snake.length; i++) {
				map[y[i]][x[i]] = i + 1;
			}
		}


	}

}
