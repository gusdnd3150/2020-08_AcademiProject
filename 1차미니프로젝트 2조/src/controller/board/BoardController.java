package controller.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.member.MemberVO;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	ArticleVO articleVO;
	ContentVO contentVO;

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage = null;
		String action = request.getPathInfo();	// url에서 요청명을 가져옴
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		System.out.println("action : "+action);
		BoardDAO dao = new BoardDAO();
		HttpSession session = request.getSession(false);
		boolean isLogined = false;
		if(session != null) {
			try {
				isLogined = (boolean) session.getAttribute("isLogined");
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
		System.out.println("isLogined: "+isLogined);

		try {
			// 게시판 세션체크 
			if( isLogined == true ) {
				MemberVO loginedMember = (MemberVO)session.getAttribute("loginedMember");
				System.out.println("session: "+loginedMember.getMember_id() + loginedMember.getName());
				String member_id = loginedMember.getMember_id();
				String name = loginedMember.getName();

				List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
				if("/listArticles.do".equals(action) || action == null) {		//게시판 리스트
					nextPage="/article/listArticles.jsp";
					String _pageNum = request.getParameter("pageNum");
					int pageNum = Integer.parseInt(((_pageNum == null)? "1": _pageNum));
					articlesList = dao.AllArticles(pageNum);
					request.setAttribute("pageNum", pageNum);
					request.setAttribute("totArticles", dao.totArticles());
					request.setAttribute("articlesList", articlesList);

				} else if("/viewArticle.do".equals(action)) { 					//게시글 조회
					String _article_no = request.getParameter("article_no");
					request.setAttribute("article_no", _article_no);
					ContentVO contentList = dao.ContentInfo(Integer.parseInt(_article_no));
					request.setAttribute("contentList", contentList);
					System.out.println("제목:"+contentList.getTitle());
					nextPage="/article/viewArticle.jsp";

				} else if("/editArticle.do".equals(action)) { 					//게시글 수정
					String _article_no = request.getParameter("article_no");
					ContentVO contentList = dao.ContentInfo(Integer.parseInt(_article_no));
					request.setAttribute("article_no", _article_no);
					request.setAttribute("contentList", contentList); //파일네임 있음
					nextPage="/article/editArticle.jsp";

				} else if("/confirmArt.do".equals(action)) { 					//게시글 비밀번호 확인
					try {
						MultipartRequest multi = new MultipartRequest( // MultipartRequest 인스턴스 생성(cos.jar의 라이브러리)
								request, 
								"C:\\file_repo", // 파일을 저장할 디렉토리 지정
								10 * 1024 * 1024, // 첨부파일 최대 용량 설정(bite) / 10KB / 용량 초과 시 예외 발생
								"utf-8", // 인코딩 방식 지정
								new DefaultFileRenamePolicy() // 중복 파일 처리(동일한 파일명이 업로드되면 뒤에 숫자 등을 붙여 중복 회피)
								);
						int _article_no = Integer.parseInt(multi.getParameter("article_no"));
						String title = multi.getParameter("title");
						String old_file_name = request.getParameter("old_file_name");
						String content = multi.getParameter("content");
						String file_name = multi.getFilesystemName("file_name");
						request.setAttribute("article_no", _article_no);
						request.setAttribute("title", title);
						request.setAttribute("content", content);
						request.setAttribute("file_name", file_name);
						request.setAttribute("old_file_name", old_file_name);
						nextPage="/article/pwCheck.jsp";
					}catch(Exception e) {
						e.printStackTrace();
					}
				} else if("/confirmOK.do".equals(action)) {					 	//수정, 비밀번호 확인 됐을시 실행
					nextPage = "/board/listArticles.do";
					int artNum = Integer.parseInt(request.getParameter("article_no"));
					String inputPw = request.getParameter("inputPw");
					String title = request.getParameter("title");
					System.out.println("");
					String content = request.getParameter("content");
					String file_name = request.getParameter("file_name");
					String old_file_name = request.getParameter("old_file_name");
					ContentVO vo = new ContentVO(title,content);
					dao.editArticle(vo,artNum,inputPw,file_name);

				} else if("/download.do".equals(action)) {  							//첨부파일 다운로드
					String file_name = request.getParameter("file_name");
					download(request,response, file_name);
					return;
				} else if("/boardWrite.do".equals(action)) {			// 게시글 쓰기
					nextPage = "/article/boardWrite.jsp";			// 게시글 정보 입력 form
				} else if("/addArticle.do".equals(action)) {
					HashMap<String, String> map = new HashMap<String, String>();
					map = upload(request);				// upload메소드 호출 후 map 정보 받아옴   
					String password = map.get("pwd");	// multipart/form-data 때문에 map에서 pwd, title, content 정보 받아옴
					String title = map.get("title");	
					String content = map.get("content"); 
					articleVO = new ArticleVO();					// ArticleVO 생성
					articleVO.setWriter_id(member_id); 
					articleVO.setWriter_name(name);
					articleVO.setPassword(password);
					articleVO.setTitle(title);						// 각각 정보 담기
					contentVO = new ContentVO();					// ContenVO 생성
					contentVO.setContent(content);		
					contentVO.setFile_name(map.get("file_name")); 	//map에서 file_name 받아옴, 각각 정보 담기
					dao.insertArticle(articleVO);
					dao.insertContentArticle(contentVO);
					nextPage ="/board/listArticles.do";			  	//게시글 리스트 호출
				} else if("/removePwd.do".equals(action)) {
					int artNum = Integer.parseInt(request.getParameter("article_no"));
					request.setAttribute("article_no",artNum);
					nextPage = "/article/removePwd.jsp";
				} else if("/download.do".equals(action)) {					//download.do 
					String file_name = request.getParameter("file_name");	//매개변수로 전송된 파일 이름 읽어옴
					download(request,response, file_name);					//download 메소드 호출
					return;
				} else if("/confirmOK2.do".equals(action)) {	//confirmOK2.do 삭제시 비밀번호 확인
					String artNum = request.getParameter("article_no");
					String inputPw = request.getParameter("inputPw");
					dao.delArticle(artNum, inputPw);
					nextPage ="/board/listArticles.do";			//게시글 리스트 호출
				}

			}else {	// 로그인이 안됐을 때
				nextPage = "/member/login.jsp";
			}

			System.out.println("****nextPage : " + nextPage);
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
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

	// method

	//다운로드 메소드
	private void download(HttpServletRequest request, HttpServletResponse response, String file_name)throws IOException, FileNotFoundException {
		System.out.println("downfileName : " + file_name);
		OutputStream outStream = response.getOutputStream();	//response 객체에서 OutputStream 객체 가져옴
		String downFile = "C:\\file_repo\\"+file_name;
		File f = new File(downFile);
		response.setHeader("Cache-Control", "no-cache");		//파일을 다운로드 할 수 있습니다.
		response.addHeader("Content-disposition", "attachment; fileName="+file_name);
		FileInputStream in = new FileInputStream(f);
		byte[] buffer = new byte[1024*8];						//버퍼 기능을 이용해 파일에서 버퍼로 데이터를 읽어와 한꺼번에 출력함.
		while(true) {
			int count = in.read(buffer);
			if(count == -1)	break;
			outStream.write(buffer,0,count);
		}
		in.close();
		outStream.close();
	}

	//업로드 메소드
	public HashMap<String, String> upload(HttpServletRequest request) {
		HashMap<String, String> map = new HashMap<String, String>();
		String encoding="utf-8";
		File currrentDirPath = new File("C:\\file_repo");   //업로드 할 파일경로 지정
		DiskFileItemFactory factory = new DiskFileItemFactory();	//FileItem 오브젝트 생성하는 클래스, 메모리, hdd, 데이터처리
		factory.setRepository(currrentDirPath);		//파일 경로 설정                지정한 크기를 넘기 전까진 메모리에 저장하고 넘어가면 디렉토리 파일로 저장
		factory.setSizeThreshold(1024*1024);		//메모리에 저장 할 수있는 최대크기(바이트단위) 기본값 1024(10KB)
		ServletFileUpload upload=new ServletFileUpload(factory);	//업로드 요청을 처리하는 ServletFileUPload 생성성
		try {
			List items = upload.parseRequest(request);		 //업로드 요청 파싱해서 FileItem 목록구함
			for(int i=0;i<items.size();i++) {
				FileItem fileItem = (FileItem)items.get(i);  //multipart/form-data로 전송된 파라미터 또는 파일 정보 저장
				if(fileItem.isFormField()) {   				 //파일이 아닌 일반적인 입력 파라미터일 경우 true
					System.out.println(fileItem.getFieldName()+"="+fileItem.getString(encoding));  //지정인 인코딩을 사용하여 파라미터 값 구함
					map.put(fileItem.getFieldName(), fileItem.getString(encoding));		
				}else {
					map.put("file_name", fileItem.getName());
					if(fileItem.getSize()>0) {				 //업로드한 파일 크기
						int idx = fileItem.getName().lastIndexOf("\\");	 //업로드 한 파일의 (경로를 제외한) 이름을 구한다.
						if(idx==-1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						String fileName = fileItem.getName().substring(idx+1);
						File uploadFile = new File(currrentDirPath+"\\"+fileName);  
						fileItem.write(uploadFile);  		 //업로드한 파일을 file이 나타내는 파일로 저장한다.
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
