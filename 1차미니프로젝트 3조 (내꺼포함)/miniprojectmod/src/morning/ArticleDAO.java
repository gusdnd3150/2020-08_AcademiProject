package morning;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ArticleDAO {

	Connection con;
	PreparedStatement pstmt;
	DataSource dataSource;

	public ArticleDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<ArticleVO> list() {

		List<ArticleVO> list = new ArrayList<ArticleVO>();
		try {
			String query = "SELECT * FROM ARTICLE ORDER BY ARTICLE_NO";
			con = dataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ArticleVO article = new ArticleVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9));
				list.add(article);
			}
			rs.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArticleVO getArticle(int articleNo) {
		ArticleVO article = new ArticleVO();
		try {
			String query = "SELECT * FROM ARTICLE WHERE ARTICLE_NO = ?";
			con = dataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, articleNo);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				article = new ArticleVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9));
			}
			rs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return article;
	}

	public void addCnt(int readCnt, int articleNo) {

		try {
			String query = "UPDATE ARTICLE SET READ_CNT = ? WHERE ARTICLE_NO = ?";
			con = dataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, readCnt + 1);
			pstmt.setInt(2, articleNo);
			pstmt.executeUpdate();
			con.close();
		} catch (Exception e) {

		}

	}

	public ArticleContentVO showContents(int articleNo) {

		ArticleContentVO content = new ArticleContentVO();
		try {
			String query = "SELECT * FROM ARTICLE_CONTENT WHERE ARTICLE_NO = ?";
			con = dataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, articleNo);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				content.setArticleNo(rs.getInt(1));
				content.setContent(rs.getString(2));
			}
			rs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	public void insertArticle(String title, String password, String articleContent, String fileName) {
		List<ArticleVO> list = list();
		int articleNo = 1;
		String writerId = "test"; // 
		String writerName = "test";
		if(list.size()!=0) {
			articleNo = list.get(list.size() - 1).getArticleNo() + 1;
		}
		try {
			String query = "INSERT INTO ARTICLE VALUES (?,?,?,?,?,SYSDATE,SYSDATE,0,?)";
			System.out.println(query);
			con = dataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, articleNo);
			pstmt.setString(2, writerId);
			pstmt.setString(3, writerName);
			pstmt.setString(4, title);
			pstmt.setString(5, password);
			pstmt.setString(6, fileName);
			pstmt.executeUpdate();
			query = "INSERT INTO ARTICLE_CONTENT VALUES (?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, articleNo);
			pstmt.setString(2, articleContent);
			pstmt.executeUpdate();
			con.close();
		} catch (Exception e) {
		}
	}

	public void deleteArticle(int articleNo) {

		try {
			String query = "delete from ARTICLE where ARTICLE_NO = ?";
			con = dataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, articleNo);
			pstmt.executeUpdate();
			query = "delete from ARTICLE_CONTENT where ARTICLE_NO = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, articleNo);
			pstmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateArticle(String title, String password, String content, int articleNo) {
		
		try {
			String query = "UPDATE ARTICLE SET title = ?, password = ?, MODDATE = SYSDATE WHERE article_no = ?";
			con = dataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, password);
			pstmt.setInt(3, articleNo);
			pstmt.executeUpdate();
			query = "UPDATE ARTICLE_CONTENT SET CONTENT = ? WHERE article_no = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, content);
			pstmt.setInt(2, articleNo);
			pstmt.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String selectCommentNo (int articleNo) {
		String nowComment = "";
		try {
			String query = "SELECT COMMENT_NO FROM ARTICLE WHERE ARTICLE_NO = ?";
			con = dataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, articleNo);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				nowComment = rs.getString(1);
			}
			rs.close();
			con.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return nowComment;
	}

}
