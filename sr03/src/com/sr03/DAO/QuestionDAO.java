package com.sr03.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.sr03.beans.*;
import com.sr03.DAO.*;

public class QuestionDAO extends DAO<Question> {

	public Question create(Question obj) {
		try {
			
		      ResultSet result = this	.connect
                      .createStatement(
                      		ResultSet.TYPE_SCROLL_INSENSITIVE, 
                      		ResultSet.CONCUR_UPDATABLE
                      ).executeQuery(
                      		"SELECT MAX( id ) AS id FROM Question"
                      );
		      if(result.first()){
		        long id = result.getLong("id") + 1;
			
				PreparedStatement prepare = this	.connect
                                                    .prepareStatement(
                                                    	"INSERT INTO Question (id, intitule, status, ordre, questionnaire) VALUES(?, ?, ?, ?, ?)"
                                                    );
				
				prepare.setLong(1, id);
				prepare.setString(2, obj.getIntitule());
				prepare.setBoolean(3, obj.getStatus());
				prepare.setLong(4, obj.getOrdre());
				prepare.setLong(5, obj.getQuestionnaire());

				prepare.executeUpdate();
				obj = this.find(id);	
			}	
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	    return obj;
	}
	
	public Question find(long id) {
		Question util = new Question();
		try {
            ResultSet result = this.connect
                                   .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM Question WHERE id = " + id
                                             );
            if(result.first())
            	util = new Question(
                                        id, 
                                        result.getString("intitule"),
                                        result.getInt("ordre"),
                                        result.getBoolean("status"),
                                        result.getInt("questionnaire")
                                    );
            
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
		   return util;

	}
	
	
	public Question update(Question obj) {
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
                         	"UPDATE Question SET intitule = '" + obj.getIntitule() + "',"+
                                	" ordre = '" + obj.getOrdre() + "',"+
                                	" status = '" + status + "',"+
                                	" questionnaire = '" + obj.getQuestionnaire() + "'"+
                                	" WHERE id = " + obj.getId()
                                	);
			
			obj = this.find(obj.getId());
	    } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	    }
	    
	    return obj;
	}


	public void delete(Question obj) {
		try {
				ReponseDAO reponsesDAO = (com.sr03.DAO.ReponseDAO) DAOFactory.getReponseDAO();
	        	ArrayList reponses = reponsesDAO.findFromQuestion(obj.getId());
	        	if (!reponses.isEmpty()) {
		        	for (int i = 0; i < reponses.size(); i++) {
		        		reponsesDAO.delete((Reponse)reponses.get(i));
		        	}
	        	}
	        	        	
				int status = 0;
                this    .connect
                    	.createStatement(
                             ResultSet.TYPE_SCROLL_INSENSITIVE, 
                             ResultSet.CONCUR_UPDATABLE
                        ).executeUpdate(
                        	"UPDATE Question SET status = '0'"+
                                    	" WHERE id = " + obj.getId()                        		
                        );
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	}
	
	
	public ArrayList findFromQuestionnaire(long questionnaire) {
		ArrayList questions = new ArrayList();
		Question util = new Question();
		try {
            ResultSet result = this.connect
                                   .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM Question WHERE status = 1 AND questionnaire = " + questionnaire +" ORDER BY ordre ASC"
                                             );
            while(result.next()){
            	util = new Question(            			
                        result.getLong("id"), 
                        result.getString("intitule"),
                        result.getInt("ordre"),
                        result.getBoolean("status"),
                        result.getInt("questionnaire")
                    );
            	questions.add(util);	
            }
            
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
		   return questions;

	}
	
	public int countQuestionFromQuestionnaire(int idQuestionnaire) {
		int nb = 0;
		try {
            ResultSet result = this.connect
                                   .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT count(*) AS nb FROM Question WHERE status = 1 AND questionnaire = " + idQuestionnaire
                                             );
            if(result.first()) {
            	nb = result.getInt("nb") +1;
            }
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
		return nb;
	}
	
		
}