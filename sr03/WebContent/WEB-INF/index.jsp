<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	</head>
	<body>
		<h1>Connexion</h1>
		<form action="/sr03/connexion" method="post">
		    <div>
		        <label for="nom">Addresse mail :</label>
		        <input type="email" id="mail" name="mail"/>
		    </div>
		    <br><br>
		    <div>
		        <label for="mdp">Mot de passe :</label>
		        <input type="password" id="mdp" name="mdp"/>
		    </div>
		    <br>
		    <div class="button">
		        <button type="submit">Se connecter</button>
		    </div>
		</form>
	</body>
</html>