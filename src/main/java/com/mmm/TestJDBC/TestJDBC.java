package com.mmm.TestJDBC;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestJDBC")
public class TestJDBC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String user = "springuser";
			String pass = "springuser";
			
			String jdbcUrl = "jdbc:mysql://localhost:3306/customerdetails?useSSL=false";
			String driver = "com.mysql.jdbc.Driver";
			
			PrintWriter out = response.getWriter();
			out.println("CONNECTING TO DATABASE!!!!!!!!!!!!!!!!");
			
			Class.forName(driver);
			
			Connection myConnection = DriverManager.getConnection(jdbcUrl, user, pass);
			
			out.println("SUCCES!");
			
			myConnection.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new ServletException(e);
		}
	}
}
