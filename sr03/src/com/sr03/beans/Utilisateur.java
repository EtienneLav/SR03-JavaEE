package com.sr03.beans;

import java.sql.Timestamp;

public class Utilisateur {

    private long   id;
    private String    email;
    private String    motDePasse;
    private String    nom;
    private String	  societe;
    private String    telephone;
    private Timestamp dateInscription;
    private Boolean   status;
    private String    type;
    
	public Utilisateur(){}
	
	public Utilisateur(long id, String email, String motDePasse, String nom, String societe, String telephone, Timestamp dateInscription, Boolean status, String type) {
		this.id = id;
		this.email = email;
		this.motDePasse = motDePasse;
		this.nom = nom;
		this.societe = societe;
		this.telephone = telephone;
		this.dateInscription = dateInscription;
		this.status = status;
		this.type = type;
	}


    public long getId() {
        return id;
    }
    public void setId( long id ) {
        this.id = id;
    }

    public void setEmail( String email ) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setMotDePasse( String motDePasse ) {
        this.motDePasse = motDePasse;
    }
    public String getMotDePasse() {
        return motDePasse;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }

    public void setSociete( String societe ) {
        this.societe = societe;
    }
    public String getSociete() {
        return societe;
    }

    public void setTelephone( String telephone ) {
        this.telephone = telephone;
    }
    public String getTelephone() {
        return telephone;
    }
    
    public Timestamp getDateInscription() {
        return dateInscription;
    }
    public void setDateInscription( Timestamp dateInscription ) {
        this.dateInscription = dateInscription;
    }
    
    public Boolean getStatus() {
        return status;
    }
    public void setStatus( Boolean status ) {
        this.status = status;
    }

    public void setType( String type ) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}