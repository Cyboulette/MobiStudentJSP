<%@page import="Utils.ControllerUtilsInterface"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Models.*"%>
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
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/font-awesome-all.js"></script>
        <script src="js/script.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div id="menu-handler">
            <nav class="navbar navbar-expand-md fixed-top navbar-dark bg-primary">
                <a class="navbar-brand" href="index.jsp">MobiStudent</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarCollapse">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item <%=ControllerUtilsInterface.active(request, "index", null)%>">
                            <a class="nav-link" href="index.jsp">Accueil</a>
                        </li>
                        <li class="nav-item <%=ControllerUtilsInterface.active(request, "search_mobilites", "search")%>">
                            <a class="nav-link" href="./mobilites?action=search">Demandes de mobilités</a>
                        </li>
                        <li class="nav-item <%=ControllerUtilsInterface.active(request, "search_programmes", "search")%>">
                            <a class="nav-link" href="./programmes?action=search">Programmes & Contrats</a>
                        </li>
                        <li class="nav-item <%=ControllerUtilsInterface.active(request, "addMobi", "add")%>">
                            <a class="nav-link" href="./mobilites?action=add">Ajouter une Demande de Mobilités</a>
                        </li>
                        <li class="nav-item <%=ControllerUtilsInterface.active(request, "search_financieres", "search")%>">
                            <a class="nav-link" href="./financieres?action=SEARCH">Demandes Financieres</a>
                        </li>
                        <li class="nav-item <%=ControllerUtilsInterface.active(request, "addFin", "add")%>">
                            <a class="nav-link" href="./financieres?action=add">Ajouter une Demande Financière</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
        <div class="container">