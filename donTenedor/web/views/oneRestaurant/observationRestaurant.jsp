<%-- 
    Document   : observationRestaurant
    Created on : 23-may-2018, 17:16:51
    Author     : danieljimenez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="css/oneRestaurant/observationRestaurant.css" rel="stylesheet" type="text/css"/>
<jsp:include page="dataRestaurant"/>
<c:set var="restaurant" value="${requestScope.dataRestaurant}"/>
<section id="section-observation">
    <c:choose>
        <c:when test="${!empty restaurant.observations}">
            <article id="data-observation">
                ${restaurant.observations}
            </article>
        </c:when>
        <c:otherwise>
            <h1 class="nothing-observation">Actualmente no hay ninguna observación</h1>
        </c:otherwise>
    </c:choose>
</section>