package p;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/m/*")
public class Controller extends HttpServlet {
	MemberVO memberVO;

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		String action = request.getPathInfo();
		System.out.println("action : "+action);
		MemberDAO dao = new MemberDAO();
		try {
			if("/search.do".equals(action)) { //조회버튼을 누르면 실행
				String id = (String) request.getParameter("id"); //파라미터로 id값 받기
				MemberVO vo = dao.search(id); 
				JSONObject memberInfo = new JSONObject();
				if(vo != null) { //id 있을 경우 넘기기
					memberInfo.put("id",vo.getId());
					memberInfo.put("pwd",vo.getPwd());
					memberInfo.put("name",vo.getName());
					String jsonInfo = memberInfo.toJSONString();
					System.out.println(jsonInfo);
					writer.print(jsonInfo);
				} 

			} else if("/save.do".equals(action)) { //저장버튼을 누르면 실행
				String id = (String) request.getParameter("id"); //파라미터로 받기
				String pwd = (String) request.getParameter("pwd");
				String name = (String) request.getParameter("name");
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				dao.saveMember(vo); //추가 혹은 수정하는 메소드

			} else if("/sib.do".equals(action)) { //이전,이후 버튼을 누르면 실행
				String id = (String) request.getParameter("id");
				String fun = (String) request.getParameter("fun");
				MemberVO vo = null;
				if(fun.equals("preId")) { //fun으로 넘긴 파라미터 값으로 분류 
					String pid = dao.pid(id); //이전 id 찾는 메소드
					vo = dao.search(pid); //이전 id로 조회 메소드 실행
				} else if(fun.equals("nextId")) {
					String nid = dao.nid(id); //이후 id 찾는 메소드
					vo = dao.search(nid); //이후 id로 조회 메소드 실행
				}
				JSONObject memberInfo = new JSONObject();
				if(vo != null) { //이전, 이후id 있을 경우 값 넘겨주기
					memberInfo.put("id",vo.getId());
					memberInfo.put("pwd",vo.getPwd());
					memberInfo.put("name",vo.getName());
					String jsonInfo = memberInfo.toJSONString();
					System.out.println(jsonInfo);
					writer.print(jsonInfo);
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

}
