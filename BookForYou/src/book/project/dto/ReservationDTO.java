//ReservationDTO.java
package book.project.dto;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationDTO {
	private int num;
	private String bookName;
	private String customerId;
	private String customerName;
	private int status;
	private Date regdate;
	private Date duedate;

    public ReservationDTO() {}
    
    public ReservationDTO(ResultSet rs) throws SQLException {
    	this.num = rs.getInt("re_num");
    	this.bookName = rs.getString("bk_name");
    	this.customerId = rs.getString("cust_id");
    	this.customerName = rs.getString("cust_name");
    	this.status = rs.getInt("re_status");
    	this.regdate = rs.getDate("re_regdate");
    	this.duedate = rs.getDate("re_duedate");
    }
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getDuedate() {
		return duedate;
	}
	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}
}
