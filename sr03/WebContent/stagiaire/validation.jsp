
<%@ include file="../WEB-INF/header.jsp" %>
<section>
	<div class="container">
	    <div class="row">
	        <div class="col-lg-12">
				<h1><center> Résultat questionnaire </center></h1><br><br>
				<div class="well">
					<h4>Ce questionnaire portait sur : ${ questionnaire.sujet }</h4>
					<br><br>
					Voici votre score : ${ score } <br>
									
					
					<br>
					<a href="/sr03/stagiaire/landing"><button type="button" class="btn btn-warning">Page d'accueil</button></a>
				</div>
            </div>
        </div>
       
    </div>
</section>

<%@ include file="../WEB-INF/footer.jsp" %>