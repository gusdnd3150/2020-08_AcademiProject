package member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
     MemberDAO memberDAO;
     
    public MemberController() {
    }
    public void init() throws ServletException{
    	memberDAO = new MemberDAO();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		MemberDAO dao = new MemberDAO();
		HttpSession session = request.getSession();
		String nextPage = null;
		String action = request.getPathInfo();
		System.out.println(action);
		
		if(action == null || action.equals("/login.do")) {
			 String id = request.getParameter("id");
			 String pw = request.getParameter("pw");
			 System.out.println(id);
			 if(dao.login(id,pw)) {
				   session.setAttribute("loginUser", dao.voReturn(id));  //login을 성공하면 MemberVO에 담은 정보 가져옴
//				   session.setAttribute("isLogon", true);
//				   nextPage ="/project/member/result.jsp";
				   response.sendRedirect("../result.jsp"); //로그인 후 결과페이지(result.jsp) 이동
			   }else {
				   response.sendRedirect("../loginfail.jsp"); //아이디나 비빌번호가 틀리면 로그인 실패페이지(loginfail.jsp)로 이동
			   }
		}else if(action.equals("/logout.do")) {//로그아웃
			request.getSession().invalidate();//세션만료
			response.sendRedirect("../login.jsp");//로그아웃 후 로그인 페이지 이동
		}else if(action.equals("/changePwd.do")) {//비밀번호변경
			 String id = request.getParameter("id");
			   String pw = request.getParameter("pw");
	           String crtPw = request.getParameter("crtPw");
	           String newPw = request.getParameter("newPw");
	           dao.changePwd(id, pw, crtPw, newPw);//비밀번호 변경 메소드 호출
	           request.getSession().invalidate();//세션만료
	           response.sendRedirect("../login.jsp");	//비밀번호 변경완료 후 로그인페이지로 이동	
	           }
    	}
}
