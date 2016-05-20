<%@ include file="../WEB-INF/header.jsp" %>
<section>
	<div class="container">
	    <div class="row">
	        <div class="col-lg-12">
				<h1><center> Résultat questionnaire </center></h1><br><br>
				<div class="well">
				
				<% 
			        boolean deja_fait = (boolean) request.getAttribute("deja_fait");
					if(deja_fait == true){
				%>
					Vous avez déjà effectué CE questionnaire lors de cette session. <br>
					Ne ne pouvons enregistrer deux parcours pourun même questionnaire et une même session.<br>
					Veuillez re-tenter ce questionnaire lors d'une session différente ou faire un autre questionnaire<br><br>
				<%  }
					
					else {%>
					<h4>Ce questionnaire portait sur : ${ questionnaire.sujet }</h4>
					<br><br>
					Voici votre score : ${ score } / ${ points_max }	 <br>			
					<br>
					<a href="/sr03/stagiaire/landing/parcoursdetails?parcours_number=${ parcours_id }"><button type="button" class="btn btn-info">Détails de votre parcours</button></a>
					<%} %>
					<a href="/sr03/stagiaire/landing"><button type="button" class="btn btn-warning">Page d'accueil</button></a>
				</div>
            </div>
        </div>
       
    </div>
</section>

<%@ include file="../WEB-INF/footer.jsp" %>