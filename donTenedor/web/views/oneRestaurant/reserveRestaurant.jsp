<%-- 
    Document   : reserveRestaurant
    Created on : 23-may-2018, 18:17:24
    Author     : danieljimenez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="js/reservationRestaurant.js" type="text/javascript"></script>
<link href="css/oneRestaurant/reservationRestaurant.css" rel="stylesheet" type="text/css"/>
<script src="js/validateForm/validateFormReserve.js" type="text/javascript"></script>
<jsp:include page="dataRestaurant"/>
<c:set var="restaurant" value="${requestScope.dataRestaurant}"/>
<section id="section-reserve">
    <h2>Formulario de Reserva</h2>
    <jsp:include page="/coreView/showMessage.jsp"/>
    <form id="form-reserve" action="makeReserver" method="POST" role="form">
        <input type="date" name="dateReserve" id="dateReserve"/>
        <select name="hourReservation" id="hourReservation">
            <option value="hour">Horas</option>
        </select>
        <input type="number" name="nPeople" id="nPeople"/>
        <input type="submit" value="Reservar" id="reserve"/>
        <input type="hidden" name="idRestaurant" value="${restaurant.idRestaurant}"/>
    </form>
</section>

