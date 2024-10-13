// MemberDAO.java
package book.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import book.project.DBUtil;
import book.project.dto.MemberDTO;

public class MemberDAO {
	/*
	 * 1. 아이디와 패스워드를 인자로 받는다.
	 * 2. 아이디와 패스워드로 멤버가 존재하는지 확인한다.
	 * 3. 존재하면 관리자 메인으로 이동
	 * 4. 실패하면 안내
	 */
    public boolean validateMember(MemberDTO member) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = null;
		ResultSet rs = null;
		
        try {
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM member WHERE me_id=? and me_password=?";
			//JDBC 수행 3단계
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			//JDBC 수행 4단계
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return true;
			} else{
				System.out.println("아이디와 비밀번호를 확인해주십시오.");
			}
			
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.executeClose(null, pstmt, conn);
        }
    }
}

