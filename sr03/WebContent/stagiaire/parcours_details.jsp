
<%@ include file="../WEB-INF/header.jsp" %>
<section>
	<div class="container">
	    <div class="row">
	        <div class="col-lg-12">
				<h1><center> Details</center></h1><br><br>
				<div class="well">
					<h4>Votre parcours</h4>
					 Vous : ${ utilisateur.nom }<br>
					 Questionnaire : ${parcours.questionnaire.sujet } <br>
					 Votre score : ${parcours.score}<br>
					 Votre temps : <% Parcours parcours = (Parcours) request.getAttribute("parcours"); out.print(parcours.getDuree()); %><br>
					 
				</div>
            </div>
        </div>
        
        
        
        
        <div class="row">
	        <div class="col-lg-12">
	        	<h3>Détail de votre parcours</h3>					    	    
			
				<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
				  <div class="panel panel-default">
				    <div class="panel-heading" role="tab" id="headingOne">
				      <h4 class="panel-title">
				        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
				          Question par question
				        </a>
				      </h4>
				    </div>
				    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
				      <div class="panel-body">
						<table class="table table-hover" style="width:100%">
						  <tr>
						  	<th style="text-align: center;">N° Question</th>
						    <th style="text-align: center;">Question</th>
						    <th style="text-align: center;">Votre réponse</th> 
						    <th style="text-align: center;">Réponse exacte</th>

						</tr>
					  		 <% 
					     ArrayList questionArray = (ArrayList)request.getAttribute("question");
					  	 ArrayList reponseArray = (ArrayList)request.getAttribute("correct_reponse");
					  	 ArrayList reponseUserArray = (ArrayList)request.getAttribute("user_reponse");
					  	
					  	 
					     for (int j = 0 ; j < questionArray.size() ; j++) {
					     	Question question_current = (Question) questionArray.get(j);
					     	Reponse reponse_correcte_current = (Reponse) reponseArray.get(j);
					     	Reponse reponse_utilisateur_current = (Reponse) reponseUserArray.get(j);
					     	
					     	String color_row;
					     	if(reponse_utilisateur_current.getId() == reponse_correcte_current.getId()){
					     		color_row = "success";
					     	}
					     	
					     	else {
					     		color_row = "danger";
					     	}
					     %>
						  <tr>
						  	<td align="center"><% out.print(question_current.getOrdre()); %></th>
						    <td align="center"><% out.print(question_current.getIntitule()); %></th>
						    <td class="<% out.print(color_row); %>" align="center"><% out.print(reponse_utilisateur_current.getIntitule()); %></td>
						    <td align="center"><% out.print(reponse_correcte_current.getIntitule()); %></td>
						     
						  </tr>
						  <% } %>
						</table>
				      </div>
				    </div>
				  </div>
				</div>
			</div>
		</div>
        
        
        
        
        
        <div class="well">
        	<h4>Statistiques</h4>
        	Nombre de stagiaires ayant répondu à ce questionnaire : ${ nombre_stagiaire }<br>
        	Score moyen : ${score_moyen} <br>
        	Duree moyenne : ${duree_moyenne} <br>
        	Votre classement : ${utilisateur_position } <br>
		</div>
	
		 <div class="row">
	        <div class="col-lg-12">
	        	<h3>Ranking</h3>					    	    
			
				<div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true">
				  <div class="panel panel-default">
				    <div class="panel-heading" role="tab" id="headingOne2">
				      <h4 class="panel-title">
				        <a role="button" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne2" aria-expanded="false" aria-controls="collapseOne2">
				          Classement
				        </a>
				      </h4>
				    </div>
				    <div id="collapseOne2" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne2">
				      	<div class="panel-body">
						<table class="table table-hover" style="width:100%">
						  <tr>
						    <th style="text-align: center;">Position</th>
						    <th style="text-align: center;">Nom</th> 
						    <th style="text-align: center;">Score</th>
						    <th style="text-align: center;">Durée</th>
						</tr>
					  		 <% 
					     ArrayList ranking = (ArrayList)request.getAttribute("ranking_utilisateur");
					  		 
					     for (int i = 0 ; i < ranking.size() ; i++) {
					     	Parcours ranking_current = (Parcours) ranking.get(i);
					     	
					     %>
						  <tr>
						    <td align="center"><% out.print(i+1); %></th>
						    <td align="center"><% out.print(ranking_current.getUtilisateur().getNom()); %></td> 
						    <td align="center"><% out.print(ranking_current.getScore()); %></td>
						    <td align="center"><% out.print(ranking_current.getDuree()); %></td>
						     
						  </tr>
						  <% } %>
						</table>
				      </div>
				    </div>
				  </div>
				</div>
			</div>
		</div>
		<a href="/sr03/stagiaire/landing"><button type="button" class="btn btn-warning">Retour</button></a>
    </div>
</section>


<%@ include file="../WEB-INF/footer.jsp" %>