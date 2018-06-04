<%-- 
    Document   : reservesRestaurant
    Created on : 31-may-2018, 17:36:37
    Author     : danieljimenez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="restaurant" value="${sessionScope.restaurante}"/>
<link href="css/restaurantPanelStyle/reservesRestaurant.css" rel="stylesheet" type="text/css"/>
<script src="js/reservesRestaurant.js" type="text/javascript"></script>
<jsp:include page="/updateSessionRestaurant"/>
<section id="section-reserve-restaurant">
    <h1>Reservas</h1>
    <article id="content-reservations">
        <table>
            <tr>
                <th>Nombre Usuario</th>
                <th>Fecha reserva</th>
                <th>Hora Reserva</th>
                <th>Estado</th>
            </tr>
            <c:choose>
                <c:when test="${!empty restaurant.reservationsList}">
                    <c:forEach var="reserve" items="${restaurant.reservationsList}">
                        <tr class="one-reserve" data-idReserve="${reserve.idReservation}">
                            <td>${reserve.idUser.name} ${reserve.idUser.subname}</td>
                            <fmt:formatDate var="fecha" pattern = "dd-MM-yyyy" 
                                            value ="${reserve.reservationDate}"/>
                            <td>${fecha}</td>
                            <fmt:formatDate var="hora" pattern = "HH:mm" 
                                            value ="${reserve.hour}"/>
                            <td>${hora}</td>
                            <td>
                                <select name="status" class="${reserve.status eq "Sin Confirmar"? 'sinConfirmar':reserve.status eq "Confirmado"? 'confirmado':'rechazado'}">
                                    <option class="sinConfirmar" value="Sin Confirmar" 
                                            ${reserve.status eq "Sin Confirmar"? 'selected':''}>Sin Confirmar
                                    </option>
                                    <option class="confirmado" value="Confirmado" 
                                            ${reserve.status eq "Confirmado"? 'selected':''}>Confirmado
                                    </option>
                                    <option class="rechazado" value="Rechazado" 
                                            ${reserve.status eq "Rechazado"? 'selected':''}>Rechazado
                                    </option>
                                </select>
                            </td>
                        </tr>
                    </c:forEach>    
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="5" class="nothing-reserve">Actualmente no hay reservas</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </table>
        <hr>
</section>
