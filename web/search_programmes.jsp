<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<h1>Demandes de mobilités</h1>
<form method="POST" action="MobilitesController">
    <div class="form-group">
        <label for="intitule">Rechercher par programme</label>
        <select name="intitule" id="intitule" class="form-control selectpicker" data-live-search="true">
            <option value="null" selected>Sélectionnez ...</option>
            <% Vector<Programme> programmes = (Vector<Programme>)request.getAttribute("programmes"); 
                for(Programme p : programmes) {
                    out.print("<option value='"+p.getId()+"'>"+p.getIntitule()+"</option>");
                }
            %>
            <input type="hidden" name="action" value="PROGRAMMES_INTITULES">
            <input type="submit">
        </select>
    </div>
</form>

<%@include file="footer.jsp" %>