<%-- 
    Document   : restaurante
    Created on : 03-may-2018, 19:15:25
    Author     : danieljimenez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="css/restaurantPanelStyle/misDatosStyle.css" rel="stylesheet" type="text/css"/>
<script src="js/validateForm/validateFormChangePassword.js" type="text/javascript"></script>

<section id="section-mis-datos">
    <h1>Datos Restaurante</h1>
    <form  method="POST" action="#" id="form-restaurant" role="form">
        <article>
            <div>
                <label>Nombre Restaurante</label>
                <input type="text" name="name" value="${sessionScope.restaurante.name}" />
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
    <c:set var="error" value="${null}" scope="session"/>


