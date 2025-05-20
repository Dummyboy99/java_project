package servletdemo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servletdemo.service.StudentService;

@WebServlet("/delete")
public class Delete extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
	
		String ids=req.getParameter("id");
		  int id=Integer.parseInt(ids);	
		  
		  StudentService service=new StudentService();
		  int res=service.delete(id);
		  if (res!=0) {
			RequestDispatcher rsdp=req.getRequestDispatcher("welcome.html");
			rsdp.include(req, resp);
			}
		  else {
			  PrintWriter pr=resp.getWriter();
			  pr.println("<html><body>");
			  pr.println("<h1>The user doesn't exist</h1>");
			  pr.println("</html></body>");
			  
		  }
		
	}
}
