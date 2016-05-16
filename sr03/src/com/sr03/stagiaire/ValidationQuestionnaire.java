package com.sr03.stagiaire;


import com.sr03.DAO.DAOFactory;
import com.sr03.DAO.ParcoursDAO;
import com.sr03.DAO.QuestionDAO;
import com.sr03.DAO.ReponseDAO;
import com.sr03.DAO.Reponse_parcoursDAO;
import com.sr03.admin.UtilisateursGestion;
import com.sr03.beans.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ValidationQuestionnaire extends HttpServlet {
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		
		//Informations sur le questionnaire
		String questionnaire_id = (String) request.getParameter("questionnaire_id");
		Questionnaire questionnaire = DAOFactory.getQuestionnaireDAO().find(Long.valueOf(questionnaire_id).longValue());
		
		//Récupération du tableau contenant les réponses aux questions
		ArrayList tableau_reponses = new ArrayList();
		tableau_reponses =  (ArrayList) session.getAttribute("tableau_reponses");
		
		//Récupération de la date de début du questionnaire
		Date date_debut = new Date();
		date_debut = (Date) session.getAttribute("debut_questionnaire");
		
		Time temps_debut_questionnaire = new Time(date_debut.getTime());
		//Récupération de l'identificateur du stagiaire
		Utilisateur current_util = DAOFactory.getUtilisateurDAO().find((long)session.getAttribute("utilisateur_ID"));
		
		//Calcul du score
		int score_questionnaire = 0;
		
		for (int i = 0 ; i < tableau_reponses.size() ; i++) {
 			long reponse_id = (long) tableau_reponses.get(i);
 			ReponseDAO reponseDAO;
 			reponseDAO = (ReponseDAO) DAOFactory.getReponseDAO();

 			Reponse current_reponse = new Reponse();
 			current_reponse = reponseDAO.find(reponse_id);
 			
 			if(current_reponse.getCorrect() == true)
 				score_questionnaire++;
 			
 			System.out.println("score du questionnaire : "+score_questionnaire);
		}
		//Calcul de la durée effective du questionnaire
		int secondes_debut = temps_debut_questionnaire.getSeconds();
		int minutes_debut = temps_debut_questionnaire.getMinutes();
		int heures_debut = temps_debut_questionnaire.getHours();

		int total_second_debut = heures_debut *3600 + minutes_debut * 60 + secondes_debut;
		
		Date current_date = new Date();
		Time current_time = new Time(current_date.getTime());
		int secondes_now = current_time.getSeconds();
		int minutes_now = current_time.getMinutes();
		int heures_now = current_time.getHours();

		int total_second_now = heures_now *3600 + minutes_now * 60 + secondes_now;
		
		int duree_questionnaire_second = total_second_now - total_second_debut;
		
		int heures_questionnaire = duree_questionnaire_second/3600;
		duree_questionnaire_second = duree_questionnaire_second - heures_questionnaire*3600;
		
		int minutes_questionnaire = duree_questionnaire_second/60;
		duree_questionnaire_second = duree_questionnaire_second - minutes_questionnaire*60;
		
		Time duree_questionnaire = new Time(heures_questionnaire, minutes_questionnaire, duree_questionnaire_second );
		
		System.out.println("duree du questionnaire : "+duree_questionnaire.toString());
		
		//Création du parcours correspondant
		ParcoursDAO parcoursDAO;
		parcoursDAO = (ParcoursDAO) DAOFactory.getParcoursDAO();
		
		Parcours current_parcours = new Parcours (-1, current_util, questionnaire, score_questionnaire, duree_questionnaire);
		
		Parcours created_parcours = new Parcours();
		created_parcours = parcoursDAO.create(current_parcours);
		
		System.out.println("id du parcours créé : "+ created_parcours.getId());
		//Création du parcours de réponse correspondant
		
		Reponse_parcoursDAO reponse_parcoursDAO;
		reponse_parcoursDAO = (Reponse_parcoursDAO) DAOFactory.getReponseparcoursDAO();
		
		for (int j = 0 ; j < tableau_reponses.size() ; j++) {
			
 			long reponse_id = (long) tableau_reponses.get(j);
 			Reponse_parcours created_reponse_parcours = new Reponse_parcours();
 			
 			Reponse_parcours current_reponse = new Reponse_parcours(-1, (int) created_parcours.getId(), (int) reponse_id);
 			created_reponse_parcours = reponse_parcoursDAO.create(current_reponse);
 			System.out.println("id du reponse_parcours créé : "+ created_reponse_parcours.getId());
 			}

		request.setAttribute("score", score_questionnaire);
		request.setAttribute("questionnaire", questionnaire);
		
		this.getServletContext().getRequestDispatcher( "/stagiaire/validation.jsp" ).forward( request, response );
		
		}
	}