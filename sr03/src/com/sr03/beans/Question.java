package com.sr03.beans;

import java.sql.Timestamp;

public class Question {

    private long   id;
    private String    intitule;
    private Boolean   status;
    private	int 	ordre;
    private int questionnaire;
    
	public Question(){}
	
	public Question(long id, String intitule, int ordre, Boolean status, int questionnaire) {
		this.id = id;
		this.intitule = intitule;
		this.ordre = ordre;
		this.status = status;
		this.questionnaire = questionnaire;
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
    
    public long getQuestionnaire() {
        return questionnaire;
    }
    public void setQuestionnaire( int questionnaire ) {
        this.questionnaire = questionnaire;
    }
}