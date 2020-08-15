package memberjoin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/mp")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doHandler(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandler(request, response);
	}

	private void doHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		MemberDAO dao = new MemberDAO();
		
		// 중복 부분
		String _id = (String) request.getParameter("id");
		String name = request.getParameter("name");
		String pwd = request.getParameter("pwd");
		String pwd2 = request.getParameter("pwd2");
		System.out.println(_id + " / " + pwd + " / " + pwd2 + " / " + name);
		
		// ID.PW 참 거짓  = DAO.Java의 overlappedID 
		boolean overlappedID = dao.overlappedID(_id);
		
		if(overlappedID == true && pwd.equals(pwd2)) { // 아이디 중복 && 암호 일치
			out.print("not_usable_id");
		} else if(overlappedID == false && !pwd.equals(pwd2)) {	// 아이디 가능 & 암호 불일치
			out.print("not_usable_pwd");
		} else if(overlappedID == true && !pwd.equals(pwd2)) {	// 아이디 가능 && 암호 불일치
			out.print("not_usable");
		} else {	// 아이디 가능 && 암호 일치
			out.print("usable");
			
			// 회원가입 부분 (아이디, 이름, 비밀번호) 
			MemberVO vo = new MemberVO();
			vo.setMember_id(_id);
			vo.setName(name);
			vo.setPassword(pwd);
			dao.addMember(vo);
		}
	}

}