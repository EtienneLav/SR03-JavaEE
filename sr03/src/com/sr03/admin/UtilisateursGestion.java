package com.sr03.admin;

import com.sr03.DAO.DAOFactory;
import com.sr03.DAO.UtilisateurDAO;
import com.sr03.beans.*;
import com.sr03.Email;
import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UtilisateursGestion {
	
	public HttpServletRequest getUtilisateurGestion(HttpServletRequest request) {
		UtilisateurDAO UtilisateurDAO = (com.sr03.DAO.UtilisateurDAO) DAOFactory.getUtilisateurDAO();
		
		// Suppression d'un utilisateur si demandé dans la requête.
		request = this.deleteUser(request);
		
        // Création d'un utilisateur si demandé dans la requête.
		request = this.registerNewUser(request);
        
		// Récupération de la liste des utilisateurs.
		request.setAttribute("stagiaires", UtilisateurDAO.findStagiaires());
		
		return request;
	}
	
	public HttpServletRequest deleteUser(HttpServletRequest request) {
		boolean deleted = false;
		UtilisateurDAO UtilisateurDAO = (com.sr03.DAO.UtilisateurDAO) DAOFactory.getUtilisateurDAO();
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
		return request;
	}
	
	public HttpServletRequest registerNewUser(HttpServletRequest request) {
		UtilisateurDAO UtilisateurDAO = (com.sr03.DAO.UtilisateurDAO) DAOFactory.getUtilisateurDAO();
        HttpSession session = request.getSession();
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
    		
    		Email email = new Email();
    		String corps = "Merci pour votre inscription au site de sr03. Votre mot de passe est "+util.getMotDePasse();
    		String subject = "Incription";
    		
    		Utilisateur user = UtilisateurDAO.find((long) session.getAttribute("utilisateur_ID"));
    		if (! email.sendMessage(user.getEmail(), util.getEmail(), subject, corps)) {
    			System.out.println("Sending mail error");
    		}
        } else {
    		boolean added = false;
    		request.setAttribute("added", added);
        }
        return request;
	}
}