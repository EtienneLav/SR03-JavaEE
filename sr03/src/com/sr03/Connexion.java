package com.sr03;

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

public class Connexion extends HttpServlet {
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		String mail = request.getParameter("mail");
		String mdp = request.getParameter("mdp");
		UtilisateurDAO UtilisateurDAO = (com.sr03.DAO.UtilisateurDAO) DAOFactory.getUtilisateurDAO();
		Utilisateur util = UtilisateurDAO.findByMailAndMdp(mail, mdp);

		if (util.getEmail() == null) {
			Boolean connexionFaillure = true;
			request.setAttribute("connexionFaillure", connexionFaillure);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/index.jsp" ).forward( request, response );
		} else {

			if (util.getType().equals("admin") && util.getStatus() == true) {
		        HttpSession session = request.getSession();
		        session.setAttribute("utilisateur_ID", util.getId());
		        session.setAttribute("utilisateur_Type", util.getType());
		        
				//this.getServletContext().getRequestDispatcher( "/admin/landing.jsp" ).forward( request, response );
	            response.sendRedirect( request.getContextPath() + "/admin/landing" );

			} else if (util.getType().equals("stagiaire") && util.getStatus() == true) {
		        HttpSession session = request.getSession();
		        session.setAttribute("utilisateur_ID", util.getId());
		        session.setAttribute("utilisateur_Type", util.getType());
	            response.sendRedirect( request.getContextPath() + "/stagiaire/landing" );
			} else {
				Boolean connexionFaillure = true;
				this.getServletContext().getRequestDispatcher( "/WEB-INF/index.jsp" ).forward( request, response );
			}
		}
	}
}