package morning;

public class MemberVO {
	private String member_id ;
	private String name;
	private String password;
	private String regdate;
	

	MemberVO(){
		
	}
	MemberVO (String member_id,String name,String password){
		this.member_id=member_id;
		this.name =name;
		this.password=password;
	}
	
	MemberVO(String member_id,String name,String password,String regdate){
		this.member_id = member_id;
		this.name =name;
		this.password =password;
		this.regdate = regdate;
	}
	


	public String getMemberId() {
		return member_id;
	}
	public void setMemberId(String memberId) {
		this.member_id = memberId;
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
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "MemberVO [member_id=" + member_id + ", name=" + name + ", password=" + password + ", regdate=" + regdate
				+ "]";
	}

}


