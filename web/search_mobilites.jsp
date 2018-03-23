<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<h1>Demandes de mobilités</h1>
<form method="POST" action="MobilitesController">
    <div class="form-group">
        <label for="etudiant">Rechercher par étudiant</label>
        <select name="etudiant" id="etudiant" class="form-control selectpicker" data-live-search="true">
            <option value="null" selected>Sélectionnez ...</option>
            <% Vector<Etudiant> etudiants = (Vector<Etudiant>)request.getAttribute("etudiants"); 
                for(Etudiant e : etudiants) {
                    out.print("<option value='"+e.getId()+"'>"+e.getPrenom()+" "+e.getNom()+" - "+e.getNumEtudiant()+"</option>");
                }
            %>
        </select>
    </div>
</form>
            
<form method="POST" action="MobilitesController">
    <div class="form-group">
        <label for="etudiant">Rechercher par université</label>
        <select name="etudiant" id="etudiant" class="form-control selectpicker" data-live-search="true">
            <option value="null" selected>Sélectionnez ...</option>
            <% Vector<Universite> universites = (Vector<Universite>)request.getAttribute("universites"); 
                for(Universite u : universites) {
                    out.print("<option value='"+u.getId()+"'>"+u.getNom()+"</option>");
                }
            %>
        </select>
    </div>
</form>
<%@include file="footer.jsp" %>