package 단계5_배열문제;

/*
 * [ 문제 ] 아래와 같이 출력하시오.
 * 1   2  3  4  5
 * 10  9  8  7  6
 * 11 12 13 14 15
 * 20 19 18 17 16
 * 21 22 23 24 25
 * 
 * [ 생각해 보기 ] 
 * - 필요한 것 ? 길이 5인 2차원 배열, 시작번호 변수 1로 초기화 
 * - for문을 돌릴 때 생각해 볼 것 : 홀수행과 짝수행을 나눠서 생각해야 함 (조건으로 홀수행과 짝수행을 분리해야 함)
 * - 홀수 행일 때는 2차원 배열에서 번호가 어떻게 증가하는가?
 * - 짝수 행일 때는 2차원 배열에서 번호가 어떻게 증가하는가? 
 * - 출력은 어떻게 할 것인가? (printf "%3d" 이용) 
 * 
 */
 

public class ㄹ배열가로 {

	public static void main(String[] args) {
	
		int[][] arr = new int[5][5];
		int num = 1;
		
		for (int row=0; row<5; row++) {
			// 짝수 행 
			if (row % 2 == 0) {
				for (int col=0; col<5; col++) {
					arr[row][col] = num++;
				}
			// 홀수 행 
			} else {
				for (int col=0; col<5; col++) {
					arr[row][4-col] = num++;
				}
			}
		}
		
		for (int row=0; row<5; row++) {
			for (int col=0; col<5; col++) {
				System.out.printf("%3d", arr[row][col]);
			}
			System.out.println();
		}
		
		
	}

}
