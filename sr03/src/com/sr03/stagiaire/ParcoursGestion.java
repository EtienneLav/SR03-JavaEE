package com.sr03.stagiaire;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sr03.manager.ParcoursManager;


public class ParcoursGestion extends HttpServlet {
	public HttpServletRequest doGetParcours( HttpServletRequest request, HttpServletResponse response, int id ) throws ServletException, IOException{
		
		ParcoursManager parcoursmanager = new ParcoursManager();
		request = parcoursmanager.getParcours(request, id);
	
		return request;
	}
	
	public HttpServletRequest doGetUtilisateurs( HttpServletRequest request, HttpServletResponse response, int _questionnaire_id, int _utilisateur_id ) throws ServletException, IOException{
		
		ParcoursManager parcoursmanager = new ParcoursManager();
		
		request = parcoursmanager.getRanking(request, _questionnaire_id);
		
		request = parcoursmanager.getStatistics(request, _questionnaire_id, _utilisateur_id);
		
		
	
		return request;
	}
	
	
}