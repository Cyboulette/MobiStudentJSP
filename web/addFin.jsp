<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<h1>Ajouter une demande financière</h1>
<form class="form-group my-4 my-lg-0" method="POST" action="financieres">
    <input class="form-control mr-sm-2" type="hidden" name="action" value="add_financieres">
            <label for="contrat_id">Choisir un Contrat</label>
        <select name="contrat_id" id="contrat_id" class="form-control selectpicker" data-live-search="true">
            <option value="null" selected>Sélectionnez ...</option>
            <% Vector<Contrat> contrats = (Vector<Contrat>)request.getAttribute("contrats"); 
                for(Contrat c : contrats) {
                    out.print("<option value='"+c.getId()+"'>Contrat pour une durée de "+c.getDuree()+ " | Diplôme : "+ c.getIntituleD() +" | Programme :"+ c.getIntituleP()+"</option>");
                }
            %>
        </select>
    <label for="date_depot">Choisir une Date</label>
    <input class="form-control" type="date" name="date_depot" id="date_depot">
    <label for="etat">Saisir l'Etat</label>
    <input class="form-control" type="text" name="etat" id="etat">
    <label for="montant_accorde">Saisir le Montant accordée</label>
    <input class="form-control" type="text" name="montant_accorde" id="montant_accorde">
    <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Enregistrer</button>
</form>
<%@include file="footer.jsp" %>