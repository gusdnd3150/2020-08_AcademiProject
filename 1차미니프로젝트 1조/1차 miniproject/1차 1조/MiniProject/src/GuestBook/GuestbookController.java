package GuestBook;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/guestbook")
public class GuestbookController extends HttpServlet {
	GuestbookDAO guestbookDAO;

	public void init() throws ServletException {
		guestbookDAO = new GuestbookDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String command = request.getParameter("command");




		if (command.equals("/delGuestbook.do")) {
			String password = request.getParameter("password");
			if( guestbookDAO.delGuestbook(password) == 1) {
				request.setAttribute("command", "ok");
				RequestDispatcher dis = request.getRequestDispatcher("/static/Member/delEditGuestbookOk.jsp");
				dis.forward(request, response);
				return;
			}else {
				request.setAttribute("command", "notok");
				RequestDispatcher dis = request.getRequestDispatcher("/static/Member/delEditGuestbookOk.jsp");
				dis.forward(request, response);
				return;
			}

		} else if(command.equals("/checkGuest.do")){
			String password = request.getParameter("password");
			if(guestbookDAO.checkGuest(password)==null) {
				request.setAttribute("command", "notok");
				RequestDispatcher dis = request.getRequestDispatcher("/static/Member/delEditGuestbookOk.jsp");
				dis.forward(request, response);
				return;
			}else {
				GuestbookVO guestInfo = guestbookDAO.checkGuest(password);
				request.setAttribute("guestInfo", guestInfo);
				RequestDispatcher dis = request.getRequestDispatcher("/static/Member/editGuestbookForm.jsp");
				dis.forward(request, response);
				return;
			}

		} else {
			GuestbookPagingVO gp = new GuestbookPagingVO();
			int count = guestbookDAO.getAllCount();
			gp.setTotalCount(count);
			int page = 1;

			if(request.getParameter("page")!=null) {
				page = Integer.parseInt(request.getParameter("page"));}
			gp.setPage(page);
			List<GuestbookVO> guestbooksList = guestbookDAO.selectAllpage(gp);
			gp.setPage(page);
			request.setAttribute("guestbooksList", guestbooksList);
			request.setAttribute("paging", gp);
			RequestDispatcher dispatchar = request.getRequestDispatcher("/static/Member/listGuestbooks.jsp");
			dispatchar.forward(request, response);
		}
	}
}
