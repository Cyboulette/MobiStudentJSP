<%@page import="java.util.Vector"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<h1>Liste des Demandes par Diplômes</h1>
<table class="table table-bordered table-hover">
    <thead>
        <th>Programme</th>
        <th>Explication</th>
        <th>Durée</th>
        <th>Etat</th>
        <th>Ordre</th>
    </thead>
    <tbody>
        <%
            Vector<Programme> programmes = (Vector<Programme>)request.getAttribute("programmes");
            for(Programme prog : programmes) {
                out.print("<tr>");
                    out.print("<td>"+prog.getIntitule()+"</td>");
                    out.print("<td>"+prog.getExplication()+"</td>");
                    out.print("<td>"+prog.getDuree()+"</td>");
                    out.print("<td>"+prog.getEtat()+"</td>");
                    out.print("<td>"+prog.getOrdre()+"</td>");
                out.print("</tr>");
            }
        %>
    </tbody>
</table>
<%@include file="footer.jsp" %>