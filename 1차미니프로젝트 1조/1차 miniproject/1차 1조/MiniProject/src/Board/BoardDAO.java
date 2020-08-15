package Board;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

    private Connection con;
    private PreparedStatement pstmt;
    private DataSource dataFactory;


    private static final String BOARD_INSERT = "insert all into ARTICLE values (ARTICLE_SEQ.nextval, ?, ?, ?, ?,sysdate,sysdate,0) into ARTICLE_CONTENT values(ARTICLE_SEQ.nextval, ?, ?) select * from DUAL";
    private static final String BOARD_GET_BY_PAGING = "select * from ( select * from (select ROWNUM as row_num,article.* from article ORDER BY ARTICLE_NO DESC )where row_num >= ? )where row_num <= ?";
    private static final String BOARD_UPDATE_CNT = "update article set read_cnt=read_cnt+1 where article_no=?";
    private static final String BOARD_GET_CNT = "SELECT COUNT(*) FROM article";
    private static final String BOARD_GET_CONTENT = "select article.article_no, ARTICLE.writer_name, ARTICLE.title,ARTICLE_CONTENT.CONTENT,ARTICLE_CONTENT.FILENAME from article, article_content where article.article_no = ? AND article_content.article_no = ?";
    private static final String BOARD_DELETE_CONTENT_1 = "delete from article where article_no=?";
    private static final String BOARD_DELETE_CONTENT_2 = "delete from ARTICLE_CONTENT where ARTICLE_NO=?";
    private static final String BOARD_UPDATE_CONTENT_1 = "update article set title=?,moddate=sysdate where article_no=?";
    private static final String BOARD_UPDATE_CONTENT_2 = "update article_content set content=? where article_no=?";
    private static final String CHECK_PWD = "select password from article where article_no = ?";
    private static final String FILE_UPLOAD = "update ARTICLE_CONTENT set FILENAME=? where ARTICLE_NO=?";

    public BoardDAO() {
        try {
            Context ctx = new InitialContext();
            Context envContext = (Context) ctx.lookup("java:/comp/env");
            dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertBoard(BoardVO vo) {
        try {
            con = dataFactory.getConnection();
            pstmt = con.prepareStatement(BOARD_INSERT);
            pstmt.setString(1, vo.getWriter_id());
            pstmt.setString(2, vo.getWriter_name());
            pstmt.setString(3, vo.getTitle());
            pstmt.setString(4, vo.getPassword());
            pstmt.setString(5, vo.getContent());
            pstmt.setString(6, vo.getFileName());

            ResultSet rs = pstmt.executeQuery();

            rs.close();
            pstmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BoardVO getBoardContent(String article_no) {
        BoardVO vo = new BoardVO();
        try {
            con = dataFactory.getConnection();
            pstmt = con.prepareStatement(BOARD_GET_CONTENT);
            pstmt.setString(1, article_no);
            pstmt.setString(2, article_no);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String article_num = rs.getString("article_no");
                String writer_name = rs.getString("writer_name");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String fileName = rs.getString("fileName");

                vo.setArticle_no(article_num);
                vo.setWriter_name(writer_name);
                vo.setTitle(title);
                vo.setContent(content);
                vo.setFileName(fileName);

            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }

    public List<BoardVO> getBoardList() {
        List<BoardVO> list = new ArrayList<>();
        try {
            con = dataFactory.getConnection();
            String query = "SELECT * FROM article JOIN article_content ON article.article_no=article_content.article_no";
            pstmt = con.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String article_no = rs.getString("article_no");
                String writer_id = rs.getString("writer_id");
                String writer_name = rs.getString("writer_name");
                String title = rs.getString("title");
                String password = rs.getString("password");
                String regdate = rs.getString("regdate");
                String moddate = rs.getString("moddate");
                int read_cnt = rs.getInt("read_cnt");

                BoardVO vo = new BoardVO();
                vo.setArticle_no(article_no);
                vo.setWriter_id(writer_id);
                vo.setWriter_name(writer_name);
                vo.setTitle(title);
                vo.setPassword(password);
                vo.setRegdate(regdate);
                vo.setModdate(moddate);
                vo.setRead_cnt(read_cnt);

                list.add(vo);
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<BoardVO> getBoardListWithPaging(int currentPage) {
        List<BoardVO> list = new ArrayList<>();

        int startNum = (currentPage-1)*10+1;
        int endNum = currentPage*10;
        try {
            con = dataFactory.getConnection();

            // sql = select * from ( select rownum as row_num,article_no from article) where row_num >= 11 AND row_num <=20 // 성능 문제 야기
            // sql = select * from ( select rownum as row_num,article_no from article where row_num >= 11) where row_num <= 20 // best
            pstmt = con.prepareStatement(BOARD_GET_BY_PAGING);
            pstmt.setInt(1, startNum);
            pstmt.setInt(2, endNum);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String row_num = rs.getString("row_num");
                String article_no = rs.getString("article_no");
                String writer_id = rs.getString("writer_id");
                String writer_name = rs.getString("writer_name");
                String title = rs.getString("title");
                String password = rs.getString("password");
                String regdate = rs.getString("regdate");
                String moddate = rs.getString("moddate");
                int read_cnt = rs.getInt("read_cnt");

                BoardVO vo = new BoardVO();
                vo.setRow_num(row_num);
                vo.setArticle_no(article_no);
                vo.setWriter_id(writer_id);
                vo.setWriter_name(writer_name);
                vo.setTitle(title);
                vo.setPassword(password);
                vo.setRegdate(regdate);
                vo.setModdate(moddate);
                vo.setRead_cnt(read_cnt);

                list.add(vo);
            }

            rs.close();
            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateBoardCnt(String article_no){
        try {
            con = dataFactory.getConnection();
            pstmt = con.prepareStatement(BOARD_UPDATE_CNT);
            pstmt.setString(1, article_no);

            ResultSet rs = pstmt.executeQuery();

            rs.close();
            pstmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getBoardCnt(){
        int count = 0;
        try {
            con = dataFactory.getConnection();
            pstmt = con.prepareStatement(BOARD_GET_CNT);

            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                count = rs.getInt(1);
            }

            rs.close();
            pstmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public void deleteContent(int article_no){
        try {
            con = dataFactory.getConnection();
            pstmt = con.prepareStatement(BOARD_DELETE_CONTENT_1);
            pstmt.setInt(1, article_no);
            pstmt.executeQuery();

            pstmt = con.prepareStatement(BOARD_DELETE_CONTENT_2);
            pstmt.setInt(1, article_no);
            pstmt.executeQuery();

            pstmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateContent(BoardVO vo){
        try {
            con = dataFactory.getConnection();

            pstmt = con.prepareStatement(BOARD_UPDATE_CONTENT_1);
            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getArticle_no());
            pstmt.executeQuery();

            pstmt = con.prepareStatement(BOARD_UPDATE_CONTENT_2);
            pstmt.setString(1, vo.getContent());
            pstmt.setString(2, vo.getArticle_no());
            pstmt.executeQuery();

            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPassword(String article_no){
        String password =null;
        try {
            con = dataFactory.getConnection();

            pstmt = con.prepareStatement(CHECK_PWD);
            pstmt.setString(1, article_no);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                password = rs.getString("password");
            }

            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }

    public int uploadFile(String file,String article_no){
        try {
            con = dataFactory.getConnection();

            pstmt = con.prepareStatement(FILE_UPLOAD);
            pstmt.setString(1, file);
            pstmt.setString(2,article_no);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()){
                return 1;
            }

            pstmt.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}


