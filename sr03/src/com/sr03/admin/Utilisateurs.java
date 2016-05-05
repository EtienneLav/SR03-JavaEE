package com.sr03.admin;

import com.sr03.DAO.DAOFactory;
import com.sr03.DAO.UtilisateurDAO;
import com.sr03.beans.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
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
		boolean deleted = false;

        if (request.getParameter("delete") != null) {
        	Utilisateur util = new Utilisateur();
        	util.setId(Long.valueOf(request.getParameter("delete")).longValue());
        	UtilisateurDAO.delete(util);
        	deleted = true;
    		request.setAttribute("deleted", deleted);
        	
        } else {
        	deleted = false;
    		request.setAttribute("deleted", deleted);
        }
        
        if (request.getParameter("mail") != null && request.getParameter("mdp") != null) {
        	Utilisateur util = new Utilisateur();
        	util.setEmail(request.getParameter("mail").toString());
        	util.setMotDePasse(request.getParameter("mdp").toString());
        	util.setType(request.getParameter("type"));
        	util.setStatus(true);
        	util.setId(-1);
        	java.util.Date date= new java.util.Date();
        	util.setDateInscription(new Timestamp(date.getTime()));
        	
        	if (request.getParameter("nom") != null)
        		util.setNom(request.getParameter("nom"));

        	if (request.getParameter("societe") != null)
        		util.setSociete(request.getParameter("societe"));

        	if (request.getParameter("telephone") != null)
        		util.setTelephone(request.getParameter("telephone"));
        	
        	util = UtilisateurDAO.create(util);
    		boolean added = false;
    		
        	if (util.getId() != -1) {
        		added = true;
        	}
    		request.setAttribute("added", added);

        } else {
    		boolean added = false;
    		request.setAttribute("added", added);
        }
        
		ArrayList<Utilisateur> stagiaires = UtilisateurDAO.findStagiaires();
		request.setAttribute("stagiaires", stagiaires);
		this.getServletContext().getRequestDispatcher( "/admin/utilisateurs.jsp" ).forward( request, response );
	}
}