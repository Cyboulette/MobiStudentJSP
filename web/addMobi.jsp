<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<h1>Ajouter une demande de mobilité</h1>
<form class="form-group my-4 my-lg-0" method="POST" action="mobilites">
    <input class="form-control mr-sm-2" type="hidden" name="action" value="add_mobilites">
    <div class="form-group">
        <label for="id_Diplome">Choisir un diplôme</label>
        <select name="id_Diplome" id="id_Diplome" class="form-control selectpicker" data-live-search="true">
            <option value="null" selected>Sélectionnez ...</option>
            <% Vector<Diplome> diplomes = (Vector<Diplome>) request.getAttribute("diplomes");
                for (Diplome d : diplomes) {
                    out.print("<option value='" + d.getId() + "'>" + d.getIntitule() + " " + d.getUniversite() + "</option>");
                }
            %>
        </select>
    </div>
        
    <div class="form-group">
        <label for="id_Etudiant">Choisir un Étudiant</label>
        <select name="id_Etudiant" id="id_Etudiant" class="form-control selectpicker" data-live-search="true">
            <option value="null" selected>Sélectionnez ...</option>
            <% Vector<Etudiant> etudiants = (Vector<Etudiant>) request.getAttribute("etudiants");
                for (Etudiant e : etudiants) {
                    out.print("<option value='" + e.getId() + "'>" + e.getPrenom() + " " + e.getNom() + " | " + e.getNumEtudiant() + "</option>");
                }
            %>
        </select>
    </div>
        
    <div class="form-group">
        <label for="date_depot">Choisir une Date</label>
        <input class="form-control" type="date" name="date_depot" id="date_depot">
    </div>
        
    <div class="form-group">
        <label for="etat">Saisir l'Etat</label>
        <select name="etat" id="etat" class="form-control selectpicker" data-live-search="true">
            <option value="null" selected>Sélectionnez ...</option>
            <% String[] etats = DemandeMobilite.etats;
                for (String e : etats) {
                    out.print("<option value=\"" + e + "\">" + e + "</option>");
                }
            %>
        </select>
    </div>
    <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Enregistrer</button>
</form>
<%@include file="footer.jsp" %>