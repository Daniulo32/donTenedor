<%-- 
    Document   : comentariosRestaurante
    Created on : 04-jun-2018, 18:50:43
    Author     : danieljimenez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/restaurantPanelStyle/commentRestaurant.css" rel="stylesheet" type="text/css"/>
<script src="js/reportComment.js" type="text/javascript"></script>
<jsp:include page="/updateSessionRestaurant"/>
<jsp:include page="/checkReportComment"/>
<c:set var="listComment" value="${sessionScope.restaurante.commentsList}"/>
<c:set var="listIncidences" value="${sessionScope.listIncidences}"/>

<section id="section-comment">
    <h1>Comentarios</h1>
    <article id="content-comment">
        <c:choose>
            <c:when test="${!empty listComment}">
                <c:forEach var="comment" items="${listComment}">
                    <div class="comment" data-idComment="${comment.idComment}">
                        <c:set var="user" value="${comment.idUser}"/>
                        <fmt:formatDate var="date" pattern="dd-M-yyyy" value="${comment.commentDate}"/>
                        <div class="info-comment">
                            <img src="images/icons/icon-user.png"/>
                            <label class="user-comment">${user.name} ${user.subname}</label>
                            <img src="images/icons/icon-calendar.png" class="icon-calendar"/>
                            <label class="date-comment">${date}</label>
                            <c:choose>
                                <c:when test="${fn:contains(listIncidences, comment)}">
                                    <label class="report">Reportado</label>
                                </c:when>
                                <c:otherwise>
                                    <a href="#" class="boton-report">Reportar</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <label class="body-comment">${comment.comment}</label>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <h2 id="nothing-comment"> No hay ningun comentario</h2>
            </c:otherwise>
        </c:choose>
    </article>
</section>