<%-- 
    Document   : mapaRestaurant
    Created on : 22-may-2018, 19:22:15
    Author     : danieljimenez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="css/oneRestaurant/mapRestaurant.css" rel="stylesheet" type="text/css"/>
<script src="js/getMapRestaurant.js" type="text/javascript"></script>
<jsp:include page="dataRestaurant"/>
<c:set var="restaurant" value="${requestScope.dataRestaurant}"/>
<div id="mapRestaurant">
    <input type="hidden" name="longitude" id="longitude" value="${restaurant.longitude}"/>
    <input type="hidden" name="latitude" id="latitude" value="${restaurant.latitude}"/>
    <div id="map"></div>
</div>