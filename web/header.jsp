<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Models.Universite"%>
<%@page import="java.util.Vector"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Gestion des mobilités des étudiants</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/font-awesome-all.js"></script>
	<script src="js/script.js"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div id="menu-handler">
    <nav class="navbar navbar-expand-md fixed-top navbar-light bg-light">
        <a class="navbar-brand" href="#">MobiStudent</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="index.jsp">Accueil</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="./universites?action=liste">Liste des universités</a>
                </li>
            </ul>
        </div>
    </nav>
</div>
<div class="container">