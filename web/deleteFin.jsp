<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<% 
    DemandeFinanciere financiere = (DemandeFinanciere) request.getAttribute("financiere");
%>
<h1>Supprimer une demande financière</h1>
<form class="form-group my-4 my-lg-0" method="POST" action="financieres">
    <input class="form-control mr-sm-2" type="hidden" name="action" value="delete_financiere">
    <input class="form-control mr-sm-2" type="hidden" name="idF" value="<%=financiere.getId()%>">
    <p>Voulez vous vraiment supprimer cette demande financière ?</p>
    <div class="form-group">
        <a href="financieres?action=search" class="btn btn-outline-primary">Annuler</a>
        <button type="submit" class="btn btn-danger">Confirmer la suppression</button>
    </div>
</form>
<%@include file="footer.jsp" %>