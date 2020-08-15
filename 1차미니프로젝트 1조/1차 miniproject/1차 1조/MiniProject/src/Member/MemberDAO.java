package Member;

import java.sql.Connection;
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

	private static final String addMember = "INSERT INTO member(member_id, password, name, regdate) VALUES(?, ?, ?,sysdate)";
	private static final String select_id_pw = "select * from member where member_id=? and password=?";
	private static final String delMember = "delete from member where member_id=? and password=?";
	private static final String updatePassword = "update member set password=? where password=?";
	private static final String selectPw = "select * from member where password=?";
	private static final String selectId = "select * from member where member_id=?";
	private static final String selectMember = "select * from member";

	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<MemberVO> listMembers() { // 전체 조회
		List<MemberVO> membersList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(selectMember);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("member_id");
				String pwd = rs.getString("password");
				String name = rs.getString("name");
				String regdate = rs.getString("regdate");
				MemberVO memberVO = new MemberVO(id, pwd, name, regdate);
				membersList.add(memberVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return membersList;
	}

	public void addMember(MemberVO m) { // 회원가입
		try {
			conn = dataFactory.getConnection();
			String id = m.getMember_id();
			String pwd = m.getPassword();
			String name = m.getName();

			pstmt = conn.prepareStatement(addMember);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.executeUpdate();
			System.out.println(id + pwd + name);
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int delMember(MemberVO m) { // 회원 탈퇴 (아이디, 암호 비교 후 참이면 탈퇴 진행)
		int rst = 0;
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(select_id_pw);
			pstmt.setString(1, m.getMember_id());
			pstmt.setString(2, m.getPassword());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				rst = 1;
				pstmt = conn.prepareStatement(delMember);
				pstmt.setString(1, m.getMember_id());
				pstmt.setString(2, m.getPassword());
				pstmt.executeUpdate();
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rst;
	}

	public String logInCheck(MemberVO m) { // 로그인 (아이디, 비밀번호 입력받아 db데이터 비교)
		String rst = "";
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(select_id_pw);
			pstmt.setString(1, m.getMember_id());
			pstmt.setString(2, m.getPassword());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setName(rs.getString("name")); // jsp로 보내줄 이름
				rst = vo.getName();
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rst;
	}

	public void editPwd(MemberVO m, String newPassword) { // 암호 수정
		String password = m.getPassword();
		String nPwd = newPassword;
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(updatePassword);
			pstmt.setString(1, nPwd); // 새로입력받은 암호
			pstmt.setString(2, password);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int idCheck(String id) { // 아이디 중복 체크
		int rst = 0;
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(selectId);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				rst = 1; // 아이디 중복시 1
			}
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rst;
	}

	public int pwdCheck(String password) { // 비밀번호 중복 체크
		int rst = 0;
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(selectPw);
			pstmt.setString(1, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				rst = 1; // 비밀번호 중복시 1
			}
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rst;
	}

}
