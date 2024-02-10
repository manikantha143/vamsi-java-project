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


@WebServlet("/delete")
public class DeleteServlet extends HttpServlet
{
	private static final String query = "DELETE FROM student WHERE id=?";
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
			ps.setInt(1, Id);
		    int count = ps.executeUpdate();
			if(count == 1) {
				out.println("Record with ID " + Id + " deleted successfully.");
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
	
	
}
