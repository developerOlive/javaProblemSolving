package 단계7_클래스심화;

class Test {

	int num;
	int size;
	char[] data;

	void print() {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
		System.out.println();
	}

}

public class 클래스심화_문자열테스트2 {

	public static void main(String[] args) {
		String data = "6\n";
		data += "0/2/a/b\n";
		data += "1/3/a/b/c\n";
		data += "2/5/a/b/c/d/e\n";
		data += "3/4/a/b/c/d\n";
		data += "4/3/a/b/c\n";
		data += "5/1/a";

		// 문제) data의 정보를 클래스 배열에 저장하기

		// 1. "\n"을 기준으로 잘라서 배열에 저장
		// 2. dataSize 변수에 6 저장
		// 3. 줄의 수에 맞춰 객체를 실체화 시킴
		// 4. for문을 돌려, "\n"을 기준으로 잘랐던 배열의 각 데이터를 "/"를 기준으로 더 자름
		// 5. "/"를 기준으로 자른 후 그 데이터의 0번째 인덱스의 값을 각 객체의 num부분에 저장하고, 1번째 인덱스의 값을 각 학생의
		// size부분에 저장
		// 6. char[] data의 공간을 각 객체의 size 크기에 맞게 만듦

		String[] dataDetach = data.split("\n");
		int dataSize = Integer.parseInt(dataDetach[0]);

		Test[] test = new Test[dataSize];
		for (int i = 0; i < dataSize; i++) {
			test[i] = new Test();
		}

		for (int i = 0; i < dataSize; i++) {
			String[] dataDetach2 = dataDetach[i + 1].split("/");

			test[i].num = Integer.parseInt(dataDetach2[0]);
			test[i].size = Integer.parseInt(dataDetach2[1]);
			test[i].data = new char[test[i].size];

			int k = 2;
			for (int j = 0; j < test[i].data.length; j++) {
				test[i].data[j] = dataDetach2[k].charAt(0);
				k += 1;
			}

		}
		
		for (int i=0; i<dataSize; i++) {
			test[i].print();
		}

	}

}
