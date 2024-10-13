// BookDAO.java
package book.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import book.project.DBUtil;
import book.project.dto.BookDTO;

public class BookDAO {

    // 도서 등록
    public void insertBook(BookDTO book) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = null;
		ResultSet rs = null;
		
        try {
            conn = DBUtil.getConnection();
            sql = "INSERT INTO sbook (bk_num, bk_name, bk_category) VALUES (book_seq.nextval, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, book.getName());
            pstmt.setString(2, book.getCategory());
            pstmt.executeUpdate();
            
			//SQL문 작성
			sql = "SELECT * FROM sbook WHERE bk_name=? and bk_category=?";       
			pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, book.getName());
            pstmt.setString(2, book.getCategory());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				System.out.println("\r\n===============================================");
				System.out.println("도서 정보");
				System.out.println("===============================================\r\n");
				
				System.out.print("도서 번호 : ");
				System.out.println(rs.getInt("bk_num"));
				System.out.print("도서명 : ");
				System.out.println(rs.getString("bk_name"));
				System.out.print("카테고리 : ");
				System.out.println(rs.getString("bk_category"));
				System.out.print("등록일 : ");
				System.out.println(rs.getDate("bk_regdate"));
				System.out.println();
				
				System.out.println("도서정보가 입력되었습니다.");
				System.out.println();
			}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.executeClose(null, pstmt, conn);
        }
    }

    // 도서 목록 조회
    public ArrayList<BookDTO> selectBooks() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        ArrayList<BookDTO> bookList = new ArrayList<>();
        
        try {
            conn = DBUtil.getConnection();
            sql = "SELECT * FROM sbook ORDER BY bk_num ASC";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
            	BookDTO book = new BookDTO(rs);
            	bookList.add(book);
            }
            
            return bookList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.executeClose(rs, pstmt, conn);
        }
        
		return bookList;
    }
}

