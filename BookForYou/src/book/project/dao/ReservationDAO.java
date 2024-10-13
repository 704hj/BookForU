package book.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import book.project.DBUtil;
import book.project.dto.CustomerDTO;
import book.project.dto.ReservationDTO;

public class ReservationDAO {

    public ArrayList<ReservationDTO> selectReservations() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        ArrayList<ReservationDTO> reservationList = new ArrayList<>();
        
        try {
            conn = DBUtil.getConnection();
            sql = "SELECT * FROM reservation";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
            	ReservationDTO reservation = new ReservationDTO(rs);
            	reservationList.add(reservation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.executeClose(rs, pstmt, conn);
        }
		return reservationList;
    }

}
