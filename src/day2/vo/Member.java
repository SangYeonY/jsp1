package day2.vo;

import java.sql.Date;

public class Member {
	private int cust_no;
	private String cust_name;
	private String phone;
	private String address;
	private Date joindate;
	private String grade;
	private String city;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(int cust_no, String cust_name, String phone, String address, Date joindate, String grade,
			String city) {
		super();
		this.cust_no = cust_no;
		this.cust_name = cust_name;
		this.phone = phone;
		this.address = address;
		this.joindate = joindate;
		this.grade = grade;
		this.city = city;
	}

	public int getCust_no() {
		return cust_no;
	}
	
	public void setCust_no(int cust_no) {
		this.cust_no = cust_no;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getJoindate() {
		return joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}