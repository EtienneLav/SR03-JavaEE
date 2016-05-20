<%@ page import="java.util.ArrayList"%>
<%@ page import="com.sr03.beans.*"%>


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
						     ArrayList parcours = (ArrayList)request.getAttribute("parcours_effectues");
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