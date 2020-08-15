package controller.member;

import java.util.Date;

public class MemberVO {
	
	private String member_id;
	private String name;
	private String password;
	Date regDate;
	

	public MemberVO() {
	}
	public MemberVO(String member_id, String password) {
		super();
		this.member_id = member_id;
		this.password = password;
	}
	public MemberVO(String member_id, String name, String password) {
		super();
		this.member_id = member_id;
		this.name = name;
		this.password = password;
	}
	
	public MemberVO(String member_id, String name, String password, Date regDate) {
		super();
		this.member_id = member_id;
		this.name = name;
		this.password = password;
		this.regDate = regDate;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
}
