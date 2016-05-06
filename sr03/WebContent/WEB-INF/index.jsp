<%@ include file="header.jsp" %>
<section>
	<div class="container">
	    <div class="row">
	        <div class="col-lg-12">
				<h1 class="section-heading">Connexion</h1>
				<form action="/sr03/connexion" method="post">
				    <div class="form-group">
				        <label for="mail">Addresse mail :</label>
				        <input type="email" id="mail" name="mail" class="form-control" placeholder="Email"/>
				    </div>
				    <br><br>
				    <div class="form-group">
				        <label for="mdp">Mot de passe :</label>
				        <input type="password" id="mdp" name="mdp" class="form-control" placeholder="Password"/>
				    </div>
				    <br>
				    <div class="button" class="form-group">
				        <button type="submit" class="btn btn-primary">Se connecter</button>
				    </div>
				</form>
            </div>
        </div>
    </div>
</section>
<%@ include file="footer.jsp" %>