package com.sr03.beans;


public class Questionnaire {

    private long   id;
    private String    sujet;
    private Boolean   status;
    
	public Questionnaire(){}
	
	public Questionnaire(long id, String sujet, Boolean status) {
		this.id = id;
		this.sujet = sujet;
		this.status = status;
	}


    public long getId() {
        return id;
    }
    public void setId( long id ) {
        this.id = id;
    }

    public void setSujet( String sujet ) {
        this.sujet = sujet;
    }
    public String getSujet() {
        return sujet;
    }
    
    public Boolean getStatus() {
        return status;
    }
    public void setStatus( Boolean status ) {
        this.status = status;
    }
}