package com.sr03.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.sr03.beans.*;
import com.sr03.DAO.*;

public class UtilisateurDAO extends DAO<Utilisateur> {

	public Utilisateur create(Utilisateur obj) {
		try {
			
		      ResultSet result = this	.connect
                      .createStatement(
                      		ResultSet.TYPE_SCROLL_INSENSITIVE, 
                      		ResultSet.CONCUR_UPDATABLE
                      ).executeQuery(
                      		"SELECT MAX( id ) AS id FROM Utilisateur"
                      );
		      if(result.first()){
		        long id = result.getLong("id") + 1;
			
				PreparedStatement prepare = this	.connect
                                                    .prepareStatement(
                                                    	"INSERT INTO Utilisateur (id, email, motDePasse, nom, societe, telephone, dateInscription, status, type) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)"
                                                    );
				
				prepare.setLong(1, id);
				prepare.setString(2, obj.getEmail());
				prepare.setString(3, obj.getMotDePasse());
				prepare.setString(4, obj.getNom());
				prepare.setString(5, obj.getSociete());
				prepare.setString(6, obj.getTelephone());
				prepare.setTimestamp(7, obj.getDateInscription());
				prepare.setBoolean(8, obj.getStatus());
				prepare.setString(9, obj.getType());


				
				prepare.executeUpdate();
				obj = this.find(id);	
			}	
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	    return obj;
	}

	public ArrayList findStagiaires() {
		ArrayList utilisateurs = new ArrayList();
		Utilisateur util;
		try {

				
	            ResultSet result = this.connect
	                                   .createStatement(
	                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
	                                                ResultSet.CONCUR_UPDATABLE
	                                             ).executeQuery(
	                                                "SELECT * FROM Utilisateur WHERE type = 'stagiaire' AND status = 1"
	                                             );
	            while(result.next()){
					
                	util = new Utilisateur(
                            				result.getLong("id"),
                                            result.getString("email"),
                                            result.getString("motDePasse"),
                                            result.getString("nom"),
                                            result.getString("societe"),
                                            result.getString("telephone"),
                                            result.getTimestamp("dateInscription"),
                                            result.getBoolean("status"),
                                            result.getString("type")
                                        );	
	                utilisateurs.add(util);	
					
	            }
            
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
		   return utilisateurs;

	}
	
	public Utilisateur findByMailAndMdp(String email, String mdp) {
		Utilisateur util = new Utilisateur();
		try {
            ResultSet result = this.connect
                                   .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM Utilisateur WHERE email = '" + email +"' AND motDePasse = '" + mdp +"'"
                                             );
            if(result.first())
            	util = new Utilisateur(
                        				result.getLong("id"),
                                        result.getString("email"),
                                        result.getString("motDePasse"),
                                        result.getString("nom"),
                                        result.getString("societe"),
                                        result.getString("telephone"),
                                        result.getTimestamp("dateInscription"),
                                        result.getBoolean("status"),
                                        result.getString("type")
                                    );
            
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
		   return util;

	}
		
	
	public Utilisateur find(long id) {
		Utilisateur util = new Utilisateur();
		try {
            ResultSet result = this.connect
                                   .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM Utilisateur WHERE id = " + id
                                             );
            if(result.first())
            	util = new Utilisateur(
                                        id, 
                                        result.getString("email"),
                                        result.getString("motDePasse"),
                                        result.getString("nom"),
                                        result.getString("societe"),
                                        result.getString("telephone"),
                                        result.getTimestamp("dateInscription"),
                                        result.getBoolean("status"),
                                        result.getString("type")
                                    );
            
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
		   return util;

	}
	
	
	public Utilisateur update(Utilisateur obj) {
		try {
				int status = 0;
				if (obj.getStatus() == true) {
					status = 1;
				}
			
                this .connect	
                     .createStatement(
                    	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE
                     ).executeUpdate(
                         	"UPDATE Utilisateur SET email = '" + obj.getEmail() + "',"+
                                	" motDePasse = '" + obj.getMotDePasse() + "',"+
                                	" nom = '" + obj.getNom() + "',"+
                                	" societe = '" + obj.getSociete() + "',"+
                                	" nom = '" + obj.getNom() + "',"+
                                	" telephone = '" + obj.getTelephone() + "',"+
                                	" status = '" + status + "',"+
                                	" type = '" + obj.getType() + "'"+
                                	" WHERE id = " + obj.getId()
                                	);
			
			obj = this.find(obj.getId());
			System.out.println(obj.getEmail());
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	    
	    return obj;
	}


	public void delete(Utilisateur obj) {
		try {
			
                this    .connect
                    	.createStatement(
                             ResultSet.TYPE_SCROLL_INSENSITIVE, 
                             ResultSet.CONCUR_UPDATABLE
                        ).executeUpdate(
                             "DELETE FROM Utilisateur WHERE id = " + obj.getId()
                        );
			
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	}
		
}