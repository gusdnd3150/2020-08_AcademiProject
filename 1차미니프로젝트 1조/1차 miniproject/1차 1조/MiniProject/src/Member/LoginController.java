package Member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Login", urlPatterns = { "/Login" })
public class LoginController extends HttpServlet {

	public LoginController() {
		super();

	}

	public void init(ServletConfig config) throws ServletException {

	}

	public void destroy() {

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
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String command = request.getParameter("command");
		String member_id = request.getParameter("member_id");
		String password = request.getParameter("password");
		MemberDAO dao = new MemberDAO();
		MemberVO vo = new MemberVO();
		vo.setMember_id(member_id);
		vo.setPassword(password);
		String rst= dao.logInCheck(vo) ;
		if (!rst.equals("")) {
			session.setAttribute("member_id", member_id);
			session.setAttribute("name", rst);
			response.sendRedirect("/static/main.jsp");

		} else if (rst.equals("")) {
			request.setAttribute("command", "NotPassword");
			RequestDispatcher dis = request.getRequestDispatcher("/static/Member/checkMember.jsp");
			dis.forward(request, response);
			return;
		}

	}
}
