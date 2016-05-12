package com.sr03.manager;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sr03.DAO.DAOFactory;
import com.sr03.DAO.QuestionDAO;
import com.sr03.DAO.QuestionnaireDAO;
import com.sr03.DAO.UtilisateurDAO;
import com.sr03.beans.Question;
import com.sr03.beans.Questionnaire;
import com.sr03.beans.Utilisateur;




public class QuestionManager{
	
	private QuestionDAO questionDAO;
	
	public QuestionManager() {
		this.questionDAO = (QuestionDAO) DAOFactory.getQuestionDAO();
	}
	
	public HttpServletRequest getQuestion(HttpServletRequest request) {
		Question question = this.questionDAO.find(Long.valueOf(request.getParameter("id")).longValue());
		request.setAttribute("id", question);
		return request;
	}
	
	public HttpServletRequest getQuestionnaireList(HttpServletRequest request) {
		ArrayList questions = this.questionDAO.findFromQuestionnaire(Long.valueOf(request.getParameter("id")).longValue());
		request.setAttribute("questions", questions);
		return request;
	}
	
	public HttpServletRequest deleteQuestion(HttpServletRequest request) {
		boolean deleted = false;
        if (request.getParameter("deleteQuestion") != null) {
        	Question quest = new Question();
        	quest.setId(Long.valueOf(request.getParameter("deleteQuestion")).longValue());
        	this.questionDAO.delete(quest);
        	deleted = true;        	
        }
        
		request.setAttribute("deletedQuestion", deleted);
		return request;
	}
	
	public HttpServletRequest createQuestion(HttpServletRequest request) {
		boolean addedQuestion = false;

        if (request.getParameter("intitule") != null && request.getParameter("id") != null) {
        	int ordre = this.questionDAO.countQuestionFromQuestionnaire(Integer.valueOf(request.getParameter("id")).intValue());
        	Question quest = new Question();
        	quest.setId(-1);
        	quest.setIntitule(request.getParameter("intitule").toString());
        	quest.setOrdre(ordre);
        	quest.setQuestionnaire(Integer.valueOf(request.getParameter("id")).intValue());
        	quest.setStatus(true);
        	this.questionDAO.create(quest);
        	addedQuestion = true;
        }
		
		request.setAttribute("addedQuestion", addedQuestion);
		return request;
	}
	
	public HttpServletRequest modifyOrderQuestions(HttpServletRequest request) {
		int orderQuestionModified = 0;
		
    	Enumeration<String> names = request.getParameterNames();
    	Hashtable ht = new Hashtable();
    	
    	while (names.hasMoreElements()) {
    		int idQuestion = -1;
    		try  
    		  {  
    		    Integer id = Integer.parseInt(names.nextElement()); 
    		    idQuestion = id.intValue();
    		  }  
    		  catch(NumberFormatException nfe)  
    		  {  
    			  idQuestion = -1;  
    		  }
    		 if (idQuestion != -1) {
    			 int ordreQuestion = Integer.valueOf(request.getParameter(Integer.toString(idQuestion))).intValue();
    			 if (!ht.containsKey(idQuestion) && !ht.containsValue(ordreQuestion)) {
        			 ht.put(idQuestion, ordreQuestion);			 
    			 } else {
    				 orderQuestionModified = -1;
    				 request.setAttribute("orderQuestionModified", orderQuestionModified);
    		    	 return request;
    			 }
    		 }
    	}
    	
    	Enumeration<Integer> enumKey = ht.keys();
    	while(enumKey.hasMoreElements()) {
    		int question = enumKey.nextElement();
    		int ordre = (int) ht.get(question);
    		Question quest = this.questionDAO.find(question);
    		quest.setOrdre(ordre);
    		this.questionDAO.update(quest);
    	}
        
		request.setAttribute("orderQuestionModified", orderQuestionModified);
		return request;
	}
	
}