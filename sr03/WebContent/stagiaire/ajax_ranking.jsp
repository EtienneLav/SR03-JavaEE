<%@ page import="java.util.ArrayList"%>
<%@ page import="com.sr03.beans.*"%>
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
					     ArrayList ranking = (ArrayList)request.getAttribute("parcours");
					  	 int current_page = (int) request.getAttribute("page_actuelle"); 
					  	 
					     for (int i = 0 ; i < ranking.size() ; i++) {
					     	Parcours ranking_current = (Parcours) ranking.get(i);
					     	
					     %>
					<tr>
						 <td align="center"><% out.print(i+1 + (current_page-1)*10); %></th>
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