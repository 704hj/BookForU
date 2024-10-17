package bookForu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Admin {
	private BufferedReader br;
	AdminDAO adminDAO = new AdminDAO();
	/*
	 * 1. 관리자 계정인지 검증을 한다.
	 * 2. 관리자 메뉴를 보여준다.
	 */

	public Admin() throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		boolean isAdmin = false;

		while(!isAdmin) 
		{
			if(validate()) 
			{
				isAdmin = true;
			}
		}

		adminMenu();
	}

	//관리자 확인
	public boolean validate() {

		System.out.println("===============================================");
		System.out.println("관리자");
		System.out.println("===============================================");
		System.out.println("");

		// 아이디
		System.out.print("아이디 : ");

		// 비밀번호
		System.out.print("비밀번호 : ");

		//		if(입력한 아이디랑 비번이 맞으면) {
		//			return true;
		//		}else {
		//			System.out.println("계정이 일치하지 않습니다. 다시 입력해주세요");
		//		return false;
		//		}
	}

	public void adminMenu() throws IOException{
		System.out.println("\r\n===============================================");
		System.out.println("책 For U");
		System.out.println("===============================================\r\n");
		System.out.println("1. 신규 도서 등록");
		System.out.println("2. 도서 목록 확인");
		System.out.println("3. 회원 목록 조회");
		System.out.println("4. 대출 목록 조회");
		System.out.println("5. 종료");

		System.out.print("\r\n👉 ");
		int no = Integer.parseInt(br.readLine());
		//1. 신규 도서 등록
		if(no == 1) {
			adminDAO.insertBook();
		}
		//2. 도서 목록 확인
		else if(no == 2) {
			adminDAO.findBooks();
		}
		//3. 회원 목록 조회
		else if(no == 3) {
			adminDAO.findUsers();
		}
		//4. 대출 목록 조회
		else if(no == 4) {
			adminDAO.findReservations();
		}
		else if(no == 5) {
			
		}

		
		
		
	}
}
