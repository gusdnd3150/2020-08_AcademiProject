package article;

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

public class ArticleDAO {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;

	public ArticleDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 게시글 조회하는 메소드	
	public List<ArticleVO> lookUp(Paging paging) {
		List<ArticleVO> articleList = new ArrayList();
		int startNum = paging.getStartNum();
		int endNum = paging.getEndNum();
		try {
			conn = dataFactory.getConnection();
			String query = " SELECT * FROM ( ";
			query += " SELECT * FROM ( ";
			query += " SELECT ROWNUM row_num, article.* FROM article ";
			query += " )WHERE row_num >= ? ";
			query += " )WHERE row_num <= ? ";
			query += " ORDER BY article_no ";
			// SELECT ROWNUM row_num, article.* FROM article;
			// SELECT * FROM (SELECT ROWNUM row_num, article.* FROM article) WHERE row_num >= startNum;
			// SELECT * FROM ( 상위SELECT한결과 ) WHERE row_num <= endNUm; 
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String article_no = rs.getString("article_no");
				String title = rs.getString("title");
				String writer_name = rs.getString("writer_name");
				String read_cnt = rs.getString("read_cnt");
				ArticleVO articleVO = new ArticleVO(article_no, title, writer_name, read_cnt);
				articleList.add(articleVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articleList;
	}
	// 총 게시글 수 확인하는 메소드
	public int getAllCount() {
		int count = 0;
		try {
			String query = " SELECT COUNT(*) as count FROM article ";
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt("count");
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	// 게시글 추가하는 메소드(Article 테이블에 추가)
	public void addArticle(ArticleVO a) {
		try {
			conn = dataFactory.getConnection();
			
			String query = "INSERT INTO article VALUES(ARTICLE_NO_SEQ.CURRVAL, ?, ?, ?, ?,";
			query += "systimestamp, systimestamp, 0)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, a.getWriter_id());
			pstmt.setString(2, a.getWriter_name());
			pstmt.setString(3, a.getTitle());
			pstmt.setString(4, a.getPassword());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	// 게시글 내용을 추가하는 메소드(Article_content 테이블에 추가)
	public void addContent(String content, String fileName) {
		try {
		conn = dataFactory.getConnection();
		
		String query = "INSERT INTO article_content VALUES(ARTICLE_NO_SEQ.NEXTVAL,?,?)";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, content);
		pstmt.setString(2, fileName);
		pstmt.executeUpdate();
		pstmt.close();
		conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	// article_no에 해당하는 게시글 내용을 가져오는 메소드
	public ContentVO getContent(String article_no) {
		ContentVO contentInfo = null;
		try {
			conn = dataFactory.getConnection();
			String query = " SELECT a.writer_name, a.title, c.content, c.filename";
			query +=" FROM article a, article_content c";
			query +=" WHERE a.article_no = c.article_no AND a.article_no=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, article_no);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String writer_name = rs.getString("writer_name");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String fileName = rs.getString("filename");
			contentInfo = new ContentVO(article_no, writer_name, title, content, fileName);
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch(Exception e) { e.printStackTrace(); }
		return contentInfo;
	}
	// 조회수를 추가하는 메소드
	public void addReadCount(String article_no) {
		try {
			conn = dataFactory.getConnection();
			String query = " UPDATE article SET read_cnt = read_cnt+1 ";
			query += " WHERE article_no=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, article_no);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	// 게시글을 수정하는 메소드
	public void modArticle(ContentVO contentVO) {
		try {
			conn = dataFactory.getConnection();
			
			String query = "UPDATE article SET title=?, moddate=systimestamp"
					+ " WHERE article_no=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, contentVO.getTitle());
			pstmt.setString(2, contentVO.getArticle_no());
			pstmt.executeUpdate();
			pstmt.close();
			
			query = " UPDATE article_content SET content=? WHERE article_no=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, contentVO.getContent());
			System.out.println(contentVO.getContent());
			pstmt.setString(2, contentVO.getArticle_no());
			pstmt.executeUpdate();
			pstmt.close();
			
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	// 게시글을 삭제하는 메소드(Article 테이블에서 삭제)
	public void delArticle(String article_no) {
		try {
			conn = dataFactory.getConnection();
			String query =" DELETE FROM article WHERE article_no=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, article_no);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	// 게시글 삭제, 수정할때 암호를 확인하기 위한 메소드
	public String getPassword(String article_no) {
		String password = "";
		try {
			System.out.println(article_no);
			conn = dataFactory.getConnection();
			String query = " SELECT password FROM article WHERE article_no=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, article_no);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			password = rs.getString("password");
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return password;
	}
	// 게시글 내용을 제거하는 메소드(Article_content에서 삭제)
	public void delContent(String article_no) {
		try {
			conn = dataFactory.getConnection();
			String query = " DELETE FROM article_content WHERE article_no=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, article_no);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	// 파일 다운로드를 위한 filename을 가져오는 메소드
	public String getFileName(String article_no) {
		String fileName = "";
		try {
			conn = dataFactory.getConnection();
			String query = " SELECT filename FROM article_content WHERE article_no=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, article_no);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			fileName = rs.getString("fileName");
			
			rs.close();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}
}
