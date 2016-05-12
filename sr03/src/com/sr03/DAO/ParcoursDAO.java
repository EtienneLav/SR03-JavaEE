package com.sr03.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.sr03.beans.*;
import com.sr03.DAO.*;

import java.sql.Connection;

import com.sr03.ConnectionMySQL;

public class ParcoursDAO extends DAO<Parcours> {
	
	
	public  Parcours create(Parcours _obj){
		try {
			
		      ResultSet result = this	.connect
                    .createStatement(
                    		ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    		ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                    		"SELECT MAX( id ) AS id FROM Parcours"
                    );
		      if(result.first()){
		        long id = result.getLong("id") + 1;
			
				PreparedStatement prepare = this	.connect
                                                  .prepareStatement(
                                                  	"INSERT INTO Parcours (id, utilisateur, questionnaire, score, duree) VALUES(?, ?, ?, ?, ?)"
                                                  );
				
				prepare.setLong(1, id);
				prepare.setLong(2, _obj.getUtilisateur());
				prepare.setLong(3, _obj.getQuestionnaire());
				prepare.setLong(4, _obj.getScore());
				prepare.setTimestamp(5, _obj.getDuree());

				prepare.executeUpdate();
				_obj = this.find(id);	
			}	
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	    return _obj;
	}
	
	
	public  Parcours find(long _id){
				
		Parcours parc = new Parcours();
		
		try {
            ResultSet result = this.connect
                                   .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM Parcours WHERE id = " + _id
                                             );
            if(result.first())
            	parc = new Parcours(
                                        _id, 
                                        result.getInt("utilisateur"),
                                        result.getInt("questionnaire"),
                                        result.getInt("score"),
                                        result.getTimestamp("duree")
                                    );
            
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
		   return parc;

	}
	
	public ArrayList findByUtilisateur(int _utilisateur_id){
		
		 ArrayList ParcoursArray = new ArrayList();
		 Parcours parc = new Parcours();
		 
		 try {
	            ResultSet result = this.connect
	                                   .createStatement(
	                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
	                                                ResultSet.CONCUR_UPDATABLE
	                                             ).executeQuery(
	                                                "SELECT * FROM Parcours WHERE utilisateur = " + _utilisateur_id
	                                             );
	            while(result.next()){
	            	parc = new Parcours(
	            							result.getLong("id"),
	            							_utilisateur_id, 
	                                        result.getInt("questionnaire"),
	                                        result.getInt("score"),
	                                        result.getTimestamp("duree")
	                                    );
	            	ParcoursArray.add(parc);
	             }
	           
			    } catch (SQLException e) {
			            e.printStackTrace();
			    }
		 
		 return ParcoursArray;
	}
	
	public ArrayList findByQuestionnaire(int _questionnaire_id){
		
		 ArrayList ParcoursArray = new ArrayList();
		 Parcours parc = new Parcours();
		 
		 try {
	            ResultSet result = this.connect
	                                   .createStatement(
	                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
	                                                ResultSet.CONCUR_UPDATABLE
	                                             ).executeQuery(
	                                                "SELECT * FROM Parcours WHERE utilisateur = " + _questionnaire_id
	                                             );
	            while(result.next()){
	            	parc = new Parcours(
	            							result.getLong("id"),
	                                        result.getInt("utilisateur"),
	                                        _questionnaire_id, 
	                                        result.getInt("score"),
	                                        result.getTimestamp("duree")
	                                    );
	            	ParcoursArray.add(parc);
	             }
	           
			    } catch (SQLException e) {
			            e.printStackTrace();
			    }
		 
		 return ParcoursArray;
	}
	
	
	public ArrayList findByScore(int _questionnaire_id){
		
		 ArrayList ParcoursArray = new ArrayList();
		 Parcours parc = new Parcours();
		 
		 try {
	            ResultSet result = this.connect
	                                   .createStatement(
	                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
	                                                ResultSet.CONCUR_UPDATABLE
	                                             ).executeQuery(
	                                                "SELECT * FROM Parcours WHERE questionnaire = "+ _questionnaire_id + "ORDER BY score DESC LIMIT 10"
	                                             );
	            while(result.next()){
	            	parc = new Parcours(
	            							result.getLong("id"),
	                                        result.getInt("utilisateur"),
	                                        _questionnaire_id,
	                                        result.getInt("score"),
	                                        result.getTimestamp("duree")
	                                    );
	            	ParcoursArray.add(parc);
	             }
	           
			    } catch (SQLException e) {
			            e.printStackTrace();
			    }
		 
		 return ParcoursArray;
	}
	
public ArrayList findAll() {
		
		ArrayList ParcoursArray = new ArrayList();
		Parcours parc;
		
		try {

				
	            ResultSet result = this.connect
	                                   .createStatement(
	                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
	                                                ResultSet.CONCUR_UPDATABLE
	                                             ).executeQuery(
	                                                "SELECT * FROM Parcours"
	                                             );
	            while(result.next()){
					
	            	parc = new Parcours(
	            			result.getLong("id"),
							result.getInt("utilisateur"),
                            result.getInt("questionnaire"),
                            result.getInt("score"),
                            result.getTimestamp("duree")
                                        );	
	            	ParcoursArray.add(parc);	
					
	            }
            
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
		   return ParcoursArray;

	}

	public  Parcours update(Parcours _obj){
		return null;
	}
	
	public void delete(Parcours _obj){}
}


