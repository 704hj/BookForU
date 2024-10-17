package bookForu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/*
 * 1. 관리자, 사용자 여부 확인
 */
public class Main {

	//int a = 0;
	private BufferedReader br;



	//생성자
	public Main() {
		//int b = 0;

		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			callMenu();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(br!=null)try {br.close();}catch(IOException e) {}
		}
	}

	//메서드
	public void callMenu() throws IOException{
		//int c = 0;
		//a = 1;
		System.out.println("===============================================");
		System.out.println("책 For U");
		System.out.println("===============================================\r\n");
		System.out.println("1. 관리자");
		System.out.println("2. 사용자");

		System.out.print("\r\n👉 ");

		int no = Integer.parseInt(br.readLine());
		if(no==1) {//관리자
			//관리자 클래스 객체 생성
			Admin admin = new Admin();
		}else if(no==2)//사용자
		//회원가입 또는 로그인 선택 가능
		{
			//사용자 클래스 객체 생성
			Customer customer = new Customer();
		}
		

	}

	//실행 함수
	public static void main(String[] args) {
		//int d = 0;
		//Main m = new Main();
		//m.a = 1;
		//a = 1;
		//생성자 호출시 객체로 불러와야함

		Main main = new Main();
	}



}
