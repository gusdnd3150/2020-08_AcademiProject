package member;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	private String selectid = "select id, pwd, name from t_member where id = ?";
	private String insertMember = "INSERT INTO t_member(id, pwd, name) VALUES(?, ?, ?)";
	private String LAG = "SELECT * FROM(SELECT  id, LAG(id) OVER (ORDER BY id) nID, LAG(pwd) OVER (ORDER BY id) npwd, LAG(name) OVER (ORDER BY id) nNAME FROM t_member ORDER BY id) WHERE id = ?";
	private String LEAD = "SELECT * FROM(SELECT id, LEAD(id) OVER (ORDER BY id) sid, LEAD(pwd) OVER (ORDER BY id) spwd, LEAD(name) OVER (ORDER BY id) sname FROM t_member ORDER BY id)WHERE id = ?";
	private String checkId = "select id from t_member where id = ?";
	private String updateMember = "update t_member set pwd=?, name =? where id =?";

	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MemberVO checkMember(String _id) { // 조회 메소드
		MemberVO memInfo = null;
		try {
			con = dataFactory.getConnection();
			String query = selectid;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, _id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String id = rs.getString("id");
			String pwd = rs.getString("pwd");
			String name = rs.getString("name");
			memInfo = new MemberVO(id, pwd, name);
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memInfo;
	}

	public int addMember(String id, String pwd, String name) { // 저장 메소드
		try {
			con = dataFactory.getConnection();
			String query = insertMember;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 1; // 등록 완료 1 리턴
	}

	public MemberVO lag(String _id) { // 이전 메소드
		MemberVO memInfo = null;
		try {
			con = dataFactory.getConnection();
			String query = LAG;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, _id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String id = rs.getString("nid");
			String pwd = rs.getString("npwd");
			String name = rs.getString("nname");
			memInfo = new MemberVO(id, pwd, name);
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memInfo;
	}

	public MemberVO lead(String _id) { // 이후 메소드
		MemberVO memInfo = null;
		try {
			con = dataFactory.getConnection();
			String query = LEAD;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, _id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String id = rs.getString("sid");
			String pwd = rs.getString("spwd");
			String name = rs.getString("sname");
			memInfo = new MemberVO(id, pwd, name);
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return memInfo;
	}

	public int checkId(String _id) { // 아이디 중복조회
		try {
			con = dataFactory.getConnection();
			String query = checkId;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, _id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next() || _id.equals("")) {
				pstmt.close();
				con.close();
				return 0; // 이미 존재하는 아이디입니다.
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1; // 가입가능한 아이디
	}

	public int updateMember(String id, String pwd, String name) {// 수정 등록
		try {
			con = dataFactory.getConnection();
			String query = updateMember;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pwd);
			pstmt.setString(2, name);
			pstmt.setString(3, id);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return 1; // 수정 완료 1 리턴
	}
}