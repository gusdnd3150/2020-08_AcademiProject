package controller.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/members/*")
public class MembersController extends HttpServlet {


	private void doHandle(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		MemberDAO memberDAO = new MemberDAO();
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		PrintWriter out = response.getWriter();

		String action = request.getPathInfo();
		System.out.println("action" + action);

		//회원가입
		if (action.equals("/addMember.do")) {
			String member_id = request.getParameter("member_id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			MemberVO memberVO = new MemberVO(member_id,password,name);
			memberDAO.addMember(memberVO);
			nextPage= "/members/listMembers.do";
			//가입 누르면 가입확인jsp로 
		}else if (action.equals("/join.do")){
			String name =request.getParameter("name");
			String password = request.getParameter("password");
			String member_id = request.getParameter("member_id");
			String checkPw = request.getParameter("checkPw");

			// 아이디체크, 비밀번호체크 전부 통과했을경우
			if(!memberDAO.idCheck(member_id) && checkPw.equals(password)){
				MemberVO memberVO = new MemberVO();
				memberVO.setMember_id(member_id);
				memberVO.setName(name);
				memberVO.setPassword(password);
				memberDAO.addMember(memberVO);
				out.print("<html><body>");
				out.print("<h3>"+name+"님,회원가입에 성공했습니다.</h3><br>");
				out.print("<a href='/pilotProject/member/login.jsp'>로그인하기</a>");
				out.print("</body></html>");
				out.close();
				return;

			}else {
				nextPage = "/member/joinOk.jsp";
				String idCheck="", pwdCheck="";
				if(memberDAO.idCheck(member_id)){
					idCheck = " 이미 사용중인 아이디입니다.";
				}
				if(!checkPw.equals(password)){
					pwdCheck =" 암호와 확인이 일치하지 않습니다.";
				}
				request.setAttribute("idCheck",idCheck);
				request.setAttribute("pwdCheck",pwdCheck);
			}

			//로그인 할 때 
		}else if(action.equals("/login.do")) {
			String member_id=request.getParameter("member_id");
			String password = request.getParameter("password");

			if(memberDAO.login(member_id, password)) { // 로그인 체크 true일 때
				MemberVO loginedMember = memberDAO.voReturn(member_id);
				// session에 loginedMember 이름으로 로그인 멤버 정보 set
				HttpSession session = request.getSession();
				session.setAttribute("isLogined", true);
				session.setAttribute("loginedMember", loginedMember);
			}
			nextPage="/index";

			//로그아웃 세션 종료
		}else if (action.equals("/logout.do")) { 
			System.out.println("로그아웃*****");
			request.getSession(false).invalidate();
			nextPage = "/index";

			//memberIndex에서 비밀번호 변경 누르면 changePw jsp로 이동 
		}else if(action.equals("/changePw.do")) {
			nextPage = "/member/changePw.jsp";

			//비밀번호 변경
		}else if(action.equals("/changeOk.do")) {
			HttpSession session = request.getSession(false);
			MemberVO loginedMember = (MemberVO) session.getAttribute("loginedMember");
			System.out.println(loginedMember.getMember_id());

			String member_id = loginedMember.getMember_id();

			String nowPw = request.getParameter("nowPw"); //현재 비밀번호
			String newPw = request.getParameter("newPw"); //변경할 비밀번호

			// 현재비밀번호 체크
			if(memberDAO.login(member_id, nowPw)) {
				// 비밀번호 변경
				memberDAO.changePw(member_id, newPw);
				request.getSession(false).invalidate();
				nextPage = "/member/login.jsp";
			}else {	// 현재비밀번호랑 맞지 않을 경우
				nextPage = "/member/changePw.jsp";
			}

		}

		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request,response);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

}
