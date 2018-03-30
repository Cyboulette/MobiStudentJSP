<%@page import="java.util.Vector"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<h1>Demandes par Université</h1>
<form class="form-inline my-2 my-lg-0" method="POST" action="mobilites">
    <input class="form-control mr-sm-2" type="hidden" name="action" value="demandes_mobilites_universites">
            <label for="num_univ">Rechercher par Université</label>
        <select name="num_univ" id="num_univ" class="form-control selectpicker" data-live-search="true">
            <option value="null" selected>Sélectionnez ...</option>
            <% Vector<Universite> universites = (Vector<Universite>)request.getAttribute("universites"); 
                for(Universite u : universites) {
                    out.print("<option value='"+u.getId()+"'>"+u.getNom()+"</option>");
                }
            %>
        </select>
    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Chercher</button>
</form>
        <% if(request.getAttribute("demandes") != null){ %>
                
<table class="table table-bordered table-hover">
    <thead>
        <th>Etudiant</th>
        <th>Diplome</th>
        <th>Date Depot</th>
        <th>Etat</th>
    </thead>
    <tbody>
        <%
            Vector<DemandeMobilite> demandes = (Vector<DemandeMobilite>)request.getAttribute("demandes");
            for(DemandeMobilite dem : demandes) {
                out.print("<tr>");
                    out.print("<td>"+dem.getNumEtudiant()+"</td>");
                    out.print("<td>"+dem.getIntituleDiplome()+"</td>");
                    out.print("<td>"+dem.getDate_depot()+"</td>");
                    out.print("<td>"+dem.getEtat()+"</td>");
                out.print("</tr>");
            }
        %>
    </tbody>
</table>
        <%
            }
        %>
<%@include file="footer.jsp" %>