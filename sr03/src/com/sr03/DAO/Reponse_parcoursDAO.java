package com.sr03.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.sr03.beans.*;
import com.sr03.DAO.*;

public class Reponse_parcoursDAO extends DAO<Reponse_parcours> {
	
	
	public  Reponse_parcours create(Reponse_parcours _obj){
		try {
			
		      ResultSet result = this	.connect
                    .createStatement(
                    		ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    		ResultSet.CONCUR_UPDATABLE
                    ).executeQuery(
                    		"SELECT MAX( id ) AS id FROM Reponse_parcours"
                    );
		      if(result.first()){
		        long id = result.getLong("id") + 1;
			
				PreparedStatement prepare = this	.connect
                                                  .prepareStatement(
                                                  	"INSERT INTO Reponse_parcours (id, parcours, reponse) VALUES(?, ?, ?)"
                                                  );
				
				prepare.setLong(1, id);
				prepare.setInt(2, _obj.getParcours());
				prepare.setInt(3, _obj.getReponse());

				prepare.executeUpdate();
				_obj = this.find(id);	
			}	
	    } catch (SQLException e) {
	            e.printStackTrace();
	    }
	    return _obj;
	}
	
	
	public  Reponse_parcours find(long _id){
				
		Reponse_parcours rep_parc = new Reponse_parcours();
		
		try {
            ResultSet result = this.connect
                                   .createStatement(
                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM Parcours WHERE id = " + _id
                                             );
            if(result.first())
            	rep_parc = new Reponse_parcours(
                                        _id, 
                                        result.getInt("parcours"),
                                        result.getInt("reponse")
                                    );
            
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
		   return rep_parc;

	}
	
	public ArrayList findByParcours(int _parcours_id){
		
		 ArrayList ReponseParcoursArray = new ArrayList();
		 Reponse_parcours parc = new Reponse_parcours();
		 
		 try {
	            ResultSet result = this.connect
	                                   .createStatement(
	                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
	                                                ResultSet.CONCUR_UPDATABLE
	                                             ).executeQuery(
	                                                "SELECT * FROM Parcours WHERE utilisateur = " + _parcours_id
	                                             );
	            while(result.next()){
	            	parc = new Reponse_parcours(
	            							result.getLong("id"),
	            							_parcours_id, 
	                                        result.getInt("reponse")
	                                        
	                                    );
	            	ReponseParcoursArray.add(parc);
	             }
	           
			    } catch (SQLException e) {
			            e.printStackTrace();
			    }
		 
		 return ReponseParcoursArray;
	}
	
	
public ArrayList findAll() {
		
		ArrayList ParcoursArray = new ArrayList();
		Reponse_parcours rep_parc;
		
		try {

				
	            ResultSet result = this.connect
	                                   .createStatement(
	                                            	ResultSet.TYPE_SCROLL_INSENSITIVE, 
	                                                ResultSet.CONCUR_UPDATABLE
	                                             ).executeQuery(
	                                                "SELECT * FROM Parcours"
	                                             );
	            while(result.next()){
					
	            	rep_parc = new Reponse_parcours(
	            			result.getLong("id"),
							result.getInt("parcours"),
                            result.getInt("reponse")
                                        );	
	            	ParcoursArray.add(rep_parc);	
					
	            }
            
		    } catch (SQLException e) {
		            e.printStackTrace();
		    }
		   return ParcoursArray;

	}

	public  Reponse_parcours update(Reponse_parcours _obj){
		return null;
	}
	
	public void delete(Reponse_parcours _obj){}


	
}


