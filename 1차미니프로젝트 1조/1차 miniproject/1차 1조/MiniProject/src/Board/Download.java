package Board;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/downloadBoard")
public class Download extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doHandle(request,response);
    }

    private void doHandle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String file_repo = "/Users/nogari03/Desktop";
        String fileName = (String) request.getParameter("fileName");

        OutputStream out = response.getOutputStream();
        String downFile = file_repo + "/" + fileName;
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
    }
}
