<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<% 
    DemandeFinanciere financiere = (DemandeFinanciere) request.getAttribute("financiere");
%>
<h1>Modifier une demande financière</h1>
<form class="form-group my-4 my-lg-0" method="POST" action="financieres">
    <input class="form-control mr-sm-2" type="hidden" name="action" value="edit_financiere">
    <input class="form-control mr-sm-2" type="hidden" name="idF" value="<%=financiere.getId()%>">
    <div class="form-group">
        <label for="contrat_id">Choisir un Contrat</label>
        <select name="contrat_id" id="contrat_id" class="form-control selectpicker" data-live-search="true">
            <option value="null" selected>Sélectionnez ...</option>
            <% Vector<Contrat> contrats = (Vector<Contrat>)request.getAttribute("contrats"); 
                for(Contrat c : contrats) {
                    String selected = (financiere.getIdContrat() == c.getId() ? "selected":"");
                    out.print("<option "+selected+" value='"+c.getId()+"'>Contrat pour une durée de "+c.getDuree()+ " ans | Diplôme : "+ c.getIntituleD() +" | Programme : "+ c.getIntituleP()+"</option>");
                }
            %>
        </select>
    </div>
        
    <div class="form-group">
        <label for="etat">Saisir l'Etat</label>
        <select name="etat" id="etat" class="form-control selectpicker" data-live-search="true">
                <option value="null" selected>Sélectionnez ...</option>
                <% String[] etats = DemandeFinanciere.etats;
                    for (String e : etats) {
                        String selected = (financiere.getEtat().equalsIgnoreCase(e) ? "selected":"");
                        out.print("<option "+selected+" value=\"" + e + "\">" + e + "</option>");
                    }
                %>
        </select>
    </div>
        
    <div class="form-group">
        <label for="montant_accorde">Saisir le Montant accordée</label>
        <input class="form-control" value="<%=financiere.getMontant_accorde()%>" type="number" name="montant_accorde" id="montant_accorde">
    </div>
        
    <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Enregistrer</button>
</form>
<%@include file="footer.jsp" %>