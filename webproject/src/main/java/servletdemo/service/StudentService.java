package servletdemo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import servletdemo.entity.Student;

public class StudentService {
	private static String url="jdbc:postgresql://localhost:5432/schoolmanagement?user=postgres&password=123";
	private static Connection con;
	static {
		try {
			Class.forName("org.postgresql.Driver");
			con=DriverManager.getConnection(url);
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int save(Student st1) {
		int res=0;
		int id=st1.getId();
	    String name	=st1.getName();
	    int age=st1.getAge();
	    String gender=st1.getGender();
	
	    String sql="INSERT into student values(?,?,?,?)";
		
	try {
		PreparedStatement pstm=con.prepareStatement(sql);
		pstm.setInt(1, id);
		pstm.setString(2,name);
		pstm.setInt(3, age);
		pstm.setString(4, gender);

		
		
		res=pstm.executeUpdate();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return res;
	
	}
	public List<Student> getall()
	{
		List<Student> list=new ArrayList<Student>();
		
		String sql="SELECT * from student";
		
		try {
			Statement stm=con.createStatement();
			
			ResultSet rs=stm.executeQuery(sql);
			while (rs.next()) {
				list.add(new Student(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4)));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	public int update(Student st)
	{
		int res=0;
		String sql="UPDATE student SET  name=?,age=?,gender=? WHERE id=?";
		try {
			PreparedStatement pstm=con.prepareStatement(sql);
			
			pstm.setString(1, st.getName());
			pstm.setInt(2, st.getAge());
			pstm.setString(3, st.getGender());
			pstm.setInt(4, st.getId());    
			 res=pstm.executeUpdate();
			
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return  res;
	}
	public int delete(int id)
	{
		int res=0;
		
		String sql="DELETE FROM student WHERE id=?";
		try {
			PreparedStatement pstm=con.prepareStatement(sql);
			
			pstm.setInt(1, id);
			 res=pstm.executeUpdate();
			
			
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return  res;
	}
}
