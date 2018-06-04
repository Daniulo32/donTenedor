<%-- 
    Document   : misReservas
    Created on : 28-may-2018, 19:21:43
    Author     : danieljimenez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/userPanel/misReservas.css" rel="stylesheet" type="text/css"/>
<script src="js/misReservas.js" type="text/javascript"></script>
<!DOCTYPE html>
<jsp:include page="/updateSessionUser"/>
<c:set var="user" value="${sessionScope.usuario}"/>

<section id="section-misReservas">
    <h1>Mis Reservas</h1>
    <jsp:include page="/coreView/showMessage.jsp"/>
    <article id="content-reservations">
        <table>
            <tr>
                <th>Nombre Restaurante</th>
                <th>Fecha reserva</th>
                <th>Hora Reserva</th>
                <th>Estado</th>
                <th></th>
            </tr>
            <c:choose>
                <c:when test="${!empty user.reservationsList}">
                    <c:forEach var="reserve" items="${user.reservationsList}">
                        <tr class="one-reserve" data-idReserve="${reserve.idReservation}">
                            <td>${reserve.idRestaurant.nameRestaurant}</td>
                            <fmt:formatDate var="fecha" pattern = "dd-MM-yyyy" 
                                            value ="${reserve.reservationDate}"/>
                            <td>${fecha}</td>
                            <fmt:formatDate var="hora" pattern = "HH:mm" 
                                            value ="${reserve.hour}"/>
                            <td>${hora}</td>
                            <td class="${reserve.status eq 'Sin Confirmar' ? 'sinConfirmar':reserve.status eq 'Confirmado' ? 'confirmado':'rechazado'}">
                                ${reserve.status}</td>
                                <c:choose>
                                    <c:when test="${sessionScope.listDatesReservation[reserve.idReservation] == true}">
                                    <td>
                                        <p class="clasificacion" data-idReserve="${reserve.idReservation}">
                                            <input id="radio1${reserve.idReservation}" type="radio" name="estrellas" value="5">
                                            <label for="radio1${reserve.idReservation}">★</label>
                                            <input id="radio2${reserve.idReservation}" type="radio" name="estrellas" value="4">
                                            <label for="radio2${reserve.idReservation}">★</label>
                                            <input id="radio3${reserve.idReservation}" type="radio" name="estrellas" value="3">
                                            <label for="radio3${reserve.idReservation}">★</label>
                                            <input id="radio4${reserve.idReservation}" type="radio" name="estrellas" value="2">
                                            <label for="radio4${reserve.idReservation}">★</label>
                                            <input id="radio5${reserve.idReservation}" type="radio" name="estrellas" value="1">
                                            <label for="radio5${reserve.idReservation}">★</label>
                                        </p>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                        <a href="#" id="cancel" class="cancel">Cancelar</a>
                                    </td>
                                </c:otherwise>
                            </c:choose>
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
    </article>
    <hr>
</section>