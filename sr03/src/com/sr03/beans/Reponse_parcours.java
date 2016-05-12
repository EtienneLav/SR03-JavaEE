package com.sr03.beans;

public class Reponse_parcours {

	 private long   id;
	 private int parcours;
	 private int  reponse;

	public Reponse_parcours(){}
	
	public Reponse_parcours(long _id, int _parcours, int _reponse){
		
		this.id = _id;
		this.parcours = _parcours;
		this.reponse = _reponse; 
		
	}
	
	public long getId() {
        return id;
    }
    public void setId( long id ) {
        this.id = id;
    }
    
    public int getParcours(){
    	return parcours;
    }
    
    public void setParcours(int _parcours){
    	this.parcours = _parcours;
    }
    
    public int getReponse(){
    	return reponse;
    }
    
    public void setReponse(int _reponse){
    	this.reponse = _reponse;
    }
	
}
