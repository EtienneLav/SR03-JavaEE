package com.sr03.stagiaire;


import com.sr03.DAO.DAOFactory;
import com.sr03.DAO.ParcoursDAO;
import com.sr03.DAO.QuestionDAO;
import com.sr03.DAO.ReponseDAO;
import com.sr03.DAO.Reponse_parcoursDAO;
import com.sr03.manager.UtilisateursGestion;
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

import com.sr03.tools.*;

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
		Utilisateur utilisateur = DAOFactory.getUtilisateurDAO().find((long)session.getAttribute("utilisateur_ID"));
		
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
		Fonctions fonction = new Fonctions();
		Time duree_questionnaire = fonction.timeDifferenceWithNow(temps_debut_questionnaire);
		System.out.println("duree du questionnaire : "+duree_questionnaire.toString());
		
		//Création du parcours correspondant
		ParcoursDAO parcoursDAO;
		parcoursDAO = (ParcoursDAO) DAOFactory.getParcoursDAO();
		
		Parcours current_parcours = new Parcours (-1, utilisateur, questionnaire, score_questionnaire, duree_questionnaire);
		
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
		
		// récupération du nombre de questions pour le questionnaire
		QuestionDAO questionDAO;
		questionDAO = (QuestionDAO) DAOFactory.getQuestionDAO();
		
		int questionnaire_id_int = (int) Long.valueOf(questionnaire_id).longValue();
		int nombre_points_max = questionDAO.countQuestionFromQuestionnaire(questionnaire_id_int);
		
		
		request.setAttribute("points_max", nombre_points_max - 1);
		request.setAttribute("score", score_questionnaire);
		request.setAttribute("questionnaire", questionnaire);
		
		this.getServletContext().getRequestDispatcher( "/stagiaire/validation.jsp" ).forward( request, response );
		
		}
	}