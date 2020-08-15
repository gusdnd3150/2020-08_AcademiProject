package morning;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PostCommentDAO {

	Connection con;
	PreparedStatement pstmt;
	DataSource dataSource;

	public PostCommentDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<postCommentVO> list() {

		List<postCommentVO> list = new ArrayList<postCommentVO>();
		try {
			String query = "SELECT * FROM POST_COMMENT ORDER BY POST_COMMENT_NO";
			con = dataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				postCommentVO comment = new postCommentVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getInt(5));
				list.add(comment);
			}
			rs.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int insertComment(String comment, int articleNo) {
		List<postCommentVO> list = list();
		int commentNo = 1;
		String writerId = "test"; //
		if (list.size() != 0) {
			commentNo = list.get(list.size() - 1).getPostCommentNo() + 1;
			System.out.println(commentNo);
		}
		try {
			String query = "INSERT INTO POST_COMMENT VALUES (?, ?, ?, SYSDATE, ?)";
			System.out.println(query);
			con = dataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, commentNo);
			pstmt.setString(2, writerId);
			pstmt.setString(3, comment);
			pstmt.setInt(4, articleNo);
			pstmt.executeUpdate();
			con.close();
		} catch (Exception e) {
		}
		return commentNo;
	}
	
	public void deleteComment(int commentNo) {
		try {
			String query = "DELETE FROM POST_COMMENT WHERE POST_COMMENT_NO = ?";
			con = dataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, commentNo);
			pstmt.executeUpdate();
			System.out.println("삭제성공");
			con.close();
		} catch (Exception e) {
		}
	}
	
	public postCommentVO selectComment(int commentNo) {
		
		postCommentVO comment = null;
		
		try {
			String query = "SELECT * FROM POST_COMMENT WHERE POST_COMMENT_NO = ?";
			con = dataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, commentNo);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				comment = new postCommentVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getInt(5));
			}
			rs.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return comment;
	}

}
