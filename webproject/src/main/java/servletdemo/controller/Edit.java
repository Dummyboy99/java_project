package servletdemo.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servletdemo.entity.Student;
import servletdemo.service.StudentService;

@WebServlet("/edit")
public class Edit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
		String id=req.getParameter("id");
		String name=req.getParameter("name");
		String age=req.getParameter("age");
		String gender=req.getParameter("gender");
		
		int ids=Integer.parseInt(id);
		int ages=Integer.parseInt(age);
		
		Student st=new Student(ids, name, ages, gender);
		
		
		StudentService service=new StudentService();
		int res=service.update(st);
		if (res!=0) {
			RequestDispatcher rsdp=req.getRequestDispatcher("welcome.html");
			
			rsdp.include(req, resp);
		}
	}
}


