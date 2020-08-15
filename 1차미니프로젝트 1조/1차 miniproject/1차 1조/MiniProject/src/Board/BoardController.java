package Board;

import Common.PagingVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/board")
public class BoardController extends HttpServlet {

    static BoardService service = new BoardServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request,response);
    }

    private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestUtils requestMap = new RequestUtils();

//        Map<String,String> map = requestMap.getParameterMap(request);

        String command = request.getParameter("command");
        String paramPage = request.getParameter("paramPage");
        String article_no = request.getParameter("article_no");
        String password = request.getParameter("password");
        String nextPage;

        System.out.println(paramPage);
        BoardVO vo;

        nextPage = "/static/Board/board.jsp";

        //nextPage check
        if("getBoard".equals(paramPage)){                                                     // 게시글 페이지로 이동
            System.out.println("체크"+article_no);
            vo = service.getBoardContent(article_no);
            service.updateBoardCnt(article_no);
            request.setAttribute("vo",vo);
            nextPage = "/static/Board/boardForm.jsp";

        }else if("writeBoard".equals(paramPage)) {                                            // 글작성 페이지로 이동
            nextPage = "/static/Board/boardForm.jsp";

        }else if("updateBoard".equals(paramPage)) {                                           // 수정 페이지로 이동
            if (service.checkPassword(article_no, password) == 0) {
                request.setAttribute("result",0);
                response.sendRedirect(request.getHeader("referer"));
                return;
            }
            vo = service.getBoardContent(article_no);
            request.setAttribute("vo", vo);
            nextPage = "/static/Board/boardForm.jsp";

        }else if("checkUpdateBoard".equals(paramPage)){                                    // 수정 비밀번호 체크 페이지로 이동
            request.setAttribute("article_no",article_no);
            nextPage = "/static/Board/checkForm.jsp";

        }else if("checkDeleteCheck".equals(paramPage)){                                    // 삭제 비밀번호 체크 페이지로 이동
            request.setAttribute("article_no",article_no);
            nextPage = "/static/Board/checkForm.jsp";
        }

        //command check
        if("delBoard".equals(command)) {                                                 // 게시글 삭제
            if (service.checkPassword(article_no, password) == 0) {
                request.setAttribute("result",0);
                response.sendRedirect(request.getHeader("referer"));
                return;
            }
            service.deleteContent(Integer.parseInt(article_no));
        }

        Paging p = new Paging();
        List<BoardVO> list = p.Paging(request);

        request.setAttribute("list",list);
        request.setAttribute("command",command);
        request.setAttribute("paramPage",paramPage);

        ServletContext context = this.getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(nextPage);
        dispatcher.forward(request, response);
    }

    public class RequestUtils {
        public Map<String,String> getParameterMap (HttpServletRequest request){
            Map<String,String> map = new HashMap<String,String>();
            Enumeration enums = request.getParameterNames();
            while(enums.hasMoreElements()){
                String paramName = (String)enums.nextElement();
                String[] parameters = request.getParameterValues(paramName);

                map.put(paramName, parameters[0]);
            }
            return map;
        }
    }

    public class Paging {

        public List<BoardVO> Paging(HttpServletRequest request){

        PagingVO paging = new PagingVO();
        paging.setTotalCount(service.getBoardCnt());

        int currentPage = 1;
        if (request.getParameter("page") != null) {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }

        paging.setCurrentPage(currentPage);

        List<BoardVO> list = service.getBoardListWithPaging(currentPage);

        paging.setEndPage( ((int) Math.ceil(paging.getCurrentPage() / (double) paging.getDisplayPage())) * paging.getDisplayPage() );	//Math.ceil : 소수점 이하를 올림한다
        paging.setBeginPage( paging.getEndPage() - (paging.getDisplayPage() - 1) );
        paging.setTotalPage( (int) Math.ceil(paging.getTotalCount() / (double) paging.getDisplayRow()) );
        if (paging.getEndPage() > paging.getTotalPage()) {
            paging.setEndPage(paging.getTotalPage());
        }
            request.setAttribute("paging",paging);
            request.setAttribute("list",list);
            return list;
        }
    }
}
