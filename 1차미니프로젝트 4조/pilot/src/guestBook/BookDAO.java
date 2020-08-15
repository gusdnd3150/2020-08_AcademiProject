package guestBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BookDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;

	public BookDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");// jdbc/oracle
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//전체 게시글 조회 메소드(select query)
	public List<BookVO> lookUp() {
		List<BookVO> list = new ArrayList<>();
		try {
			con = dataFactory.getConnection();
			String query = "select * from GUESTBOOK_MESSAGE ORDER BY message_id DESC ";
			//글번호 기준 내림차순 정렬 -> 최신 글이 list의 앞쪽에 들어가도록. 최신 글을 위쪽에 보여주기 위함.
			System.out.println("PrepareStatement : " + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("message_id");
				String name = rs.getString("guest_name");
				String pw = rs.getString("password");
				String msg = rs.getString("message");
				String path = rs.getString("filepath");
				BookVO b = new BookVO();
				b.setId(id);
				b.setName(name);
				b.setPw(pw);
				b.setMsg(msg);
				b.setPath(path);
				list.add(b);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//특정 페이지 조회 메소드(다중 select)
	public List<BookVO> pageLookUp(String page) {
		List<BookVO> list = new ArrayList<>();
		int idx;
		if(page.equals("1")) {
			idx = 1;
		}else {
			idx = (Integer.parseInt(page) * 3)-2;
		}
		//요청받은 페이지에 따라서 특정 인덱스의 객체들을 검색하도록 수식을 짰다. idx 변수 활용.
		//이 방명록의 경우, 한 페이지에 세개의 글을 보여줄 예정. 즉 3페이지라면 7,8,9번째 글이 표시됨
		//따라서 7번째 글부터 리스트에 넣도록 계산을 해준다. 3*3-2 = 7
		try {
			con = dataFactory.getConnection();
			String query = "select message_id, guest_name, password, message, filepath " + 
					"from " + 
					"       ( select seq, message_id, guest_name, password, message, filepath " + 
					"        from" + 
					"            (" + 
					"                select rownum as seq, message_id, guest_name, password, message, filepath" + 
					"                from" + 
					"                    (" + 
					"                        select *" + 
					"                        from guestbook_message" + 
					"                        order by message_id desc" + 
					"                    )" + 
					"            )" + 
					"        where seq >="+idx + 
					"    ) " + 
					"where rownum <=3";
			System.out.println("PrepareStatement : " + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("message_id");
				String name = rs.getString("guest_name");
				String pw = rs.getString("password");
				String msg = rs.getString("message");
				String path = rs.getString("filepath");
				BookVO b = new BookVO();
				b.setId(id);
				b.setName(name);
				b.setPw(pw);
				b.setMsg(msg);
				b.setPath(path);
				list.add(b);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//비밀번호 확인 메소드(select 이후 .equals)
	public BookVO getPassword(String _id) {
		BookVO vo = new BookVO();
		try {
			con = dataFactory.getConnection();
			String query = "select * from GUESTBOOK_MESSAGE WHERE message_id='"+_id+"'";
			System.out.println("PrepareStatement : " + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String id = rs.getString("message_id");
				String name = rs.getString("guest_name");
				String pw = rs.getString("password");
				String msg = rs.getString("message");
				String path = rs.getString("filepath");
				vo.setId(id);
				vo.setName(name);
				vo.setPw(pw);
				vo.setMsg(msg);
				vo.setPath(path);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	//글 작성 메소드(insert query)
	public void addGuestbook(int id, String name, String pw, String msg, String path) {
		try {
			con = dataFactory.getConnection();
			String query = "insert into guestbook_message";
			query += " (message_id,guest_name,password,message,filePath)";
			query += " values(?,?,?,?,?)";
			System.out.println("prepareStatement : " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, pw);
			pstmt.setString(4, msg);
			pstmt.setString(5, path);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//글 수정 메소드(update query)
	public void modGuestbook(String id, String name, String pw, String msg) {
		try {
			con = dataFactory.getConnection();
			String query = "update guestbook_message set";
			query += " guest_name='"+name+"'";
			query += ",password='"+pw+"'";
			query += ",message='"+msg+"'";
			query += " WHERE message_id='"+id+"'";
			System.out.println("prepareStatement : " + query);
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//글 삭제 메소드(delete query)
	public void delGuestbook(String id) {
		try {
			con = dataFactory.getConnection();
			String query = "DELETE FROM guestbook_message WHERE message_id = '" + id + "' ";
			System.out.println("prepareStatement:" + query);
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
