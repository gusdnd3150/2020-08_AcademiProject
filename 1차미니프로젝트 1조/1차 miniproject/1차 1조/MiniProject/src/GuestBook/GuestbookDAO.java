package GuestBook;

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
	public static final int MESSAGE_NOUNT_PER_PAGE = 3;
	public static final String GUESTBOOK_EDIT_UPDATE = "GUESTBOOK_MESSAGE set guest_name=?, message=?, fileName=? where message_id=?";
	public static final String GUESTBOOK_EDIT_CHECK = "select * from GUESTBOOK_MESSAGE where password = ?";
	public static final String GUESTBOOK_DEL_CHECKING = "select * from GUESTBOOK_MESSAGE where password=?";
	public static final String GUESTBOOK_DEL_STARTING = "delete from GUESTBOOK_MESSAGE where password=?";
	public static final String GUESTBOOK_ADD = "INSERT INTO GUESTBOOK_MESSAGE(Message_id, guest_name, password, message, filename) VALUES(guestbook_seq.nextval, ?, ?, ?, ?)";
	private static final String GUESTBOOK_GET_BY_PAGING = "SELECT * from (select * from (select ROWNUM as row_num, guestbook_message.* from guestbook_message order by message_id desc) where row_num >=? )where row_num <=?";
	private static final String GUESTBOOK_GET_BY_ALLCOUNT = "SELECT COUNT(*) as count FROM guestbook_message";
	private DataSource dataFactory;
	private Connection conn;
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

	public GuestbookVO checkGuest(String password) {
		GuestbookVO guestInfo = null;

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(GUESTBOOK_EDIT_CHECK);

			pstmt.setString(1, password);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String message_id = rs.getString("message_id");
			String guest_name = rs.getString("guest_name");
			String password2 = rs.getString("password");
			String message = rs.getString("message");
			guestInfo = new GuestbookVO(message_id, guest_name, password2, message);
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return guestInfo;
	}

	public void editGuest(GuestbookVO guestbookVO) {

		try {
			conn = dataFactory.getConnection();
			String guest_name = guestbookVO.getGuest_name();
			String message = guestbookVO.getMessage();
			String message_id = guestbookVO.getMessage_id();
			String fileName = guestbookVO.getFileName();

			pstmt = conn.prepareStatement(GUESTBOOK_EDIT_UPDATE);
			pstmt.setString(1, guest_name);
			pstmt.setString(2, message);
			pstmt.setString(3, fileName);
			pstmt.setString(4, message_id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public int delGuestbook(String password) {
		int result = 0;
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(GUESTBOOK_DEL_CHECKING);
			pstmt.setString(1, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = 1;
				pstmt = conn.prepareStatement(GUESTBOOK_DEL_STARTING);
				pstmt.setString(1, password);
				pstmt.executeUpdate();
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void addGuestbook(GuestbookVO m) {
		try {
			conn = dataFactory.getConnection();
			String guest_name = m.getGuest_name();
			String password = m.getPassword();
			String message = m.getMessage();
			String fileName = m.getFileName();
			pstmt = conn.prepareStatement(GUESTBOOK_ADD);
			pstmt.setString(1, guest_name);
			pstmt.setString(2, password);
			pstmt.setString(3, message);
			pstmt.setString(4, fileName);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<GuestbookVO> selectAllpage(GuestbookPagingVO gp) { // 페이징 처리된 목록 보기
		int page = gp.getPage();
		int startNum = gp.getStartNum();
		int endNum = gp.getEndNum();
		List<GuestbookVO> list = new ArrayList<GuestbookVO>();
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(GUESTBOOK_GET_BY_PAGING);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			ResultSet rs = pstmt.executeQuery();
			System.out.println(GUESTBOOK_GET_BY_PAGING);
			while (rs.next()) {
				GuestbookVO vo = new GuestbookVO();
				vo.setMessage_id(rs.getString("message_id"));
				vo.setGuest_name(rs.getString("guest_name"));
				vo.setPassword(rs.getString("password"));
				vo.setMessage(rs.getString("message"));
				vo.setFileName(rs.getString("filename"));
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

	public int getAllCount() { // 페이징 처리하기 위한 방명록 개수 계
		int count = 0;
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(GUESTBOOK_GET_BY_ALLCOUNT);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("count");
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
}
