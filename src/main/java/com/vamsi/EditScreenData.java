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

@WebServlet("/editScreen")
public class EditScreenData extends HttpServlet {
    private static final String query = "select * from student where id=?";

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        int id = Integer.parseInt(req.getParameter("id"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_details", "root", "kgfyash@1234");
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Edit Student</title>");
            out.println("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css'>");
            out.println("</head>");
            out.println("<body>");

            out.println("<div class='container mt-5'>");
            out.println("<form action='editurl' method='post'>"); // Changed action URL
            out.println("<input type='hidden' name='id' value='" + id + "'>"); // Add hidden input for ID
            out.println("<table class='table'>");

            if (rs.next()) {
                out.println("<tr>");
                out.println("<td>ID</td>");
                out.println("<td>" + rs.getInt(1) + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>Name</td>");
                out.println("<td><input type='text' name='studentName' class='form-control' value='" + rs.getString(2) + "'></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>Village</td>");
                out.println("<td><input type='text' name='studentVillage' class='form-control' value='" + rs.getString(3) + "'></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>Number</td>");
                out.println("<td><input type='text' name='studentPhoneNumber' class='form-control' value='" + rs.getString(4) + "'></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>Gender</td>");
                out.println("<td>");
                out.println("<select name='studentGender' class='form-control'>"); // Removed duplicate name attribute

                String gender = rs.getString(5);
                out.println("<option value='male'" + (gender.equals("male") ? " selected" : "") + ">Male</option>");
                out.println("<option value='female'" + (gender.equals("female") ? " selected" : "") + ">Female</option>");
                out.println("<option value='transgender'" + (gender.equals("transgender") ? " selected" : "") + ">Transgender</option>");

                out.println("</select>");
                out.println("</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("<button type='submit' class='btn btn-primary'>Update</button>");
            out.println("</form>");
            out.println("</div>");

            out.println("</body>");
            out.println("</html>");

            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println(e.getMessage());
        }
    }
}
