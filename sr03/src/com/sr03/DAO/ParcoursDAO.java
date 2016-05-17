package com.sr03.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

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
				prepare.setInt(2, (int) _obj.getUtilisateur().getId());
				prepare.setInt(3, (int) _obj.getQuestionnaire().getId());
				prepare.setInt(4, _obj.getScore());
				prepare.setTime(5, _obj.getDuree());

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
                                        result.getTime("duree")
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
	                                                "SELECT DISTINCT * FROM Parcours, Questionnaire, Utilisateur WHERE Parcours.questionnaire = Questionnaire.id AND Questionnaire.status = 1 AND Utilisateur.id = Parcours.utilisateur AND utilisateur = " + _utilisateur_id
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
	                                        result.getTime("duree")
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
	                                                "SELECT * FROM Parcours, Questionnaire, Utilisateur WHERE Questionnaire.status = 1 AND Utilisateur.id = Parcours.utilisateur AND Questionnaire.id = Parcours.questionnaire AND Questionnaire.id = " + _questionnaire_id + " ORDER BY Parcours.score DESC"
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
	                                        result.getTime("duree")
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
	                                        result.getTime("duree")
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
                            result.getTime("duree")
                                        );	
	            	ParcoursArray.add(parc);	
					
	            }
            
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
		   return ParcoursArray;

	}

public ArrayList findQuestionnairesLibre(int _user_id) {
	
	ArrayList QuestionnaireArray = new ArrayList();
	Questionnaire questionnaire = new Questionnaire();
	
	try {

			
            ResultSet result = this.connect
                                   .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT distinct * FROM Questionnaire T1 LEFT OUTER JOIN Parcours T2 ON T1.id= T2.questionnaire AND T2.Utilisateur = "+_user_id+"  WHERE T2.questionnaire IS NULL AND T1.status = 1"
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
                        result.getTime("Parcours.duree")
            			);
            	
            	
            	ParcoursArray.add(parcours);	
				
            }
        
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	   return ParcoursArray;
}


	public int countParcoursByQuestionnaire(int _questionnaire_id) {
		int nb = 0;
		try {
	        ResultSet result = this.connect
	                               .createStatement(
	                                        	ResultSet.TYPE_SCROLL_INSENSITIVE, 
	                                            ResultSet.CONCUR_UPDATABLE
	                                         ).executeQuery(
	                                            "SELECT count(*) AS nb FROM Utilisateur, Parcours WHERE Utilisateur.id = Parcours.utilisateur AND Parcours.questionnaire = "+ _questionnaire_id + " ORDER BY Parcours.score DESC"
	                                         );
	        if(result.first()) {
	        	nb = result.getInt("nb") +1;
	        }
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
		return nb;
	}


	public ArrayList findSpecifiqueIntervalle(int _questionnaire_id, int _limite_basse){
		
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
	                                               "SELECT * FROM Parcours, Questionnaire, Utilisateur WHERE Questionnaire.status = 1 AND Utilisateur.id = Parcours.utilisateur AND Questionnaire.id = Parcours.questionnaire AND Questionnaire.id = " + _questionnaire_id + " ORDER BY Parcours.score DESC LIMIT 10 OFFSET "+_limite_basse
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
	                                       result.getTime("duree")
	                                   );
	           	ParcoursArray.add(parc);
	            }
	          
			    } catch (SQLException e) {
			            e.printStackTrace();
			    }
		 
		 return ParcoursArray;
	}

	public int countParcoursDoneByUser(int _utilisateur_id) {
		int nb = 0;
		try {
	        ResultSet result = this.connect
	                               .createStatement(
	                                        	ResultSet.TYPE_SCROLL_INSENSITIVE, 
	                                            ResultSet.CONCUR_UPDATABLE
	                                         ).executeQuery(
	                                        		 "SELECT count(*) AS nb FROM Parcours, Questionnaire, Utilisateur WHERE Parcours.questionnaire = Questionnaire.id AND Questionnaire.status = 1 AND Utilisateur.id = Parcours.utilisateur AND utilisateur = " + _utilisateur_id
	                                         );
	        if(result.first()) {
	        	nb = result.getInt("nb") +1;
	        }
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
		return nb;
	}
	
	
	public ArrayList findSpecifiqueIntervalleParcoursDone(int _utilisateur_id, int _limite_basse){
		
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
	                                                "SELECT DISTINCT * FROM Parcours, Questionnaire, Utilisateur WHERE Parcours.questionnaire = Questionnaire.id AND Questionnaire.status = 1 AND Utilisateur.id = Parcours.utilisateur AND utilisateur = " + _utilisateur_id +" LIMIT 10 OFFSET "+_limite_basse
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
	                                        result.getTime("duree")
	                                    );
	            	ParcoursArray.add(parc);
	             }
	           
			    } catch (SQLException e) {
			            e.printStackTrace();
			    }
		 
		 return ParcoursArray;
	}
	
	


	public int countParcoursNotDoneByUser(int _utilisateur_id) {
		int nb = 0;
		try {
	        ResultSet result = this.connect
	                               .createStatement(
	                                        	ResultSet.TYPE_SCROLL_INSENSITIVE, 
	                                            ResultSet.CONCUR_UPDATABLE
	                                         ).executeQuery(
	                                        		 "SELECT count(*) AS nb FROM Questionnaire T1 LEFT OUTER JOIN Parcours T2 ON T1.id= T2.questionnaire AND T2.Utilisateur = "+_utilisateur_id+"  WHERE T2.questionnaire IS NULL AND T1.status = 1"
	                                         );
	        if(result.first()) {
	        	nb = result.getInt("nb") +1;
	        }
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
		return nb;
	}
	
	public ArrayList findSpecifiqueIntervalleParcoursNotDone(int _user_id, int _limite_basse) {
		
		ArrayList QuestionnaireArray = new ArrayList();
		Questionnaire questionnaire = new Questionnaire();
		
		try {

				
	            ResultSet result = this.connect
	                                   .createStatement(
	                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
	                                                ResultSet.CONCUR_UPDATABLE
	                                             ).executeQuery(
	                                                "SELECT distinct * FROM Questionnaire T1 LEFT OUTER JOIN Parcours T2 ON T1.id= T2.questionnaire AND T2.Utilisateur = "+_user_id+"  WHERE T2.questionnaire IS NULL AND T1.status = 1 LIMIT 10 OFFSET "+_limite_basse
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


	public  Parcours update(Parcours _obj){
		return null;
	}
	
	public void delete(Parcours _obj){}
}




