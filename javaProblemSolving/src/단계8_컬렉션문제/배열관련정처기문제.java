package 단계8_컬렉션문제;

import java.util.ArrayList;

class Member {

	private int customerNo; // 회원번호
	private String customerName; // 회원성명
	private String phone; // 회원전화
	private String address; // 통신사
	private String joinDate; // 가입일자
	private String grade; // 고객등급
	private String city; // 거주도시

	public int getcustomerNo() {
		return customerNo;
	}

	public void setcustomerNo(int customerNo) {
		this.customerNo = customerNo;
	}

	public String getcustomerName() {
		return customerName;
	}

	public void setcustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Member() {
	}

	public Member(int customerNo, String customerName, String phone, String address, String joinDate, String grade,
			String city) {
		this.customerNo = customerNo;
		this.customerName = customerName;
		this.phone = phone;
		this.address = address;
		this.joinDate = joinDate;
		this.grade = grade;
		this.city = city;
	}
}

class Money {
	int customerNo; // 회원번호
	int saleNo; // 판매번호
	int cost; // 단가
	int amount; // 수량
	int price; // 가격(매출)
	String code; // 상품코드
	String saleDate; // 판매일자

	public Money() {
	}

	public Money(int customerNo, int saleNo, int cost, int amount, int price, String code, String saleDate) {
		this.customerNo = customerNo;
		this.saleNo = saleNo;
		this.cost = cost;
		this.amount = amount;
		this.price = price;
		this.code = code;
		this.saleDate = saleDate;
	}
}

// 문제를 풀기 위해 
// 회원번호, 회원이름, 합계금액을 변수로 가지고 있고 생성자를 포함하고 있는 Result 클래스 생성 
class Result {
	private int customerNo;
	private String customerName;
	private int total;

	public int getcustomerNo() {
		return customerNo;
	}

	public void setcustomerNo(int customerNo) {
		this.customerNo = customerNo;
	}

	public String getcustomerName() {
		return customerName;
	}

	public void setcustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Result(int customerNo, String customerName, int total) {
		this.customerNo = customerNo;
		this.customerName = customerName;
		this.total = total;
	}

}

class Manager {

	ArrayList<Member> memberList = new ArrayList<Member>();

	ArrayList<Money> moneyList = new ArrayList<Money>();

	// resultList 생성
	ArrayList<Result> resultList = new ArrayList<Result>();

	void init() {
		memberList.add(new Member(100001, "김행복", "010-1111-2222", "SK", "20151202", "A", "01"));
		memberList.add(new Member(100002, "이축복", "010-1111-3333", "SK", "20151206", "B", "01"));
		memberList.add(new Member(100003, "장믿음", "010-1111-4444", "SK", "20151001", "B", "30"));
		memberList.add(new Member(100004, "최사랑", "010-1111-5555", "SK", "20151113", "A", "30"));
		memberList.add(new Member(100005, "진평화", "010-1111-6666", "SK", "20151225", "B", "60"));
		memberList.add(new Member(100006, "차공단", "010-1111-7777", "SK", "20151211", "C", "60"));

		moneyList.add(new Money(100001, 20160001, 500, 5, 2500, "A001", "20160101"));
		moneyList.add(new Money(100001, 20160002, 1000, 4, 4000, "A002", "20160101"));
		moneyList.add(new Money(100001, 20160003, 500, 3, 1500, "A008", "20160101"));
		moneyList.add(new Money(100002, 20160004, 2000, 1, 2000, "A004", "20160102"));
		moneyList.add(new Money(100002, 20160005, 500, 1, 500, "A001", "20160103"));
		moneyList.add(new Money(100003, 20160006, 1500, 2, 3000, "A003", "20160103"));
		moneyList.add(new Money(100004, 20160007, 500, 2, 1000, "A001", "20160104"));
		moneyList.add(new Money(100004, 20160008, 300, 1, 300, "A005", "20160104"));
		moneyList.add(new Money(100004, 20160009, 600, 1, 600, "A006", "20160104"));
		moneyList.add(new Money(100004, 20160010, 3000, 1, 3000, "A007", "20160106"));
	}

	// memberList에 있던 정보들 중 회원번호, 회원 이름을 가져와 resultList에 add하면서 넣어줌 
	// (total은 아직 계산 전이기 때문에 0으로 통일)
	void initResult() {
		for (int i = 0; i < memberList.size(); i++) {
			resultList.add(new Result(memberList.get(i).getcustomerNo(), memberList.get(i).getcustomerName(), 0));

		}
	}

	//// total(합계금액) 계산 부분
	// resultList에 있는 회원 번호와 moneyList에 있는 회원 번호가 일치하면, 
	// resultList의 total 부분에 그 값을 누적하여 저장함
	void sumPrice() {
		for (int i = 0; i < resultList.size(); i++) {
			for (int j = 0; j < moneyList.size(); j++) {
				if (resultList.get(i).getcustomerNo() == moneyList.get(j).customerNo) {
					resultList.get(i).setTotal(moneyList.get(j).price + resultList.get(i).getTotal());
				}
			}
		}
	}

	//// 내림차순 정렬 코드
	void descending() {
		for (int i = 0; i < resultList.size(); i++) {
			for (int j = 0; j < resultList.size(); j++) {
				if (resultList.get(i).getTotal() > resultList.get(j).getTotal()) {

					Result temp = resultList.get(i);
					resultList.set(i, resultList.get(j));
					resultList.set(j, temp);

				}
			}
		}
	}

	// 회원번호, 회원이름, 합계금액 순으로 출력
	void print() {
		for (int i = 0; i < resultList.size(); i++) {
			if (resultList.get(i).getTotal() != 0) {
				System.out.println(resultList.get(i).getcustomerNo() + " / " + resultList.get(i).getcustomerName() + " / "
						+ resultList.get(i).getTotal());
			}

		}
	}

	void solution() {
		initResult();
		sumPrice();
		descending();
		print();
	}

}

public class 배열관련정처기문제 {

	public static void main(String[] args) {
		

		Manager mg = new Manager();
		mg.init();
		
		/*
		   [문제] 아래와 같이 출력  매출(price) 의 합계 + 내림차순 
		  
			100001	김행복		8000
			--------------------------------
			100004	최사랑		4900
			--------------------------------
			100003	장믿음		3000
			--------------------------------
			100002	이축복		2500
			--------------------------------
		 */
		

		// 정답 
		mg.solution();
		

	}

}
