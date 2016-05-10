package com.sr03.manager;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sr03.DAO.DAOFactory;
import com.sr03.DAO.QuestionnaireDAO;
import com.sr03.DAO.UtilisateurDAO;
import com.sr03.beans.Questionnaire;
import com.sr03.beans.Utilisateur;




public class QuestionnaireManager{
	
	private QuestionnaireDAO questionnaireDAO;
	
	public QuestionnaireManager() {
		this.questionnaireDAO = (QuestionnaireDAO) DAOFactory.getQuestionnaireDAO();
	}
	
	public HttpServletRequest getQuestionnaireList(HttpServletRequest request) {
		ArrayList questionnaires = this.questionnaireDAO.findAll();
		request.setAttribute("questionnaires", questionnaires);
		return request;
	}
	
	public HttpServletRequest getQuestionnaireAdminGestion(HttpServletRequest request) {		
		// Suppression d'un utilisateur si demandé dans la requête.
		request = this.deleteQuestionnaire(request);

        // Création d'un questionnaire si demandé dans la requête.
		request = this.createQuestionnaire(request);
        
		// Récupération de la liste des utilisateurs.
		request = this.getQuestionnaireList(request);
		
		return request;
	}
	
	public HttpServletRequest deleteQuestionnaire(HttpServletRequest request) {
		boolean deleted = false;
        if (request.getParameter("deleteQuestionnaire") != null) {
        	Questionnaire quest = new Questionnaire();
        	quest.setId(Long.valueOf(request.getParameter("deleteQuestionnaire")).longValue());
        	this.questionnaireDAO.delete(quest);
        	deleted = true;        	
        }
        
		request.setAttribute("deleteQuestionnaire", deleted);
		return request;
	}
	
	public HttpServletRequest createQuestionnaire(HttpServletRequest request) {
		boolean addedQuestionnaire = false;

        if (request.getParameter("sujet") != null) {
        	Questionnaire quest = new Questionnaire();
        	quest.setSujet(request.getParameter("sujet").toString());
        	quest.setStatus(true);
        	quest.setId(-1);
        	this.questionnaireDAO.create(quest);
        	addedQuestionnaire = true;
        }
		
		request.setAttribute("addedQuestionnaire", addedQuestionnaire);
		return request;
	}
	
}