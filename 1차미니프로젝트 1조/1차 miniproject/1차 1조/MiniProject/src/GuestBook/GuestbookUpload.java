package GuestBook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/upload")
public class GuestbookUpload extends HttpServlet{
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String uploadPath = "/Users/nogari03/Desktop";
		System.out.println(uploadPath);
		MultipartRequest multi = new MultipartRequest(req,uploadPath,1024*1024*3,
				"utf-8",new DefaultFileRenamePolicy());
		String command =  multi.getParameter("command");
		
		if("editGuestbook.do".equals(command)) {
			String message_id = multi.getParameter("message_id");
			String guest_name = multi.getParameter("guest_name");
			String message = multi.getParameter("message");
			String fileName = multi.getFilesystemName("fileName");
			System.out.println("수정");
			GuestbookDAO dao = new GuestbookDAO();
			GuestbookVO vo = new GuestbookVO();
			vo.setMessage_id(message_id);
			vo.setGuest_name(guest_name);
			vo.setMessage(message);
			vo.setFileName(fileName);
			dao.editGuest(vo);
			resp.sendRedirect("/guestbook?command='12'");
			
			
		}else if ("add".equals(command)) {
			String guest_name = multi.getParameter("guest_name");
			String password = multi.getParameter("password");
			String message = multi.getParameter("message");
			String fileName = multi.getFilesystemName("fileName");
			
			System.out.println("파일 업로드  컨트롤러");
			
			GuestbookVO vo = new GuestbookVO();
			vo.setGuest_name(guest_name);
			vo.setPassword(password);
			vo.setMessage(message);
			vo.setFileName(fileName);
			GuestbookDAO dao = new GuestbookDAO();
			dao.addGuestbook(vo);
		
			resp.sendRedirect("/static/Member/addGuestbookOk.jsp");
		}
	
		
	
	}
	

}
