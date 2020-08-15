package controller.board;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;

public class BoardDAO {
	private DataSource dataFactory;
//	Connection conn;
	PreparedStatement pstmt;
	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//게시글 리스트 보기. 게시판 메인
		public List<ArticleVO> AllArticles(int pageNum){ 
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
			try(Connection conn = dataFactory.getConnection()) {
				String query="select * from(select rownum rnum, a.* from article a order by article_no desc)" + 
						"where rnum>=("+pageNum+"-1)*10+1 and rnum<=("+pageNum+")*10"; //클릭한 숫자에 대한 article_no 조회
				pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					int article_no = rs.getInt("article_no");
					String writer_id = rs.getString("writer_id");
					String writer_name = rs.getString("writer_name");
					String title = rs.getString("title");
					String password = rs.getString("password");
					Date regdate = rs.getDate("regdate");
					Date moddate = rs.getDate("moddate");
					int read_cnt = rs.getInt("read_cnt");
					ArticleVO article = new ArticleVO(article_no, writer_id, writer_name, title, password, regdate, moddate, read_cnt);
					articlesList.add(article);
				}
				rs.close();
				pstmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
			return articlesList;
		}
		//게시글 조회할 때 쓰는 메소드
		public ContentVO ContentInfo(int artNum){ 
			ContentVO article = null;
			try(Connection conn = dataFactory.getConnection()){
				addRead_cnt(artNum);
				String query="SELECT A.article_no, A.password, A.title, A.writer_name, B.content, B.file_name\r\n" + 
						"FROM article A, article_content B\r\n" + 
						"WHERE A.article_no = B.article_no"
						+" AND B.article_no = '"+artNum+"'";
				System.out.println(query);
				pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					int article_no = rs.getInt("article_no");
					String password = rs.getString("password");
					String title = rs.getString("title");
					String content = rs.getString("content");
					String writer_name = rs.getString("writer_name");
					String file_name = rs.getString("file_name");
					article = new ContentVO(article_no, content, password, title, writer_name, file_name);
				}
				rs.close();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return article;
		}
		//게시글 수정
		public void editArticle(ContentVO vo, int artNO, String inputPw,String file_name) {
			try(Connection conn = dataFactory.getConnection()) {
				System.out.println(artNO);
				String query = "SELECT password\r\n" + 
						"FROM article\r\n" + 
						"WHERE article_no = '"+artNO+"'";
				System.out.println(query);
				pstmt = conn.prepareStatement(query);
				pstmt.executeUpdate();
				ResultSet rs = pstmt.executeQuery();
				String password ="";
				while (rs.next()) {
					password = rs.getString("password");
				}
				if(password.equals(inputPw)) { //비밀번호 맞을 경우
					if(file_name=="") { 							//수정할 때 첨부파일 수정 안할 경우 내용만 변경
						query = "update article_content set content=? where article_no ='"+artNO+"'";
						System.out.println(query);
						pstmt = conn.prepareStatement(query);
						pstmt.setString(1, vo.getContent());
						pstmt.executeUpdate();
					} else { 										//첨부파일 수정도 할 경우 내용,첨부파일 변경
						query = "update article_content set content=?, file_name=? where article_no ='"+artNO+"'";
						System.out.println(query);
						pstmt = conn.prepareStatement(query);
						pstmt.setString(1, vo.getContent());
						pstmt.setString(2, file_name);
						pstmt.executeUpdate();
					}
					//제목 변경
					query = "update article set title=?,moddate=systimestamp where article_no ='"+artNO+"'";
					System.out.println(query);
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, vo.getTitle());
					pstmt.executeUpdate();
				}
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		 //페이징을 위한 전체 게시글 갯수 리턴 메소드
		public int totArticles() { 
			try(Connection conn = dataFactory.getConnection()) {
				String query="SELECT count(article_no) FROM article";
				System.out.println(query);
				pstmt = conn.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) 
					return (rs.getInt(1));
				rs.close();
				pstmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
		//조회수 카운트, 게시글 조회시 실행됨.
		private void addRead_cnt(int artNum) {
			try(Connection conn = dataFactory.getConnection()) {
				int getCnt = getRead_cnt(artNum, conn);
				String query = "update article set read_cnt="
							+ getCnt +" where article_no="+artNum;
				pstmt = conn.prepareStatement(query);
				System.out.println(query);
				pstmt.executeUpdate();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//조회수 카운트 리턴
		private int getRead_cnt(int artNum, Connection conn) {
			try {
				String query = "select read_cnt from article where article_no="+artNum;
				pstmt = conn.prepareStatement(query);
				System.out.println(query);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					return (rs.getInt(1)+1);
				}
				rs.close();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}


		// articleVO, 게시글 추가
		public void insertArticle(ArticleVO articleVO) {   	
			try(Connection conn = dataFactory.getConnection()) {  
				String writer_id = articleVO.getWriter_id();
				String writer_name = articleVO.getWriter_name();
				String title = articleVO.getTitle();
				String password = articleVO.getPassword();
				String query = "INSERT INTO article VALUES(ARTICLE_NO_SEQ.NEXTVAL,"  //sequence 이용해서 게시글 번호 증가
						+ "?,?,?,?,systimestamp,systimestamp,0)";  // insert query문
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, writer_id);
				pstmt.setString(2, writer_name);
				pstmt.setString(3, title);
				pstmt.setString(4, password);
				System.out.println(query);
				pstmt.executeUpdate();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// contentVO, 게시글 추가
		public void insertContentArticle(ContentVO contentVO) { 
			try(Connection conn = dataFactory.getConnection()) {
				String content = contentVO.getContent();
				String file_name = contentVO.getFile_name();
				String query = "INSERT INTO article_content VALUES(ARTICLE_NO_SEQ.CURRVAL,?,?)"; //sequence 이용해서 게시글 번호 증가
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, content);
				pstmt.setString(2, file_name);
				pstmt.executeUpdate();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// article_no와 password 받아서 게시글 삭제
		public void delArticle(String artNO, String inputPw) {
			try(Connection conn = dataFactory.getConnection()) {
				System.out.println(artNO);
				String query = "SELECT password\r\n" + "FROM article\r\n" + "WHERE article_no = '" + artNO + "'";
				pstmt = conn.prepareStatement(query);						//article_no 를 조건으로 password 추출쿼리
				pstmt.executeQuery();
				ResultSet rs = pstmt.executeQuery();
				String password = "";
				while (rs.next()) {											//해당 게시물의 password 저장
					password = rs.getString("password");
					System.out.println("pass: " + password);
				}
				System.out.println("입력받은 비번:" + inputPw);
				if (password.equals(inputPw)) {									//비밀번호가 같은때 삭제 쿼리 수행
					query = "delete from article where article_no = ?"; 		//article DB에서 삭제
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, artNO);
					pstmt.executeUpdate();
					query = "delete from article_content where article_no = ?"; //article_content DB에서 삭제
					pstmt = conn.prepareStatement(query);
					pstmt.setString(1, artNO);
					pstmt.executeUpdate();
				}
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

}
