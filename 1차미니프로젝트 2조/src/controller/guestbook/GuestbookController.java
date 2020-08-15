package controller.guestbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/guest/*")
public class GuestbookController extends HttpServlet {

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		GuestbookDAO dao = new GuestbookDAO(); 
		PrintWriter out;
		// 파일다운을 위한 선언
		String savePath = "C:\\file_repo";
		File currentDirPath = new File(savePath);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024*1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		// action에 따른 분기를 위한 선언
		String nextPage = null;
		String action = request.getPathInfo();	// url에서 요청명을 가져옴
		System.out.println("action : "+action);	
		
		//--------------
		// 메뉴 분기
		//--------------
		// 방명록 초기페이지
		if("/search.do".equals(action) || action==null) {	
			nextPage = "/guestbook/main.jsp";
			String _pageNum = request.getParameter("pageNum");	// 현재 조회 페이지 set (값이 없을 경우 1페이지)
			int pageNum = Integer.parseInt( ((_pageNum==null)? "1": _pageNum) );
			List<GuestbookVO> guestList = dao.guestbookList(pageNum);
			// 바인딩
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("totalGuestbook", dao.totalGuestbook());	// 페이징을 위해 방명록 전체글 수 전달
			request.setAttribute("list", guestList);
		// 방명록 글쓰기 완료
		}else if("/add.do".equals(action)) {	
			HashMap<String, String> getParameter = new HashMap<String, String>(); // fileName과 getParameter을 set
			// 폼 정보 get
			String fileName = getParameters(request, currentDirPath, upload, getParameter);	// extra method
			String guest_name = getParameter.get("guest_name");
			String password = getParameter.get("password");
			String message = getParameter.get("message");
			GuestbookVO vo = new GuestbookVO(guest_name, password, message); vo.setFileName(fileName); // 파일 이름 추가 **
			dao.addGuestbook(vo);	// db 추가
			// 등록 후 출력 페이지
			out = response.getWriter();
			out.println("방명록에 메시지를 남겼습니다.<br>");
			out.println("[<a href='/pilotProject/guest/search.do'>목록 보기</a>]");
			out.close();
			return;
		// 다운로드 클릭시
		}else if("/downdload.do".equals(action)){	
			download(request, response, savePath);	// extra method
			return;	// 페이지 이동 없음.
		// 삭제하기 Click
		}else if("/del.do".equals(action)) {	
			nextPage = "/guestbook/confirm.jsp";	// 비밀번호 확인
			String message_id = request.getParameter("message_id");
			// 바인딩
			request.setAttribute("message_id", message_id);
			request.setAttribute("action","삭제");
			request.setAttribute("action2","del");
		}else if("/delOk.do".equals(action) ) {		// 비밀번호 입력 체크
			String inputPassword = request.getParameter("inputPassword");
			String message_id = request.getParameter("message_id");
			boolean flag = dao.guestPwdCheck(inputPassword, message_id);	// 비밀번호 true 여부
			// 출력 분기
			out = response.getWriter();
			if(flag) {
				out.print("메세지를 삭제했습니다.<br>");
				dao.delGuestbook(message_id);
			}else {
				out.println("입력한 암호가 올바르지 않습니다. 암호를 확인해주세요.<br>");
			}
			out.println("[<a href='/pilotProject/guest/search.do'>목록 보기</a>]");
			out.close();
			return;
		// 수정하기 Click
		}else if("/edit.do".equals(action)) {		
			nextPage = "/guestbook/confirm.jsp";	// 비밀번호 확인
			String message_id = request.getParameter("message_id");
			request.setAttribute("message_id", message_id);
			request.setAttribute("action","수정");
			request.setAttribute("action2","edit");
		}else if("/editOk.do".equals(action)) {	// 비번체크 입력 후
			nextPage = "/guest/search.do";
			String inputPassword = request.getParameter("inputPassword");
			String message_id = request.getParameter("message_id");
			boolean flag = dao.guestPwdCheck(inputPassword, message_id);
			if(flag) {	// 비밀번호 true일 경우 수정페이지로 이동
				nextPage = "/guestbook/edit.jsp";
				int id = Integer.parseInt(request.getParameter("message_id"));
				request.setAttribute("vo", dao.searchGuestbookVO(id));	// id에 따른 게시글 정보 바인딩
			}	
		}else if("/editCompl.do".equals(action)) {	// 수정완료
			nextPage = "/guest/search.do";
			HashMap<String, String> getParameter = new HashMap<String, String>(); // fileName과 getParameter을 셋
			String fileName = getParameters(request, currentDirPath, upload, getParameter);
			if(fileName == null) {	// 수정 시 파일을 등록하지 않았을 경우
				// 이전 등록 파일이 있었다면 fileName에 이전 파일이름을 set
				if(getParameter.get("old_file")!=null)	fileName = getParameter.get("old_file");
			}else {	// 파일을 새로 등록했을 경우 이전 파일 삭제
				File oldFile = new File(currentDirPath+"\\"+getParameter.get("old_file"));
				oldFile.delete();
			}
			// 수정처리
			String guest_name = getParameter.get("guest_name");
			String password = getParameter.get("password");
			String message = getParameter.get("message");
			int message_id = Integer.parseInt(getParameter.get("message_id"));
			GuestbookVO editVO = new GuestbookVO(message_id, guest_name, password, message); editVO.setFileName(fileName); // 파일 이름 추가 **
			dao.editGuestbook(editVO);
		}
		// nextPage로 포워드
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
		
	}
	
	//--------------
	// extra method
	//--------------
	// 파일 처리 (파라미터 get 및 파일 명 리턴)
	private String getParameters(HttpServletRequest request, File currentDirPath, ServletFileUpload upload,
			HashMap<String, String> getParameter) {
		String fileName = null;
		try {
			List items = upload.parseRequest(request);
			for(int i = 0; i < items.size(); i++) {
				FileItem fileItem = (FileItem) items.get(i);
				if(!fileItem.isFormField()) {
					System.out.println("파일이름: "+fileItem.getName());
					System.out.println("파일크기: "+fileItem.getSize()+"bytes");
					if(fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if(idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						fileName = fileItem.getName().substring(idx+1);
						File uploadFile = new File(currentDirPath + "\\" + fileName);
						fileItem.write(uploadFile);
					}
				}else {
					System.out.println(fileItem.getFieldName()+" = "+fileItem.getString("utf-8"));
					getParameter.put(fileItem.getFieldName(), fileItem.getString("utf-8"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}

	// 다운로드 처리
	private void download(HttpServletRequest request, HttpServletResponse response, String savePath)
			throws IOException, FileNotFoundException {
		String fileName = request.getParameter("fileName");
		System.out.println("downfileName : " + fileName);
		OutputStream outStream = response.getOutputStream();
		String downFile = savePath+"\\"+fileName;
		System.out.println(downFile);
		File f = new File(downFile);
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName="+fileName);
		FileInputStream in = new FileInputStream(f);
		byte[] buffer = new byte[1024*8];
		while(true) {
			int count = in.read(buffer);
			if(count == -1)	break;
			outStream.write(buffer,0,count);
		}
		in.close();
		outStream.close();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
}
