<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<h1>Demandes Financières</h1>
<form class="form-inline my-2 my-lg-0" method="POST" action="financieres">
    <input class="form-control mr-sm-2" type="hidden" name="action" value="demandes_financieres">
            <label for="contrat_id">Rechercher par Contrat</label>
        <select name="contrat_id" id="contrat_id" class="form-control selectpicker" data-live-search="true">
            <option value="null" selected>Sélectionnez ...</option>
            <% Vector<Contrat> contrats = (Vector<Contrat>)request.getAttribute("contrats"); 
                for(Contrat c : contrats) {
                    out.print("<option value='"+c.getId()+"'>Contrat pour une durée de "+c.getDuree()+ " | Diplôme : "+ c.getIntituleD() +" | Programme :"+ c.getIntituleP()+"</option>");
                }
            %>
        </select>
    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Chercher</button>
</form>
<form class="form-inline my-2 my-lg-0" method="POST" action="financieres">
    <input class="form-control mr-sm-2" type="hidden" name="action" value="demandes_financieres">
            <label for="programme_id">Rechercher par Programme</label>
        <select name="programme_id" id="programme_id" class="form-control selectpicker" data-live-search="true">
            <option value="null" selected>Sélectionnez ...</option>
            <% Vector<Programme> programmes = (Vector<Programme>)request.getAttribute("programmes"); 
                for(Programme p : programmes) {
                    out.print("<option value='"+p.getId()+"'>"+ p.getIntitule() +"</option>");
                }
            %>
        </select>
    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Chercher</button>
</form>
        <% 
                if(request.getAttribute("financieres") != null){
                %>
                
<table class="table table-bordered table-hover">
    <thead>
        <th>Date de dépôt</th>
        <th>Etat</th>
        <th>Montant Accordé</th>
    </thead>
    <tbody>
        <%
            Vector<DemandeFinanciere> financieres = (Vector<DemandeFinanciere>)request.getAttribute("financieres");
            for(DemandeFinanciere dem : financieres) {
                out.print("<tr>");
                    out.print("<td>"+dem.getDate_depot()+"</td>");
                    out.print("<td>"+dem.getEtat()+"</td>");
                    out.print("<td>"+dem.getMontant_accorde()+"</td>");
                out.print("</tr>");
            }
        %>
    </tbody>
</table>
        <%
            }
        %>
<%@include file="footer.jsp" %>