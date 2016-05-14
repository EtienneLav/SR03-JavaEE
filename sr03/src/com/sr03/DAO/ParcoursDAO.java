package com.sr03.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.sr03.beans.*;
import com.sr03.DAO.*;

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
				((ArrayList) prepare).set(3, _obj.getUtilisateur());
				((ArrayList) prepare).set(3, _obj.getQuestionnaire());
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
		Questionnaire questionnaire = new Questionnaire();
		Utilisateur utilisateur = new Utilisateur();
		
		try {
            ResultSet result = this.connect
                                   .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM Parcours, Questionnaire WHERE Questionnaire.id = Parcours.questionnaire AND Parcours.id = " + _id
                                             );
            if(result.first()){
            	
            	utilisateur = new Utilisateur(
            			);
            	
            	questionnaire = new Questionnaire(
            			result.getInt("Questionnaire.id"),
            			result.getString("sujet"),
            			result.getBoolean("status")
            		);
            	
            	parc = new Parcours(
                                        _id, 
                                        utilisateur,
                                       	questionnaire,
                                        result.getInt("score"),
                                        result.getTimestamp("duree")
                    );
            	}
            
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
		   return parc;

	}
	
	public ArrayList findByUtilisateur(int _utilisateur_id){
		
		 ArrayList ParcoursArray = new ArrayList();
		 Parcours parc = new Parcours();
		 Utilisateur utilisateur = new Utilisateur();
		 
		 Questionnaire questionnaire = new Questionnaire();
		 
		 try {
	            ResultSet result = this.connect
	                                   .createStatement(
	                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
	                                                ResultSet.CONCUR_UPDATABLE
	                                             ).executeQuery(
	                                                "SELECT DISTINCT * FROM Parcours, Questionnaire, Utilisateur WHERE Parcours.questionnaire = Questionnaire.id AND Utilisateur.id = Parcours.utilisateur AND utilisateur = " + _utilisateur_id
	                                             );
	            while(result.next()){
	            	utilisateur = new Utilisateur(
	            			result.getLong("Utilisateur.id"),
	            			null,
	            			null,
	            			null,
	            			null,
	            			null,
	            			null,
	            			null,
	            			null
	      
	            			);
	            	
	            	questionnaire = new Questionnaire(
	            			result.getInt("Questionnaire.id"),
	            			result.getString("sujet"),
	            			result.getBoolean("status")
	            		);
	            	
	            	parc = new Parcours(
	            							result.getLong("id"),
	            							utilisateur, 
	                                        questionnaire,
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
		 Utilisateur utilisateur = new Utilisateur();
		 
		 Questionnaire questionnaire = new Questionnaire();
		 
		 try {
	            ResultSet result = this.connect
	                                   .createStatement(
	                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
	                                                ResultSet.CONCUR_UPDATABLE
	                                             ).executeQuery(
	                                                "SELECT * FROM Parcours, Questionnaire, Utilisateur WHERE Utilisateur.id = Parcours.utilisateur AND Questionnaire.id = Parcours.questionnaire AND Questionnaire.id = " + _questionnaire_id + " ORDER BY Parcours.score DESC"
	                                             );
	            while(result.next()){
	            	
	            	utilisateur = new Utilisateur(
	            			result.getLong("Utilisateur.id"),
	            			result.getString("Utilisateur.email"),
	            			null,
	            			result.getString("Utilisateur.nom"),
	            			null,
	            			null,
	            			null,
	            			null,
	            			null
	            			);
	            	questionnaire = new Questionnaire(
	            			_questionnaire_id,
	            			result.getString("sujet"),
	            			result.getBoolean("status")
	            		);
	            	
	            	parc = new Parcours(
	            							result.getLong("id"),
	                                        utilisateur,
	                                        questionnaire, 
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
		 Utilisateur utilisateur = new Utilisateur();
		 
		 Questionnaire questionnaire = new Questionnaire();
		 
		 try {
	            ResultSet result = this.connect
	                                   .createStatement(
	                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
	                                                ResultSet.CONCUR_UPDATABLE
	                                             ).executeQuery(
	                                                "SELECT * FROM Parcours, Questionnaire WHERE Parcours.questionnaire = Questionnaire.id AND Parcours.questionnaire = "+ _questionnaire_id + " ORDER BY Parcours.score DESC LIMIT 10"
	                                             );
	            while(result.next()){
	            	
	            	utilisateur = new Utilisateur(
	            			);
	            	
	            	questionnaire = new Questionnaire(
	            			_questionnaire_id,
	            			result.getString("sujet"),
	            			result.getBoolean("status")
	            		);
	            	
	            	parc = new Parcours(
	            							result.getLong("id"),
	                                        utilisateur,
	                                        questionnaire,
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
		
		Questionnaire questionnaire = new Questionnaire();
		
		Utilisateur utilisateur = new Utilisateur();
		
		try {

				
	            ResultSet result = this.connect
	                                   .createStatement(
	                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
	                                                ResultSet.CONCUR_UPDATABLE
	                                             ).executeQuery(
	                                                "SELECT * FROM Parcours, Questionnaire WHERE Parcours.questionnaire = Questionnaire.id"
	                                             );
	            while(result.next()){
	            	
	            	utilisateur = new Utilisateur(
	            			);
	            	
	            	questionnaire = new Questionnaire(
	            			result.getInt("Questionnaire.id"),
	            			result.getString("sujet"),
	            			result.getBoolean("status")
	            		);
					
	            	parc = new Parcours(
	            			result.getLong("id"),
							utilisateur,
                            questionnaire,
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

public ArrayList findQuestionnairesLibre() {
	
	ArrayList QuestionnaireArray = new ArrayList();
	Questionnaire questionnaire = new Questionnaire();
	
	try {

			
            ResultSet result = this.connect
                                   .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT distinct * FROM Questionnaire T1 LEFT OUTER JOIN Parcours T2 ON T1.id= T2.questionnaire WHERE T2.questionnaire IS NULL"
                                             );
            while(result.next()){
            	
            	questionnaire = new Questionnaire(
            			result.getInt("T1.id"),
            			result.getString("T1.sujet"),
            			result.getBoolean("status")
            		);
				
        
            	QuestionnaireArray.add(questionnaire);	
				
            }
        
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	   return QuestionnaireArray;

}

public ArrayList findUtilisateursByQuestions(int _questionnaire_id) {
	
	ArrayList ParcoursArray = new ArrayList();
	Utilisateur utilisateur = new Utilisateur();
	Parcours parcours = new Parcours();
	Questionnaire questionnaire = new Questionnaire();
	
	
	
	try {

			
            ResultSet result = this.connect
                                   .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM Utilisateur, Parcours WHERE Utilisateur.id = Parcours.utilisateur AND Parcours.questionnaire = "+ _questionnaire_id + " ORDER BY Parcours.score DESC LIMIT 10"
                                             );
            
            
            
            while(result.next()){
            	
            	utilisateur = new Utilisateur(
            			result.getLong("Utilisateur.id"),
            			result.getString("Utilisateur.email"),
            			null,
            			result.getString("Utilisateur.nom"),
            			null,
            			null,
            			null,
            			null,
            			null
                		);
            	
            	questionnaire = new Questionnaire();
            	
            	
            	parcours = new Parcours(
            			result.getLong("Parcours.id"),
						utilisateur,
                        questionnaire,
                        result.getInt("Parcours.score"),
                        result.getTimestamp("Parcours.duree")
            			);
            	
            	
            	ParcoursArray.add(parcours);	
				
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


