
package com.sr03.manager;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sr03.DAO.DAOFactory;
import com.sr03.DAO.ParcoursDAO;
import com.sr03.DAO.QuestionnaireDAO;
import com.sr03.DAO.UtilisateurDAO;
import com.sr03.beans.Utilisateur;


public class ParcoursManager{
	
	private ParcoursDAO parcoursDAO;
	
	
	public ParcoursManager() {
		this.parcoursDAO = (ParcoursDAO) DAOFactory.getParcoursDAO();
		
	}
	
	public HttpServletRequest getParcours(HttpServletRequest request, int _id) {
		
		ArrayList ParcoursArray = new ArrayList();
		ParcoursArray = this.parcoursDAO.findByUtilisateur(_id);
		
		ArrayList QuestionnaireArray = new ArrayList();
		QuestionnaireArray = this.parcoursDAO.findQuestionnairesLibre();
		
		request.setAttribute("questionnaires_libres", QuestionnaireArray);

		request.setAttribute("parcours", ParcoursArray);
		return request;
	}
	
}