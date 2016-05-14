
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
import com.sr03.DAO.UtilisateurDAO;
import com.sr03.beans.Utilisateur;
import com.sr03.beans.Parcours;


public class ParcoursManager{
	
	private ParcoursDAO parcoursDAO;
	
	
	public ParcoursManager() {
		this.parcoursDAO = (ParcoursDAO) DAOFactory.getParcoursDAO();
		
	}
	
	public HttpServletRequest getParcours(HttpServletRequest request, int _id) {
		
		ArrayList ParcoursArray = new ArrayList();
		ParcoursArray = this.parcoursDAO.findByUtilisateur(_id);
		
		ArrayList QuestionnaireArray = new ArrayList();
		QuestionnaireArray = this.parcoursDAO.findQuestionnairesLibre();
		
		request.setAttribute("questionnaires_libres", QuestionnaireArray);

		request.setAttribute("parcours", ParcoursArray);
		return request;
	}
	
	public HttpServletRequest getRanking(HttpServletRequest request, int _id) {
		

		ArrayList RankingParcours = new ArrayList();
		RankingParcours = this.parcoursDAO.findUtilisateursByQuestions(_id);
	
		request.setAttribute("ranking_utilisateur", RankingParcours);
		
		
		return request;
	}
	
public HttpServletRequest getStatistics(HttpServletRequest request, int _id, int _utilisateur_id) {
		

		ArrayList FieldParcours = new ArrayList();
		FieldParcours = this.parcoursDAO.findByQuestionnaire(_id);
		
		//Calcul du score moyen et temps moyen
		int score_moyen = 0;
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
	
	
	
	
}