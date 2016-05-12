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

public class GestionQuestionnaire extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		QuestionnaireManager questionnaireManager = new QuestionnaireManager();
		request = questionnaireManager.modifyQuestionnaire(request);
		request = questionnaireManager.getQuestionnaire(request);
		
		QuestionManager questionManager = new QuestionManager();
		request = questionManager.deleteQuestion(request);
		request = questionManager.modifyOrderQuestions(request);	
		request = questionManager.createQuestion(request);
		request = questionManager.getQuestionnaireList(request);
		
		this.getServletContext().getRequestDispatcher( "/admin/gestionQuestionnaire.jsp" ).forward( request, response );
	}
}