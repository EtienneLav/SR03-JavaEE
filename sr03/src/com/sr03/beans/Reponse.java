package com.sr03.beans;

public class Reponse {

    private long   id;
    private String    intitule;
    private Boolean   status;
    private Boolean   correct;
    private	int 	ordre;
    private int 	question;
    
	public Reponse(){}
	
	public Reponse(long id, String intitule, int ordre, Boolean status,  Boolean correct, int question) {
		this.id = id;
		this.intitule = intitule;
		this.ordre = ordre;
		this.status = status;
		this.correct = correct;
		this.question = question;
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
        return this.intitule;
    }

    public long getOrdre() {
        return this.ordre;
    }
    
    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }
    
    public Boolean getStatus() {
        return this.status;
    }
    
    public void setQuestion(int question) {
        this.question = question;
    }
    
    public int getQuestion() {
        return this.question;
    }
    
    public void setStatus( Boolean status ) {
        this.status = status;
    }
    
    public Boolean getCorrect() {
        return correct;
    }
    
    public void setCorrect( Boolean correct ) {
        this.correct = correct;
    }
}