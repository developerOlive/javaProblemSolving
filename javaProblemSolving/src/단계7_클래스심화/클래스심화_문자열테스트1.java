package 단계7_클래스심화;


class Student3 {

	String name;
	int score;

	void print() {
		System.out.println(name + " : " + score);
	}

}

public class 클래스심화_문자열테스트1 {

	public static void main(String[] args) {
		
		String data = "3\n";
		data += "김영희/30\n";
		data += "이만수/40\n";
		data += "이철민/60";

		// 문제1) data에 있는 내용을 잘라서 student 배열에 저장 후 출력하기 
		
		/*
		 * [ 풀이 ]
		 * 1. data를 "\n" 기준으로 잘라서 배열에 저장 (배열 변수명 : dataDetach)  
		 * 2. size 변수에 dataDetach[0] 저장 
		 * 3. 학생 3명의 공간을 만들고 for문을 돌려 실체화 시킴 
		 * 4. for문 돌려, "\n"을 기준으로 잘랐던 배열의 각 데이터를 "/"를 기준으로 다시 자름 
		 * 5. "/"를 기준으로 자른 후 그 데이터의 0번째 인덱스의 값을 각 학생의 name부분에 저장하고, 1번째 인덱스의 값을 각 학생의 score부분에 저장 
		 * 6. 출력 
		 */
		
		
		String[] dataDetach = data.split("/");
		int size = Integer.parseInt(dataDetach[0]); 
		
		Student3[] student = new Student3[size];
		for (int i=0; i<size; i++) {
			student[i] = new Student3();
		}
		
		for (int i=0; i<size; i++) {
			String[] dataDetach2 = dataDetach[i].split("/");
			student[i].name = dataDetach2[0];
			student[i].score = Integer.parseInt(dataDetach2[1]);
			
			student[i].print();
		}
		
		// 문제2) 꼴등 삭제 후 다시 data에 저장

		/*
		 * [ 풀이 ] 
		 * 1. minScore과 minIdx 초기화 시킴
		 * 2. for문을 돌려 가장 작은 점수를 찾고, 해당 인덱스를 minIdx에 저장
		 * 3. 현재 학생 배열을 tempSt 배열에 옮겨 저장 
		 * 4. 학생 수를 한 명 줄여 student 배열 다시 생성
		 * 5. j라는 변수를 이용해 minIdx에 해당하는 데이터를 제외한 값들을 tempSt배열에서 student배열로 저장 
		 * 6. size - 1 처리 
		 * 7. 출력
		 */
		
		int minScore = 0;
		int minIdx = 0;
		
		for (int i=0; i<size; i++) {
			if (student[i].score < minScore) {
				minScore = student[i].score;
				minIdx = i;
			}
		}
		
		Student3[] tempSt = student;
		student = new Student3[size-1];
		
		int j=0;
		for (int i=0; i<size; i++) {
			if (i != minIdx) {
				student[j] = tempSt[i];
			}
		}
		size = size - 1;
		
		for (int i=0; i<size; i++) {
			student[i].print();
		}
		
	}

}
