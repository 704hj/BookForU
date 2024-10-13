package book.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import book.project.DBUtil;

public class BookDAO {
	//관리자 프로그램에서 사용할 메서드 정의
	//도서등록
	public void insertBook(
			String bk_name, String bk_category) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {//JDBC 수행 1단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO sbook (bk_num,bk_name,"
					+ "bk_category) VALUES (book_seq.nextval,?,?)";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, bk_name);
			pstmt.setString(2, bk_category);
			//JDBC 수행 4단계
			int count = pstmt.executeUpdate();
			System.out.println(count + "개의 도서를 등록했습니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//JDBC 수행 5단계 : 자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//관리자,사용자 도서 목록
	public void selectBooks() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM sbook ORDER BY bk_num DESC";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//JDBC 수행 4단계
			rs = pstmt.executeQuery();
			System.out.println("-------------------------------------");
			if(rs.next()) {
				System.out.println("번호\t도서명\t도서분류\t등록일");
				do {
					System.out.print(rs.getInt("bk_num"));
					System.out.print("\t");
					System.out.print(rs.getString("bk_name"));
					System.out.print("\t");
					System.out.print(rs.getString("bk_category"));
					System.out.print("\t");
					System.out.print(rs.getDate("bk_regdate"));
					System.out.print("\n");
				}while(rs.next());					
			}else {
				System.out.println("등록된 도서가 없습니다.");
			}System.out.println("-------------------------------------");		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//JDBC 수행 5단계 : 자원정리
			DBUtil.executeClose(rs, pstmt, conn);			
		}
	}
	//관리자 회원목록
	public void selectMembers() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {//JDBC 수행 1단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM member ORDER BY me_regdate DESC";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//JDBC 수행 4단계
			rs = pstmt.executeQuery();

			System.out.println("-------------------------------------");
			if(rs.next()) {
				System.out.println("아이디\t이름\t전화번호");
				do {
					System.out.print(rs.getString("me_id"));
					System.out.print("\t");
					System.out.print(rs.getString("me_name"));
					System.out.print("\t");
					System.out.print(rs.getString("me_phone"));
					System.out.println();
				}while(rs.next());
			}else {
				System.out.println("등록된 회원정보가 없습니다.");
			}
		}catch(Exception e){
			e.printStackTrace();			
		}finally {
			//JDBC 수행 5단계 : 자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}

	}
	//관리자 대출목록
	public void selectOrders() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT me_id,bk_num,"
					+ "bk_category FROM sbook JOIN "
					+ "reservation USING(bk_num) ORDER BY re_num DESC";

			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//JDBC 수행 4단계
			rs = pstmt.executeQuery();
			System.out.println("-------------------------------------");
			if(rs.next()) {
				System.out.println("회원ID\t도서번호");
				do {
					System.out.print(rs.getString("me_id"));
					System.out.print("\t");
					System.out.print(rs.getInt("bk_num"));
					System.out.print("\n");
				}while(rs.next());
			}else {
				System.out.println("대출목록이 없습니다.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//JDBC 수행 5단계 : 자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}

	//관리자 회원등록
	public void insertMembers(String me_id,String me_name,String me_phone) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO member(me_id,me_name"
					+ ",me_phone) VALUES (?,?,?)";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, me_id);
			pstmt.setString(2, me_name);
			pstmt.setString(3, me_phone);
			//JDBC 수행 4단계
			int count = pstmt.executeUpdate();
			System.out.println(
					count + "개의 회원 정보를 저장했습니다.");					
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//JDBC 수행 5단계 : 자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	//사용자 프로그램에서 사용할 메서드 정의

	//사용자 회원상세
	public void selectDetailMember(String me_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT *FROM member WHERE me_id=?";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, me_id);
			//JDBC 수행 4단계
			rs = pstmt.executeQuery();
			System.out.println("-------------------------------------");
			if(rs.next()) {
				System.out.println("아이디:"+rs.getString("me_id"));
				System.out.println("이름:"+rs.getString("me_name"));
				System.out.println("전화번호:"+rs.getString("me_phone"));
			}else {
				System.out.println("검색된 회원정보가 없습니다.");
			}
			System.out.println("-------------------------------------");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//JDBC 수행 5단계 : 자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	//사용자 도서대출
	public void insertOrder(int bk_num,String me_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO reservation (re_num, re_status, bk_num,"
					+ "me_id) VALUES (reservation_seq.nextval, 0, ?, ?)";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, bk_num);
			pstmt.setString(2, me_id);
			
			int count = pstmt.executeUpdate();
			System.out.println(
					count + "개의 대출 정보를 저장했습니다.");	
			//JDBC 수행 4단계
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//JDBC 수행 5단계 : 자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	//사용자 대출내역
	public void selectRentById(String me_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT re.me_id, re.bk_num, b.bk_name, b.bk_category, re.re_status "
				    + "FROM reservation re JOIN sbook b ON re.bk_num = b.bk_num "
				    + "WHERE re.me_id = ? ORDER BY re.bk_num DESC";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, me_id);
			//JDBC 수행 4단계
			rs = pstmt.executeQuery();			
			System.out.println("---------------------");
			System.out.println(me_id + "님의 대출내역");
			System.out.println("---------------------");
			if(rs.next()) {
				System.out.println("번호\t도서명\t도서분류\t반납상태");
				do {
					System.out.print(rs.getInt("bk_num"));
					System.out.print("\t");
					System.out.print(rs.getString("bk_name"));
					System.out.print("\t");
					System.out.print(rs.getString("bk_category"));
					System.out.print("\t");
					String status = rs.getInt("re_status") == 0 ? "대출 중" : "반납 완료";
					System.out.println(status);
					System.out.print("\n");
				}while(rs.next());
			}else {
				System.out.println("대출한 도서가 없습니다.");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//JDBC 수행 5단계 : 자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	
	//사용자 도서 반납
	public void returnBooks(String me_id, int bk_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			//JDBC 수행 1,2단계
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE reservation SET re_status = 1, re_modifydate = sysdate \r\n"
					+ "WHERE me_id = ? AND bk_num = ?";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, me_id);
			pstmt.setInt(2, bk_num);
			
			
			int count = pstmt.executeUpdate();
			System.out.println(
					count + "개의 대출 정보를 저장했습니다.");	
			//JDBC 수행 4단계
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//JDBC 수행 5단계 : 자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
}
