<%@page import="java.util.Vector"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<h1>Liste des Demandes par Université</h1>
<table class="table table-bordered table-hover">
    <thead>
        <th>Universite</th>
        <th>Diplome</th>
        <th>Date Depot</th>
        <th>Etat</th>
    </thead>
    <tbody>
        <%
            Vector<DemandeMobilite> demandes = (Vector<DemandeMobilite>)request.getAttribute("demandes");
            for(DemandeMobilite dem : demandes) {
                out.print("<tr>");
                    out.print("<td>"+dem.getNomUniv()+"</td>");
                    out.print("<td>"+dem.getIntituleDiplome()+"</td>");
                    out.print("<td>"+dem.getDate_depot()+"</td>");
                    out.print("<td>"+dem.getEtat()+"</td>");
                out.print("</tr>");
            }
        %>
    </tbody>
</table>
<%@include file="footer.jsp" %>