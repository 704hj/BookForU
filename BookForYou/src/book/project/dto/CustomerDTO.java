// MemberDTO.java
package book.project.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDTO {
	private String id;
    private String name;
    private String phone;

    public CustomerDTO() {}
    
    public CustomerDTO(ResultSet rs) throws SQLException {
    	this.id = rs.getString("cust_id");
    	this.name = rs.getString("cust_name");
    	this.phone = rs.getString("cust_tel");
    }

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
