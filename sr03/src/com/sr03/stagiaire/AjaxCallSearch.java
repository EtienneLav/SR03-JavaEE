package com.sr03.stagiaire;

import com.sr03.DAO.DAOFactory;
import com.sr03.DAO.ParcoursDAO;
import com.sr03.DAO.QuestionDAO;
import com.sr03.DAO.QuestionnaireDAO;
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

import com.sr03.tools.*;

public class AjaxCallSearch extends HttpServlet {
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		
		//Tester si l'on provient bien d'une requête Ajax
		if("XMLHttpRequest".equals(
               request.getHeader("X-Requested-With")) == true ) {
		
			//récupérer le champ de rehcerche  du parcours envoyé par GET
			String recherche_champ = (String) request.getParameter("Recherche");
			
			//récupérer les DAO pour faire les requêtes
			QuestionnaireDAO questionnaireDAO;
			questionnaireDAO = (QuestionnaireDAO) DAOFactory.getQuestionnaireDAO();
			
			QuestionDAO questionDAO;
			questionDAO = (QuestionDAO) DAOFactory.getQuestionDAO();
			
			//modifier la chaine de caractère pour une recherche LARGE
			Fonctions fonction = new Fonctions();
			String champ_recherche_parse;
			
			champ_recherche_parse = fonction.parsedStringToBeSearched(recherche_champ);
			
			//rechercher les questionnaires correspondants à la recherche
			ArrayList liste_questionnaires = new ArrayList();
			liste_questionnaires = questionnaireDAO.findBySubject(champ_recherche_parse);
			
			//ajouter le nombre de questions de chaque questionnaire
			ArrayList nombre_question = new ArrayList();
			for(int i=0; i < liste_questionnaires.size(); i++){
				
				Questionnaire questionnaire_current = new Questionnaire();
				questionnaire_current = (Questionnaire) liste_questionnaires.get(i);
				
				int nombre_question_current = questionDAO.countQuestionFromQuestionnaire((int) questionnaire_current.getId());
				nombre_question.add(nombre_question_current-1);
			}
				
			request.setAttribute("nombre_questions", nombre_question);
			request.setAttribute("questionnaires", liste_questionnaires);
			
			this.getServletContext().getRequestDispatcher( "/stagiaire/ajax.jsp" ).forward( request, response );
		}
		
		else
			this.getServletContext().getRequestDispatcher( "/WEB-INF/erreur.jsp" ).forward( request, response );
		
	}

}