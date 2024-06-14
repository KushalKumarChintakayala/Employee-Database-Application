package com.sql.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.*;
import java.sql.*;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		String name = req.getParameter("name");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "root");
			PreparedStatement st = con.prepareStatement("select * from employees where employeename=?");
			st.setString(1, name);
			ResultSet rs = st.executeQuery();
			out.println("<table style='border: 1px solid black'");
			out.println("<tr>");
			out.println("<td style='border: 1px solid black', 'border-collapse: collapse'><strong> Customer Name </strong></td>");
			out.println("<td style='border: 1px solid black', 'border-collapse: collapse'><strong> Gender</strong> </td>");
			out.println("<td style='border: 1px solid black', 'border-collapse: collapse'><strong> Age </strong></td>");
			out.println("</tr>");
			int c = 0;
			while(rs.next()) {
				out.println("<tr>");
				out.println("<td style='border: 1px solid black', 'border-collapse: collapse'>" +  rs.getString("employeename") + "</td>");
				out.println("<td style='border: 1px solid black', 'border-collapse: collapse'>" + rs.getString("gender") + "</td>");
				out.println("<td style='border: 1px solid black', 'border-collapse: collapse'>" + rs.getString("age") + "</td>");
				out.println("</tr>");
				c += 1;
			}
			out.println("</table>");
			
			if(c==0) {
				out.println("Details not found of the given name " + name);
				out.println("<form method='get' action='index1.html'>");
				out.println("<input type='submit' value='to add'/>");
				out.println("</form>");

			}
			out.println("<form method='get' action='index.html'>");
			out.println("<input type='submit' value='return to main page'/>");
			out.println("</form>");
		}
		catch(Exception e) {
			out.println(e);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
