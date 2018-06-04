<%-- 
    Document   : comentariosRestaurante
    Created on : 31-may-2018, 20:23:16
    Author     : danieljimenez
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="dataRestaurant"/>
<link href="css/restaurantPanelStyle/commentRestaurant.css" rel="stylesheet" type="text/css"/>
<c:set var="restaurant" value="${requestScope.dataRestaurant}"/>
<section id="section-comment">
    <jsp:include page="/coreView/showMessage.jsp"/>
    <article id="send-comment">
        <form method="POST" action="sendComment" role="form">
            <label>Escriba aquí su comentarios</label>
            <textarea name="comment" id="comment" required maxlength="200"></textarea>
            <input type="hidden" value="${restaurant.idRestaurant}" name="idRestaurant"/>
            <input type="submit" class="boton-aceptar" value="Enviar"/>
        </form>
    </article>
    <article id="content-comment">
        <c:choose>
            <c:when test="${!empty restaurant.commentsList}">
                <c:forEach var="comment" items="${restaurant.commentsList}">
                    <div class="comment">
                        <c:set var="user" value="${comment.idUser}"/>
                        <fmt:formatDate var="date" pattern="dd-M-yyyy" value="${comment.commentDate}"/>
                        <div class="info-comment">
                            <img src="images/icons/icon-user.png"/>
                            <label class="user-comment">${user.name} ${user.subname}</label>
                            <img src="images/icons/icon-calendar.png" class="icon-calendar"/>
                            <label class="date-comment">${date}</label>
                        </div>
                        <label class="body-comment">${comment.comment}</label>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <label>Actualmente no existen comentarios</label>
            </c:otherwise>
        </c:choose>
    </article>
</section>