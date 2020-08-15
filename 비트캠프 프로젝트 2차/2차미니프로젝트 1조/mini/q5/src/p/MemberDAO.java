package p;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;

public class MemberDAO {
	private DataSource dataFactory;
	Connection conn;
	PreparedStatement pstmt;
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//id로 member 찾는 메소드
	public MemberVO search(String pid){
		MemberVO member = null;
		try {
			conn = dataFactory.getConnection();
			String query="SELECT  id, pwd, name\r\n" + 
					"FROM t_member\r\n" + 
					"WHERE id = '"+pid+"'";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				member = new MemberVO(id,pwd,name);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return member;
	}
	
	//수정 혹은 추가하는 메소드
	public void saveMember(MemberVO vo){
		try {
			conn = dataFactory.getConnection();
			String id = vo.getId();
			String pwd = vo.getPwd();
			String name = vo.getName();
			String query="merge into t_member\r\n" + 
					"using dual\r\n" + 
					"on (id = '"+id+"') \r\n" + 
					"when matched then\r\n" + 
					"update set pwd = '"+pwd+"', name = '"+name+"' \r\n" + 
					"when not matched then\r\n" + 
					"insert (id, pwd, name, joindate ) \r\n" + 
					"values ('"+id+"' ,'"+ pwd+"' , '"+name+"',sysdate) ";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//이전 아이디 찾는 메소드
	public String pid(String id) {
		String result=""; 
		try {
			conn = dataFactory.getConnection();
			String query= "SELECT before_id FROM(SELECT id, LAG(id, 1, 0) OVER (ORDER BY id asc) as before_id FROM t_member)"
					+ " where id=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getString("before_id"); //이전 아이디 result에 넣기
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result; //이전 아이디 리턴
	}
	
	//이후 아이디 찾는 메소드
	public String nid(String id) {
		String result="";
		try {
			conn = dataFactory.getConnection();
			String query= "SELECT after_id FROM(SELECT id, LEAD(id, 1, 0) OVER (ORDER BY id asc) as after_id FROM t_member)"
					+ " where id=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getString("after_id"); //이후 아이디 result에 넣기
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return result; //이후 아이디 리턴
	}
	

}
