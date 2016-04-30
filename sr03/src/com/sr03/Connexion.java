package com.sr03;

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

		Utilisateur util = checkUser(mail, mdp);

		if (util == null) {
			Boolean connexionFaillure = true;
			request.setAttribute("connexionFaillure", connexionFaillure);
			this.getServletContext().getRequestDispatcher( "/WEB-INF/index.jsp" ).forward( request, response );
		} else {
			request.setAttribute("utilisateur", util);
			if (util.getType() == "admin" && util.getStatus() == true) {
		        HttpSession session = request.getSession();
		        session.setAttribute("utilisateur_ID", util.getId());
		        session.setAttribute("utilisateur_Type", util.getType());
		        
	            response.sendRedirect( request.getContextPath() + "/admin/landing" );

				//this.getServletContext().getRequestDispatcher( "/admin/landing.jsp" ).forward( request, response );
			} else if (util.getType() == "stagiaire" && util.getStatus() == true) {
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
	
	public Utilisateur checkUser(String email, String mdp) {
		if (email.equals("admin@hotmail.fr") && mdp.equals("test")) {
			Utilisateur util = new Utilisateur();
			util.setId(1);
			util.setNom("admin");
			util.setEmail("admin@hotmail.fr");
			util.setType("admin");
			util.setStatus(true);
			return util;
		} else if (email.equals("stagiaire@hotmail.fr") && mdp.equals("test")) {
			Utilisateur util = new Utilisateur();
			util.setId(2);
			util.setNom("stagiaire");
			util.setEmail("stagiaire@hotmail.fr");
			util.setType("stagiaire");
			util.setStatus(true);
			return util;
		} else {
			return null;
		}
	}
}