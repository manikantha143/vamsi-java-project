package com.vamsi;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/studentList")
public class ReviewListServlet extends HttpServlet {
    private static final String query = "select * from student";

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            out.println("Error occurred: " + e.getMessage()); // Print error message to response
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_details", "root", "kgfyash@1234");
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Student List</title>");
            out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<a class='btn btn-secondary' href='home.html'>Regiser</a>");
            out.println("<div class='container mt-5'>");
            out.println("<table class='table table-striped'>");
            out.println("<thead class='thead-dark'>");
            out.println("<tr>");
            out.println("<th scope='col'>ID</th>");
            out.println("<th scope='col'>Student Name</th>");
            out.println("<th scope='col'>Village</th>");
            out.println("<th scope='col'>Number</th>");
            out.println("<th scope='col'>Gender</th>");
            out.println("<th scope='col'>Edit</th>");
            out.println("<th scope='col'>Delete</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt(1) + "</td>");
                out.println("<td>" + rs.getString(2) + "</td>");
                out.println("<td>" + rs.getString(3) + "</td>");
                out.println("<td>" + rs.getString(4) + "</td>");
                out.println("<td>" + rs.getString(5) + "</td>");
                out.println("<td> <a class='btn btn-primary' href='editScreen?id=" + rs.getInt(1) + "'>Edit</a></td>");
                out.println("<td> <a class='btn btn-danger' href='delete?id=" + rs.getInt(1) + "'>Delete</a></td>");
                out.println("</tr>");
            }

            out.println("</tbody>");
            out.println("</table>");
            
            out.println("</div>");

            out.println("</body>");
            out.println("</html>");

        } catch (SQLException e) {
            e.printStackTrace();
            out.println(e.getMessage());
        }
    }
}
