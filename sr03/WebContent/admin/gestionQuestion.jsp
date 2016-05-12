<%@ include file="../WEB-INF/header.jsp" %>
<section>
	<div class="container">

      <div class="row">
      		<h1 style="text-align: center;">Gestion Question n°${ question.id }</h1>
	        <div class="col-lg-6">
	        <br><br>
				<div class="panel-group" id="accordion1" role="tablist" aria-multiselectable="true">
				  <div class="panel panel-default">
				    <div class="panel-heading" role="tab" id="headingOne1">
				      <h4 class="panel-title">
				        <a role="button" data-toggle="collapse" data-parent="#accordion1" href="#collapseOne1" aria-expanded="false" aria-controls="collapseOne1">
				          Informations Question
				        </a>
				      </h4>
				    </div>
				    <div id="collapseOne1" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne1">
				      <div class="panel-body">
						Id : ${ question.id }<br>
						Intitule : ${ question.intitule }<br>
						Ordre : ${ question.ordre }<br>
						Statut : ${ question.status }<br>
				      </div>
				    </div>
				  </div>
				  <div class="panel panel-default">
				    <div class="panel-heading" role="tab" id="headingTwo1">
				      <h4 class="panel-title">
				        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion1" href="#collapseTwo1" aria-expanded="false" aria-controls="collapseTwo1">
							Modifier Question 
				        </a>
				      </h4>
				    </div>
				    <div id="collapseTwo1" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo1">
				      <div class="panel-body">
						<form action="/sr03/admin/gestionQuestion" method="get">
						    <div class="form-group">
						        <label for="intitule">Intitule :</label>
						        <input type="text" id="modifierIntitule" name="modifierIntitule" class="form-control"/>
						    </div>
							<input type="hidden" id="id" name="id" value="${ question.id }" class="form-control"/>
							<button type="submit" class="btn btn-primary">Modifier</button>
						</form>
				      </div>
				    </div>
				  </div>
				</div>
            </div>
            	<%
					int modifiedQuestion = (int) request.getAttribute("modifiedQuestion");
				    if (modifiedQuestion == -1) {%>
				    <div class="col-lg-6 alert alert-info" role="alert">Erreur lors de la modification de la question.</div>
				<%    	
				    } else if (modifiedQuestion == 1) {
				%>
					<div class="col-lg-6 alert alert-success" role="alert">Question modifiée</div>
				<%} else {} %> 				
        </div>  

       
	    <div class="row">
	        <div class="col-lg-12">
	        	<h3>Gestion des Réponses</h3>					    	    
				<%
					boolean added = (boolean) request.getAttribute("addedReponse");
				    if (added == true) {%>
				    <div class="alert alert-success" role="alert">Réponse ajoutée</div>
				<%    	
				    } 		
				%>
				<%
					boolean deleted = (boolean) request.getAttribute("deletedReponse");
				    if (deleted == true) {%>
				    <div class="alert alert-info" role="alert">Réponse supprimée</div>
				<%    	
				    } 		
				%>
				<%
					int orderReponseModified = (int) request.getAttribute("orderReponseModified");
				    if (orderReponseModified == -1) {%>
				    <div class="alert alert-info" role="alert">Erreur lors de l'ordonnancement des réponses</div>
				<%    	
				    } else if (orderReponseModified == 1) {
				%>
					<div class="alert alert-success" role="alert">Réponses ré-ordonnées</div>
				<%} else {} %> 
	      	        
				<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				  <div class="panel panel-default">
				    <div class="panel-heading" role="tab" id="headingOne">
				      <h4 class="panel-title">
				        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
				          Liste des réponses
				        </a>
				      </h4>
				    </div>
				    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
				      <div class="panel-body">
						<table class="table table-hover" style="width:100%">
						  <tr>
						    <th>id</th>
						    <th>intitule</th> 
						    <th>statut</th>
						    <th>ordre</th>
						    <th>correct</th>
							<th></th>
							<th></th>
						</tr>
					  		 <% 
					     ArrayList reponses = (ArrayList)request.getAttribute("reponses");
					     for (int i = 0 ; i < reponses.size() ; i++) {
					     	Reponse reponse = (Reponse) reponses.get(i);
					     %>
						  <tr>
						    <th><% out.print(reponse.getId()); %></th>
						    <td><% out.print(reponse.getIntitule()); %></td> 
						    <td><% out.print(reponse.getStatus()); %></td>
						    <td>
						    	<select name="<% out.print(reponse.getId()); %>" form="ordreReponses">
						    		<% for( int j = 1; j <= reponses.size(); j++) {%>
						    			<option value="<%out.print(j);%>" <% if(j == reponse.getOrdre()){%>selected<% } %>><%out.print(j);%></option>
						    		<% } %>
						    	</select>
							</td>
							<td><% out.print(reponse.getCorrect()); %></td>
						    <td><a href="/sr03/admin/gestionReponse?id=<% out.print(reponse.getId()); %>">Modifier</a></td> 
						   	<td><a href="/sr03/admin/gestionQuestion?deleteReponse=<% out.print(reponse.getId()); %>&id=${ question.id }">Supprimer</a></td>
						  </tr>
						  <% } %>
						  <tr><td></td><td></td><td></td><td>						  
					      <form action="/sr03/admin/gestionQuestion" id="ordreReponses" method="get">
						  	<input type="hidden" name="id" value="${ question.id }">
						  	<button type="submit" class="btn btn-default btn-xs">Modifier</button>
						  </form>						  
						  </td><td></td><td></td><td></td></tr>
						</table>
				      </div>
				    </div>
				  </div>
				  <div class="panel panel-default">
				    <div class="panel-heading" role="tab" id="headingTwo">
				      <h4 class="panel-title">
				        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
							Ajouter une réponse 
				        </a>
				      </h4>
				    </div>
				    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
				      <div class="panel-body">
						<form action="/sr03/admin/gestionQuestion" method="get">
						    <div class="form-group">
						        <label for="intitule">Intitule :</label>
						        <input type="text" id="intitule" name="intitule" class="form-control"/>
						    </div>
						    <br>Véracité :
						    <div class="radio">
							  <label>
							    <input type="radio" name="correct" value="1" > Vrai
							  </label>
							</div>
						    <div class="radio">
							  <label>
							    <input type="radio" name="correct" value="0" > Faux
							  </label>
							</div>
							<input type="hidden" id="id" name="id" value="${ question.id }" class="form-control"/>
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