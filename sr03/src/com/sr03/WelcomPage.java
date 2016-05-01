package com.sr03;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.sr03.beans.*;
import com.sr03.DAO.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomPage extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
				
		this.getServletContext().getRequestDispatcher( "/WEB-INF/index.jsp" ).forward( request, response );
	}
}