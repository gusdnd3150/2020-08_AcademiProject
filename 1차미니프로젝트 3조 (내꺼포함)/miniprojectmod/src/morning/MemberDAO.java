package morning;



import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private DataSource dataFactory;
	private Connection conn;
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

	public List<MemberVO> listMembers() {
		List<MemberVO> membersList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from member ";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);        //sql문 전달
			System.out.println(pstmt);
			ResultSet rs = pstmt.executeQuery();          //sql결과를 가지고옴
			while (rs.next()) {
				String member_id = rs.getString("member_Id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String regdate = rs.getString("regdate");
				
				MemberVO memberVO = new MemberVO(member_id, name, password,regdate);
				membersList.add(memberVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("오류발생");
			e.printStackTrace();
		}
		return membersList;
	}
	
	public void modPassward(MemberVO vo) {
		String pwd = vo.getPassword();
		String id = vo.getMemberId();
		
		try {
			conn = dataFactory.getConnection();
			String query = "update member set password=? where member_id=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pwd);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addMember(MemberVO m) {
		try {
			conn = dataFactory.getConnection();
			String member_id = m.getMemberId();
			String name = m.getName();
			String password = m.getPassword();
			System.out.println(name);
			String query = "insert into member";
			query += "(member_id, name, password, regdate)";
			query += " values(?, ?, ?, sysdate)";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member_id);
			pstmt.setString(2, name);
			pstmt.setString(3, password);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean checkId(String id) {
		String sql = "SELECT COUNT(1) AS CNT FROM MEMBER WHERE MEMBER_ID = ?";
		Boolean b_chk = true;
		try {
			Connection conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				if (Integer.parseInt(rs.getString("CNT")) > 0) {
					b_chk = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b_chk;
	}
	
	
	
	
	
}