<%@ page import="java.util.ArrayList"%>
<%@ page import="com.sr03.beans.*"%>

					 <% 
					    ArrayList questionnaires = (ArrayList)request.getAttribute("questionnaires");
					 ArrayList nombre_questions = (ArrayList)request.getAttribute("nombre_questions");
					 
					  	if(questionnaires.size() >0)	 {
					  		
					  		%><table class="table table-striped">
					  			<tr>
								    <th style="text-align: center;">Sujet</th>
								    <th style="text-align: center;">Nombre de questions</th> 
						    
								</tr>
					  		<%
					  	 
					     for (int i = 0 ; i < questionnaires.size() ; i++) {
					     	Questionnaire questionnaire_current = (Questionnaire) questionnaires.get(i);
					 	  	int nombre_question_current = (int) nombre_questions.get(i);
					     	 %>
						     	 <tr>
								    <td align="center"><a href="/sr03/stagiaire/landing/accueilQuestionnaire?questionnaire_id=<% out.print(questionnaire_current.getId());%>"><% out.print(questionnaire_current.getSujet()); %></a></td>
								    <td align="center"><% out.print(nombre_question_current); %></td> 
							 	 </tr>
						<%
					     }
					  		
					  		%></table><%
					  	}
					  	else
					  		out.print("Aucun résultat ne correspond à votre recherche...");
						  %>


				

