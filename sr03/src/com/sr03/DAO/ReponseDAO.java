package com.sr03.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.sr03.beans.*;
import com.sr03.DAO.*;

public class ReponseDAO extends DAO<Reponse> {

	public Reponse create(Reponse obj) {
		try {
		      ResultSet result = this	.connect
                      .createStatement(
                      		ResultSet.TYPE_SCROLL_INSENSITIVE, 
                      		ResultSet.CONCUR_UPDATABLE
                      ).executeQuery(
                      		"SELECT MAX( id ) AS id FROM Reponse"
                      );
		      if(result.first()){
		        long id = result.getLong("id") + 1;
			
				PreparedStatement prepare = this	.connect
                                                    .prepareStatement(
                                                    	"INSERT INTO Reponse (id, intitule, status, correct, ordre, Question) VALUES(?, ?, ?, ?, ?, ?)"
                                                    );
				
				prepare.setLong(1, id);
				prepare.setString(2, obj.getIntitule());
				prepare.setBoolean(3, obj.getStatus());
				prepare.setBoolean(4, obj.getCorrect());
				prepare.setLong(5, obj.getOrdre());
				prepare.setInt(6, obj.getQuestion());

				prepare.executeUpdate();
				obj = this.find(id);	
			}	
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	    return obj;
	}
	
	public Reponse find(long id) {
		Reponse util = new Reponse();
		try {
            ResultSet result = this.connect
                                   .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM Reponse WHERE id = " + id
                                             );
            if(result.first())
            	util = new Reponse(
                                        id, 
                                        result.getString("intitule"),
                                        result.getInt("ordre"),
                                        result.getBoolean("status"),
                                        result.getBoolean("correct"),
                                        result.getInt("question")
                                    );
            
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
		   return util;

	}

	public ArrayList findFromQuestion(long question) {
		ArrayList reponses = new ArrayList();
		Reponse util = new Reponse();
		try {
            ResultSet result = this.connect
                                   .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM Reponse WHERE question = " + question
                                             );
            while(result.next()){
            	util = new Reponse(
                        result.getLong("id"), 
                        result.getString("intitule"),
                        result.getInt("ordre"),
                        result.getBoolean("status"),
                        result.getBoolean("correct"),
                        result.getInt("question")
                    );
            	reponses.add(util);	
            }
            
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
		   return reponses;

	}
	
	public Reponse update(Reponse obj) {
		try {
				int status = 0;
				int correct = 0;
				if (obj.getStatus() == true) {
					status = 1;
				}
				if (obj.getCorrect() == true) {
					correct = 1;
				}
			
                this .connect	
                     .createStatement(
                    	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE
                     ).executeUpdate(
                         	"UPDATE Reponse SET intitule = '" + obj.getIntitule() + "',"+
                                	" ordre = '" + obj.getOrdre() + "',"+
                                	" status = '" + status + "',"+
                                	" correct = '" + correct + "'"+
                                	" WHERE id = " + obj.getId()
                                	);
			
			obj = this.find(obj.getId());
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	    
	    return obj;
	}


	public void delete(Reponse obj) {
		try {
			
                this    .connect
                    	.createStatement(
                             ResultSet.TYPE_SCROLL_INSENSITIVE, 
                             ResultSet.CONCUR_UPDATABLE
                        ).executeUpdate(
                             "DELETE FROM Reponse WHERE id = " + obj.getId()
                        );
			
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	}
	
	public int countReponseFromQuestion(int idQuestion) {
		int nb = 0;
		try {
            ResultSet result = this.connect
                                   .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT count(*) AS nb FROM Reponse WHERE question = " + idQuestion
                                             );
            if(result.first()) {
            	nb = result.getInt("nb") + 1;
            }
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
		return nb;
	}
		
}