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
}