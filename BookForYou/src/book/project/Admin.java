package book.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import book.project.dao.BookDAO;
import book.project.dao.CustomerDAO;
import book.project.dao.MemberDAO;
import book.project.dao.ReservationDAO;
import book.project.dto.BookDTO;
import book.project.dto.CustomerDTO;
import book.project.dto.MemberDTO;
import book.project.dto.ReservationDTO;

public class Admin {
	private BufferedReader br;
	private MemberDAO memberDAO = new MemberDAO();
	private BookDAO bookDAO = new BookDAO();
	private CustomerDAO customerDAO = new CustomerDAO();
	private ReservationDAO reservationDAO = new ReservationDAO();
	
	/*
	 * 1. 관리자 계정인지 검증을 한다.
	 * 2. 관리자 메뉴를 보여준다.
	 */
	public void main() throws IOException {
		br = new BufferedReader(
				new InputStreamReader(System.in));
		
		findReservations();
		if (validate()) {
			menu();
		}
	}
	
	/*
	 * 1. 반복문에서 아이디를 입력받는다.
	 * 2. 비밀번호를 입력받는다.
	 * 3. 해당 member 정보를 validateMember로 검증한다.
	 * 4. 검증 성공하면 for문 빠져나온다.
	 */
	public boolean validate() throws IOException {
		MemberDTO member = new MemberDTO();
		
		while (true) {
			System.out.println("===============================================");
			System.out.println("관리자");
			System.out.println("===============================================");
			System.out.println("");
			
			// 아이디
			System.out.print("아이디 : ");
			member.setId(br.readLine());
			
			// 비밀번호
			System.out.print("비밀번호 : ");
			member.setPassword(br.readLine());
			
			// 검증 결과
			if (memberDAO.validateMember(member)) {
				return true;
			}
		}
	}

	/*
	 * 1. 메뉴를 보여준다.
	 */
	public void menu() throws IOException {
		while (true) {
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
			
			if(no == 1){
				insertBook();
			} else if (no == 2){
				findBooks();
			} else if (no == 3){
				findUsers();
			} else if (no == 4){
				findReservations();
			} else if (no == 5) {
				return;
			}
		}
	}
	

	/*
	 * 1.신규 도서 등록창 뜬다.
	 * 2.도서명과 카테고리 입력창 뜬다.
	 * 3.입력을 받은 후 도서가 등록되었습니다. 라는 문구가 뜬다
	 */
	public void insertBook() throws IOException {
		BookDTO book = new BookDTO();
	
		System.out.println("\r\n===============================================");
		System.out.println("신규 도서 등록");
		System.out.println("===============================================\r\n");
		
		System.out.print("도서명 : ");
		book.setName(br.readLine());
		System.out.print("카테고리 : ");
		
		book.setCategory(br.readLine());
		
		bookDAO.insertBook(book);
		
	}
	
	/*
	 * 1.도서 목록 확인창이 뜬다.
	 * 2.도서번호 도서명 분류 등록일이 20개씩 뜬다.
	 * 3.20개 마다 페이지가 넘어간다
	 */
	public void findBooks() throws IOException {
		ArrayList<BookDTO> bookList = bookDAO.selectBooks();
		int index = 0;
		int max = bookList.size() / 10;
		
		while (true) {
			System.out.println("\r\n===============================================");
			System.out.println("도서 목록");
			System.out.println("===============================================\r\n");
			System.out.println("도서 번호 도서명 작가 카테고리 등록일");
			
			for (int i=10 * index; i < 10 * (index + 1); i++) {
				if (i >= bookList.size()) {
					break;
				}
				
				BookDTO book = bookList.get(i);
//				D2coding 글꼴
				System.out.printf("%02d %-10s %s %s %s\n"
						, book.getNum()
						, book.getName()
						, book.getAuthor()
						, book.getCategory()
						, book.getRegdate());
			};
			

			System.out.printf("%d/%d\n", index + 1, max + 1);
			
			System.out.println("<이전페이지|다음페이지>");
			System.out.println("1. 검색");
			System.out.println("B. 뒤로가기");
			
			System.out.print("\r\n👉 ");
			String input = br.readLine();
			
			if(input.equals("1")){
				System.out.println("검색");
			} else if (input.equals("<")){
				if (index == 0) {
					System.out.println("이전 페이지 없음.");
				} else {
					index -= 1;
				}
			} else if (input.equals(">")){
				if (index == max) {
					System.out.println("다음 페이지 없음.");
				} else {
					index += 1;
				}
			} else {
				return;
			}
		}
		
	}
	/*
	 * 1. 회원목록이 보인다
	 * 2. 회원번호, 이름, 아이디, 전화번호가 출력된다
	 * 3. 10개씩 보인다
	 * */
	public void findUsers() throws IOException {
		System.out.println("회원 목록 조회");
		ArrayList<CustomerDTO> customerList = customerDAO.selectCustomers();
		int index = 0;
		int max = customerList.size() / 10;
		
		while (true) {
			System.out.println("\r\n===============================================");
			System.out.println("회원 목록");
			System.out.println("===============================================\r\n");
			System.out.println("회원 번호 이름 아이디 전화번호");
			
			for (int i=10 * index; i < 10 * (index + 1); i++) {
				if (i >= customerList.size()) {
					break;
				}
				
				CustomerDTO book = customerList.get(i);
//				D2coding 글꼴
				System.out.printf("%d %-10s %s %s\n"
						, i + 1
						, book.getName()
						, book.getId()
						, book.getPhone());
			};
			

			System.out.printf("%d/%d\n", index + 1, max + 1);
			
			System.out.println("<이전페이지|다음페이지>");
			System.out.println("1. 검색");
			System.out.println("B. 뒤로가기");
			
			System.out.print("\r\n👉 ");
			String input = br.readLine();
			
			if(input.equals("1")){
				System.out.println("검색");
			} else if (input.equals("<")){
				if (index == 0) {
					System.out.println("이전 페이지 없음.");
				} else {
					index -= 1;
				}
			} else if (input.equals(">")){
				if (index == max) {
					System.out.println("다음 페이지 없음.");
				} else {
					index += 1;
				}
			} else {
				return;
			}
		}
		
	}
	
	public void findReservations() throws IOException {
		System.out.println("대출 목록 조회");
		ArrayList<ReservationDTO> reservationList = reservationDAO.selectReservations();
		int index = 0;
		int max = reservationList.size() / 10;
		
		while (true) {
			System.out.println("\r\n===============================================");
			System.out.println("대출 목록");
			System.out.println("===============================================\r\n");
			System.out.println("번호 아이디 이름 책이름 상태 대출날짜 대출기한");
			
			for (int i=10 * index; i < 10 * (index + 1); i++) {
				if (i >= reservationList.size()) {
					break;
				}
				ReservationDTO reservation = reservationList.get(i);
				System.out.printf("%d %5s %5s %5s %s %s\n"
						, i + 1
						, reservation.getCustomerId()
						, reservation.getCustomerName()
						, reservation.getStatus() == 0 ? "대출 중" : "대출 완료"
						, reservation.getRegdate()
						, reservation.getDuedate());
						
			};
			
//				D2coding 글꼴

			System.out.printf("%d/%d\n", index + 1, max + 1);
			
			System.out.println("<이전페이지|다음페이지>");
			System.out.println("1. 검색");
			System.out.println("B. 뒤로가기");
			
			System.out.print("\r\n👉 ");
			String input = br.readLine();
			
			if(input.equals("1")){
				System.out.println("검색");
			} else if (input.equals("<")){
				if (index == 0) {
					System.out.println("이전 페이지 없음.");
				} else {
					index -= 1;
				}
			} else if (input.equals(">")){
				if (index == max) {
					System.out.println("다음 페이지 없음.");
				} else {
					index += 1;
				}
			} else {
				return;
			}
		}
		
	}
}
