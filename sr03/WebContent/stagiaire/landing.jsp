<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Accueil - Stagiaire</title>
</head>
<body>
	<h1>Accueil Stagiaire</h1>
	Nom : ${ utilisateur.nom }<br>
	Email : ${ utilisateur.email }<br>
	Societe : ${ utilisateur.societe }<br>
	Telephone : ${ utilisateur.telephone }<br>
	Date d'inscription : ${ utilisateur.dateInscription }<br>
	<br><br>
	<a href="/sr03/deconnexion">Deconnexion</a>
</body>
</html>