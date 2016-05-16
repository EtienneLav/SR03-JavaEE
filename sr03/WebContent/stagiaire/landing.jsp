
<%@ include file="../WEB-INF/header.jsp" %>
<section>
	<div class="container">
	    <div class="row">
	        <div class="col-lg-12">
				<h1><center> Bonjour ${ utilisateur.nom } ! </center></h1><br><br>
				<div class="well">
					<h4>Info compte :</h4>
					Nom : ${ utilisateur.nom }<br>
					Email : ${ utilisateur.email }<br>
					Societe : ${ utilisateur.societe }<br>
					Telephone : ${ utilisateur.telephone }<br>
					Date d'inscription : ${ utilisateur.dateInscription }<br>
					<br>
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
		

		
	
		 <div class="row">
	        <div class="col-lg-12">
	        	<h3>Questionnaires parcourus</h3>					    	    
			
				<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				  <div class="panel panel-default">
				    <div class="panel-heading" role="tab" id="headingOne">
				      <h4 class="panel-title">
				        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
				          Questionnaire(s)
				        </a>
				      </h4>
				    </div>
				    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
				      <div class="panel-body">
						<table class="table table-hover" style="width:100%">
						  <tr>
						    <th style="text-align: center;">Parcours numéro</th>
						    <th style="text-align: center;">Votre id</th> 
						    <th style="text-align: center;">Sujet</th>
						    <th style="text-align: center;">Score obtenu</th>
						    <th style="text-align: center;">Durée</th>
						    <th style="text-align: center;">Détails</th>
						</tr>
					  		 <% 
					     ArrayList parcours = (ArrayList)request.getAttribute("parcours");
					     for (int i = 0 ; i < parcours.size() ; i++) {
					     	Parcours parcours_current = (Parcours) parcours.get(i);
					     %>
						  <tr>
						    <td align="center"><% out.print(parcours_current.getId()); %></th>
						    <td align="center"><% out.print(parcours_current.getUtilisateur().getId()); %></td> 
						    <td align="center"><% out.print(parcours_current.getQuestionnaire().getSujet()); %></td>
						    <td align="center"><% out.print(parcours_current.getScore()); %></td>
						    <td align="center"><% out.print(parcours_current.getDuree()); %></td>
						    <td align="center"><a href="/sr03/stagiaire/landing/parcoursdetails?parcours_number=<% out.print(parcours_current.getId()); %>"><button type="button" class="btn btn-info">Détails</button></a></td> 
						  </tr>
						  <% } %>
						</table>
				      </div>
				    </div>
				  </div>
				</div>
			</div>
		</div>
		
		
		
		
		<div class="row">
	        <div class="col-lg-12">
	        	<h3>Questionnaires non parcourus</h3>					    	    
			
				<div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true">
				  <div class="panel panel-default">
				    <div class="panel-heading" role="tab" id="headingOne2">
				      <h4 class="panel-title">
				        <a role="button" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne2" aria-expanded="false" aria-controls="collapseOne2">
				          Questionnaire(s)
				        </a>
				      </h4>
				      </div>
				      <div id="collapseOne2" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne2">
				      	<div class="panel-body">
				      	<br>
				      		<ul>
				      		<%  
				      		ArrayList questionnaires_libres = (ArrayList)request.getAttribute("questionnaires_libres");
				      		if (questionnaires_libres.size() != 0)
				      			for (int i = 0 ; i < questionnaires_libres.size() ; i++) {
				    	 			Questionnaire questionnaire_current = (Questionnaire) questionnaires_libres.get(i);
				    	  		%>
					     		<li><a href="/sr03/stagiaire/landing/accueilQuestionnaire?questionnaire_id=<% out.print(questionnaire_current.getId());%>"><% out.print(questionnaire_current.getSujet()); %></a></li>
					     		<% } 
				      		else
				      			%>Aucun
				      			
				      		</ul>
						</div>
				    </div>
				  </div>
				</div>
			</div>
		</div>
		
		
    </div>
</section>


<%@ include file="../WEB-INF/footer.jsp" %>