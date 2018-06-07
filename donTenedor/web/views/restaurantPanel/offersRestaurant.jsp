<%-- 
    Document   : offersRestaurant
    Created on : 05-jun-2018, 18:07:25
    Author     : danieljimenez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/restaurantPanelStyle/offersRestaurant.css" rel="stylesheet" type="text/css"/>
<script src="js/validateForm/validateFormOfferts.js" type="text/javascript"></script>
<script src="js/offersRestaurant.js" type="text/javascript"></script>

<jsp:include page="/updateSessionRestaurant"/>
<c:set var="offertsList" value="${sessionScope.restaurante.offersList}"/>

<section id="section-offerts">
    <h1 class="h1Grey">Ofertas</h1>
    <jsp:include page="/coreView/showMessage.jsp"/>
    <article id="article-form-offerts">
        <form method="POST" action="registerOffert" role="form" id="form-offert">
            <div>
                <label>Fecha Inicio</label>
                <input type="date" name="dateStart" id="dateStart">
            </div>
            <div>
                <label>Fecha Finalización</label>
                <input type="date" name="dateEnd" id="dateEnd">
            </div>
            <div>
                <label>Dto%</label>
                <input type="number" name="percentage" id="percentage"/>
            </div>
            <input type="submit" class="boton-aceptar" value="Aceptar"/>
        </form>
    </article>
    <h2>Ofertas Actuales</h2>
    <table class="table-offert">
        <tr>
            <th>Fecha Inicio</th>
            <th>Fecha Finalización</th>
            <th>Porcentaje</th>
            <th></th>
        </tr>
        <c:choose>
            <c:when test="${!empty offertsList}">
                <c:forEach var="offer" items="${offertsList}">
                    <tr data-idOffer="${offer.idOffer}">
                        <fmt:formatDate var="fechaInicio" pattern = "dd-MM-yyyy" value ="${offer.startDate}"/>
                        <td>${fechaInicio}</td>
                        <fmt:formatDate var="fechaFinal" pattern = "dd-MM-yyyy" value ="${offer.endDate}"/>
                        <td>${fechaFinal}</td>
                        <td>${offer.percentage}%</td>
                        <td>
                            <a href="#" class="boton-report">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="4" class="nothing-offert">
                        <h1>No hay ofertas actualmente</h1>
                    </td>
                </tr>
            </c:otherwise>
        </c:choose>
    </table>
    <hr>
</section>