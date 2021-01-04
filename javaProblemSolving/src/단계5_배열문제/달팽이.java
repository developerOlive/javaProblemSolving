package 단계5_배열문제;

/*
 * [ 문제 ]  아래와 같이 출력하시오. (달팽이) 
 * 1     2     3     4    5    
 * 16    17    18    19    6    
 * 15    24    25    20    7    
 * 14    23    22    21    8    
 * 13    12    11    10    9
 * 
 *  >>>>>[ 문제 풀이 ] <<<<<
 * 
 * [초기 세팅]
 * 1. size 변수 생성 (크기: 5) / 2차원 map 배열 생성 (크기: 5) / 2차원 check 배열 생성 (크기: 5)
 * 2. 변수 x, y = 0으로 초기화 / num = 1로 초기화 / dir = 1로 초기화
 * (여기서 dir의 경우 1(우), 2(하), 3(좌), 4(상)을 의미함)  
 * 3. map[y][x] = num 저장 / check[y][x]에 true 저장 
 * 
 * [방향 이동]
 * 0부터 24까지 for문 돌림 
 * 1. 이동할 좌표 yy에 현재위치인 y 저장 / 이동할 좌표 xx에 현재위치인 x 저장 
 * 2. dir이 각각 1,2,3,4인 경우 결과값 처리 
 * 3. (달팽이 형식으로 돌 수 있도록 처리하는 부분) 
 *     --> size보다 이동할 좌표가 더 크거나, 이동할 좌표가 0보다 작은 경우, 이미 갔던 자리인 경우 dir을 + 1처리
 *    : 이동할 좌표 yy에 현재위치인 y 저장 / 이동할 좌표 xx에 현재위치인 x 저장 
 *    : dir == 5면, 다시 dir = 1로 만들기 
 *    : dir이 각각 1,2,3,4인 경우 결과값 처리 
 * 4. num = num + 1처리
 * 5. 다시 현재위치인 y에 이동할 좌표 yy 저장 (x도 마찬가지)  
 * 6. map[y][x] = num;  / check[y][x] = true 처리 
 * 
 * [map 출력] 
 */

public class 달팽이 {

	public static void main(String[] args) {

		int size = 5;
		int[][] map = new int[size][size]; // 판 출력을 위한 배열 
		boolean[][] check = new boolean[size][size]; // 출력할 숫자가 이미 지나갔던 자리인지 확인하기 위한 배열 
		
		int x = 0; // 현재 x좌표 
		int y = 0; // 현재 y좌표 
		int num = 1; // 출력할 숫자 
		int dir = 1; // 방향 : 1(우) / 2(하) / 3(좌) / 4(상)
		
		map[y][x] = num;
		check[y][x] = true;
		
		
		for (int i=0; i<24; i++) {
			
			// 이동할 좌표 yy(xx)에 현재 위치인 y(x) 저장 
			int yy = y;
			int xx = x;
			
			// 방향 변화에 따라 이동할 위치값 변경 
			if (dir == 1) {
				xx = xx + 1;
			} else if (dir == 2) {
				yy = yy + 1; 
			} else if (dir == 3) {
				xx = xx - 1;
			} else if (dir == 4) {
				yy = yy - 1;
			}
			
			// 달팽이 형식으로 돌 수 있도록 처리한 부분
			if (size <= xx || xx < 0 || size <= yy || yy < 0 || check[yy][xx] == true) {
				// 방향 바꿈 
				dir = dir + 1;
				
				// 이동할 좌표에 현재 좌표 저장 
				yy = y;
				xx = x;
				
				// 방향이 4를 넘어 5가 되면, 1로 다시 변경 시킴 
				if (dir == 5) {
					dir = 1;
				}
				
				if (dir == 1) {
					xx = xx + 1;
				} else if (dir == 2) {
					yy = yy + 1; 
				} else if (dir == 3) {
					xx = xx - 1;
				} else if (dir == 4) {
					yy = yy - 1;
				} 

			}
			num += 1;
			
			y = yy;
			x = xx;
			
			map[y][x] = num;
			check[y][x] = true;
			
			
		}
		
		for (int i=0; i<size; i++) {
			for (int j=0; j<size; j++) {
				System.out.printf("%3d", map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		
	}

}
