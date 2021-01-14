package 단계8_컬렉션문제;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

class User {
	String id;
	String pw;
	String name;
}

class UserManager {

	static Scanner scan = new Scanner(System.in);

	static Vector<User> userList = new Vector<User>();

	// 기존에 주어진 데이터를 잘라 userList에 저장하는 코드
	static void dataSetting(String data) {
		String[] token = data.split("\n");

		for (int i = 0; i < token.length; i++) {
			User user = new User();

			String[] token2 = token[i].split("/");
			user.id = token2[0];
			user.pw = token2[1];
			user.name = token[2];

			userList.add(user);
		}
	}

	// id가 userList에 존재하는지 확인하는 메서드
	static boolean checkId(String id) {
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).equals(id)) {
				return true;
			}
		}
		return false;
	}

	// id와 pw가 userList의 데이터와 일치하는지 확인하는 메서드
	static boolean checkLogin(String id, String pw) {
		boolean check = false;
		for (int i = 0; i < userList.size(); i++) {
			if (id.equals(userList.get(i).id) && pw.equals(userList.get(i).pw)) {
				check = true;
				return true;
			}
		}
		if (check != true) {
			System.out.println("id와 pw가 맞지 않거나 존재하지 않는 정보입니다.");
		}
		return false;
	}

	// 회원 가입
	static void join() {
		/*
		 * 1) 가입할 id를 입력 받음 
		 * 2) checkId 메서드를 통해 중복된 id인지 체킹 
		 * 3) 중복되지 않은 경우, pw와 id 입력받고 User 객체 생성함
		 * 4) 생성한 User 객체에 id,pw,name 정보 저장하고 userList에 add함 
		 * 5) AccountManager의 openAccount 메서드에 id를 매개변수로 넣어 실행함 
		 * 6) 회원가입 축하 메세지 출력
		 */
		System.out.println("[회웝가입] id 입력 : ");
		String id = scan.next();

		if (checkId(id)) {
			System.out.println("중복된 id 입니다.");
		} else {
			System.out.println("[회원가입] pw 입력 : ");
			String pw = scan.next();
			System.out.println("[회웝가입] 이름 입력 : ");
			String name = scan.next();

			User user = new User();
			user.id = id;
			user.pw = pw;
			user.name = name;

			userList.add(user);
			AccountManager.openAccount(id);
			System.out.println("회원가입을 축하합니다.");
		}
	}

}

class Account {
	String userId;
	String accountId;
	int money;
}

class AccountManager {

	static Scanner scan = new Scanner(System.in);

	static Vector<Account> accountList = new Vector<Account>();

	static void dataSetting(String data) {
		String[] token = data.split("\n");

		for (int i = 0; i < token.length; i++) {
			Account account = new Account();
			String token2[] = token[i].split("/");
			account.userId = token2[0];
			account.accountId = token2[1];
			account.money = Integer.parseInt(token2[2]);

			accountList.add(account);

		}
	}

	// 선택한 회원의 정보만 가져오기 위한 메서드
	static Vector<Account> getPersonalAccount(String id) {

		Vector<Account> personalList = new Vector<Account>();

		for (int i = 0; i < accountList.size(); i++) {
			if (accountList.get(i).userId.equals(id)) {
				personalList.add(accountList.get(i));
			}
		}
		return personalList;
	}

	// 신규 계좌 개설
	static void openAccount(String id) {
		/*
		 * 1) random 클래스 객체화 
		 * 2) acId에 0000-0000-0000 형식으로 랜덤하게 숫자 저장 (for문 이용) 
		 * 3) 생성된 계좌 출력 
		 * 4) Account 객체 생성
		 * 5) 생성한 Account 객체에 accoundId, userId, money 저장한 후accountList에 add함
		 */
		Random ran = new Random();
		String acId = "";

		for (int i = 0; i < 3; i++) {
			int r1 = ran.nextInt(10);
			acId += r1;
			int r2 = ran.nextInt(10);
			acId += r2;
			int r3 = ran.nextInt(10);
			acId += r3;
			int r4 = ran.nextInt(10);
			acId += r4;

			if (i < 3 - 1) {
				acId += "-";
			}
		}

		System.out.println("[계좌 생성]" + id + " : " + acId);
		Account account = new Account();
		account.accountId = acId;
		account.userId = id;
		account.money = 0;
		accountList.add(account);
	}

	// 입금
	static void deposit() {
		/*
		 * 1) id와 pw를 입력받음 
		 * 2) 입력받은 id와 pw가 회원정보 리스트에 있는 것과 일치하면, 입금 계좌번호 목록을 출력함 
		 * 이 때 personalAccount메서드를 이용함 for문을 돌려 personalAccount가 가지고 있는 계좌 목록 출력 
		 * 3) 어떤 계좌를 선택할 지 입력받은 후(sel이라는 변수에 저장), 금액 입력 받음 (inputMoney라는 변수에 저장) 
		 * 4) 기존 금액에 입력받은 금액 더함
		 */

		System.out.println("[입금] id 입력 : ");
		String id = scan.next();
		System.out.println("[입금] pw 입력 : ");
		String pw = scan.next();

		if (UserManager.checkLogin(id, pw)) {
			System.out.println("[입금] 계좌번호 선택 : ");

			Vector<Account> personalAccount = getPersonalAccount(id);

			for (int i = 0; i < personalAccount.size(); i++) {
				System.out.print("[" + i + "]" + personalAccount.get(i).accountId + " : ");
				System.out.println(personalAccount.get(i).money);
			}
			int sel = scan.nextInt();

			System.out.println("[입금] 금액 입력 : ");
			int inputMoney = scan.nextInt();

			personalAccount.get(sel).money += inputMoney;

		}
	}

	// 잔액 조회
	static void checkBalance() {
		/*
		 * 1) id와 pw를 입력 받음
		 * 2) 입력받은 id와 pw가 회원정보 리스트에 있는 것과 일치하면, 입금 계좌번호 목록과 금액을 for문을 활용하여 출력함
		 */
		System.out.println("[조회] id 입력 : ");
		String id = scan.next();
		System.out.println("[조회] pw 입력 : ");
		String pw = scan.next();

		if (UserManager.checkLogin(id, pw)) {
			Vector<Account> personalAccount = getPersonalAccount(id);
			for (int i = 0; i < personalAccount.size(); i++) {
				System.out.print("[" + i + "]" + personalAccount.get(i).accountId + " : ");
				System.out.println(personalAccount.get(i).money);
			}
		}
	}

	// 계좌 추가
	static void addAccount() {
		/*
		 * 1) id와 pw를 입력 받음 
		 * 2) 입력받은 id와 pw가 회원정보 리스트에 있는 것과 일치하면, 
		 * openAccount메서드를 이용해 계좌를 추가하고 추가 되었다는 메세지 출력 
		 * 3) id와 pw가 일치하지 않으면 다시 확인해 달라는 메세지 출력
		 */
		System.out.println("[계좌 추가] id 입력 : ");
		String id = scan.next();
		System.out.println("[계좌 추가] pw 입력 : ");
		String pw = scan.next();

		if (UserManager.checkLogin(id, pw)) {
			openAccount(id);
			System.out.println("위 계좌가 추가되었습니다.");
		} else if (!UserManager.checkLogin(id, pw)) {
			System.out.println("id와 pw를 다시 확인해 주세요. ");
		}
	}

	// 이체
	static void transfer() {
		/*
		 * 1) id와 pw를 입력 받음 
		 * 2) 입력받은 id와 pw가 회원정보 리스트에 있는 것과 일치하면, 계좌번호 목록과 금액을 출력함 
		 * (출력할 때는 getPersonalAccount 메서드와 for문 활용) 
		 * 3) 이체할 계좌 인덱스를 입력 받은 후, 이체할 금액을 입력 받음 
		 * 4)기존 금액에 입력받은 금액을 마이너스 시킴 5) 이체 후 잔액 계좌와 금액 출력
		 */
		System.out.println("[이체] id 입력 : ");
		String id = scan.next();
		System.out.println("[이체] pw 입력 : ");
		String pw = scan.next();

		if (UserManager.checkLogin(id, pw)) {
			System.out.println("[이체] 이체를 진행할 계좌번호를 선택하세요. ");

			Vector<Account> personalAccount = getPersonalAccount(id);
			for (int i = 0; i < personalAccount.size(); i++) {
				System.out.print("[" + i + "]" + personalAccount.get(i).accountId);
				System.out.println(personalAccount.get(i).money);
			}

			int sel = scan.nextInt();

			System.out.println("[이체] 이체할 금액을 입력하세요. : ");
			int transferMoney = scan.nextInt();

			personalAccount.get(sel).money -= transferMoney;

			System.out.println("이체 후 잔액은 다음과 같습니다.");
			System.out.print("[" + sel + "]" + personalAccount.get(sel).accountId);
			System.out.println(personalAccount.get(sel).money);

		} else if (!UserManager.checkLogin(id, pw)) {
			System.out.println("id와 pw를 다시 확인해 주세요. ");
		}

	}

	// 회원 탈퇴
	static void withdrawl() {
		System.out.println("[탈퇴] id 입력 : ");
		String id = scan.next();
		System.out.println("[탈퇴] pw 입력 : ");
		String pw = scan.next();

		//// id 및 pw 삭제
		int delIdx = 0;
		for (int i = 0; i < UserManager.userList.size(); i++) {
			if (UserManager.userList.get(i).id.equals(id) && UserManager.userList.get(i).pw.equals(pw)) {
				delIdx = i;
			}
		}
		UserManager.userList.remove(delIdx);

		//// 계좌 삭제
		for (int i = 0; i < accountList.size(); i++) {
			if (accountList.get(i).userId.equals(id)) {
				accountList.remove(accountList.get(i));
			}
		}
		System.out.println("입력하신 id와 계좌가 모두 삭제되었습니다.");

	}

	// 계좌 삭제
	static void deleteAccount() {
		/*
		 * 1) id와 pw를 입력 받음 
		 * 2) getPersonalAccount메서드를 이용해 입력한 id가 가지고 있는 계좌를 for문을 이용해 출력함
		 * 3) 삭제할 계좌 인덱스를 입력받은 후, 그 인덱스를 이용해 계좌번호를 delAccount라는 변수에 저장함
		 * 4) delAccount와 accoountList에 있는 계좌번호가 동일하면, accountList에서 해당 인덱스를 삭제함 
		 */
		System.out.println("[계좌삭제] id 입력 : ");
		String id = scan.next();
		System.out.println("[계좌삭제] pw 입력 : ");
		String pw = scan.next();

		Vector<Account> personalAccount = getPersonalAccount(id);

		for (int i = 0; i < personalAccount.size(); i++) {
			System.out.print("[" + i + "]" + personalAccount.get(i).accountId + " : ");
			System.out.println(personalAccount.get(i).money);
		}
		System.out.println("[계좌삭제] 삭제할 계좌 인덱스를 입력하세요. : ");
		int sel = scan.nextInt();

		String delAccount = personalAccount.get(sel).accountId;

		for (int i = 0; i < accountList.size(); i++) {
			if (delAccount.equals(accountList.get(i).accountId)) {
				accountList.remove(i);
			}
		}
	}
	
}

class Bank {
	Scanner scan = new Scanner(System.in);
	
	void run() {

        while (true) {
            System.out.println("(1)회원가입  (2)입금 (3)조회 (4)계좌추가 (5)이체 (6)회원탈퇴 (7)계좌삭제 (0)종료");
            int sel = scan.nextInt();
            if (sel == 1) {
                UserManager.join();
            } else if (sel == 2) {
                AccountManager.deposit();
            } else if (sel == 3) {
                AccountManager.checkBalance();
            } else if (sel == 4) {
                AccountManager.addAccount();
            } else if (sel == 5) {
                AccountManager.transfer();
            } else if (sel == 6) {
                AccountManager.withdrawl();
            } else if (sel == 7) {
                AccountManager.deleteAccount();
            } else if (sel == 0) {
                System.out.println("종료");
                break;
            }
        }
	}
}

public class ATM문제_static사용 {

	public static void main(String[] args) {
		
		String userData = "test01/pw1/김철수\n";
		userData += "test02/pw2/이영희\n";
		userData += "test03/pw3/신민수\n";
		userData += "test04/pw4/최상민";

		String accountData = "test01/1111-1111-1111/8000\n";
		accountData += "test02/2222-2222-2222/5000\n";
		accountData += "test01/3333-3333-3333/11000\n";
		accountData += "test03/4444-4444-4444/9000\n";
		accountData += "test01/5555-5555-5555/5400\n";
		accountData += "test02/6666-6666-6666/1000\n";
		accountData += "test03/7777-7777-7777/1000\n";
		accountData += "test04/8888-8888-8888/1000";
		// 1) test01 김철수 는 계좌를 3개 가지고있다.
		// 2) test02 이영희 는 계좌를 2개 가지고있다.
		// 3) test03 신민수 는 계좌를 2개 가지고있다.
		// 4) test04 최상민 은 계좌를 1개 가지고있다.

		UserManager.dataSetting(userData);
		AccountManager.dataSetting(accountData);		
		Bank bank = new Bank();
		bank.run();

	}

}
