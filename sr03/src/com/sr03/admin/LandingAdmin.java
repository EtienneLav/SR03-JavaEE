package com.sr03.admin;

import com.sr03.DAO.DAOFactory;
import com.sr03.DAO.UtilisateurDAO;
import com.sr03.beans.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LandingAdmin extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        HttpSession session = request.getSession();
        Utilisateur util = DAOFactory.getUtilisateurDAO().find((long)session.getAttribute("utilisateur_ID"));
        
		request.setAttribute("utilisateur", util);
		this.getServletContext().getRequestDispatcher( "/admin/landing.jsp" ).forward( request, response );
	}
}