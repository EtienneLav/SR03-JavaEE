<%@ page import="java.util.ArrayList"%>
<%@ page import="com.sr03.beans.*"%>

					 <% 
					    ArrayList questionnaire = (ArrayList)request.getAttribute("questionnaires");
					 ArrayList nombre_question = (ArrayList)request.getAttribute("nombre_questions");
					 
					  	if(questionnaire.size() >0)	 {
					  		
					  		%><table class="table table-striped">
					  			<tr>
								    <th style="text-align: center;">Sujet</th>
								    <th style="text-align: center;">Nombre de questions</th> 
						    
								</tr>
					  		<%
					  	 
					     for (int i = 0 ; i < questionnaire.size() ; i++) {
					     	Questionnaire questionnaire_current = (Questionnaire) questionnaire.get(i);
					 	  	int nombre_question_current = (int) nombre_question.get(i);
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


				

