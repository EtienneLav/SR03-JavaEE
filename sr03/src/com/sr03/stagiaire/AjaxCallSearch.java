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

public class AjaxCallSearch extends HttpServlet {
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		
		//récupérer le champ de rehcerche  du parcours envoyé par GET
		String recherche_field = (String) request.getParameter("Recherche");
		
		//récupérer les DAO pour faire les requêtes
		QuestionnaireDAO questionnaireDAO;
		questionnaireDAO = (QuestionnaireDAO) DAOFactory.getQuestionnaireDAO();
		
		QuestionDAO questionDAO;
		questionDAO = (QuestionDAO) DAOFactory.getQuestionDAO();
		
		//modifier la chaine de caractère pour une recherche LARGE
		recherche_field = "'%"+recherche_field+"%'";
		ArrayList questionnaire_list = new ArrayList();
		
		//rechercher les questionnaires correspondants à la recherche
		questionnaire_list = questionnaireDAO.findBySubject(recherche_field);
		
		//ajouter le nombre de questions de chaque questionnaire
		ArrayList nombre_question = new ArrayList();
		for(int i=0; i < questionnaire_list.size(); i++){
			Questionnaire questionnaire_current = new Questionnaire();
			questionnaire_current = (Questionnaire) questionnaire_list.get(i);
			
			int nombre_question_current = questionDAO.countQuestionFromQuestionnaire((int) questionnaire_current.getId());
			nombre_question.add(nombre_question_current-1);
		}
			
		request.setAttribute("nombre_questions", nombre_question);
		request.setAttribute("questionnaires", questionnaire_list);
		
		this.getServletContext().getRequestDispatcher( "/stagiaire/ajax.jsp" ).forward( request, response );
	}

}
