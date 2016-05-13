package com.sr03.stagiaire;

import com.sr03.DAO.DAOFactory;
import com.sr03.admin.UtilisateursGestion;
import com.sr03.beans.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LandingStagiaire extends HttpServlet {
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        
		HttpSession session = request.getSession();
        Utilisateur util = DAOFactory.getUtilisateurDAO().find((long)session.getAttribute("utilisateur_ID"));
		request.setAttribute("utilisateur", util);
		
		//Gestion pour afficher parcours déjà faits et les autres disponibles
		int id = (int) (long) session.getAttribute("utilisateur_ID");
		ParcoursGestion parcGestion = new ParcoursGestion();
		request = parcGestion.doGetParcours(request, response, id);
		
		this.getServletContext().getRequestDispatcher( "/stagiaire/landing.jsp" ).forward( request, response );
	}
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		Utilisateur updated_stagiaire = new Utilisateur();
		
		//Gestion pour afficher parcours déjà faits et les autres disponibles
		int id = (int) (long) session.getAttribute("utilisateur_ID");
		ParcoursGestion parcGestion = new ParcoursGestion();
		request = parcGestion.doGetParcours(request, response, id);
		
		//Récupère les info de l'utilisateur courant
		Utilisateur current_stagiaire = DAOFactory.getUtilisateurDAO().find((long)session.getAttribute("utilisateur_ID"));
		
		Long updated_id = (Long) session.getAttribute("utilisateur_ID");
		updated_stagiaire.setId(updated_id);
		
		//Récupère les champs pouvant avoir été modifiés
		String updated_nom = (String) request.getParameter("nom");
		updated_stagiaire.setNom(updated_nom);
		
		String updated_email = (String) request.getParameter("email");
		updated_stagiaire.setEmail(updated_email);
		
		String updated_societe = (String) request.getParameter("societe");
		updated_stagiaire.setSociete(updated_societe);
		
		String updated_telephone = (String) request.getParameter("telephone");
		updated_stagiaire.setTelephone(updated_telephone);
		
		//Concatène les champ non modifiable
		updated_stagiaire.setMotDePasse(current_stagiaire.getMotDePasse());
		updated_stagiaire.setType(current_stagiaire.getType());
		updated_stagiaire.setStatus(current_stagiaire.getStatus());
		
		//Mise à jour de l'utilisateur stagiaire
		Utilisateur stagiaire = DAOFactory.getUtilisateurDAO().update(updated_stagiaire);

		//Renvoie de l'objet utilisateur de type stagiaire à notre même page
		request.setAttribute("utilisateur", stagiaire);
		
		this.getServletContext().getRequestDispatcher( "/stagiaire/landing.jsp" ).forward( request, response );
	}
}