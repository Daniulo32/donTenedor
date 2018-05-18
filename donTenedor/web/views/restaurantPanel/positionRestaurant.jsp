<%-- 
    Document   : positionRestaurant
    Created on : 14-may-2018, 21:20:27
    Author     : danieljimenez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="js/positionRestaurant.js" type="text/javascript"></script>
<link href="css/restaurantPanelStyle/positionRestaurant.css" rel="stylesheet" type="text/css"/>
<section id="section-position">
    <h1>Ubicación del Restaurante</h1>
    <div id="container-message">
        <c:if test="${empty sessionScope.restaurante}">
            <label class="message-error">Debes indicar los datos del restaurante para indicar su ubicación</label>
        </c:if>
    </div>
    <div>
        <input id="pac-input" type="text" placeholder="Ciudad, Calle, Plaza...">
        <input type="button" value="Guardar Posicion" id="savePosition" class="boton-aceptar"
               ${empty sessionScope.restaurante ? "disabled":""}/>
    </div>
               <input type="hidden" name="longitude" id="longitude" value="${sessionScope.restaurante.longitude}"/>
               <input type="hidden" name="latitude" id="latitude" value="${sessionScope.restaurante.latitude}"/>
    <div id="map"></div>
</section>