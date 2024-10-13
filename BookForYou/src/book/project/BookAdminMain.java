package book.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BookAdminMain {
	//수행해야 할 작업
	//메뉴 : 도서등록,도서목록,회원목록,대출목록,종료
	private BufferedReader br;
	private BookDAO dao;
	//생성자
	public BookAdminMain() {
		try {
			br = new BufferedReader(
					new InputStreamReader(System.in));
			dao = new BookDAO();
			//메뉴호출
			callMenu();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//자원정리
			if(br!=null)try {br.close();}catch(IOException e) {}
		}
	}
	//메뉴
	public void callMenu()throws IOException{
		while(true) {
			System.out.print("1.도서등록,2.도서목록,3.회원목록,4.대출목록,5.종료>");
			try {
				int no = Integer.parseInt(br.readLine());
				if(no==1) {//상품등록
					System.out.print("도서명:");
					String bk_name = br.readLine();
					System.out.print("도서 분류:");
					String bk_category = br.readLine();
					dao.insertBook(bk_name,bk_category);					
				}else if(no==2) {//도서목록
					dao.selectBooks();
				}else if(no==3) {//회원목록
					dao.selectMembers();
				}else if(no==4) {//대출목록
					dao.selectOrders();
				}else if(no==5) {//종료
					System.out.println("프로그램 종료");
					break;
				}else {
					System.out.println("잘못 입력했습니다.");
				}
			}catch(NumberFormatException e) {
				System.out.println("[숫자만 입력 가능]");
			}
		}
	}
	public static void main(String[] args) {
		new BookAdminMain();
	}
}
