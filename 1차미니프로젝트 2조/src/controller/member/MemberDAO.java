package controller.member;

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

	//회원가입
	public void addMember(MemberVO m) {
		try {
			conn = dataFactory.getConnection();
			
			String member_id = m.getMember_id();
			String password = m.getPassword();
			String name = m.getName();

			String query = "insert into member(member_id,name,password,regdate)" 
					+ " VALUES(?, ? ,? , sysdate)";// DB에 저장 
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
	//비밀번호 변경
	public void changePw(String member_id, String newPw) {
		try {
			conn = dataFactory.getConnection();
			String query = "update member set password =? where member_id=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newPw);
			pstmt.setString(2, member_id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	//로그인
	public boolean login(String member_id, String password) {
		boolean check = false;
		try{
			conn = dataFactory.getConnection();
			String query = "select member_id, password, name from member where member_id=?";
			System.out.println(query);
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, member_id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {

				String _password = rs.getString("password");

				if(_password.equals(password)) {
					check = true;
				}
			}
			rs.close();
			pstmt.close();
			conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return check;
	}

	//멤버 id 리턴 -->로그인 때 id확인용으로 씀 
	public MemberVO voReturn(String member_id) {
		MemberVO vo = new MemberVO();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from member where member_id='"+member_id+"'";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				String _member_id = rs.getString("member_id");
				String _password = rs.getString("password");
				String _name = rs.getString("name");
				vo.setMember_id(_member_id);
				vo.setPassword(_password);
				vo.setName(_name);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	// 아이디 중복체크
	public boolean idCheck (String member_id) {
		boolean result = false;
		try {
			conn = dataFactory.getConnection();
			String query = "select member_id from member where member_id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member_id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				String _member_id = rs.getString("member_id");
				if(_member_id != null ) {
					result = true;
				}
			}
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	

	}