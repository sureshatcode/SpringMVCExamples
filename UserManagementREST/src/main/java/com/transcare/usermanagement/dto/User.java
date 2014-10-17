package com.transcare.usermanagement.dto;

public class User {
	private long userid;
	private String username;
	private String password;
	private int age;
	private String contactno;
	private String emailaddress;

	public long getUserid() {
		return userid;
	}

	public void setUserid(long long1) {
		this.userid = long1;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getContactno() {
		return contactno;
	}

	public void setContactno(String contactno) {
		this.contactno = contactno;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	@Override
	public String toString() {

		return "[ UserId:" + userid + ", UserName: " + username
				+ ", Password: " + password + ", Age: " + age
				+ ", Contact No: " + contactno + ",Email Address: "
				+ emailaddress + " ]";
	}
}
