package 단계5_배열문제;

import java.util.Arrays;

/*
 * [ 문제 ] 
 * 다음은 학생 정보와 성적이 담긴 데이터이다.
 * 전체 성적이 1등인 학생 이름 출력하기.
 * student ==> 1.id 2.주소 3.번호
 * score ==> 1.번호 2.국어 3.수학 4.영어 점수
 * 
 * 
 * [ 풀이 방법 ]
 * 
 * 1) score 배열에서 국어, 수학, 영어 점수를 합친 후 새로운 배열에 넣는다.
 * 2) 가장 높은 점수를 알아내기 위해 점수끼리 비교한다.
 * 3) 가장 높은 점수를 받은 학생의 번호 인덱스를 알아낸 후, student 배열에서 찾아 출력한다
 * 
 */

public class 문자열심화문제1 {

	public static void main(String[] args) {
		
		 String [][] student = {
	                {"aaa","신촌","1001"}, 
	                {"bbb","강남","1002" },
	                {"ccc" ,"대치", "1003"} , 
	                {"ddd" , "강동","1004"}};
	        
	     String[][] score = {
	                {"1001" ,"100" ,"20" , "30"},
	                {"1002" ,"10" ,"60" , "60"},
	                {"1003" ,"23" ,"63" , "31"},
	                {"1004" ,"45" ,"30" , "35"},
	     };
	 
	    
	     int[] scoreSum = new int[score.length];
	     
	     for (int i=0; i<score.length; i++) {
	    	 for (int j=0; j<score.length-1; j++) {
	    		 scoreSum[i] += Integer.parseInt(score[i][j+1]);
	    	 }
	     }
	     System.out.println(Arrays.toString(scoreSum));
	        
	     int maxScore = 0;
	     int maxIdx = 0;
	     
	     for (int i=0; i<scoreSum.length; i++) {
	    	 if (maxScore < scoreSum[i]) {
	    		 maxScore = scoreSum[i];
	    		 maxIdx = i;
	    	 }
	     }

	     System.out.println("가장 높은 점수를 받은 학생 : " + student[maxIdx][0]);
	     
	}

}






