package com.sr03.stagiaire;

import com.sr03.DAO.DAOFactory;
import com.sr03.manager.UtilisateursGestion;
import com.sr03.beans.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DetailsParcours extends HttpServlet {
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        
		HttpSession session = request.getSession();
		Utilisateur util = DAOFactory.getUtilisateurDAO().find((long)session.getAttribute("utilisateur_ID"));
		request.setAttribute("utilisateur", util);
		
		//récupérer l'id de l'utilisateur
		int utilisateur_id = (int) (long) session.getAttribute("utilisateur_ID");
		
		//récupérer id du parcours envoyé par GET
		String number_parcours = (String) request.getParameter("parcours_number");
		
		//recupérer le parcours correspond à cet id
		Parcours parcours = DAOFactory.getParcoursDAO().find(Long.valueOf(number_parcours).longValue());
		request.setAttribute("parcours", parcours);
		
		int questionnaire_id = (int) parcours.getQuestionnaire().getId();
		request.setAttribute("questionnaire_id", questionnaire_id);
		
		//récupérer les utilisateurs ayant réalisés ce parcours + questions du questionnaire
		ParcoursGestion parcGestion = new ParcoursGestion();
		request = parcGestion.doGetUtilisateurs(request, response, (int) parcours.getQuestionnaire().getId(), utilisateur_id, Integer.parseInt(number_parcours));
		

		this.getServletContext().getRequestDispatcher( "/stagiaire/parcours_details.jsp" ).forward( request, response );
	}

}
