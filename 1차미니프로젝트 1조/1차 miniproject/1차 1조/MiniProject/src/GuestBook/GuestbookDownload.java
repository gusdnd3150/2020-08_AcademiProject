package GuestBook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class GuestbookDownload extends HttpServlet{

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doPHandle(req,resp);
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      doPHandle(req,resp);
   }
   protected void doPHandle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
      req.setCharacterEncoding("utf-8");
      resp.setContentType("text/html;charset=utf-8");
      String fileName = (String) req.getParameter("fileName");
      String fileRepo = "/Users/nogari03/Desktop";
      
      System.out.println("다운로드 서블릿 파일 이름:"+fileName);
      
        
         OutputStream out = resp.getOutputStream();
         String downFile = fileRepo + "/" + fileName;
         File f = new File(downFile);
         resp.setHeader("Cache-Control", "no-cache");
         resp.addHeader("Content-disposition", "attachment; fileName=" + fileName);
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