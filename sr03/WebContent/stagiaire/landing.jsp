
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
			<h4>Modification compte :</h4>
	        <form class="form-inline" method="POST" action ="/sr03/stagiaire/landing">
	        
		        <table class="table" style=" border-top: none;">
		        
		        <tr>
		        	<td style=" border-top: none;">
			  			<div class="form-group">
			   				<label class="sr-only">Nom</label>
			    			<p class="form-control-static">Nom : </p>
			  			</div>
			  		</td>
			  		<td style=" border-top: none;">
			  			<div class="form-group">
			    			<label for="inputName" class="sr-only"></label>
			    			<input name ="nom" type="name" class="form-control" id="inputName" placeholder="${ utilisateur.nom }" value="${ utilisateur.nom }">
			  			</div>
			  		</td>
			  		<td style=" border-top: none;">	
			  			<div class="form-group">
			   				<label class="sr-only">email</label>
			    			<p class="form-control-static">Email : </p>
			  			</div>
			  		</td>
			  		<td style=" border-top: none;">
			  			<div class="form-group">
			    			<label for="inputName" class="sr-only"></label>
			    			<input name ="email" type="email" class="form-control" id="inputEmail" placeholder="${ utilisateur.email }" value="${ utilisateur.email }">
			  			</div>
			  		</td>
			  	</tr>
			  	<tr>
			  		<td style=" border-top: none;">
			  			<div class="form-group">
			   				<label class="sr-only">Société</label>
			    			<p class="form-control-static">Société : </p>
			  			</div>
			  		</td>
			  		<td style=" border-top: none;">
			  			<div class="form-group">
			    			<label for="inputName" class="sr-only"></label>
			    			<input name ="societe" type="societe" class="form-control" id="inputSociete" placeholder="${ utilisateur.societe }" value="${ utilisateur.societe }">
			  			</div>
			  		</td>
			  		<td style=" border-top: none;">	
			  			<div class="form-group">
			   				<label class="sr-only">Téléphone</label>
			    			<p class="form-control-static">Téléphone : </p>
			  			</div>
			  		</td>
			  		<td style=" border-top: none;">
			  			<div class="form-group">
			    			<label for="inputName" class="sr-only"></label>
			    			<input name ="telephone" type="telephone" class="form-control" id="inputTelephone" placeholder="${ utilisateur.telephone }" width="10" value="${ utilisateur.telephone }">
			  			</div>
			  		</td>
			  	<tr>
			  	<tr>
			  		<td style=" border-top: none;">
			  			<button type="submit" class="btn btn-default">Valider</button>
			  		</td>
		  		</tr>
		  		</table>
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
						    <td align="center"><a href="/sr03/stagiaire/landing/accueilQuestionnaire?questionnaire_id=<% out.print(parcours_current.getQuestionnaire().getId());%>"><% out.print(parcours_current.getQuestionnaire().getSujet()); %></a></td>
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
				    	 			Questionnaire questionnaire_libre_current = (Questionnaire) questionnaires_libres.get(i);
				    	  		%>
					     		<li><a href="/sr03/stagiaire/landing/accueilQuestionnaire?questionnaire_id=<% out.print(questionnaire_libre_current.getId());%>"><% out.print(questionnaire_libre_current.getSujet()); %></a></li>
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
		
		
		
		
		<form class="form-inline">
		  <div class="form-group">
		    <label for="exampleInputName2">Mot-clé</label>
		    <input type="text" name="recherche" class="form-control" id="rechercher" placeholder="rechercher...">
		  </div>
		  <button class="LienModal" type="submit" class="btn btn-default btn-lg">Rechercher</button>

		</form>
		
		
		<!-- Event Modal -->
		<div id="modal" class="modal fade">
		    <div class="modal-dialog">
		        <div class="modal-content">
		 
		                <div class="modal-header">
		                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                    <h4 class="modal-title">Recherche</h4>
		                </div>
		                <div class="modal-body">
		                    <p>Loading...</p>
		                </div>        		 
		        </div>
		        <!-- /.modal-content -->
		    </div>
		    <!-- /.modal-dialog -->
		</div>
		<!-- Event modal -->
			
		
    </div>
</section>

  <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
    <script>

		$(".LienModal").click(function(oEvt){
		    oEvt.preventDefault();
		    var Recherche=$('#rechercher').val();
		    
		        $(".modal-body").fadeIn(50).html('<div style="text-align:center; margin-right:auto; margin-left:auto">Patientez...</div>');
		        $.ajax({
		            type:"GET",
		            data : "Recherche="+Recherche,
		            url:"/sr03/stagiaire/ajax",
		            error:function(msg){
		                $(".modal-body").addClass("tableau_msg_erreur").fadeOut(800).fadeIn(800).fadeOut(400).fadeIn(400).html('<div style="margin-right:auto; margin-left:auto; text-align:center">Impossible de charger cette page</div>');
		            },
		            success:function(data){
		            	 $(".modal-body").fadeIn(200).html(data);
		            	
		            	 $('#modal').modal({'show' : true});
		            }
		        });
		    });
    
    </script>
<%@ include file="../WEB-INF/footer.jsp" %>