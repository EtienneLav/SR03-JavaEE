<%@ include file="../WEB-INF/header.jsp" %>
<section>
	<div class="container">
       <div class="row">
            <h1 style="text-align: center;">Gestion Questionnaire n°${ questionnaire.id }</h1>
	        <div class="col-lg-6">
	        <br><br>
				<div class="panel-group" id="accordion1" role="tablist" aria-multiselectable="true">
				  <div class="panel panel-default">
				    <div class="panel-heading" role="tab" id="headingOne1">
				      <h4 class="panel-title">
				        <a role="button" data-toggle="collapse" data-parent="#accordion1" href="#collapseOne1" aria-expanded="false" aria-controls="collapseOne1">
				          Informations questionnaire
				        </a>
				      </h4>
				    </div>
				    <div id="collapseOne1" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne1">
				      <div class="panel-body">
						Id : ${ questionnaire.id }<br>
						Sujet : ${ questionnaire.sujet }<br>
						Statut : ${ questionnaire.status }<br>
				      </div>
				    </div>
				  </div>
				  <div class="panel panel-default">
				    <div class="panel-heading" role="tab" id="headingTwo1">
				      <h4 class="panel-title">
				        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion1" href="#collapseTwo1" aria-expanded="false" aria-controls="collapseTwo1">
							Modifier Questionnaire 
				        </a>
				      </h4>
				    </div>
				    <div id="collapseTwo1" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo1">
				      <div class="panel-body">
						<form action="/sr03/admin/gestionQuestionnaire" method="get">
						    <div class="form-group">
						        <label for="intitule">Intitule :</label>
						        <input type="text" id="modifierIntitule" name="modifierIntitule" class="form-control"/>
						    </div>
							<input type="hidden" id="id" name="id" value="${ questionnaire.id }" class="form-control"/>
							<button type="submit" class="btn btn-primary">Modifier</button>
						</form>
				      </div>
				    </div>
				  </div>
				</div>
            </div>
            	<%
					int modifiedQuestionnaire = (int) request.getAttribute("modifiedQuestionnaire");
				    if (modifiedQuestionnaire == -1) {%>
				    <div class="col-lg-6 alert alert-info" role="alert">Erreur lors de la modification du questionnaire ( intitulé probablement existant ! ).</div>
				<%    	
				    } else if (modifiedQuestionnaire == 1) {
				%>
					<div class="col-lg-6 alert alert-success" role="alert">Questionnaire modifié</div>
				<%} else {} %> 				
        </div>  
        
	    <div class="row">
	        <div class="col-lg-12">
	        	<h3>Gestion des questions</h3>					    	    
				<%
					boolean added = (boolean) request.getAttribute("addedQuestion");
				    if (added == true) {%>
				    <div class="alert alert-success" role="alert">Question ajoutée</div>
				<%    	
				    } 		
				%>
				<%
					boolean deleted = (boolean) request.getAttribute("deletedQuestion");
				    if (deleted == true) {%>
				    <div class="alert alert-info" role="alert">Question supprimée</div>
				<%    	
				    } 		
				%>
				<%
					int orderQuestionModified = (int) request.getAttribute("orderQuestionModified");
				    if (orderQuestionModified == -1) {%>
				    <div class="alert alert-info" role="alert">Erreur lors de l'ordonnancement des questions</div>
				<%    	
				    } else if (orderQuestionModified == 1) {
				%>
					<div class="alert alert-success" role="alert">Questions ré-ordonnées</div>
				<%} else {} %> 
	      	        
				<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				  <div class="panel panel-default">
				    <div class="panel-heading" role="tab" id="headingOne">
				      <h4 class="panel-title">
				        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
				          Liste des questions
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
							<th></th>
							<th></th>
						</tr>
					  		 <% 
					     ArrayList questions = (ArrayList)request.getAttribute("questions");
					     for (int i = 0 ; i < questions.size() ; i++) {
					     	Question question = (Question) questions.get(i);
					     %>
						  <tr>
						    <th><% out.print(question.getId()); %></th>
						    <td><% out.print(question.getIntitule()); %></td> 
						    <td><% out.print(question.getStatus()); %></td>
						    <td>
						    	<select name="<% out.print(question.getId()); %>" form="ordreQuestions">
						    		<% for( int j = 1; j <= questions.size(); j++) {%>
						    			<option value="<%out.print(j);%>" <% if(j == question.getOrdre()){%>selected<% } %>><%out.print(j);%></option>
						    		<% } %>
						    	</select>
							</td>
						    <td><a href="/sr03/admin/gestionQuestion?id=<% out.print(question.getId()); %>">Modifier</a></td> 
						   	<td><a href="/sr03/admin/gestionQuestionnaire?deleteQuestion=<% out.print(question.getId()); %>&id=${ questionnaire.id }">Supprimer</a></td>
						  </tr>
						  <% } %>
						  <tr><td></td><td></td><td></td><td>						  
					      <form action="/sr03/admin/gestionQuestionnaire" id="ordreQuestions" method="get">
						  	<input type="hidden" name="id" value="${ questionnaire.id }">
						  	<button type="submit" class="btn btn-default btn-xs">Modifier</button>
						  </form>						  
						  </td><td></td><td></td></tr>
						</table>
				      </div>
				    </div>
				  </div>
				  <div class="panel panel-default">
				    <div class="panel-heading" role="tab" id="headingTwo">
				      <h4 class="panel-title">
				        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
							Ajouter une question 
				        </a>
				      </h4>
				    </div>
				    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
				      <div class="panel-body">
						<form action="/sr03/admin/gestionQuestionnaire" method="get">
						    <div class="form-group">
						        <label for="intitule">Intitule :</label>
						        <input type="text" id="intitule" name="intitule" class="form-control"/>
						    </div>
							<input type="hidden" id="id" name="id" value="${ questionnaire.id }" class="form-control"/>
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