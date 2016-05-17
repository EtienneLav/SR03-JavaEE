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

public class GestionReponse extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		ReponseManager reponseManager = new ReponseManager();
		request = reponseManager.modifyReponse(request);
		request = reponseManager.getReponse(request);
		
		this.getServletContext().getRequestDispatcher( "/admin/gestionReponse.jsp" ).forward( request, response );
	}
}