package com.sr03.beans;

import java.sql.Timestamp;
import com.sr03.beans.*;

public class Parcours {
	
	 private long   id;
	 private int utilisateur;
	 private Questionnaire  questionnaire;
	 private int score;
	 private Timestamp duree;

	public Parcours(){}
	
	public Parcours(long _id, int _utilisateur, Questionnaire _questionnaire, int _score, Timestamp _duree){
		
		this.id = _id;
		this.utilisateur = _utilisateur;
		this.questionnaire = _questionnaire;
		this.score = _score;
		this.duree = _duree;
		
	}
	
	public long getId() {
        return id;
    }
    public void setId( long id ) {
        this.id = id;
    }
    
    public int getUtilisateur(){
    	return utilisateur;
    }
    
    public void setUtilisateur(int _utilisateur){
    	this.utilisateur = _utilisateur;
    }
    
    public Questionnaire getQuestionnaire(){
    	return questionnaire;
    }
    
    public void setQuestionnaire(Questionnaire _questionnaire){
    	this.questionnaire = _questionnaire;
    }
    
    public int getScore(){
    	return score;
    }
    
    public void setScore(int _score){
    	this.score = _score;
    }
    
    public Timestamp getDuree(){
    	return duree;
    }
    
    public void setDuree(Timestamp _duree){
    	this.duree = _duree;
    }
    
}
