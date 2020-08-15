package controller.guestbook;

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

public class GuestbookDAO {
	private DataSource dataFactory;
	private PreparedStatement pstmt;

	public GuestbookDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<GuestbookVO>  guestbookList() {	// 전체 리스트 조회
		List<GuestbookVO> list = new ArrayList<GuestbookVO>();
		try(Connection conn = dataFactory.getConnection()) {
			String query = "select * from guestbook_message order by message_id desc";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int message_id = rs.getInt("message_id");
				String guest_name = rs.getString("guest_name");
				String password = rs.getString("password");
				String message = rs.getString("message");
				String fileName = rs.getString("fileName");
				GuestbookVO vo = new GuestbookVO(message_id, guest_name, password, message, fileName);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<GuestbookVO>  guestbookList(int pageNum) {	// 페이징 기준으로 리스트 조회
		List<GuestbookVO> list = new ArrayList<GuestbookVO>();
		try(Connection conn = dataFactory.getConnection()) {
			String query = " select rnum, A.message_id, A.guest_name, A.password, A.message, A.fileName \r\n" + 
					"     from (\r\n" + 
					"         select rownum as rnum, message_id, guest_name, password, message, fileName\r\n" + 
					"         from guestbook_message\r\n" + 
					"         order by message_id desc )A \r\n" + 
					" where rnum between ("+pageNum+"-1)*3+1 and "+pageNum+"*3";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int message_id = rs.getInt("message_id");
				String guest_name = rs.getString("guest_name");
				String password = rs.getString("password");
				String message = rs.getString("message");
				String fileName = rs.getString("fileName");
				GuestbookVO vo = new GuestbookVO(message_id, guest_name, password, message, fileName);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public GuestbookVO searchGuestbookVO(int id) {
		GuestbookVO resultVO = new GuestbookVO();
		List<GuestbookVO> list = this.guestbookList();	// 메시지 아이디에 따른 vo 정보 찾기
		for(GuestbookVO vo : list) {
			if(vo.getMessage_id()==id) {
				resultVO = vo;
				break;
			}
		}
		return resultVO;
	}
	
	public int totalGuestbook() {	// 총 게시글 수 조회
		try(Connection conn = dataFactory.getConnection()) {
			String query = "select count(message_id) from guestbook_message";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return (rs.getInt(1));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return 0;
	}
	private int getNewGuestbookNO() {	// 신규 게시글 번호
		try(Connection conn = dataFactory.getConnection()) {
			String query = "select max(message_id) from guestbook_message";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return (rs.getInt(1)+1);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return 0;
	}
	
	public void addGuestbook(GuestbookVO vo) {	// 방명록 작성
		try(Connection conn = dataFactory.getConnection()) {
			int guestNo = getNewGuestbookNO();
			String query = "insert into guestbook_message(message_id, guest_name, password, message, fileName)"
					+ " values(?, ?, ?, ?, ?)";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, guestNo);
			pstmt.setString(2, vo.getGuest_name());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getMessage());
			pstmt.setString(5, vo.getFileName());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delGuestbook(String message_id) {	// 방명록 삭제
		try(Connection conn = dataFactory.getConnection()) {
			String query = "delete from guestbook_message where message_id=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, message_id);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean guestPwdCheck(String inputPassword, String message_id){	// 비밀번호 correct 체크
		try(Connection conn = dataFactory.getConnection()) {
			System.out.println(inputPassword + " "+ message_id +"**");
			String query = "select password from guestbook_message where message_id=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, message_id);
			ResultSet rs = pstmt.executeQuery();
			String password ="";
			while (rs.next()) {
				password = rs.getString("password");
				System.out.println("pass: "+password);
			}
			if(password.equals(inputPassword))
				return true;			
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void editGuestbook(GuestbookVO editVO) {	// 방명록 수정
		try(Connection conn = dataFactory.getConnection()) {
			String query = "update guestbook_message set guest_name=?, password=?, message=?, fileName=? "
					+ " where message_id=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, editVO.getGuest_name());
			pstmt.setString(2, editVO.getPassword());
			pstmt.setString(3, editVO.getMessage());
			pstmt.setString(4, editVO.getFileName());
			pstmt.setString(5, editVO.getMessage_id()+"");
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
