package memberjoin;

import java.sql.Date;

public class MemberVO {
	// 멤버변수
	private String Member_id; // 아이디
	private String name; // 이름
	private String password; // 비밀번호
	private Date regdate; // 날짜
	
	// 생성자
	public MemberVO() {

	}

	// GET / SET 메소드
	public String getMember_id() {
		return Member_id;
	}

	public void setMember_id(String member_id) {
		Member_id = member_id;
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

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	

	

}
