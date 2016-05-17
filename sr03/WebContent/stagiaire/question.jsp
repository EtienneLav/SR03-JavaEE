
<%@ include file="../WEB-INF/header.jsp" %>
<section>
	<div class="container">
	    <div class="row">
	        <div class="col-lg-12">
				<h1><center> Questionnaire ${ questionnaire.sujet } </center></h1><br><br>
				<div class="well">
					
					
					<% 
					
						Boolean last_question = (Boolean)request.getAttribute("last_question"); 
						if (last_question){
					%>
							<br>
								Vous avez fini ce questionnaire sur le thème du  ${ questionnaire.sujet }<br>
								Pour le valider, cliquer sur le bouton ci-dessous :)<br><br>
							<a href="/sr03/stagiaire/validation?questionnaire_id=${ questionnaire.id }"><button type="button" class="btn btn-success">Valider questionnaire</button></a>
						
					<% }
						else { %>
					
							<h4>Question numéro ${ question.ordre } </h4>
							<br>
							${ question.intitule }
							 <form class="form-inline" method="POST" action ="/sr03/stagiaire/question?questionnaire_id=${ questionnaire.id }">
							 	<br>
							 	
							 	<% 
							 	ArrayList reponses = (ArrayList)request.getAttribute("reponse");
						      			for (int i = 0 ; i < reponses.size() ; i++) {
						    	 			Reponse reponse_current = (Reponse) reponses.get(i);
						    	 %>
							 	<div class="radio">
								  <label>
								    <input type="radio" name="reponse_chosen" id="optionsRadios1" value="<% out.print(reponse_current.getId());%>" checked>
								    <% out.print(reponse_current.getIntitule());%>
								  </label>
								</div>
								<br>
								<% } %>
								<br>
								<br>
							 
							 <button type="submit" class="btn btn-default">Valider</button>
							 </form>
							
							<br>
						<% } %>
					<a href="/sr03/stagiaire/landing"><button type="button" class="btn btn-warning">Accueil</button></a>
				</div>
            </div>
        </div>
       
		
    </div>
</section>


<%@ include file="../WEB-INF/footer.jsp" %>