package 단계5_배열문제;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class 파일로드하기 {

	public static void main(String[] args) {
		
		String[] ids = null; // 배열은 null로 초기화 함 
		String[] pws = null;
		int[] moneys = null;

		String fileName = "fileTest02.txt"; // 불러올 파일명 

		File file = new File(fileName); // file 객체 생성 : file이 있는지 없는지 확인하기 위함 

		
		String data = ""; // "";이 아니면 null이 들어가있음 // null은 null이 들어있는 것임 // 문자는 ""로 초기화 함 

		// file이 존재하면, 
		if (file.exists()) {
			// try 안에만 안 있으면 됨 (try 밖에서는 못 써서 재활용이 안 됨)
			FileReader fr = null; // FileReader은 파일을 읽어오는 클래스 (char 배열로만 가져옴) 
			BufferedReader br = null; // BufferreadReader은 char 배열을 string으로 변환하기 위해서 필요한 클래스 
			
			try {
				fr = new FileReader(file); // 파일을 char 배열로 가져옴 
				br = new BufferedReader(fr); // char 배열로 가져온 것을 string으로 변환 
				
				while (true) {
					// readLine() : BufferedReader 클래스가 가지고 있는 기능 
					String line = br.readLine(); // 한 줄 읽어와서 data에 저장 // readLine은 한줄만 읽어올 수 있음. 만약 여러줄이면 반복문 사용해야 함
					// 만약 데이터가 없으면 반복문 종료 
					if (line == null) {
						break;
					}
					data += line; // \n으로 분리되있는 것을 다시 이어붙임
					data += "\n";
				}
				// 마지막 한 줄 삭제 
				data = data.substring(0, data.length() - 1); 
				System.out.println("data : " + data);
				
				
				// 배열에 넣기위해 자르는 코드 (다 붙어있던 데이터를 잘라서 배열에 넣는다는 의미) 
				String[] temp = data.split("\n"); // 줄 분리 시킨 것 
				
				
				int size = temp.length; // 공간 만들기 위한 사이즈 

				ids = new String[size];
				pws = new String[size];
				moneys = new int[size];

				// 슬러시로 구분되있는 것을 하나씩 자름 
				for (int i = 0; i < size; i++) {
					String[] info = temp[i].split("/");
					ids[i] = info[0];
					pws[i] = info[1];
					moneys[i] = Integer.parseInt(info[2]);
				}

				for (int i = 0; i < size; i++) {
					System.out.println(ids[i] + ":" + pws[i] + ":" + moneys[i]);
				}

				fr.close();
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


	}

}
