
<%@ include file="../WEB-INF/header.jsp" %>
<section>
	<div class="container">
	    <div class="row">
	        <div class="col-lg-12">
				<h1><center>Accueil Stagiaire</center></h1><br><br>
				<div class="well">
					<h4>Info compte :</h4>
					Nom : ${ utilisateur.nom }<br>
					Email : ${ utilisateur.email }<br>
					Societe : ${ utilisateur.societe }<br>
					Telephone : ${ utilisateur.telephone }<br>
					Date d'inscription : ${ utilisateur.dateInscription }<br>
					<br><br>
					<a href="/sr03/deconnexion">Deconnexion</a>
				</div>
            </div>
        </div>
        
        <div class="well">
			<h4>Modifications compte :</h4>
	        <form class="form-inline" method="POST" action ="/sr03/stagiaire/landing">
	  			<div class="form-group">
	   				<label class="sr-only">Nom</label>
	    			<p class="form-control-static">Nom : </p>
	  			</div>
	  			<div class="form-group">
	    			<label for="inputName" class="sr-only"></label>
	    			<input name ="nom" type="name" class="form-control" id="inputName" placeholder="${ utilisateur.nom }" value="${ utilisateur.nom }">
	  			</div>
	  			
	  			<div class="form-group">
	   				<label class="sr-only">email</label>
	    			<p class="form-control-static">Email : </p>
	  			</div>
	  			<div class="form-group">
	    			<label for="inputName" class="sr-only"></label>
	    			<input name ="email" type="email" class="form-control" id="inputEmail" placeholder="${ utilisateur.email }" value="${ utilisateur.email }">
	  			</div>
	  			
	  			<div class="form-group">
	   				<label class="sr-only">Société</label>
	    			<p class="form-control-static">Société : </p>
	  			</div>
	  			<div class="form-group">
	    			<label for="inputName" class="sr-only"></label>
	    			<input name ="societe" type="societe" class="form-control" id="inputSociete" placeholder="${ utilisateur.societe }" value="${ utilisateur.societe }">
	  			</div>
	  			
	  			<div class="form-group">
	   				<label class="sr-only">Téléphone</label>
	    			<p class="form-control-static">Téléphone : </p>
	  			</div>
	  			<div class="form-group">
	    			<label for="inputName" class="sr-only"></label>
	    			<input name ="telephone" type="telephone" class="form-control" id="inputTelephone" placeholder="${ utilisateur.telephone }" width="10" value="${ utilisateur.telephone }">
	  			</div>
	  		<button type="submit" class="btn btn-default">Valider</button>
			</form>
		</div>
    </div>
</section>


<%@ include file="../WEB-INF/footer.jsp" %>