<%-- 
    Document   : routeTapas
    Created on : 07-jun-2018, 22:07:50
    Author     : danieljimenez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/routeTapas/routeTapas.css" rel="stylesheet" type="text/css"/>
<script src="js/routeTapas.js" type="text/javascript"></script>
<section id="section-route-tapas">
    <h1>Rutas de tapas</h1>
    <c:choose>
        <c:when test="${empty sessionScope.usuario}">
            <jsp:include page="routeTapas/notUserSession.jsp"/>
        </c:when>
        <c:when test="${!empty param.notLocation}">
            <jsp:include page="routeTapas/notLocation.jsp"/>
        </c:when>
        <c:otherwise>
            <jsp:include page="routeTapas/resultRoute.jsp"/>
        </c:otherwise>
    </c:choose>
</section>