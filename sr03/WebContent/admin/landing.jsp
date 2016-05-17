<%@ include file="../WEB-INF/header.jsp" %>
<section>
	<div class="container">
	    <div class="row">
	        <div class="col-lg-12">
				<h1><center>Accueil Administrateur</center></h1><br><br>
				<div class="well">
					<h4>Info compte :</h4>
					Nom : ${ utilisateur.nom }<br>
					Email : ${ utilisateur.email }<br>
					Societe : ${ utilisateur.societe }<br>
					Telephone : ${ utilisateur.telephone }<br>
					Date d'inscription : ${ utilisateur.dateInscription }
				</div>
            </div>
        </div>
        
	    <div class="row">
	        <div class="col-lg-12">
	        	<h3>Gestion des utilisateurs</h3>					    	    
				<%
					boolean added = (boolean) request.getAttribute("added");
				    if (added == true) {%>
				    <div class="alert alert-success" role="alert">Utilisateur ajouté</div>
				<%    	
				    } 		
				%>
				<%
					boolean deleted = (boolean) request.getAttribute("deleted");
				    if (deleted == true) {%>
				    <div class="alert alert-info" role="alert">Utilisateur supprimé</div>
				<%    	
				    } 		
				%>
 
	      	        
				<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				  <div class="panel panel-default">
				    <div class="panel-heading" role="tab" id="headingOne">
				      <h4 class="panel-title">
				        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
				          Liste des Stagiaires
				        </a>
				      </h4>
				    </div>
				    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
				      <div class="panel-body">
						<table class="table table-hover" style="width:100%">
						  <tr>
						    <th>id</th>
						    <th>email</th> 
						    <th>motDePasse</th>
						    <th>nom</th>
						    <th>societe</th>
						    <th>telephone</th>
						    <th>dateInscription</th>
						    <th>status</th>
						    <th>type</th>
							<th></th>
							<th></th>
							<th></th>
						</tr>
					  		 <% 
					     ArrayList stagiaires = (ArrayList)request.getAttribute("stagiaires");
					     for (int i = 0 ; i < stagiaires.size() ; i++) {
					     	Utilisateur stagiaire = (Utilisateur) stagiaires.get(i);
					     %>
						  <tr>
						    <th><% out.print(stagiaire.getId()); %></th>
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
						   	<td><a href="/sr03/admin/landing?delete=<% out.print(stagiaire.getId()); %>">Supprimer</a></td>
						  </tr>
						  <% } %>
						</table>
				      </div>
				    </div>
				  </div>
				  <div class="panel panel-default">
				    <div class="panel-heading" role="tab" id="headingOne3">
				      <h4 class="panel-title">
				        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne3" aria-expanded="false" aria-controls="collapseOne3">
				          Liste des Admins
				        </a>
				      </h4>
				    </div>
				    <div id="collapseOne3" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne3">
				      <div class="panel-body">
						<table class="table table-hover" style="width:100%">
						  <tr>
						    <th>id</th>
						    <th>email</th> 
						    <th>motDePasse</th>
						    <th>nom</th>
						    <th>societe</th>
						    <th>telephone</th>
						    <th>dateInscription</th>
						    <th>status</th>
						    <th>type</th>
							<th></th>
							<th></th>
							<th></th>
						</tr>
					  		 <% 
					     ArrayList admins = (ArrayList)request.getAttribute("admins");
					     for (int i = 0 ; i < admins.size() ; i++) {
					     	Utilisateur admin = (Utilisateur) admins.get(i);
					     %>
						  <tr>
						    <th><% out.print(admin.getId()); %></th>
						    <td><% out.print(admin.getEmail()); %></td> 
						    <td><% out.print(admin.getMotDePasse()); %></td>
						    <td><% out.print(admin.getNom()); %></td>
						    <td><% out.print(admin.getSociete()); %></td>
						    <td><% out.print(admin.getTelephone()); %></td>
						    <td><% out.print(admin.getDateInscription()); %></td>
						    <td><% out.print(admin.getStatus()); %></td>
						    <td><% out.print(admin.getType()); %></td>
						    <td><a href="#">Résultats</a></td> 
						    <td><a href="#">Modifier</a></td> 
						   	<td><a href="/sr03/admin/landing?delete=<% out.print(admin.getId()); %>">Supprimer</a></td>
						  </tr>
						  <% } %>
						</table>
				      </div>
				    </div>
				  </div>
				  <div class="panel panel-default">
				    <div class="panel-heading" role="tab" id="headingTwo">
				      <h4 class="panel-title">
				        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
							Ajouter un utilisateur 
				        </a>
				      </h4>
				    </div>
				    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
				      <div class="panel-body">
						<form action="/sr03/admin/landing" method="get">
						    <div class="form-group">
						        <label for="mail">Addresse mail :</label>
						        <input type="email" id="mail" name="mail" class="form-control"/>
						    </div>
						    <div class="form-group">
						        <label for="mdp">Mot de passe :</label>
						        <input type="text" id="mdp" name="mdp" class="form-control"/>
						    </div>
						    <div class="form-group">
						        <label for="nom">Nom :</label>
						        <input type="text" id="nom" name="nom" class="form-control"/>
						    </div>
						    <div class="form-group">
						        <label for="societe">Societe</label>
						        <input type="text" id="societe" name="societe" class="form-control"/>
						    </div>
						    <div class="form-group">
						        <label for="telephone">Telephone</label>
						        <input type="text" id="telephone" name="telephone" class="form-control"/>
						    </div>
						    <br>Type :
						    <div class="radio">
							  <label>
							    <input type="radio" name="type" value="admin" > Administrateur
							  </label>
							</div>
						    <div class="radio">
							  <label>
							    <input type="radio" name="type" value="stagiaire" > Stagiaire
							  </label>
							</div>
							<button type="submit" class="btn btn-primary">Ajouter</button>
						</form>
				      </div>
				    </div>
				  </div>
				</div>
            </div>
        </div>



		<div class="row">
	        <div class="col-lg-12">
	        	<h3>Gestion des questionnaires</h3>
	        	<%
					boolean addedQuestionnaire = (boolean) request.getAttribute("addedQuestionnaire");
				    if (addedQuestionnaire == true) {%>
				    <div class="alert alert-success" role="alert">Questionnaire ajouté</div>
				<%    	
				    } 		
				%>
				<%
					boolean deletedQuestionnaire = (boolean) request.getAttribute("deleteQuestionnaire");
				    if (deletedQuestionnaire == true) {%>
				    <div class="alert alert-info" role="alert">Questionnaire supprimé</div>
				<%    	
				    } 		
				%>
 					    	    	      	        
				<div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true">
				  <div class="panel panel-default">
				    <div class="panel-heading" role="tab" id="headingOne2">
				      <h4 class="panel-title">
				        <a role="button" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne2" aria-expanded="false" aria-controls="collapseOne2">
				          Liste des questionnaires
				        </a>
				      </h4>
				    </div>
				    <div id="collapseOne2" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne2">
				      <div class="panel-body">
						<table class="table table-hover" style="width:100%">
						  <tr>
						    <th>id</th>
						    <th>Sujet</th> 
						    <th>status</th>
							<th></th>
							<th></th>
						</tr>
					  		 <% 
					     ArrayList questionnaires = (ArrayList)request.getAttribute("questionnaires");
					     for (int i = 0 ; i < questionnaires.size() ; i++) {
					     	Questionnaire questionnaire = (Questionnaire) questionnaires.get(i);
					     %>
						  <tr>
						    <th><% out.print(questionnaire.getId()); %></th>
						    <td><% out.print(questionnaire.getSujet()); %></td> 
						    <td><% out.print(questionnaire.getStatus()); %></td>
						    <td><a href="/sr03/admin/gestionQuestionnaire?id=<% out.print(questionnaire.getId()); %>">Gérer</a></td> 
						   	<td><a href="/sr03/admin/landing?deleteQuestionnaire=<% out.print(questionnaire.getId()); %>">Supprimer</a></td>
						  </tr>
						  <% } %>
						</table>
				      </div>
				    </div>
				  </div>
				  <div class="panel panel-default">
				    <div class="panel-heading" role="tab" id="headingTwo2">
				      <h4 class="panel-title">
				        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo2" aria-expanded="false" aria-controls="collapseTwo2">
							Ajouter un Questionnaire 
				        </a>
				      </h4>
				    </div>
				    <div id="collapseTwo2" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
				      <div class="panel-body">
						<form action="/sr03/admin/landing" method="get">
						    <div class="form-group">
						        <label for="sujet">Sujet :</label>
						        <input type="text" id="sujet" name="sujet" class="form-control"/>
						    </div>
							<button type="submit" class="btn btn-primary">Ajouter</button>
						</form>
				      </div>
				    </div>
				  </div>
				</div>
            </div>
        </div>



    </div>
</section>


<%@ include file="../WEB-INF/footer.jsp" %>