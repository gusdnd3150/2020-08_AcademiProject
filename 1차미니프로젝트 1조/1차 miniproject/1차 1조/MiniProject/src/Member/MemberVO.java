package Member;

public class MemberVO {

    private String member_id;
    private String name;
    private String password;
    private String regdate;

    public MemberVO() {
    }


    public MemberVO(String member_id, String name, String password, String regdate) {
        this.member_id = member_id;
        this.name = name;
        this.password = password;
        this.regdate = regdate;

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

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    @Override
    public String toString() {
        return "MemberVO{" +
                "member_id='" + member_id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", regdate='" + regdate + '\'' +
                '}';
    }
}
