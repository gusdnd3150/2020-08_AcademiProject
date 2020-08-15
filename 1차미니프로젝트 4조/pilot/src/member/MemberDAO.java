package member;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
 		}catch(Exception e) {
 			e.printStackTrace();
 		}	
	}
	public boolean login(String id, String pw) {//로그인
		boolean check = false;//로그인이 되었는지 확인
		try {
			con = dataFactory.getConnection();
//			String query = "select member_id, password, name from member"
//					+ " where member_id=? and password=?";
			String query = "select * from member where member_id='"+id+"' ";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				System.out.println("1111");
				String _id = rs.getString("member_id");
				String _pw = rs.getString("password");
				String _name = rs.getString("name");
				MemberVO vo = new MemberVO();
				vo.setMember_id(_id);
				vo.setPassword(_pw);
				vo.setName(_name);
				if(vo.getPassword().equals(pw)) {
					check = true;
				}
			}
			rs.close();
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}
	
	public MemberVO voReturn(String id) { //로그인 성공시 정보를 가져오기 위한 메소드
		MemberVO vo = new MemberVO();
		try {
			con = dataFactory.getConnection();
//			String query = "select member_id, password, name from member"
//					+ " where member_id=? and password=?";
			String query = "select * from member where MEMBER_ID='"+id+"'";
			System.out.println(query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				String _id = rs.getString("member_id");
				String _pw = rs.getString("password");
				String _name = rs.getString("name");
				Date _regDate = rs.getDate("regDate");
				vo.setMember_id(_id);
				vo.setPassword(_pw);
				vo.setName(_name);
				vo.setRegDate(_regDate);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	public void changePwd(String id, String pw, String crtPw, String newPw) {
	    if(pw.equals(crtPw)) { //회원가입시 입력받은 비밀번호와 비밀번호 변경시 입력받은 비밀번호 비교
	    	try {
	    		con = dataFactory.getConnection();
	    		String query = "update member set password=? where member_id=?";
	    		System.out.println(query);
	    		pstmt = con.prepareStatement(query);
	    		pstmt.setString(1, newPw);  //새로운 비밀번호로 변경
	    		pstmt.setString(2, id);
	    		pstmt.executeUpdate();
	    		pstmt.close();         
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    }else {
	    	System.out.println("비밀번호를 다시입력해주세요");
	    }  
		
	   }
}