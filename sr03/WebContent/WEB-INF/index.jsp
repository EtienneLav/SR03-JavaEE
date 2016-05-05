<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="fr">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Projets de SR03</title>
    <link href="/sr03/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/sr03/css/full-width-pics.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">

            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/~sr03p016">SR03</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="/~sr03p016/projet1/formulaire.php">Projet 1</a>
                    </li>
                    <li>
                        <a href="#">Projet 2</a>
                    </li>
                    <li>
                        <a href="#">Projet 3</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <header class="image-bg-fluid-height">
        <img class="img-responsive img-center" src="https://www.utc.fr/cru-1455812804/typo3conf/ext/site/Resources/Public/Frontend/vendor/html/images/utc-site-logo.png" alt="">
    </header>

    <section>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
					<h1 class="section-heading">Connexion</h1>
					<form action="/sr03/connexion" method="post">
					    <div class="form-group">
					        <label for="mail">Addresse mail :</label>
					        <input type="email" id="mail" name="mail" class="form-control" placeholder="Email"/>
					    </div>
					    <br><br>
					    <div class="form-group">
					        <label for="mdp">Mot de passe :</label>
					        <input type="password" id="mdp" name="mdp" class="form-control" placeholder="Password"/>
					    </div>
					    <br>
					    <div class="button" class="form-group">
					        <button type="submit" class="btn btn-primary">Se connecter</button>
					    </div>
					</form>
	            </div>
	        </div>
	    </div>
	</section>
	
	<footer>
	    <div class="container">
	        <div class="row">
	            <div class="col-lg-12">
	                <p>Travail réalisé par LAVIOLETTE Etienne et FRISCH Gabriel.</p>
	            </div>
	        </div>
	    </div>
	</footer>

	<script src="/sr03/js/jquery.js"></script>
	<script src="/sr03/js/bootstrap.min.js"></script>

</body>

</html>