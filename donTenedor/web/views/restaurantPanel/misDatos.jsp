<%-- 
    Document   : misDatos
    Created on : 03-may-2018, 19:14:50
    Author     : danieljimenez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="css/restaurantPanelStyle/misDatosStyle.css" rel="stylesheet" type="text/css"/>
<script src="js/validateForm/validateFormChangePassword.js" type="text/javascript"></script>
<section id="section-mis-datos">
    <h1>Mis Datos</h1>
    <form  method="POST" action="#" id="form-update-user" role="form">
        <article>
            <div>
                <label>Usuario</label>
                <input type="text" name="name" value="${sessionScope.usuario.name}" disabled="disabled"/>
            </div>
            <div>
                <label>Apellidos</label>
                <input type="text" name="subname" value="${sessionScope.usuario.subname}" disabled="disabled"/>
            </div>
            <div>
                <label>E-mail</label>
                <input type="text" name="text" value="${sessionScope.usuario.email}" disabled="disabled"/>
            </div>
        </article>

        <article>
            <div>
                <fmt:formatDate var="fecha" pattern = "dd-MM-yyyy" value ="${sessionScope.usuario.birthday}"/>
                <label>Fecha Nacimiento</label>
                <input type="text" name="date" value="${fecha}" disabled="disabled"/>
            </div>
            <div>
                <label>DNI</label>
                <input type="text" name="dni" value="${sessionScope.usuario.dni}" disabled="disabled"/>
            </div>
        </article>
    </form>

    <hr>
    <h1>Cambiar Contraseña</h1>
    <form id="form-change-password" method="POST" action="changePassword" role="form">
        
        <c:if test="${!empty sessionScope.error}">
            <c:choose>
                <c:when test="${sessionScope.error eq 'no'}">
                    <p id="error-change" class="change-password-successful">
                        La contraseña se modificó correctamente
                    </p>
                </c:when>
                <c:otherwise>
                    <p id="error-change" class="change-password-error">
                        Se produjo un error al modificar la contraseña
                    </p>
                </c:otherwise>
            </c:choose>
        </c:if>

        <article id="content-new-password">
            <input type="hidden" name="idUser" id="idUser" value="${sessionScope.usuario.idUser}"/>
            <div>
                <input type="password" name="passwordActual" id="passwordActual" placeholder="Contraseña Actual">
            </div>
            <div>
                <input type="password" name="newPassword" placeholder="Nueva Contraseña">
            </div>
            <div>
                <input type="password" name="rNewpassword" id="rNewPassword" placeholder="Repetir Contraseña">
            </div>
        </article>

        <article>
            <input type="submit" value="Cambiar Contraseña" class="boton-aceptar">
        </article>
        <hr>
    </form>
    <c:set var="error" value="${null}" scope="session"/>
