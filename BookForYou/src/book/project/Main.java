package book.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import book.project.dao.BookDAO;
import book.project.dao.MemberDAO;
import book.project.dto.BookDTO;
import book.project.dto.MemberDTO;


/*
 * 1. 관리자, 사용자 여부 확인
 */
public class Main {

	//수행해야 할 작업
	//메뉴 : 도서등록,도서목록,회원목록,대출목록,종료
	private BufferedReader br;
	private BookDAO bookDAO = new BookDAO();
	private MemberDAO memberDAO = new MemberDAO();
	
	//생성자
	public Main() {
		try {
			br = new BufferedReader(
					new InputStreamReader(System.in));
			//메뉴호출
			callMenu();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			if(br!=null)try {br.close();}catch(IOException e) {}
		}
	}
	
	/*
	 * 1. 프로젝트명과 관리자, 사용자 선택을 출력한다.
	 * 2. 번호를 입력 받아 해당 페이지로 이동한다. 
	 */
	public void callMenu()throws IOException{
		System.out.println("===============================================");
		System.out.println("책 For U");
		System.out.println("===============================================\r\n");
		System.out.println("1. 관리자");
		System.out.println("2. 사용자");

		System.out.print("\r\n👉 ");
		int no = Integer.parseInt(br.readLine());
		if(no ==1){//관리자
			System.out.println("\r\n");
			Admin admin = new Admin();
			//매서드를 대신해 생성자를 명시하여 코드 간결화
			//admin.main();
			
			
		}else {//사용자
			
		}
	}
	
	public static void main(String[] args) {
		//실질적으로 실행되는 부분, 객체로 생성해서 호출하려고 함
		//->class 내부에서 클래스 이름과 똑같은 이름을 가진 생성자를 호출
		Main main = new Main();
	}
}
