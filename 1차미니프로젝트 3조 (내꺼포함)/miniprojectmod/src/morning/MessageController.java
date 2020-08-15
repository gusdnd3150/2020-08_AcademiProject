package morning;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/message/*")
public class MessageController extends HttpServlet {
	MessageDAO messageDAO;
	MessageVO messageVO;

	public void init() throws ServletException {
		messageDAO = new MessageDAO();
		messageVO = new MessageVO();
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
		List<MessageVO> messageList = null;
		String nextPage = null;

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String action = request.getPathInfo();
		System.out.println("action : " + action);

		if ("/messageWriteResult.do".equals(action)) { // 방명록 작성 + 파일 첨부
			String encoding = "utf-8"; // 파일 첨부
			File currentDirPath = new File("C:\\file_repo");
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(currentDirPath);
			factory.setSizeThreshold(1024 * 1024);
			ServletFileUpload upload = new ServletFileUpload(factory);
			MultipartRequest mr = new MultipartRequest(request, "C:\\file_repo", 1024 * 1024, "UTF-8",
					new DefaultFileRenamePolicy());
			Enumeration<String> files = mr.getFileNames();
			String fileNames = files.nextElement();
			String getfileName = mr.getFilesystemName(fileNames);
			int id = messageVO.getMessageId();
			String name = mr.getParameter("name");
			String password = mr.getParameter("password");
			String message = mr.getParameter("message");
			System.out.println("id : " + id);
			System.out.println("name : " + name);
			System.out.println("password : " + password);
			System.out.println("message : " + message);
			System.out.println("file : " + getfileName);
			MessageVO messageVO = new MessageVO(id, name, password, message, getfileName);
			request.setAttribute("id", id);
			messageDAO.addMessage(messageVO);
			nextPage = "/messageWriteResult.jsp";
		} else if ("/deleteCheckForm.do".contentEquals(action)) { // 방명록 삭제 폼
			nextPage = "/deleteCheckForm.jsp";
		} else if ("/deleteSuccess.do".equals(action)) { // 삭제 성공
			int id = Integer.parseInt(request.getParameter("id2"));
			String password = request.getParameter("password");
			if (password.equals(messageDAO.getMessage(id).getPassword())) {
				messageDAO.deleteMessage(id);
				nextPage = "/deleteSuccess.jsp"; // 삭제 성공
			} else {
				nextPage = "/deleteEditFail.jsp"; // 삭제 실패
			}
		} else if ("/editCheckForm.do".contentEquals(action)) // 수정 비밀번호 확인 폼 
			{
			nextPage = "/editCheckForm.jsp";
		} else if ("/editCheck.do".equals(action)) { // 수정 비밀번호 체크
			int id = Integer.parseInt(request.getParameter("id2"));
			request.setAttribute("id", id);
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String message = request.getParameter("message");
			if (password.equals(messageDAO.getMessage(id).getPassword())) { // 비밀번호 체크
				MessageVO messageVO = new MessageVO(id, name, password, message);
				List<MessageVO> messageList2 = new ArrayList<MessageVO>();
				messageList2 = messageDAO.listMessages2(id);
				request.setAttribute("messageList2", messageList2);
				System.out.println(messageList2.size());
				nextPage = "/messageEditForm.jsp";
			} else {
				nextPage = "/deleteEditFail.jsp";
			}
		} else if ("/editSuccess.do".equals(action)) { // 수정
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String message = request.getParameter("message");
			int id = Integer.parseInt(request.getParameter("id3"));
			System.out.println(id);
			messageDAO.editMessage(name, password, message, id);
			nextPage = "/editSuccess.jsp";
		} else { // default면 리스트 목록
			String _pageNum = request.getParameter("pageNum");
			int pageNum = Integer.parseInt(((_pageNum == null) ? "1" : _pageNum));
			messageList = messageDAO.listMessages(pageNum);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("totMessage", messageDAO.totMessage());
			request.setAttribute("messageList", messageList);
			nextPage = "/messageList.jsp";
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}
