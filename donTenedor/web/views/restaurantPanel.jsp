<%-- 
    Document   : restaurantPanel
    Created on : 03-may-2018, 17:42:19
    Author     : danieljimenez
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="css/restaurantPanelStyle.css" rel="stylesheet" type="text/css"/>
<script src="js/restaurantPanel.js" type="text/javascript"></script>
<input type="hidden" id="error" name="error" value="${requestScope.error}"/>
<div id="tab-menu-restaurant">
    <ul>
        <li data-view="misDatos">Mis datos</li>
        <li data-view="restaurante">Restaurante</li>
        <li data-view="fotosRestaurante">Fotos</li>
        <li data-view="ubicacionRestaurante">Ubicación</li>
        <li data-view="comentariosRestaurante">Comentarios</li>
    </ul>
    
    <div id="content-restaurant">
        <%--Se carga el contenido llamado por el menu--%>
    </div>
</div>
