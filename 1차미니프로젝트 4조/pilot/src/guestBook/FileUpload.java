package guestBook;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class FileUpload
 */
@WebServlet("/bookUpload")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String encoding = "utf-8";
		File currentDirPath = new File("C:\\file_repo");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		PrintWriter out = response.getWriter();
		BookDAO dao = new BookDAO();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024);

		ServletFileUpload upload = new ServletFileUpload(factory);
		List<String> list1 = new ArrayList<>(); //파일 외의 파라미터를 담기 위한 list 생성
		try {
			List items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem fileItem = (FileItem) items.get(i);

				if (fileItem.isFormField()) {
					list1.add(fileItem.getString(encoding)); //생성한 list에 value값만 담아준다.
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
				} else {
					if (list1.get(1).equals("") || list1.get(2).equals("") || list1.get(3).equals("")) {
						out.write("<html><body>");
						out.write("오류 : 모든 항목을 입력해주세요.<br><a href='./book'>[목록 보기]</a>");
						out.write("</body></html>");
						return;
						//비어있는 항목(이름,비밀번호,글 내용)이 있을 시 에러메세지 출력 후 초기페이지로 돌려보낸다.
					}
					System.out.println("파라미터명:" + fileItem.getFieldName());
					System.out.println("파일명:" + fileItem.getName());
					System.out.println("파일크기:" + fileItem.getSize() + "bytes");

					if (fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						String fileName = fileItem.getName().substring(idx + 1);
						File uploadFile = new File(currentDirPath + "\\" + fileName);
						fileItem.write(uploadFile);
						list1.add(fileName); //파라미터 담던 list에 파일명도 담아준다.
					} 
				} 
			}
			for (String i : list1) {
				System.out.println(i);
			}
			//글번호 매겨주는 부분. nullpointer방지하기 위해 if분기 설정
			List<BookVO> list2 = new ArrayList<>();
			list2 = dao.lookUp();
			int id;
			if (list2.size() > 0) {
				id = Integer.parseInt((list2.get(0).getId())) + 1;
			} else {
				id = 1;
			}
			String name = list1.get(1);
			String pw = list1.get(2);
			String msg = list1.get(3);
			String path = "";
			//list1에 파일명이 담겨있으면 파일명을 객체에 저장하고 없다면(사이즈가 3이면) noFile로 설정.
			if (list1.size() > 4) {
				path = list1.get(4);
			} else {
				path = "noFile";
			}
			dao.addGuestbook(id, name, pw, msg, path);
			out.write("<html><body>");
			out.write("방명록에 메세지를 남겼습니다<br><a href='./book'>[목록 보기]</a>");
			out.write("</body></html>");
			return;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}