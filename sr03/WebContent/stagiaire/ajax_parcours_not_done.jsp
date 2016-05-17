<%@ page import="java.util.ArrayList"%>
<%@ page import="com.sr03.beans.*"%>


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
				      		ArrayList questionnaires_libres = (ArrayList)request.getAttribute("parcours_non_effectues");
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