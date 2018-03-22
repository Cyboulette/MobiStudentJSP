<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<h1>Liste des universités</h1>
<table class="table table-bordered table-hover">
    <thead>
        <th>Nom</th>
        <th>Adresse postale</th>
        <th>Site web</th>
        <th>Contact</th>
        <th>Programmes</th>
    </thead>
    <tbody>
        <%
            Vector<Universite> universites = (Vector<Universite>)session.getAttribute("universites");
            for(Universite universite : universites) {
                out.print("<tr>");
                    out.print("<td>"+universite.getNom()+"</td>");
                    out.print("<td>"+universite.getAdressePostale()+"</td>");
                    out.print("<td>"+universite.getAdresseWeb()+"</td>");
                    out.print("<td>"+universite.getAdresseMail()+"</td>");
                    out.print("<td>"+universite.getProgrammes().size()+"</td>");
                out.print("</tr>");
            }
        %>
    </tbody>
</table>
<%@include file="footer.jsp" %>