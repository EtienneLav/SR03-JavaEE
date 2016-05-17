package com.sr03.stagiaire;


import com.sr03.DAO.DAOFactory;
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

public class AccueilQuestionnaire extends HttpServlet {
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
        
		HttpSession session = request.getSession();
		
		String questionnaire_id = (String) request.getParameter("questionnaire_id");
		
		Questionnaire questionnaire = DAOFactory.getQuestionnaireDAO().find(Long.valueOf(questionnaire_id).longValue());
		
		request.setAttribute("questionnaire", questionnaire);
		
		this.getServletContext().getRequestDispatcher( "/stagiaire/accueil_questionnaire.jsp" ).forward( request, response );
	}

}
