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
import com.sr03.DAO.ReponseDAO;
import com.sr03.DAO.UtilisateurDAO;
import com.sr03.beans.Question;
import com.sr03.beans.Questionnaire;
import com.sr03.beans.Reponse;
import com.sr03.beans.Utilisateur;

public class ReponseManager{
	
	private ReponseDAO reponseDAO;
	
	public ReponseManager() {
		this.reponseDAO = (ReponseDAO) DAOFactory.getReponseDAO();
	}
	
	public HttpServletRequest getReponse(HttpServletRequest request) {
		Reponse reponse = this.reponseDAO.find(Long.valueOf(request.getParameter("id")).longValue());
		request.setAttribute("reponse", reponse);
		return request;
	}
	
	public HttpServletRequest getReponseList(HttpServletRequest request) {
		ArrayList reponses = this.reponseDAO.findFromQuestion(Long.valueOf(request.getParameter("id")).longValue());
		request.setAttribute("reponses", reponses);
		return request;
	}
	
	public HttpServletRequest deleteReponse(HttpServletRequest request) {
		boolean deleted = false;
        if (request.getParameter("deleteReponse") != null) {
        	Reponse rep = new Reponse();
        	rep.setId(Long.valueOf(request.getParameter("deleteReponse")).longValue());
        	this.reponseDAO.delete(rep);
        	deleted = true;        	
        }
        
		request.setAttribute("deletedReponse", deleted);
		return request;
	}
	
	public HttpServletRequest createReponse(HttpServletRequest request) {
		boolean addedReponse = false;

        if (request.getParameter("intitule") != null && request.getParameter("correct") != null && request.getParameter("id") != null) {
        	int ordre = this.reponseDAO.countReponseFromQuestion(Integer.valueOf(request.getParameter("id")).intValue());
        	Reponse rep = new Reponse();
        	rep.setId(-1);
        	rep.setIntitule(request.getParameter("intitule").toString());
        	rep.setOrdre(ordre);
        	rep.setQuestion(Integer.valueOf(request.getParameter("id")).intValue());
        	rep.setStatus(true);
        	if (request.getParameter("correct").toString().equals("1")) {
        		rep.setCorrect(true);
        	} else {
        		rep.setCorrect(false);
        	}
        	this.reponseDAO.create(rep);
        	addedReponse = true;
        }
		
		request.setAttribute("addedReponse", addedReponse);
		return request;
	}
	
	public HttpServletRequest modifyOrderReponses(HttpServletRequest request) {
		int orderReponseModified = 0;
		
    	Enumeration<String> names = request.getParameterNames();
    	Hashtable ht = new Hashtable();
    	
    	while (names.hasMoreElements()) {
    		int idReponse = -1;
    		try  
    		  {  
    		    Integer id = Integer.parseInt(names.nextElement()); 
    		    idReponse = id.intValue();
    		  }  
    		  catch(NumberFormatException nfe)  
    		  {  
    			  idReponse = -1;  
    		  }
    		 if (idReponse != -1) {
    			 int ordreReponse = Integer.valueOf(request.getParameter(Integer.toString(idReponse))).intValue();
    			 if (!ht.containsKey(idReponse) && !ht.containsValue(ordreReponse)) {
        			 ht.put(idReponse, ordreReponse);			 
    			 } else {
    				 orderReponseModified = -1;
    				 request.setAttribute("orderReponseModified", orderReponseModified);
    		    	 return request;
    			 }
    		 }
    	}
    	
    	Enumeration<Integer> enumKey = ht.keys();
    	while(enumKey.hasMoreElements()) {
    		int reponse = enumKey.nextElement();
    		int ordre = (int) ht.get(reponse);
    		Reponse rep = this.reponseDAO.find(reponse);
    		rep.setOrdre(ordre);
    		this.reponseDAO.update(rep);
			orderReponseModified = 1;
    	}
        
		request.setAttribute("orderReponseModified", orderReponseModified);
		return request;
	}
	
	public HttpServletRequest modifyReponse(HttpServletRequest request) {
		int modifiedReponse = 0;
		
        if (request.getParameter("modifierIntitule") != null && request.getParameter("id") != null) {
        	Reponse rep = this.reponseDAO.find(Long.valueOf(request.getParameter("id")).longValue());
        	rep.setIntitule(request.getParameter("modifierIntitule").toString());
        	if (request.getParameter("correct").toString().equals("1")) {
            	rep.setCorrect(true);
        	} else {
        		rep.setCorrect(false);
        	}
        	if (this.reponseDAO.update(rep) == null) {
        		modifiedReponse = -1;
        	} else {
        		modifiedReponse = 1;
        	}
        }
        
		request.setAttribute("modifiedReponse", modifiedReponse);
		return request;
	}
}