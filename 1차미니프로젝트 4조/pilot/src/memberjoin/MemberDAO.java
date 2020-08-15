package memberjoin;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class MemberDAO {
	private DataSource dataFactory;
	private Connection con;
	private PreparedStatement pstmt;
	
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//회원정보 조회 메소드
	public List listMember() {
		List list = new ArrayList();
		try {
			con = dataFactory.getConnection();
			String query = " select * from member";

			pstmt = con.prepareStatement(query);
			System.out.println(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				String Member_id = rs.getString("Member_id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				MemberVO vo = new MemberVO();
				vo.setMember_id(Member_id);
				vo.setName(name);
				vo.setPassword(password);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//회원가입 메소드
	public void addMember(MemberVO m) {
		try {
			con = dataFactory.getConnection();
			String Member_id = m.getMember_id();
			String password = m.getPassword();
			String name = m.getName();
			//regdate(날짜) 부분은 가입시 필요없어 sysdate로 처리
			String query = "INSERT INTO member (member_id, password, name, regdate)" + " VALUES(?, ? ,?, SYSDATE)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, Member_id);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//중복확인 메소드
	public boolean overlappedID(String id){
		boolean result = false;
		try {
			con = dataFactory.getConnection();
			// 이부분 이해가 부족한데..음  decode(전체),1,참,거짓 일 때) as result(1) member모든 것  // 결론: member DB 전부 보여줌
			String query = "select decode(count(*),1,'true','false') as result from member";
			// query가 member DB내용을 전부 보여주는데 Member_id=? 하면 id는 예를들면 아이유! 라고 보여줌
			query += " where Member_id=?";
		
			System.out.println("prepareStatememt: " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			result =Boolean.parseBoolean(rs.getString("result"));
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}