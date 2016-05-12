package com.sr03.admin;



import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sr03.manager.QuestionManager;
import com.sr03.manager.QuestionnaireManager;
import com.sr03.manager.ReponseManager;

public class GestionQuestion extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		QuestionManager questionManager = new QuestionManager();
		request = questionManager.modifyQuestion(request);
		request = questionManager.getQuestion(request);
		
		ReponseManager reponseManager = new ReponseManager();
		request = reponseManager.deleteReponse(request);
		request = reponseManager.modifyOrderReponses(request);	
		request = reponseManager.createReponse(request);
		request = reponseManager.getReponseList(request);
		
		this.getServletContext().getRequestDispatcher( "/admin/gestionQuestion.jsp" ).forward( request, response );
	}
}