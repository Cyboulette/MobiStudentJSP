<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<% 
    DemandeMobilite mobilite = (DemandeMobilite) request.getAttribute("mobilite");
%>
<h1>Modifier une demande de mobilité</h1>
<form class="form-group my-4 my-lg-0" method="POST" action="mobilites">
    <input class="form-control mr-sm-2" type="hidden" name="action" value="edit_mobilite">
    <input class="form-control mr-sm-2" type="hidden" name="idM" value="<%=mobilite.getId()%>">
    <div class="form-group">
        <label for="id_Diplome">Choisir un diplôme</label>
        <select name="id_Diplome" id="id_Diplome" class="form-control selectpicker" data-live-search="true">
            <option value="null" selected>Sélectionnez ...</option>
            <% Vector<Diplome> diplomes = (Vector<Diplome>) request.getAttribute("diplomes");
                for (Diplome d : diplomes) {
                    String selected = (mobilite.getIdDiplome() == d.getId() ? "selected":"");
                    out.print("<option "+selected+" value='" + d.getId() + "'>" + d.getIntitule() + " " + d.getUniversite() + "</option>");
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
                    String selected = (mobilite.getIdEtudiant() == e.getId() ? "selected":"");
                    out.print("<option "+selected+" value='" + e.getId() + "'>" + e.getPrenom() + " " + e.getNom() + " | " + e.getNumEtudiant() + "</option>");
                }
            %>
        </select>
    </div>
        
    <div class="form-group">
        <label for="etat">Saisir l'Etat</label>
        <select name="etat" id="etat" class="form-control selectpicker" data-live-search="true">
            <option value="null" selected>Sélectionnez ...</option>
            <% String[] etats = DemandeMobilite.etats;
                for (String e : etats) {
                    String selected = (mobilite.getEtat().equalsIgnoreCase(e) ? "selected":"");
                    out.print("<option "+selected+" value=\"" + e + "\">" + e + "</option>");
                }
            %>
        </select>
    </div>
    <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Enregistrer</button>
</form>
<%@include file="footer.jsp" %>