<%-- 
    Document   : viewOneRestaurant
    Created on : 21-may-2018, 22:05:41
    Author     : danieljimenez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/viewOneRestaurant.css" rel="stylesheet" type="text/css"/>
<link href="css/restaurantPanelStyle.css" rel="stylesheet" type="text/css"/>
<script src="js/viewOneRestaurant.js" type="text/javascript"></script>
<c:set var="restaurant" value="${requestScope.restaurantView}"/>

<c:set var="photo" value=""/>
<c:forEach var="foto" items="${restaurant.photoRestaurantList}">
    <c:if test="${foto.principalPhoto == 1}">
        <c:set var="photo" value="${foto.photoRestaurantPK.namePhoto}"/>
    </c:if>
</c:forEach>
<aside>
    <jsp:include page="formSearch.jsp"/>
</aside>
<section id="section-one-restaurant">
    <input type="hidden" name="idRestaurant" id="idRestaurant" value="${restaurant.idRestaurant}"/>
    <article>
        <h2>${restaurant.nameRestaurant}</h2>
        <c:choose>
            <c:when test="${!empty photo}">
                <img src="images/photoRestaurant/${restaurant.idAdmin.name}${restaurant.idAdmin.subname}-${restaurant.idAdmin.idUser}/${photo}"/>
            </c:when>
            <c:otherwise>
                <img src="images/no_foto.jpg">
            </c:otherwise>
        </c:choose>
        <div id="info-restaurant">
            <label>C/ ${restaurant.address} ${restaurant.town.poblacion} (${restaurant.province.provincia})</label>
            <label id="mail">
                <img src="images/icons/icon-mail.png" class="icon-info"/>
                <span>${restaurant.email}</span>
            </label>
            <label id="tel">
                <img src="images/icons/icon-phone.png" class="icon-info"/>
                <span>${restaurant.phone}</span>
            </label>
            <label id="price">
                <img src="images/icons/icon-coins.png" class="icon-info"/>
                <fmt:formatNumber var="price" type ="currency" maxIntegerDigits = "2" value = "${restaurant.halfPrice}" />
                <span>Precio medio ${price} / Persona</span>
            </label>
            <div id="listService">
                <c:forEach var="service" items="${requestScope.listService}">
                    <c:if test="${service.value == 1}">
                        <label>${service.key}</label>
                    </c:if>
                </c:forEach>
            </div>
            <label id="valore">
                <img src="images/icons/icon-color-star.png" class="icon-valore"/>
                <c:set var="allVote" value="0"/>
                <c:forEach var="vote" items="${restaurant.votingList}">
                    <c:set var="allVote" value="${vote.vote + allVote}"/>
                </c:forEach>
                <fmt:formatNumber var="valoring"
                                  value="${(allVote*2)/fn:length(restaurant.votingList)}"
                                  maxFractionDigits="0" />
                <c:if test="${allVote == 0}">
                    <c:set var="valoring" value="0"/>
                </c:if>
                <span>${valoring}/10</span>
            </label>
        </div>
    </article>
    <article id="tab-menu">
        <div id="tab-menu-restaurant">
            <ul>
                <li data-view="mapRestaurant" id="positionRestaurant">Ubicación</li>
                <li data-view="photosRestaurant" id="photosRestaurant">Fotos</li>
                <li data-view="comentariosRestaurante" id="comentariosRestaurante">Comentarios</li>
                <li data-view="observationRestaurant" id="observationRestaurant">Observaciones</li>
                <li data-view="reserveRestaurant" id="reserveRestaurant">Reservar</li>
            </ul>

            <div id="content-restaurant">
                <%--Se carga el contenido llamado por el menu--%>
            </div>
    </article>
    <hr>
</section>
