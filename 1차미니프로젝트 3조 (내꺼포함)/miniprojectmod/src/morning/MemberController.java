package morning;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/MemberController/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		MemberDAO dao = new MemberDAO();
		String command = request.getParameter("command");
		
		String nextPage = null;
		System.out.println("커멘드값"+command);
		
		String result = "";

		if ("modify".equals(command)) {                                   //비밀번호 변경~~~~~~~~~~~~~~~~
			HttpSession session = request.getSession();
			System.out.println(dao.listMembers().size());

			MemberVO vo = (MemberVO) session.getAttribute("member");// 세션에있는 member정보를 VO객체로 받아옴
			String pwd = request.getParameter("modiPass");       //변경할 비밀번호를 받아옴
			vo.setPassword(pwd);                     //set을통해 vo객체의 비밀번호를 수정
			dao.modPassward(vo);             // dao에 modPassward메서드를통해 파라미터의 vo객체를 토대로 sql문실행
			
			nextPage = "main.jsp";

            session.invalidate();   //암호 변경 후 로그아웃 (세션에있는 정보를 삭제 해주어야함)

		} else if ("logout".equals(command)) {                           // 로그아웃~~~~~~~~~~~~~~~~~~~~~~~~
			HttpSession session = request.getSession();
			session.invalidate();      //세션 저장소 내용을 삭제
			
			nextPage="main.jsp";
			
		} else if ("logins".equals(command)) {                              //로그인~~~~~~~~~~~~~~~~~~~~~~~~
			String _id = request.getParameter("_id");    //클라이언트로부터 요청값을 받아온다
			String _pwd = request.getParameter("_pwd");
			
			List<MemberVO> listMembers = (ArrayList) dao.listMembers();
			//요청값을 비교해주기 위해 회원전체 리스트메서드를 재활용

			for (int i = 0; i < listMembers.size(); i++) {
				System.out.println(listMembers.size());        //list값을 체크하기위해 테스트용으로 넣음
            System.out.println(listMembers.get(i).toString());
           
				if (listMembers.get(i).getMemberId().equals(_id) && listMembers.get(i).getPassword().equals(_pwd)) {
					 //요청받은 id,pwd 값과 list안의 값이 일치한다면 로그인 성공
					
					
					System.out.println("listMembers id체크:" + listMembers.get(i).toString());
					
					
					HttpSession session = request.getSession();
					session.setAttribute("member", listMembers.get(i));
					// 성공 후 해당 list 객체를 세션에 추가
					
					result = "success"; 
					// 성공시 result good으로 설정 
				}
			}
			
			if(result != "success") {
				result ="fail";// good이 아닐경우 fail
			}
			
			nextPage ="loginView.jsp";
			System.out.println(result); //result값 체크용
			
			
			
		}else if ("joinForm".equals(command)) {           
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String pwd = request.getParameter("pwd1");
			
			MemberVO memberVO = new MemberVO(id, name, pwd);
			System.out.println("ddddddddddddddddddddddddddddddd");
			//기존에 등록된 아이디가 있는지 찾는다.
			Boolean b_chk = dao.checkId(id);
			
			System.out.println("rrrrrrrrrrrrr");
			if(b_chk) {//같은 아이디가 없으면
				//memberDAO.addMember(memberVO);
				System.out.println("아이디 등록 시작");
				dao.addMember(memberVO);
				System.out.println("아이디 등록 완료");
				nextPage = "/miniprojectmod/memberCheck.jsp?id="+id; // 원래 화면으로
			}else {//같은 아이디가 있으면
				System.out.println("중복 아이디 있음");
				nextPage = "/miniprojectmod/memberForm.jsp?chk=N"; // 원래 화면으로
			}
			
			}
		
		else if("enter".equals(command)) {        //게시판 접속 시 실행
			HttpSession session = request.getSession();
			
			if(session.isNew()) {           // 세션이 최초인지 구분하고 맞으면 true or false
				result ="true";
			}else {
				result ="false";
			}
			nextPage ="loginView.jsp";
		} 
		
		request.setAttribute("result", result);  //결과값을 바인딩하고 디스패치로 페이지 이동
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}
