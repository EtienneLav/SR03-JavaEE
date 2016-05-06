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
				    <div class="alert alert-success" role="alert">Utilisateur ajout�</div>
				<%    	
				    } 		
				%>
				<%
					boolean deleted = (boolean) request.getAttribute("deleted");
				    if (deleted == true) {%>
				    <div class="alert alert-info" role="alert">Utilisateur supprim�</div>
				<%    	
				    } 		
				%>
 
	      	        
				<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				  <div class="panel panel-default">
				    <div class="panel-heading" role="tab" id="headingOne">
				      <h4 class="panel-title">
				        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
				          Liste des utilisateurs
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
						    <td><a href="#">R�sultats</a></td> 
						    <td><a href="#">Modifier</a></td> 
						   	<td><a href="/sr03/admin/landing?delete=<% out.print(stagiaire.getId()); %>">Supprimer</a></td>
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
							    <input type="radio" name="type" value="admin" > Stagiaire
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
    </div>
</section>


<%@ include file="../WEB-INF/footer.jsp" %>