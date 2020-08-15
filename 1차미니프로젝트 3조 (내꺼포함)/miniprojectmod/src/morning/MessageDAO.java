package morning;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.sql.DataSource;

import org.apache.catalina.tribes.MessageListener;

public class MessageDAO {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;
	
	public MessageDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<MessageVO> listMessages(int pageNum) { // 방명록 리스트
		List<MessageVO> messageList = new ArrayList<MessageVO>();
		
		try {
			conn = dataFactory.getConnection();
			String query ="select * from(select rownum rnum, b.*  from (select a.* from guestbook_message a order by message_id desc )b)c where rnum>=("+pageNum+"-1)*3+1 and rnum<=("+pageNum+")*3\r\n";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int message_id = rs.getInt("message_id");
				String guest_name = rs.getString("guest_name");
				String password = rs.getString("password");
				String message = rs.getString("message");
				String file = rs.getString("fileName");
				MessageVO memberVO = new MessageVO(message_id, guest_name, password, message, file);
				messageList.add(memberVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messageList;
	}
	
	public int totMessage() { // 방명록 전체 갯수
	      try (Connection conn = dataFactory.getConnection()){
	         String query="SELECT count(message_id) FROM guestbook_message";
	         System.out.println(query);
	         pstmt = conn.prepareStatement(query);
	         ResultSet rs = pstmt.executeQuery();
	         if(rs.next()) 
	            return (rs.getInt(1));
	         rs.close();
	         pstmt.close();
	         conn.close();
	      } catch(Exception e) {
	         e.printStackTrace();
	      }
	      return 0;
	   }

	public List<MessageVO> listMessages2(int id) { // 방명록 수정 조회
		List<MessageVO> messageList2 = new ArrayList<MessageVO>();
		
		try {
			conn = dataFactory.getConnection();
			String query = "select * from guestbook_message where message_id = ";
			query += "'"+id+"'";
			System.out.println(id);
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int message_id = rs.getInt("message_id");
				String guest_name = rs.getString("guest_name");
				String password = rs.getString("password");
				String message = rs.getString("message");
				String file = rs.getString("fileName");
				MessageVO memberVO = new MessageVO(message_id, guest_name, password, message);
				messageList2.add(memberVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messageList2;
	}
	
	public void addMessage(MessageVO m) { // 방명록 추가
		try {
			conn = dataFactory.getConnection();
			String guest_name = m.getGuestName();
			String password = m.getPassword();
			String message = m.getMessage();
			String file = m.getFileName();
			System.out.println(message);
			String query = "INSERT INTO guestbook_message" + " VALUES(MESSAGE_ID_SEQ.NEXTVAL, ? ,? ,?, ?)";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, guest_name);
			pstmt.setString(2, password);
			pstmt.setString(3, message);
			pstmt.setString(4, file);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteMessage(int id) { // 방명록 삭제
		try {
			conn = dataFactory.getConnection();
			String query = "delete from guestbook_message where message_Id =";
			query += "'" + id +"'";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			System.out.println(query);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void editMessage(String name, String password, String message, int id) { // 방명록 수정
		try {
			conn = dataFactory.getConnection();
			System.out.println(message);
			System.out.println(name);
			System.out.println(password);
			String query = "update guestbook_message set guest_name=?, password=?, message=? where message_Id = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			pstmt.setString(3, message);
			pstmt.setInt(4, id);
			System.out.println(query);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MessageVO getMessage(int id) { // 방명록 파일 첨부
		MessageVO message = new MessageVO();
		try {
			String query = "SELECT * FROM guestbook_message WHERE message_id = ?";
			conn = dataFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				message = new MessageVO(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4));
			}
			rs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

}