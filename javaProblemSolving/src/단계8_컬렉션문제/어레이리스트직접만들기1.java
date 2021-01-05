package 단계8_컬렉션문제;

import java.util.Scanner;

class MyList {

	int[] arr;
	int count;

	void print() {
		System.out.print("[");
		for (int i = 0; i < count; i++) {
			System.out.print(arr[i]);
			if (i != count - 1) {
				System.out.print(",");
			}
		}
		System.out.println("]");
	}

	void add(int value) {
		if (count == 0) {
			arr = new int[count + 1];
		} else if (count > 0) {
			int[] temp = arr;
			arr = new int[count + 1];

			for (int i = 0; i < count; i++) {
				arr[i] = temp[i];
			}
			temp = null;
		}
		arr[count] = value;
		count += 1;
	}

	void add(int index, int value) {
		if (count == 0) {
			arr = new int[count + 1];
		} else if (count > 0) {
			int[] temp = arr;
			arr = new int[count + 1];

			int j = 0;
			for (int i = 0; i < count; i++) {
				if (i != index) {
					arr[i] = temp[j];
					j += 1;
				}
			}
			temp = null;
		}
		arr[index] = value;
		count += 1;
	}

	void remove(int index) {
		if (count == 1) {
			arr = null;
		} else if (count > 1) {
			int[] temp = arr;
			arr = new int[count - 1];

			int j = 0;
			for (int i = 0; i < count; i++) {
				if (i != index) {
					arr[j] = temp[i];
					j += 1;
				}
			}
			temp = null;
		}
		count -= 1;
	}

	int size() {
		return count;
	}

	int get(int index) {
		return arr[index];
	}

	void set(int index, int value) {
		arr[index] = value;
	}

	void clear() {
		arr = null;
		count = 0;
	}

}

public class 어레이리스트직접만들기1 {

	public static void main(String[] args) {

		MyList list = new MyList();
		Scanner scan = new Scanner(System.in);

		while (true) {
			list.print();

			System.out.println("1.추가  2.삭제  3.삽입  4.종료");
			int sel = scan.nextInt();

			// 1. 추가
			if (sel == 1) {
				System.out.println("추가할 숫자 입력 : ");
				int num = scan.nextInt();
				list.add(num);

				// 2. 삭제
			} else if (sel == 2) {
				list.print();
				System.out.println("삭제할 인덱스 입력 ");
				int index = scan.nextInt();

				if (list.size() <= 0) {
					continue;
				}
				if (index < 0) {
					continue;
				}
				if (index >= list.size()) {
					continue;
				}
				list.remove(index);

				// 3. 삽입
			} else if (sel == 3) {
				list.print();
				System.out.println("삽입할 위치 입력 : ");
				int pos = scan.nextInt();
				System.out.println("삽입할 값 입력 : ");
				int value = scan.nextInt();
				list.add(pos, value);

				// 4. 종료
			} else if (sel == 4) {
				System.out.println("종료");
				break;
			}

		}
		scan.close();

	}

}
