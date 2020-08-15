package Common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/board/*")
public class MainFilter implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");

        HttpServletRequest httpRequest= (HttpServletRequest ) request;
//        HttpServletRequest httpResponse= (HttpServletRequest ) response;

        HttpSession session = httpRequest.getSession();
        boolean login = false;

        if(session != null) {
            if(session.getAttribute("member_id")!=null) {
                login = true;
            }
        }
        if(login) {
            chain.doFilter(request, response);
        }
        PrintWriter out = response.getWriter();
        out.print("<script>");
        out.print("alert('로그인 먼저 해주세요');");
        out.print("location.href='/static/Member/logIn.jsp';");
        out.print("</script>");

//        RequestDispatcher dispatcher = request.getRequestDispatcher("/static/main.jsp");
//        dispatcher.forward(request, response);
    }
}
