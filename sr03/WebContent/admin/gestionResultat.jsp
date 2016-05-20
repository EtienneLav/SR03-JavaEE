 <%@ include file="../WEB-INF/header.jsp" %>
<section>
	<div class="container">
 		<div class="row">
	        <div class="col-lg-12">
	        	<h3>Questionnaires parcourus</h3>	
	        	
	        	<div id=content_effectue>				    	    
			
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
							  </tr>
							  <% } %>
							</table>
					      </div>
					    </div>
					  </div>
					</div>
					
				</div>
			</div>
		</div>
		<div id="page-selection-effectues" style="text-align: center;"></div>
		<a href="/sr03/admin/landing"><button type="button" class="btn btn-warning">Retour</button></a>
		</div>
</section>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="//rawgit.com/botmonster/jquery-bootpag/master/lib/jquery.bootpag.min.js"></script>
<script>
<%int nb_page_parcours_effectues = (int) request.getAttribute("nombre_pages_parcours_effectues");%>
<%int numero_stagiaire = (int) request.getAttribute("numero_stagiaire");%>


// init bootpag
$('#page-selection-effectues').bootpag({
    total: <% out.print(nb_page_parcours_effectues); %>
}).on("page", function(event, /* page number here */ num){
	
	$.ajax({
        type:"GET",
        data : "numero_page_done="+num+"&numero_stagiaire="+<% out.print(numero_stagiaire);%>, 
        url:"/sr03/admin/parcoursDone; %>",
        error:function(msg){
            ;
        },
        success:function(data){
        	 $('#content_effectue').empty();
        	 $("#content_effectue").html(data); // some ajax content loading...
        }
    });
    
});
</script>
		
		