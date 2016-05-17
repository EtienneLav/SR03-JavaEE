package com.sr03.stagiaire;


import com.sr03.DAO.DAOFactory;
import com.sr03.manager.UtilisateursGestion;
import com.sr03.beans.*;
import com.sr03.DAO.QuestionDAO;
import com.sr03.DAO.ReponseDAO;

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

public class QuestionToDisplay extends HttpServlet {
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		
		String questionnaire_id = (String) request.getParameter("questionnaire_id");
		
		Questionnaire questionnaire = DAOFactory.getQuestionnaireDAO().find(Long.valueOf(questionnaire_id).longValue());
		
		//Récupérer la première question
		ArrayList question = new ArrayList();
		
		QuestionDAO questionDAO;
		questionDAO = (QuestionDAO) DAOFactory.getQuestionDAO();

		question = questionDAO.findFromQuestionnaire(Long.valueOf(questionnaire_id).longValue());
		
		Question question_a_afficher = (Question) question.get(0);
		
		//Récupere la liste des réponses possibles
		
		ArrayList liste_reponses_possibles = new ArrayList();
		ReponseDAO reponseDAO;
		reponseDAO = (ReponseDAO) DAOFactory.getReponseDAO();
			
		liste_reponses_possibles = reponseDAO.findFromQuestion(question_a_afficher.getId());
		
		boolean est_derniere_question;
		if(question.size() == 0)
			 est_derniere_question = true;
		else
			est_derniere_question = false;

		//Variables de session pour la question courrante et la date de début du questionnaire
		Date current_date = new Date();
		Time current_time = new Time (current_date.getTime());
	
		System.out.println("temps debut questionnaire = "+current_time.toString());
		
		ArrayList tableau_reponses = new ArrayList();
		
		int question_init = 0;
		session.setAttribute("tableau_reponses", tableau_reponses);
		session.setAttribute("numero_question", question_init);
		session.setAttribute("debut_questionnaire", current_date);
		
		
		//Variables de requêtes pour afficher le questionnaire actuel, la question actuelle et la liste des réponses actuelles
		request.setAttribute("est_derniere_question", est_derniere_question);
		request.setAttribute("liste_reponses_possibles", liste_reponses_possibles);
		request.setAttribute("question", question_a_afficher);
		request.setAttribute("questionnaire", questionnaire);
		
		this.getServletContext().getRequestDispatcher( "/stagiaire/question.jsp" ).forward( request, response );
	}
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		
		//Enregistrement de la réponse donnée à la page précédente :
		String reponse_given = (String) request.getParameter("reponse_chosen");
		ArrayList tableau_reponses = new ArrayList();
		tableau_reponses =  (ArrayList) session.getAttribute("tableau_reponses");
		
		tableau_reponses.add(Long.valueOf(reponse_given).longValue());
		
		 for (int j = 0 ; j < tableau_reponses.size() ; j++) {
		     	System.out.println("clé: "+j+"valeur : "+tableau_reponses.get(j));
		 }
		
		//récupérer variable de session correspondant à la question courante
		int question_courante;
		question_courante = (int) session.getAttribute("numero_question");
		question_courante++;
				
		
		//Info sur le questionnaire
		String questionnaire_id = (String) request.getParameter("questionnaire_id");
		Questionnaire questionnaire = DAOFactory.getQuestionnaireDAO().find(Long.valueOf(questionnaire_id).longValue());
		

		//Récupérer la question à afficher question
		ArrayList question = new ArrayList();
		
		QuestionDAO questionDAO;
		questionDAO = (QuestionDAO) DAOFactory.getQuestionDAO();
		question = questionDAO.findFromQuestionnaire(Long.valueOf(questionnaire_id).longValue());
		
		Question question_a_afficher = new Question();
		ArrayList liste_reponses_possibles = new ArrayList();
		
		
		//Test si on est arrivé au bout du questionnaire ..
				boolean est_derniere_question;
				if( question.size()  <= (question_courante)){
					 est_derniere_question = true;
					 
				}
				else {
					est_derniere_question = false;
					question_a_afficher = (Question) question.get(question_courante);
					
					//Récupere la liste des réponses possibles
					ReponseDAO reponseDAO;
					reponseDAO = (ReponseDAO) DAOFactory.getReponseDAO();
						
					liste_reponses_possibles = reponseDAO.findFromQuestion(question_a_afficher.getId());
					
				}

		
		//Variables de session pour la question courrante
		session.setAttribute("numero_question", question_courante);
		session.setAttribute("tableau_reponses", tableau_reponses);
		
		//Variables de requêtes pour afficher le questionnaire actuel, la question actuelle et la liste des réponses actuelles
		
		request.setAttribute("est_derniere_question", est_derniere_question);
		request.setAttribute("liste_reponses_possibles", liste_reponses_possibles);
		request.setAttribute("question", question_a_afficher);
		request.setAttribute("questionnaire", questionnaire);
	
		
		this.getServletContext().getRequestDispatcher( "/stagiaire/question.jsp" ).forward( request, response );
	}

}
