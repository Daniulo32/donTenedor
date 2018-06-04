<%-- 
    Document   : userPanel
    Created on : 24-may-2018, 18:14:39
    Author     : danieljimenez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="css/userPanelStyle.css" rel="stylesheet" type="text/css"/>
<script src="js/userPanel.js" type="text/javascript"></script>
<input type="hidden" id="error" name="error" value="${requestScope.error}"/>
<div id="tab-user-panel" data-content="${sessionScope.content}">
    <ul>
        <li data-view="misDatos" id="misDatos">Mis datos</li>
        <li data-view="misReservas" id="misReservas">Mis Reservas</li>
        <li data-view="misComentarios" id="misComentarios">Mis Comentarios</li>
        <li data-view="incidencias" id="incidencias">Reportar Incidencia</li>
    </ul>
    
    <div id="content-user">
        <%--Se carga el contenido llamado por el menu--%>
    </div>
</div>
