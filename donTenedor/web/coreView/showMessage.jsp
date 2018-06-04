<%-- 
    Document   : showMessage
    Created on : 28-may-2018, 18:46:43
    Author     : danieljimenez
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${!empty sessionScope.error}">
    <p id="error-change" class="message-error">
        ${sessionScope.error}
    </p>
</c:if>
<c:if test="${!empty sessionScope.message}">
    <p id="error-change" class="message-successful">
        ${sessionScope.message}
    </p>
</c:if>

<c:set var="error" value="${null}" scope="session"/>
<c:set var="message" value="${null}" scope="session"/>