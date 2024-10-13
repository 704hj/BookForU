// BookDTO.java
package book.project.dto;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class BookDTO {
    private int num;
	private String name;
	private String author;
    private String category;
    private Date regdate;

    public BookDTO() {}
    
    public BookDTO(ResultSet rs) throws SQLException {
    	this.num = rs.getInt("bk_num");
    	this.name = rs.getString("bk_name");
    	this.author = rs.getString("bk_author");
    	this.category = rs.getString("bk_category");
    	this.regdate = rs.getDate("bk_regdate");
    }

    public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date date) {
		this.regdate = date;
	}
	
	@Override
	public String toString() {
		return this.num + " " + this.name + " " + this.category + " " + this.regdate + " ";
	}
}
