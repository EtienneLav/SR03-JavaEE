<%@ include file="../WEB-INF/header.jsp" %>
<section>
	<div class="container">

      <div class="row">
      		<h1 style="text-align: center;">Gestion Reponse n°${ reponse.id }</h1>
	        <div class="col-lg-6">
	        <br><br>
				<div class="panel-group" id="accordion1" role="tablist" aria-multiselectable="true">
				  <div class="panel panel-default">
				    <div class="panel-heading" role="tab" id="headingOne1">
				      <h4 class="panel-title">
				        <a role="button" data-toggle="collapse" data-parent="#accordion1" href="#collapseOne1" aria-expanded="false" aria-controls="collapseOne1">
				          Informations Reponse
				        </a>
				      </h4>
				    </div>
				    <div id="collapseOne1" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne1">
				      <div class="panel-body">
						Id : ${ reponse.id }<br>
						Intitule : ${ reponse.intitule }<br>
						Ordre : ${ reponse.ordre }<br>
						Statut : ${ reponse.status }<br>
						Correct : ${ reponse.correct }<br>
				      </div>
				    </div>
				  </div>
				  <div class="panel panel-default">
				    <div class="panel-heading" role="tab" id="headingTwo1">
				      <h4 class="panel-title">
				        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion1" href="#collapseTwo1" aria-expanded="false" aria-controls="collapseTwo1">
							Modifier Reponse 
				        </a>
				      </h4>
				    </div>
				    <div id="collapseTwo1" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo1">
				      <div class="panel-body">
						<form action="/sr03/admin/gestionReponse" method="get">
						    <div class="form-group">
						        <label for="intitule">Intitule :</label>
						        <input type="text" value="${ reponse.intitule }" id="modifierIntitule" name="modifierIntitule" class="form-control"/>
						    </div>
						    <br>Véracité :
						    <div class="radio">
							  <label>
							    <input type="radio" name="correct" value="1" 
							    <%
							    Reponse reponse= (Reponse) request.getAttribute("reponse");
							    boolean correct = reponse.getCorrect();
							    if( correct == true) {%>checked<% } %> 
							    > Vrai
							  </label>
							</div>
						    <div class="radio">
							  <label>
							    <input type="radio" name="correct" value="0" <% if(correct == false) {%>checked<% } %>> Faux
							  </label>
							</div>
							<input type="hidden" id="id" name="id" value="${ reponse.id }" class="form-control"/>
							<button type="submit" class="btn btn-primary">Modifier</button>
						</form>
				      </div>
				    </div>
				  </div>
				</div>
            </div>
            	<%
					int modifiedReponse = (int) request.getAttribute("modifiedReponse");
				    if (modifiedReponse == -1) {%>
				    <div class="col-lg-6 alert alert-info" role="alert">Erreur lors de la modification de la réponse.</div>
				<%    	
				    } else if (modifiedReponse == 1) {
				%>
					<div class="col-lg-6 alert alert-success" role="alert">Réponse modifiée</div>
				<%} else {} %> 				
        </div>  
        
        <a href="/sr03/admin/landing"><button type="button" class="btn btn-warning">Retour</button></a>
    </div>
</section>


<%@ include file="../WEB-INF/footer.jsp" %>