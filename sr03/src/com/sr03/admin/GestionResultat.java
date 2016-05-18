
package com.sr03.admin;

import com.sr03.DAO.DAOFactory;
import com.sr03.manager.UtilisateursGestion;
import com.sr03.stagiaire.ParcoursGestion;
import com.sr03.beans.*;
import com.sr03.manager.QuestionnaireManager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GestionResultat extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		
		 HttpSession session = request.getSession();
	     Utilisateur util = DAOFactory.getUtilisateurDAO().find((long)session.getAttribute("utilisateur_ID"));
	     
	    //Gestion pour afficher parcours déjà faits et les autres disponibles
		int user_id = (int) (long) session.getAttribute("utilisateur_ID");
			
		//récupérer id du stagiaire passé en paramètre
		String stagiaire_id_string =request.getParameter("stagiaire_id");
		int stagiaire_id = (int) Long.valueOf(stagiaire_id_string).longValue();
		
		System.out.print(Long.valueOf(stagiaire_id_string).longValue());
		
		//récupérer les parcours du stagiaire dont ona  récupéré l'id
		ParcoursGestion parcGestion = new ParcoursGestion();
		request = parcGestion.doGetParcours(request, response, stagiaire_id);
		
	    request.setAttribute("numero_stagiaire", stagiaire_id);
	    this.getServletContext().getRequestDispatcher( "/admin/gestionResultat.jsp" ).forward( request, response );
	}
}