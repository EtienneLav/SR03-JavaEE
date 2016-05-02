package com.sr03.admin;

import com.sr03.DAO.DAOFactory;
import com.sr03.DAO.UtilisateurDAO;
import com.sr03.beans.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Utilisateurs extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        HttpSession session = request.getSession();
		UtilisateurDAO UtilisateurDAO = (com.sr03.DAO.UtilisateurDAO) DAOFactory.getUtilisateurDAO();

        if (request.getParameter("delete") != null) {
        	Utilisateur util = new Utilisateur();
        	//util.setId(Long.getLong(request.getParameter("delete")));
        	util.setId(6);
        	UtilisateurDAO.delete(util);
        	
        }
        
		ArrayList<Utilisateur> stagiaires = UtilisateurDAO.findStagiaires();
		request.setAttribute("stagiaires", stagiaires);
		this.getServletContext().getRequestDispatcher( "/admin/utilisateurs.jsp" ).forward( request, response );
	}
}