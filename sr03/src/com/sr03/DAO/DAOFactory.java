package com.sr03.DAO;

import com.sr03.beans.*;
import com.sr03.DAO.*;

public class DAOFactory {

	public static DAO<Utilisateur> getUtilisateurDAO(){
		return new UtilisateurDAO();
	}
	
	public static DAO<Reponse> getReponseDAO(){
		return new ReponseDAO();
	}
	
	public static DAO<Question> getQuestionDAO(){
		return new QuestionDAO();
	}
	
	public static DAO<Questionnaire> getQuestionnaireDAO(){
		return new QuestionnaireDAO();
	}
	
	public static DAO<Parcours> getParcoursDAO(){
		return new ParcoursDAO();
	}
	
	public static DAO<Reponse_parcours> getReponseparcoursDAO(){
		return new Reponse_parcoursDAO();
	}
	
}