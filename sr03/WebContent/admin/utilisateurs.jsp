<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.sr03.beans.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Accueil - Aministrateur</title>
</head>
<body>
	<h1>Gestion des utilisateurs</h1>
	<a href="/sr03/admin/landing">retour</a><br>
	<br>
	<table style="width:100%">
	  <tr>
	    <td>id</td>
	    <td>email</td> 
	    <td>motDePasse</td>
	    <td>nom</td>
	    <td>societe</td>
	    <td>telephone</td>
	    <td>dateInscription</td>
	    <td>status</td>
	    <td>type</td>
	  </tr>
	  		 <% 
	     ArrayList stagiaires = (ArrayList)request.getAttribute("stagiaires");
	     for (int i = 0 ; i < stagiaires.size() ; i++) {
	     	Utilisateur stagiaire = (Utilisateur) stagiaires.get(i);
	     %>
	  <tr>
	    <td><% out.print(stagiaire.getId()); %></td>
	    <td><% out.print(stagiaire.getEmail()); %></td> 
	    <td><% out.print(stagiaire.getMotDePasse()); %></td>
	    <td><% out.print(stagiaire.getNom()); %></td>
	    <td><% out.print(stagiaire.getSociete()); %></td>
	    <td><% out.print(stagiaire.getTelephone()); %></td>
	    <td><% out.print(stagiaire.getDateInscription()); %></td>
	    <td><% out.print(stagiaire.getStatus()); %></td>
	    <td><% out.print(stagiaire.getType()); %></td>
	    <td><a href="/sr03/admin/utilisateurs?delete=<% out.print(stagiaire.getId()); %>">Supprimer</a></td>
	  </tr>
	       <% } %>
	</table>
	
	<br><br>
	<a href="/sr03/deconnexion">Deconnexion</a>
</body>
</html>