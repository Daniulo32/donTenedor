<%-- 
    Document   : index
    Created on : 25-abr-2018, 17:24:39
    Author     : danieljimenez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="controller"  value="${param.controller}"/>
<c:set var="view"  value="${param.view}"/>

<c:if test="${!empty controller}">
    <jsp:include page="${controller}"/>
</c:if>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link href="css/footerStyle.css" rel="stylesheet" type="text/css"/>
        <link href="css/formSearchStyle.css" rel="stylesheet" type="text/css"/>
        <link href="css/generalStyle.css" rel="stylesheet" type="text/css"/>
        <link href="css/headerStyle.css" rel="stylesheet" type="text/css"/>
        <link href="css/indexStyle.css" rel="stylesheet" type="text/css"/>
        <link href="tooltipster/dist/css/tooltipster.bundle.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/libraries/jquery-3.3.1.js" type="text/javascript"></script>
        <script src="js/libraries/jquery.validate.min.js" type="text/javascript"></script>
        <script src="js/headerJS.js" type="text/javascript"></script>
        <script src="tooltipster/dist/js/tooltipster.bundle.min.js" type="text/javascript"></script>
        <link href="tooltipster/dist/css/plugins/tooltipster/sideTip/themes/tooltipster-sideTip-punk.min.css" rel="stylesheet" type="text/css"/>
        <title>Don Tenedor, tu eliges, tu disfrutas...</title>
    </head>
    <body>
        <jsp:include page="views/header.jsp"/>


                <c:choose>
                    <c:when test="${empty view}">
                        <jsp:include page="views/principal.jsp"/>
                    </c:when>
                    <c:otherwise>
                        <jsp:include page="views/${view}.jsp"/>
                    </c:otherwise>
                </c:choose>

        <jsp:include page="views/footer.jsp"/>
    </body>
</html>
