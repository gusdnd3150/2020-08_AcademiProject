package member;

import javax.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doHandle(req, resp);
	}

	protected void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		String action = request.getPathInfo(); // url요청명을 가져옴
		System.out.println("액션:  " + action);

		JSONObject totalObject = new JSONObject();
		// JSONArray membersArrat = new JSONArray();//여러명의 목록을 담는것이 아니라서 array 사용 안해도 된다
		JSONObject memberInfo = new JSONObject();

		if (action.equals("/idsearch.do")) { // 조회 컨트롤
			String id = request.getParameter("id");
			MemberDAO dao = new MemberDAO();
			MemberVO vo = dao.checkMember(id);
			if (vo != null) {
				memberInfo.put("id", vo.getId());
				memberInfo.put("pwd", vo.getPwd());
				memberInfo.put("name", vo.getName());
				// membersArrat.add(memberInfo); //여러명의 목록을 담는것이 아니라서 array 사용 안해도 된다
				totalObject.put("member", memberInfo);
				writer.print(totalObject.toJSONString());
			} else {
				writer.print("nonMember");// 등록된 회원이 아니면 null
			}

		} else if (action.equals("/add.do")) { // 저장 컨트롤
			String id = request.getParameter("id");
			MemberDAO dao = new MemberDAO();
			int result = dao.checkId(id); // 아이디 중복 체크 1이면 회원가입 0면이 수정
			if (result == 1) { // 1이면 회원가입
				String pwd = request.getParameter("pwd");
				String name = request.getParameter("name");
				int result1 = dao.addMember(id, pwd, name);
				writer.print("등록완료");
				return;
			} else { // 0이면 회원정보 수정
				String pwd = request.getParameter("pwd");
				String name = request.getParameter("name");
				int result2 = dao.updateMember(id, pwd, name);
				if (result2 == 1) {
					writer.print("수정완료");
				}
			}return;

		} else if (action.equals("/lag.do")) { // 이전 컨트롤
			String _id = request.getParameter("id");
			MemberDAO dao = new MemberDAO();
			MemberVO vo = dao.lag(_id);
			if (vo != null) {
				memberInfo.put("id", vo.getId());
				memberInfo.put("pwd", vo.getPwd());
				memberInfo.put("name", vo.getName());
				totalObject.put("member", memberInfo);
				writer.print(totalObject.toJSONString());
			} else {
				writer.print("null"); // 더이상 조회할 회원 목록이 없으면 null
			}return;

		} else if (action.equals("/lead.do")) { // 이후 컨트롤
			String _id = request.getParameter("id");
			MemberDAO dao = new MemberDAO();
			MemberVO vo = dao.lead(_id);
			if (vo != null) {
				memberInfo.put("id", vo.getId());
				memberInfo.put("pwd", vo.getPwd());
				memberInfo.put("name", vo.getName());
				totalObject.put("member", memberInfo);
				writer.print(totalObject.toJSONString());
			} else {
				writer.print("null");// 더이상 조회할 회원 목록이 없으면 null
			}return;			
		}
	}
}
