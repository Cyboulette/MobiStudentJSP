<%@page import="java.util.Vector"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<div class="row">
    <div class="col-3">

        <div class="nav flex-column nav-pills mb-3" id="v-pills-tab" role="tablist" aria-orientation="vertical">
            <a class="nav-link <%=ControllerUtilsInterface.activeTab(request, "v-pills-diplomes")%>" id="v-pills-diplomes-tab" data-toggle="pill" href="#v-pills-diplomes" role="tab" aria-controls="v-pills-diplomes" aria-selected="true">Diplômes</a>
            <a class="nav-link <%=ControllerUtilsInterface.activeTab(request, "v-pills-etudiants")%>" id="v-pills-etudiants-tab" data-toggle="pill" href="#v-pills-etudiants" role="tab" aria-controls="v-pills-etudiants" aria-selected="false">Étudiants</a>
            <a class="nav-link <%=ControllerUtilsInterface.activeTab(request, "v-pills-universites")%>" id="v-pills-universites-tab" data-toggle="pill" href="#v-pills-universites" role="tab" aria-controls="v-pills-universites" aria-selected="false">Universités</a>
        </div>
    </div>
    <div class="col-9">
        <div class="tab-content" id="v-pills-tabContent">
            <div class="tab-pane fade <%=ControllerUtilsInterface.activeTab(request, "v-pills-diplomes")%>" id="v-pills-diplomes" role="tabpanel" aria-labelledby="v-pills-diplomes-tab">
                <form class="form-group my-4 my-lg-0" method="POST" action="mobilites">
                    <input class="form-control mr-sm-2" type="hidden" name="action" value="search">
                    <label for="id_diplome">Rechercher par Diplôme</label>
                    <div class="row">
                        <div class="col-10">
                            <select name="id_diplome" id="id_diplome" class="form-control selectpicker" data-live-search="true">
                                <option value="null" selected>Sélectionnez ...</option>
                                <% Vector<Diplome> diplomes = (Vector<Diplome>) request.getAttribute("diplomes");
                                    for (Diplome d : diplomes) {
                                        out.print("<option value='" + d.getId() + "'>" + d.getIntitule() + " " + d.getUniversite() + "</option>");
                                    }
                                %>
                            </select>
                        </div>
                        <div class="col-2">
                            <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Chercher</button>
                        </div>
                    </div>
                </form>       
            </div>
            <div class="tab-pane fade <%=ControllerUtilsInterface.activeTab(request, "v-pills-etudiants")%>" id="v-pills-etudiants" role="tabpanel" aria-labelledby="v-pills-etudiants-tab">
                <form class="form-group my-4 my-lg-0" method="POST" action="mobilites">
                    <input class="form-control mr-sm-2" type="hidden" name="action" value="search">
                    <label for="id_etudiant">Rechercher par Étudiant</label>
                    <div class="row">
                        <div class="col-10">
                            <select name="id_etudiant" id="id_etudiant" class="form-control selectpicker" data-live-search="true">
                                <option value="null" selected>Sélectionnez ...</option>
                                <% Vector<Etudiant> etudiants = (Vector<Etudiant>) request.getAttribute("etudiants");
                                    for (Etudiant e : etudiants) {
                                        out.print("<option value='" + e.getId() + "'>" + e.getPrenom() + " " + e.getNom() + " | " + e.getNumEtudiant() + "</option>");
                                    }
                                %>
                            </select>
                        </div>
                        <div class="col-2">
                            <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Chercher</button>
                        </div>
                    </div>
                </form>       
            </div>
            <div class="tab-pane fade <%=ControllerUtilsInterface.activeTab(request, "v-pills-universites")%>" id="v-pills-universites" role="tabpanel" aria-labelledby="v-pills-universites-tab">
                <form class="form-group my-4 my-lg-0" method="POST" action="mobilites">
                    <input class="form-control mr-sm-2" type="hidden" name="action" value="search">
                    <label for="num_univ">Rechercher par Université</label>
                    <div class="row">
                        <div class="col-10">
                            <select name="num_univ" id="num_univ" class="form-control selectpicker" data-live-search="true">
                                <option value="null" selected>Sélectionnez ...</option>
                                <% Vector<Universite> universites = (Vector<Universite>) request.getAttribute("universites");
                                    for (Universite u : universites) {
                                        out.print("<option value='" + u.getId() + "'>" + u.getNom() + "</option>");
                                    }
                                %>
                            </select>
                        </div>
                        <div class="col-2">
                            <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Chercher</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>


<%
    if (request.getAttribute("demandes") != null) {
%>

<table class="table table-bordered table-hover mt-4">
    <thead>
    <th>Etudiant</th>
    <th>Diplome</th>
    <th>Date Depot</th>
    <th>Etat</th>
</thead>
<tbody>
    <%
        Vector<DemandeMobilite> demandes = (Vector<DemandeMobilite>) request.getAttribute("demandes");
        for (DemandeMobilite dem : demandes) {
            out.print("<tr>");
            out.print("<td>" + dem.getNumEtudiant() + "</td>");
            out.print("<td>" + dem.getIntituleDiplome() + "</td>");
            out.print("<td>" + dem.getDate_depot() + "</td>");
            out.print("<td>" + dem.getEtat() + "</td>");
            out.print("</tr>");
        }
    %>
</tbody>
</table>
<%
    }
%>
<%@include file="footer.jsp" %>