package com.sr03.beans;

import java.sql.Timestamp;

public class Question {

    private long   id;
    private String    intitule;
    private Boolean   status;
    private	int 	ordre;
    
	public Question(){}
	
	public Question(long id, String intitule, int ordre, Boolean status) {
		this.id = id;
		this.intitule = intitule;
		this.ordre = ordre;
		this.status = status;
	}


    public long getId() {
        return id;
    }
    public void setId( long id ) {
        this.id = id;
    }

    public void setIntitule( String intitule ) {
        this.intitule = intitule;
    }
    public String getIntitule() {
        return intitule;
    }

    public long getOrdre() {
        return ordre;
    }
    
    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }
    
    public Boolean getStatus() {
        return status;
    }
    public void setStatus( Boolean status ) {
        this.status = status;
    }
}