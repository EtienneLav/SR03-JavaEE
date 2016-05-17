
package com.sr03.stagiaire;

import com.sr03.DAO.DAOFactory;
import com.sr03.DAO.ParcoursDAO;
import com.sr03.DAO.QuestionDAO;
import com.sr03.DAO.QuestionnaireDAO;
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

import com.sr03.tools.*;

public class AjaxRanking extends HttpServlet {
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		
		//Récuperer deux paramètres GET : numéro page & id du questionnaire
		String numero_parcours_string = (String) request.getParameter("parcours_number");
		String numero_page_string = (String) request.getParameter("numero_page");
		
		int numero_parcours = (int) Long.valueOf(numero_parcours_string).longValue();
		int numero_page = (int) Long.valueOf(numero_page_string).longValue();
		
		
		//fixer limite haute et basse en fonction du numéro de la page reçu
		int limite_haute = numero_page * 10;
		int limite_basse = limite_haute - 10;
		
		System.out.println("page : "+numero_page);
		System.out.println("haute : "+limite_haute);
		System.out.println("basse : "+limite_basse);
		
		ArrayList parcours = new ArrayList();

		ParcoursDAO parcoursDAO;
		parcoursDAO = (ParcoursDAO) DAOFactory.getParcoursDAO();
		
		parcours = (ArrayList) parcoursDAO.findSpecifiqueIntervalle(numero_parcours, limite_basse);
		
		request.setAttribute("parcours", parcours);
		request.setAttribute("page_actuelle", numero_page);
		
		this.getServletContext().getRequestDispatcher( "/stagiaire/ajax_ranking.jsp" ).forward( request, response );
	}
	
}