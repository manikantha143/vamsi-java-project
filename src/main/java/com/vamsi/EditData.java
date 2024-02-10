package com.vamsi;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/editurl")
public class EditData extends HttpServlet
{
	private static final String query = "UPDATE student SET STUDENT_NAME=?, STUDENT_VILLAGE=?, STUDENT_PHONE_NUMBER=?, STUDENT_GENDER=? WHERE ID=?";
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String Name = req.getParameter("studentName");
	    String Village = req.getParameter("studentVillage");
	    String Number = req.getParameter("studentPhoneNumber");
	    String Gender = req.getParameter("studentGender");
	    int Id = Integer.parseInt(req.getParameter("id"));
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        out.println("Error occurred: " + e.getMessage()); // Print error message to response
	    }
	    try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_details","root","kgfyash@1234");
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, Name);
			ps.setString(2, Village);
			ps.setString(3, Number);
			ps.setString(4,Gender);
			ps.setInt(5, Id);
		    int count = ps.executeUpdate();
			if(count == 1) {
				out.println("updated");
				out.println("<br></br>");
				out.println("<a href='studentList'>List</a>");
			}else {
				out.println("not saved");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println(e.getMessage());
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req,resp);
	}
}
