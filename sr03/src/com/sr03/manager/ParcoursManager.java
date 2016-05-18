
package com.sr03.manager;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sr03.DAO.DAOFactory;
import com.sr03.DAO.ParcoursDAO;
import com.sr03.DAO.QuestionnaireDAO;
import com.sr03.DAO.QuestionDAO;
import com.sr03.DAO.ReponseDAO;
import com.sr03.DAO.UtilisateurDAO;
import com.sr03.beans.Utilisateur;
import com.sr03.beans.Parcours;
import com.sr03.beans.Question;
import com.sr03.beans.Reponse;



public class ParcoursManager{
	
	final int NOMBRE_ELEMENTS_PAR_PAGE = 10;
	
	private ParcoursDAO parcoursDAO;
	private QuestionDAO questionDAO;
	private ReponseDAO reponseDAO;
	
	
	public ParcoursManager() {
		this.parcoursDAO = (ParcoursDAO) DAOFactory.getParcoursDAO();
		this.questionDAO = (QuestionDAO) DAOFactory.getQuestionDAO();
		this.reponseDAO = (ReponseDAO) DAOFactory.getReponseDAO();
		
	}
	
	public HttpServletRequest getParcours(HttpServletRequest request, int _id) {
		
		//récupérer les parcours déjà effectués
		ArrayList ParcoursArray = new ArrayList();
		ParcoursArray = this.parcoursDAO.findByUtilisateur(_id);
		
		//Récupérer le nombre de parcours déjà effectués pour la pagination
			int nombre_parcours_effectues ;
			nombre_parcours_effectues = this.parcoursDAO.countParcoursDoneByUser(_id) - 1;
					
			//Adapter le nombre de page en fonction du nombre de parcours à afficher
			int nombre_pages_parcours_effectues;
			if(nombre_parcours_effectues % NOMBRE_ELEMENTS_PAR_PAGE == 0)
				nombre_pages_parcours_effectues = nombre_parcours_effectues/NOMBRE_ELEMENTS_PAR_PAGE;
			else
				nombre_pages_parcours_effectues = nombre_parcours_effectues/NOMBRE_ELEMENTS_PAR_PAGE +1;
					
			System.out.println("nombre de page parcours effectues : "+nombre_pages_parcours_effectues);
			request.setAttribute("nombre_parcours_effectues", nombre_parcours_effectues);
			request.setAttribute("nombre_pages_parcours_effectues", nombre_pages_parcours_effectues);
		
		

		//récupérer les parcours libres (non effectués)
			ArrayList QuestionnaireArray = new ArrayList();
			QuestionnaireArray = this.parcoursDAO.findQuestionnairesLibre(_id);
			
			//Récupérer le nombre de parcours déjà effectués pour la pagination
			int nombre_parcours_non_effectues ;
			nombre_parcours_non_effectues = this.parcoursDAO.countParcoursNotDoneByUser(_id) - 1;
							
			//Adapter le nombre de page en fonction du nombre de parcours à afficher
			int nombre_pages_parcours_non_effectues;
			if(nombre_parcours_non_effectues % NOMBRE_ELEMENTS_PAR_PAGE == 0)
				nombre_pages_parcours_non_effectues = nombre_parcours_non_effectues / NOMBRE_ELEMENTS_PAR_PAGE;
			else
				nombre_pages_parcours_non_effectues = nombre_parcours_effectues/NOMBRE_ELEMENTS_PAR_PAGE +1;
							
			System.out.println("nombre de page parcours non effectues : "+nombre_pages_parcours_non_effectues);
			request.setAttribute("nombre_parcours_non_effectues", nombre_parcours_non_effectues);
			request.setAttribute("nombre_pages_parcours_non_effectues", nombre_pages_parcours_non_effectues);
				
				

		request.setAttribute("questionnaires_libres", QuestionnaireArray);
		request.setAttribute("parcours", ParcoursArray);
		
		return request;
	}
	
	public HttpServletRequest getRanking(HttpServletRequest request, int _id) {
		

		ArrayList RankingParcours = new ArrayList();
		RankingParcours = this.parcoursDAO.findUtilisateursByQuestions(_id);
	
		
		//Récupérer le nombre de parcours pour la pagination
		int nombre_parcours ;
		nombre_parcours = this.parcoursDAO.countParcoursByQuestionnaire(_id) - 1;
		
		//Adapter le nombre de page en fonction du nombre de parcours à afficher
		int nombre_pages;
		if(nombre_parcours % NOMBRE_ELEMENTS_PAR_PAGE == 0)
			nombre_pages = nombre_parcours /NOMBRE_ELEMENTS_PAR_PAGE;
		else
			nombre_pages = nombre_parcours/NOMBRE_ELEMENTS_PAR_PAGE +1;
		
		System.out.println("nombre de page : "+nombre_pages);
		request.setAttribute("nombre_parcours", nombre_parcours);
		request.setAttribute("nombre_pages", nombre_pages);
		request.setAttribute("ranking_utilisateur", RankingParcours);
		
		return request;
	}
	
	public HttpServletRequest getStatistics(HttpServletRequest request, int _id, int _utilisateur_id) {
		

		ArrayList FieldParcours = new ArrayList();
		FieldParcours = this.parcoursDAO.findByQuestionnaire(_id);
		
		//Calcul du score moyen et temps moyen
		float score_moyen = 0;
		Time duree_moyenne;
		int minutes = 0;
		int secondes = 0;
		int heures = 0;
		int total = 0;
		
		 for (int i = 0 ; i < FieldParcours.size() ; i++) {
		     	Parcours ranking_current = (Parcours) FieldParcours.get(i);
		     	score_moyen = ranking_current.getScore() + score_moyen;
		     	minutes = minutes + ranking_current.getDuree().getMinutes();
		     	secondes = secondes + ranking_current.getDuree().getSeconds();
		     	heures = heures + ranking_current.getDuree().getHours();
		 }
		 
		total = secondes + minutes*60 + heures*3600;
		heures = total / 3600;
		
		total = total - heures *3600;
		
		minutes = total / 60;
		
		total = total - minutes*60;
		
		secondes = total;
		
		heures = heures /FieldParcours.size();
		secondes = secondes / FieldParcours.size();
		minutes = minutes / FieldParcours.size();
		
		duree_moyenne = new Time(heures, minutes, secondes);
		
		//Calcul de la position du joueurs par rapport au field
		int position = 0;
		ArrayList Field = new ArrayList();
		Field = this.parcoursDAO.findByQuestionnaire(_id);
		
		System.out.println("taille : "+Field.size());
		System.out.println("numéro utilisateur : "+_utilisateur_id);
		
		for (int j = 0 ; j < Field.size() ; j++) {
	     	Parcours position_current = (Parcours) Field.get(j);
	     	System.out.println("numéro  : "+position_current.getUtilisateur().getId());
	     	if(position_current.getUtilisateur().getId() == _utilisateur_id)
	     		position = j+1;
	 }
		
		request.setAttribute("nombre_stagiaire", FieldParcours.size());
		request.setAttribute("utilisateur_position", position);
		request.setAttribute("score_moyen", score_moyen/FieldParcours.size());
		request.setAttribute("duree_moyenne", duree_moyenne);
		
		
		return request;
	}
	
	public HttpServletRequest getQuestionsFromAQuestionnaire(HttpServletRequest request, int _questionnaire_id, int _parcours_id) {
		
		//récuperer la liste des questions pour le questionnaire voulu
		ArrayList QuestionsArray = new ArrayList();
		QuestionsArray = this.questionDAO.findFromQuestionnaire(_questionnaire_id);
	
		// Pour chaque question récupérer la bonne réponse
		ArrayList ReponseArray = new ArrayList();
		
		//Pour chaque question récupérer la réponse de l'utilisateur
		ArrayList ReponseUserArray = new ArrayList();
		
		for (int j = 0 ; j < QuestionsArray.size() ; j++) {
			
	     	Question question_current = (Question) QuestionsArray.get(j);
	     	Reponse reponse_question = new Reponse();
	     	reponse_question = this.reponseDAO.findCorrectFromQuestion(question_current.getId());
	     	ReponseArray.add(reponse_question);
	     	
	     	Reponse reponse_user_question = new Reponse();
	     	reponse_user_question = this.reponseDAO.findUserReponse(question_current.getId(), _parcours_id);
	     	ReponseUserArray.add(reponse_user_question);
	     	}
		
		
		
		request.setAttribute("question", QuestionsArray);
		request.setAttribute("correct_reponse", ReponseArray);
		request.setAttribute("user_reponse", ReponseUserArray);
		
		
		
		return request;
	}
	
	
	
	
}