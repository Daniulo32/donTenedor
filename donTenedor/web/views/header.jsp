<%-- 
    Document   : header
    Created on : 25-abr-2018, 17:27:51
    Author     : danieljimenez
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="lightBox">
    <div id="login">

        <a id="cerrarLogin" href="#">
            <img src="/donTenedor/images/icons/cancel.png"/>
        </a>

        <h1>Iniciar Sesión</h1>

        <div class="campos">
            <img  src="/donTenedor/images/icons/user.png" alt="User"/>
            <input type="text" name="usuario" id="usuario">
        </div>

        <div class="campos">
            <img  src="/donTenedor/images/icons/key.png" alt="Password"/>
            <input type="password" name="password" id="password">
        </div>

        <input type="button" value="Entrar" id="botonLogin" class="boton-login centrar-boton-login"/>

        <p id="linkRegistro">Para Registrase diríjase a <a href="index.jsp?view=register">éste formulario</a></p>

        <div class="textoErrores">
            <label id="textoError"></label>
        </div>

    </div>
</div>
<header>
    <a href="/donTenedor/index.jsp">
        <img src="/donTenedor/images/logo.png" id="logoImg">
    </a>

    <c:choose>
        <c:when test="${empty sessionScope.usuario}">
            <div id="content-icon-user">
                <a href="#" id="linkUser"><img src="images/icons/icon-user.png" id="icon-user"/></a>
            </div>
        </c:when>
        <c:when test="${sessionScope.usuario.role == 1}">
            <div id="content-icon-user">
                <a href="index.jsp?view=restaurantPanel" id="linkUser"><img src="images/icons/icon-user.png" id="icon-user"/></a>
            </div>
        </c:when>
        <c:when test="${sessionScope.usuario.role == 0}">
            <div id="content-icon-user">
                <a href="index.jsp?view=userPanel" id="linkUser"><img src="images/icons/icon-user.png" id="icon-user"/></a>
            </div>
        </c:when>
    </c:choose>

    <nav>
        <ul>
            <li><a href="#">Ver Rutas</a></li>
            <li><a href="#">Contactar</a></li>
                <c:if test="${!empty sessionScope.usuario}">
                <li><a href="logOut">Cerrar Sesion</a></li>
                </c:if>
        </ul>
    </nav>
    <div class="menu_bar">
        <a href="#"  id="boton-menu">
            <img src="/donTenedor/images/icons/icon-menu.png" class="menu_icon"/>   
        </a>
    </div>
</header>