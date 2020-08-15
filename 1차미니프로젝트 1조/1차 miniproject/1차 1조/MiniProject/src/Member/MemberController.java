package Member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member")
public class MemberController extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberDAO memberDAO = new MemberDAO();
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String command = request.getParameter("command");

		if (command.equals("check.do")) { // 회원가입 중복
			String member_id = request.getParameter("member_id");
			int check = memberDAO.idCheck(member_id);
			if (check == 1) {
				boolean idDup = true;
				out.print(1);
			} else if (check == 0) {
				out.print(0);
			}

		} else if (command.equals("addMember.do")) { // 회원가입 컨트롤
			MemberVO memberVO = new MemberVO();
			String member_id = request.getParameter("member_id");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			memberVO.setMember_id(member_id);
			memberVO.setName(name);
			memberVO.setPassword(password);
			memberDAO.addMember(memberVO);
			request.setAttribute("member_id", member_id);
			request.setAttribute("name", name);
			RequestDispatcher dis = request.getRequestDispatcher("/static/Member/addMember.jsp");
			dis.forward(request, response);

		} else if (command.equals("delMember.do")) {// 회원탈퇴 컨트롤
			String member_id = request.getParameter("member_id"); // 삭제 요청시 아이디, 비밀번호 받음
			String password = request.getParameter("password");
			MemberVO memberVO = new MemberVO();
			MemberDAO dao = new MemberDAO();
			memberVO.setMember_id(member_id);
			memberVO.setPassword(password);
			int rst = dao.delMember(memberVO);
			if (rst == 0) {
				request.setAttribute("command", "NotDel");
				RequestDispatcher dis = request.getRequestDispatcher("/static/Member/checkMember.jsp");
				dis.forward(request, response);
				return;
			} else if (rst == 1) {
				request.setAttribute("command", "OkDel");
				RequestDispatcher dis = request.getRequestDispatcher("/static/Member/logOut.jsp");
				dis.forward(request, response);
				return;
			}

		} else if (command.equals("editPwd.do")) { // 암호변경
			MemberDAO dao = new MemberDAO();
			MemberVO memberVO = new MemberVO();
			String password = request.getParameter("password");
			String newPassword = request.getParameter("newPassword");
			int rst = dao.pwdCheck(password);
			if (rst == 0) {
				request.setAttribute("command", "NotPassword");
				RequestDispatcher dis = request.getRequestDispatcher("/static/Member/checkMember.jsp");
				dis.forward(request, response);
				return;
			} else if (rst == 1) {
				memberVO.setPassword(password);
				dao.editPwd(memberVO, newPassword);
				request.setAttribute("command", "OkPassword");
				RequestDispatcher dis = request.getRequestDispatcher("/static/Member/checkMember.jsp");
				dis.forward(request, response);
				return;
			}
		}
	}
}