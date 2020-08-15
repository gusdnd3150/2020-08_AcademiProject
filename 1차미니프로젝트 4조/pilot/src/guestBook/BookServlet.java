package guestBook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/book")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String command = request.getParameter("command");
		BookDAO dao = new BookDAO();
		List<BookVO> list = new ArrayList<>();
		String page = request.getParameter("page");
		//get 방식으로 표시할 페이지(방명록 페이징)를 받아온다.
		if(page == null) {page="1";}
		if (command != null) { //컨트롤러는 hidden타입 command로 조작
			if (command.equals("modify")) { //글 수정 동작
				command = "";
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				BookVO vo = dao.getPassword(id);
				if (vo.getPw().equals(pw)) {
					request.setAttribute("vo", vo);
					RequestDispatcher dispatch = request.getRequestDispatcher("modify.jsp");
					dispatch.forward(request, response);
					//비밀번호 일치시 수정 페이지로 디스패치
				} else {
					out.write("<html><body>");
					out.write("비밀번호가 다릅니다.<br><a href='./book'>[목록 보기]</a>");
					out.write("</body></html>");
					//다를시 초기 화면으로 되돌려주는 html tag 출력
				}
				return;
			} else if (command.equals("delete")) { //삭제 동작
				command = "";
				String id = request.getParameter("id");
				String pw = request.getParameter("pw");
				BookVO vo = dao.getPassword(id); // 이후 수정과 동일
				if (vo.getPw().equals(pw)) {
					dao.delGuestbook(id);
					out.write("<html><body>");
					out.write("삭제완료.<br><a href='./book'>[목록 보기]</a>");
					out.write("</body></html>");
				} else {
					out.write("<html><body>");
					out.write("비밀번호가 다릅니다.<br><a href='./book'>[목록 보기]</a>");
					out.write("</body></html>");
				}
				return;
			} else if (command.equals("modify2")) { //수정.jsp에서 submit시 이쪽으로 들어옴
				String id = request.getParameter("id");
				String name = request.getParameter("name");
				String pw = request.getParameter("pw");
				String msg = request.getParameter("msg");
				if (name != null && name.length() != 0 && pw != null && pw.length() != 0 && msg != null
						&& msg.length() != 0) {
					dao.modGuestbook(id, name, pw, msg); // update query 실행
					command = "";
					out.write("<html><body>");
					out.write("메세지 수정 완료.<br><a href='./book'>[목록 보기]</a>");
					out.write("</body></html>");
					return;
				} else {
					command = "";
					out.write("<html><body>");
					out.write("오류 : 모든 항목을 입력해주세요.<br><a href='./book'>[목록 보기]</a>");
					out.write("</body></html>");
					return;
				}
			}
		}

		//list = dao.lookUp();
		list = dao.pageLookUp(page); //요청받은 page에 해당하는 글들을 리스트에 넣는다.
		request.setAttribute("list", list); //디스패치로 쏜다. view 페이지로~~
		request.setAttribute("size", dao.lookUp().size());
		RequestDispatcher dispatch = request.getRequestDispatcher("guestBook.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
