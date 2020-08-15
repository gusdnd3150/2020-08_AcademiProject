package article;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import member.*;

/**
 * Servlet implementation class ArticleController
 */
@WebServlet("/article/*")
public class ArticleController extends HttpServlet {
	ArticleDAO articleDAO;

	public void init() throws ServletException {
		articleDAO = new ArticleDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request, response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		String action = request.getPathInfo();
		System.out.println("action>>>>>"+action);

		if(action.equals("/listArticles.do")) { // 게시판 조회하기
			// 로그인 확인
			HttpSession session = request.getSession();
			MemberVO mem = (MemberVO) session.getAttribute("loginUser");
			if(mem == null) { // 로그인이 안되어있으면 로그인 페이지로
				nextPage = "/login.jsp";
			} else { // 로그인이 되어있으면 첫 번째 페이지 출력
				int page = 1;
				if(request.getParameter("page") != null) { // 초기 게시판화면이 아닌 다른 페이지를 눌렀을 때
					page = Integer.parseInt(request.getParameter("page"));
				}
				Paging paging = new Paging();
				paging.setPage(page); // 현재 페이지 설정
				int count = articleDAO.getAllCount(); // 총 게시글 수 가져옴
				paging.setTotalCount(count); // 총 게시판 글 수
				List<ArticleVO> articleList = articleDAO.lookUp(paging);
				if(articleList.size() != 0) { // 등록된 게시글이 있으면
					request.setAttribute("articleList", articleList);
					request.setAttribute("paging", paging);
				}
				nextPage = "/board/articleList.jsp"; // 게시글 조회하는 jsp
			}
		} else if(action.equals("/wrtieArticleForm.do")) { // 게시글쓰기를 눌렀을 때
			nextPage = "/board/writeArticleForm.jsp"; // 게시글 쓰기로 이동
		} else if(action.equals("/addArticle.do")) { // 새 글 등록 버튼을 눌렀을 때
			HashMap<String, String> map = fileUpload(request); // fileUpload() 메소드 실행
			HttpSession session = request.getSession(); // 로그인 된 정보를 가져오기 위한 session
			MemberVO vo = (MemberVO)session.getAttribute("loginUser"); // 로그인 된 회원정보 가져옴
			String title = map.get("title");
			String pwd = map.get("pwd");
			String content = map.get("content");
			String fileName = map.get("fileName");
			System.out.println(fileName);
			String writer_id = vo.getMember_id();
			String writer_name = vo.getName();
			articleDAO.addContent(content, fileName);	// article_content 추가

			ArticleVO articlevo = new ArticleVO();
			articlevo.setWriter_id(writer_id);
			articlevo.setWriter_name(writer_name);
			articlevo.setTitle(title);
			articlevo.setPassword(pwd);
			articleDAO.addArticle(articlevo);	// article 추가

			nextPage = "/article/listArticles.do"; // 게시글 조회하기로 이동
		} else if(action.equals("/contentForm.do")) { // 게시판에서 제목을 눌렀을 때
			String article_no = request.getParameter("article_no");
			articleDAO.addReadCount(article_no); // 조회수 증가 메소드 호출
			ContentVO c = articleDAO.getContent(article_no); // 게시글을 보여주기 위한 정보를 가져오는 메소드 호출
			request.setAttribute("content", c);

			nextPage = "/board/contentForm.jsp"; // 게시글 내용 화면으로 이동
		} else if(action.equals("/checkPwd.do")) { // 게시글 수정, 삭제하기 전 암호확인
			
			nextPage = "/board/checkPwdForm.jsp"; // 암호 확인 화면으로 이동
		} else if(action.equals("/delArticle.do")) { // 게시글 삭제버튼을 눌렀을 때
			String article_no = request.getParameter("article_no");
			String pwd = request.getParameter("pwd");
			String password = articleDAO.getPassword(article_no);
			if(pwd.equals(password)) { // 암호가 일치하면 삭제하고
				articleDAO.delArticle(article_no);
				articleDAO.delContent(article_no);
				
				nextPage = "/article/listArticles.do"; // 게시글 조회하기로 이동
			} else { // 암호가 다르면 다시 게시글 화면으로 이동
				
				nextPage = "/article/contentForm.do";
			}
		} else if(action.equals("/modArticleForm.do")) {  // 암호확인 후 게시글 수정 화면 url
			String article_no = request.getParameter("article_no");
			String pwd = request.getParameter("pwd");
			String password = articleDAO.getPassword(article_no);
			if(pwd.equals(password)) { // 암호가 같으면
				
				nextPage = "/board/modArticleForm.jsp"; // 게시글 수정 화면으로 이동
			} else {
				
				nextPage = "/article/contentForm.do";
			}
		} else if(action.equals("/modArticle.do")) { // 게시글 수정버튼 url
			String article_no = request.getParameter("article_no");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			System.out.println(article_no + "///" + title + "///" + content);
			ContentVO c = new ContentVO(article_no, title, content);
			articleDAO.modArticle(c); // dao의 수정메소드 호출

			nextPage = "/article/contentForm.do"; // 수정된 게시글을 보여주는 화면으로 이동
		} else if(action.equals("/downloadFile.do")) { // 파일 다운로드 링크 url
			fileDownload(request, response); // 파일 다운로드 메소드 호출

			nextPage = "/article/contentForm.do";
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
	// 파일업로드 메소드
	private HashMap<String, String> fileUpload(HttpServletRequest request) {
		HashMap<String, String> map = new HashMap<String, String>();
		String encoding = "utf-8";
		File currentDirPath = new File("C:\\file_repo");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(1024 * 1024);

		ServletFileUpload upload = new ServletFileUpload(factory);
		FileItem fileItem = null;
		try {
			List items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				fileItem = (FileItem) items.get(i);
				// form 데이터 일 때 form의 name을 key로 value를 value로 사용하여 map에 저장	
				if (fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					map.put(fileItem.getFieldName(), fileItem.getString(encoding));// form 이름과 value를 map에 저장 
				} else {
					System.out.println("파라미터명:" + fileItem.getFieldName());
					System.out.println("파일명:" + fileItem.getName());
					System.out.println("파일크기:" + fileItem.getSize() + "bytes");
					map.put("fileName", fileItem.getName());// "fileName"을 key로 사용하여 파일이름을  value에 저장

					if (fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						String fileName = fileItem.getName().substring(idx + 1);
						File uploadFile = new File(currentDirPath + "\\" + fileName);
						fileItem.write(uploadFile);
					} // end if
				} // end if
			} // end for
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;// form데이터와 파일 이름을 저장한 map을 return
	}
	// 파일 다운로드 메소드
	public void fileDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String file_repo = "C:\\file_repo";
		String article_no = request.getParameter("article_no");
		String fileName = articleDAO.getFileName(article_no);
		System.out.println("fileName=" + fileName);
		OutputStream out = response.getOutputStream();
		String downFile = file_repo + "\\" + fileName;
		File f = new File(downFile);
		response.setHeader("Cache-Control", "no-cache");
		response.addHeader("Content-disposition", "attachment; fileName=" + fileName);
		FileInputStream in = new FileInputStream(f);
		byte[] buffer = new byte[1024 * 8];
		while (true) {
			int count = in.read(buffer);
			if (count == -1)
				break;
			out.write(buffer, 0, count);
		}
		in.close();
		out.close();
	}

}
