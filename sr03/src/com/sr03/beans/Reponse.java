package com.sr03.beans;

public class Reponse {

    private long   id;
    private String    intitule;
    private Boolean   status;
    private Boolean   correct;
    private	int 	ordre;
    
	public Reponse(){}
	
	public Reponse(long id, String intitule, int ordre, Boolean status,  Boolean correct) {
		this.id = id;
		this.intitule = intitule;
		this.ordre = ordre;
		this.status = status;
		this.correct = correct;
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
    
    public Boolean getCorrect() {
        return correct;
    }
    
    public void setCorrect( Boolean correct ) {
        this.correct = correct;
    }
}