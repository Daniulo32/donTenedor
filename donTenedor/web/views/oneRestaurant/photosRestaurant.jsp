<%-- 
    Document   : photosRestaurant
    Created on : 22-may-2018, 20:17:05
    Author     : danieljimenez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="css/oneRestaurant/photosRestaurant.css" rel="stylesheet" type="text/css"/>
<script src="js/carrouselPhotoRestaurant.js" type="text/javascript"></script>
<jsp:include page="dataRestaurant"/>
<c:set var="restaurant" value="${requestScope.dataRestaurant}"/>
<section id="photos-restaurant">

    <c:choose>
        <c:when test="${!empty restaurant.photoRestaurantList}">
            <article id="carousel">
                <ul class="flip-items">
                    <c:forEach var="imagen" items="${restaurant.photoRestaurantList}">
                        <li>
                            <img class="imgCarrousel" src="images/photoRestaurant/${restaurant.idAdmin.name}${restaurant.idAdmin.subname}-${restaurant.idAdmin.idUser}/${imagen.photoRestaurantPK.namePhoto}"/>
                        </li>
                    </c:forEach>
                </ul>
            </article>
        </c:when>
        <c:otherwise>
            <img src="images/fotos_no_disponibles.jpg" class="nothingPhotos"/>
        </c:otherwise>
    </c:choose>

</section>
