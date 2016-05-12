package com.sr03.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.sr03.beans.*;
import com.sr03.DAO.*;

public class QuestionnaireDAO extends DAO<Questionnaire> {

	public Questionnaire create(Questionnaire obj) {
		try {
			
		      ResultSet result = this	.connect
                      .createStatement(
                      		ResultSet.TYPE_SCROLL_INSENSITIVE, 
                      		ResultSet.CONCUR_UPDATABLE
                      ).executeQuery(
                      		"SELECT MAX( id ) AS id FROM Questionnaire"
                      );
		      if(result.first()){
		        long id = result.getLong("id") + 1;
			
				PreparedStatement prepare = this	.connect
                                                    .prepareStatement(
                                                    	"INSERT INTO Questionnaire (id, sujet, status) VALUES(?, ?, ?)"
                                                    );
				
				prepare.setLong(1, id);
				prepare.setString(2, obj.getSujet());
				prepare.setBoolean(3, obj.getStatus());

				prepare.executeUpdate();
				obj = this.find(id);	
			}	
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	    return obj;
	}
	
	public Questionnaire find(long id) {
		Questionnaire util = new Questionnaire();
		try {
            ResultSet result = this.connect
                                   .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM Questionnaire WHERE id = " + id
                                             );
            if(result.first())
            	util = new Questionnaire(
                                        id, 
                                        result.getString("sujet"),
                                        result.getBoolean("status")
                                    );
            
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
		   return util;

	}
	
	
	public Questionnaire update(Questionnaire obj) {
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
                         	"UPDATE Questionnaire SET sujet = '" + obj.getSujet() + "',"+
                                	" status = '" + status + "'"+
                                	" WHERE id = " + obj.getId()
                                	);
			
			obj = this.find(obj.getId());
	    } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	    }
	    
	    return obj;
	}


	public void delete(Questionnaire obj) {
		try {
				QuestionDAO questionDAO = (com.sr03.DAO.QuestionDAO) DAOFactory.getQuestionDAO();
	        	ArrayList questions = questionDAO.findFromQuestionnaire(obj.getId());
	        	if (!questions.isEmpty()) {
		        	for (int i = 0; i < questions.size(); i++) {
		        		questionDAO.delete((Question)questions.get(i));
		        	}
	        	}

                this    .connect
                    	.createStatement(
                             ResultSet.TYPE_SCROLL_INSENSITIVE, 
                             ResultSet.CONCUR_UPDATABLE
                        ).executeUpdate(
                             "DELETE FROM Questionnaire WHERE id = " + obj.getId()
                        );	
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	}
	
	public ArrayList findAll() {
		ArrayList questionnaire = new ArrayList();
		Questionnaire quest;
		try {

				
	            ResultSet result = this.connect
	                                   .createStatement(
	                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
	                                                ResultSet.CONCUR_UPDATABLE
	                                             ).executeQuery(
	                                                "SELECT * FROM Questionnaire WHERE status = 1"
	                                             );
	            while(result.next()){
					
	            	quest = new Questionnaire(
                            				result.getLong("id"),
                                            result.getString("sujet"),
                                            result.getBoolean("status")
                                        );	
	            	questionnaire.add(quest);	
					
	            }
            
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
		   return questionnaire;

	}
	
		
}