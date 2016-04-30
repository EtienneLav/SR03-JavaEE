package com.sr03.admin;

import com.sr03.beans.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LandingAdmin extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{

		this.getServletContext().getRequestDispatcher( "/admin/landing.jsp" ).forward( request, response );
	}
}