package servletdemo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servletdemo.entity.Student;
import servletdemo.service.StudentService;

@WebServlet("/register")
public class Register extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		System.out.println("register class triggerd");
		
		int id=Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("name");
		int age=Integer.parseInt(req.getParameter("age"));
		String gender=req.getParameter("gender");
		
		Student st1=new Student(id,name,age,gender);
		st1.setId(id);
		st1.setAge(age);
		st1.setGender(gender);
		
		StudentService service=new StudentService();
		
		int res=service.save(st1);
		if (res!=0) {
			RequestDispatcher dispatcher=req.getRequestDispatcher("welcome.html");
		    dispatcher.forward(req, resp);
		}
		else
		{
			PrintWriter pr=resp.getWriter();
			pr.print("<h1>DATA NOT SAVED</h1>");
		}
	}
}
