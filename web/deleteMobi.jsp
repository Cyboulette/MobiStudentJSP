<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<% 
    DemandeMobilite mobilite = (DemandeMobilite) request.getAttribute("mobilite");
%>
<h1>Supprimer une demande de mobilité</h1>
<form class="form-group my-4 my-lg-0" method="POST" action="mobilites">
    <input class="form-control mr-sm-2" type="hidden" name="action" value="delete_mobilite">
    <input class="form-control mr-sm-2" type="hidden" name="idM" value="<%=mobilite.getId()%>">
    <p>Voulez vous vraiment supprimer cette demande de mobilité ?</p>
    <div class="form-group">
        <a href="mobilites?action=search" class="btn btn-outline-primary">Annuler</a>
        <button type="submit" class="btn btn-danger">Confirmer la suppression</button>
    </div>
</form>
<%@include file="footer.jsp" %>