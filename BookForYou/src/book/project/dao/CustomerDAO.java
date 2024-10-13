// MemberDAO.java
package book.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import book.project.DBUtil;
import book.project.dto.BookDTO;
import book.project.dto.CustomerDTO;
import book.project.dto.MemberDTO;

public class CustomerDAO {

    // 회원 목록 조회
    public ArrayList<CustomerDTO> selectCustomers() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        ArrayList<CustomerDTO> customerList = new ArrayList<>();
        
        try {
            conn = DBUtil.getConnection();
            sql = "SELECT * FROM customer";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
            	CustomerDTO customer = new CustomerDTO(rs);
            	customerList.add(customer);
            }
            
            return customerList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.executeClose(rs, pstmt, conn);
        }
        
		return customerList;
    }
}

