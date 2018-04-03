<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<div class="row">
    <div class="col-3">
        <div class="nav flex-column nav-pills mb-3" id="v-pills-tab" role="tablist" aria-orientation="vertical">
            <a class="nav-link active" id="v-pills-contrats-tab" data-toggle="pill" href="#v-pills-contrats" role="tab" aria-controls="v-pills-contrats" aria-selected="true">Contrats</a>
            <a class="nav-link" id="v-pills-programmes-tab" data-toggle="pill" href="#v-pills-programmes" role="tab" aria-controls="v-pills-programmes" aria-selected="false">Programmes</a>
        </div>
    </div>
    <div class="col-9">
        <div class="tab-content" id="v-pills-tabContent">
            <div class="tab-pane fade show active" id="v-pills-contrats" role="tabpanel" aria-labelledby="v-pills-contrats-tab">
                <form class="form-group my-2 my-lg-0" method="POST" action="financieres">
                    <input class="form-control mr-sm-2" type="hidden" name="action" value="search">
                    <label for="contrat_id">Rechercher par Contrat</label>
                    <div class="row">
                        <div class="col-10">
                            <select name="contrat_id" id="contrat_id" class="form-control selectpicker" data-live-search="true">
                                <option value="null" selected>Sélectionnez ...</option>
                                <% Vector<Contrat> contrats = (Vector<Contrat>) request.getAttribute("contrats");
                                    for (Contrat c : contrats) {
                                        out.print("<option value='" + c.getId() + "'>Contrat pour une durée de " + c.getDuree() + " | Diplôme : " + c.getIntituleD() + " | Programme :" + c.getIntituleP() + "</option>");
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
            <div class="tab-pane fade" id="v-pills-programmes" role="tabpanel" aria-labelledby="v-pills-programmes-tab">
                <form class="form-group my-2 my-lg-0" method="POST" action="financieres">
                    <input class="form-control mr-sm-2" type="hidden" name="action" value="search">
                    <label for="programme_id">Rechercher par Programme</label>
                    <div class="row">
                        <div class="col-10">
                            <select name="programme_id" id="programme_id" class="form-control selectpicker" data-live-search="true">
                                <option value="null" selected>Sélectionnez ...</option>
                                <% Vector<Programme> programmes = (Vector<Programme>) request.getAttribute("programmes");
                                    for (Programme p : programmes) {
                                        out.print("<option value='" + p.getId() + "'>" + p.getIntitule() + "</option>");
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
    if (request.getAttribute("financieres") != null) {
%>

<table class="table table-bordered table-hover">
    <thead>
    <th>Date de dépôt</th>
    <th>Etat</th>
    <th>Montant Accordé</th>
</thead>
<tbody>
    <%
        Vector<DemandeFinanciere> financieres = (Vector<DemandeFinanciere>) request.getAttribute("financieres");
        for (DemandeFinanciere dem : financieres) {
            out.print("<tr>");
            out.print("<td>" + dem.getDate_depot() + "</td>");
            out.print("<td>" + dem.getEtat() + "</td>");
            out.print("<td>" + dem.getMontant_accorde() + "</td>");
            out.print("</tr>");
        }
    %>
</tbody>
</table>
<%
    }
%>
<%@include file="footer.jsp" %>