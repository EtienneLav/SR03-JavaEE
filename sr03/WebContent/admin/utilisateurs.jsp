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
	<a href="/sr03/admin/landing">retour</a><br><br>
		    <br><br>
		    <br><br>
		    	    
	<%
		boolean added = (boolean) request.getAttribute("added");
	    if (added == true) {%>
	    <h3>Utilisateur ajouté</h3>
	<%    	
	    } 		
	%>
	<h3>Ajout d'utilisateur :</h3>
	<form action="/sr03/admin/utilisateurs" method="get">
	    <div>
	        <label for="mail">Addresse mail :</label>
	        <input type="email" id="mail" name="mail"/>
	    </div>
	    <br><br>
	    <div>
	        <label for="mdp">Mot de passe :</label>
	        <input type="text" id="mdp" name="mdp"/>
	    </div>
	   	<br><br>
	    <div>
	        <label for="nom">Nom :</label>
	        <input type="text" id="nom" name="nom"/>
	    </div>
	    <br><br>
	    <div>
	        <label for="societe">Societe</label>
	        <input type="text" id="societe" name="societe"/>
	    </div>
	   	<br><br>
	    <div>
	        <label for="telephone">Telephone</label>
	        <input type="text" id="telephone" name="telephone"/>
	    </div>
	    <br><br>Type : <br>
	    <input type="radio" name="type" value="admin" > Administrateur<br>
		<input type="radio" name="type" value="stagiaire" checked> Stagiaire<br>
	    <br><br>
	    <div class="button">
	        <button type="submit">Ajouter</button>
	    </div>
	</form>
	<br>
	<br><br>
	<%
		boolean deleted = (boolean) request.getAttribute("deleted");
	    if (deleted == true) {%>
	    <h3>Utilisateur ajouté</h3>
	<%    	
	    } 		
	%>
	<br><br><br>
	<h3>Liste des utilisateurs</h3>
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
		<td></td>
		<td></td>
		<td></td>
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
	    <td><a href="#">Résultats</a></td> 
	    <td><a href="#">Modifier</a></td> 
	   	<td><a href="/sr03/admin/utilisateurs?delete=<% out.print(stagiaire.getId()); %>">Supprimer</a></td>
	  </tr>
	       <% } %>
	</table>
	
	<br><br>
	<a href="/sr03/deconnexion">Deconnexion</a>
</body>
</html>