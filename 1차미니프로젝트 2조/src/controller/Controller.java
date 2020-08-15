package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.member.MemberVO;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/index")
public class Controller extends HttpServlet {
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = null;
		String action = request.getPathInfo();	// url에서 요청명을 가져옴
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("action : "+action);
		boolean isLogined = false;
		HttpSession session = request.getSession(false);
		System.out.println("session : "+session);
		if(session != null) {
			try {
				isLogined = (boolean)session.getAttribute("isLogined");
				System.out.println("isLogined : "+isLogined);
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			if(isLogined == true) {
				nextPage = "/memberIndex.jsp";
			}else {
				nextPage = "/index.jsp";
			}
		}else {
			nextPage = "/index.jsp";
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
