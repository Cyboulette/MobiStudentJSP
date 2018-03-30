<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<h1>Programmes</h1>
<form method="POST" action="programmes">
    <div class="form-group">
        <input type="hidden" name="action" value="LISTE_PROGRAMME_CONTRAT"/>
        <label for="intitule">Rechercher par programme</label>
        <select name="intitule" id="intitule" class="form-control selectpicker" data-live-search="true">
            <option value="null" selected>Sélectionnez ...</option>
            <% Vector<Programme> programmes = (Vector<Programme>)request.getAttribute("programmes"); 
                for(Programme p : programmes) {
                    out.print("<option value='"+p.getId()+"'>"+p.getIntitule()+"</option>");
                }
            %>
        </select>
        <input type="submit">
    </div>
</form>
<%
    if(request.getAttribute("programmes_contrats") != null){
            out.print("<h2> Programmes et Contrats Associés</h2>");
            %>
    <table class="table table-bordered table-hover">
        <thead>
            <th>Programme</th>
            <th>Diplome</th>
            <th>Durée</th>
            <th>Etat</th>
            <th>Ordre</th>
        </thead>
        <tbody>        
            <%
            Vector<Contrat> contrats = (Vector<Contrat>)request.getAttribute("programmes_contrats");
            for(Contrat c : contrats) {
                out.print("<tr>");
                    out.print("<td>"+c.getIntituleP()+"</td>");
                    out.print("<td>"+c.getIntituleD()+"</td>");
                    out.print("<td>"+c.getDuree()+"</td>");
                    out.print("<td>"+c.getEtat()+"</td>");
                    out.print("<td>"+c.getOrdre()+"</td>");
                out.print("</tr>");
            }
            
            %>
            </tbody>
    </table>
<%
    }
%>
<%@include file="footer.jsp" %>