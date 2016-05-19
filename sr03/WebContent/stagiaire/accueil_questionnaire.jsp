
<%@ include file="../WEB-INF/header.jsp" %>
<section>
	<div class="container">
	    <div class="row">
	        <div class="col-lg-12">
				<h1><center> Bienvenue sur l'accueil questionnaire </center></h1><br><br>
				<div class="well">
					<h4>Ce questionnaire porte sur : ${ questionnaire.sujet }</h4>
					<br><br>
					Voici les règles de ce questionnaire : <br>
					
					<ul>
						<br>
						<li>Le questionnaire se présente la forme d'un QCM, une réponse est correcte pour chaque question proposée.</li>
						<li>Une bonne réponse rapport 1 point, une mauvaise 0.</li>
						<li>Une fois que cliquez sur le bouton "Démarrer", le chronomètre est lancé.</li>
						<li>Celui-ci ainsi que le score seront sauvegardé uniquement si vous arrivez au bout du questionnaire (cliquer sur valider lorsque cela vous sera demandé).</li>
						<li>Vous ne pouvez faire un même questionnaire qu'une fois par session.</li>
					</ul>
					<br>
					<h5>Bonne chance ! </h5>
					
					<br>
					<a href="/sr03/stagiaire/question?questionnaire_id=${ questionnaire.id }"><button type="button" class="btn btn-success">Démarrer</button></a>
					<a href="/sr03/stagiaire/landing"><button type="button" class="btn btn-warning">Retour</button></a>
				</div>
            </div>
        </div>
       
		
    </div>
</section>


<%@ include file="../WEB-INF/footer.jsp" %>