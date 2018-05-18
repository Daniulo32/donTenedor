<%-- 
    Document   : formSearch
    Created on : 26-abr-2018, 17:31:59
    Author     : danieljimenez
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/restaurantSV"/>
<script src="js/searchRestaurant.js" type="text/javascript"></script>
<div id="content-form-search">
    <form id="form-search" method="POST" action="index.jsp?view=resultSearchRestaurant&controller=searchRestaurant" >
        <div id="content-select-type">
            <img src="images/icons/icon-white-restaurant.png"/>
            <select name="type">
                <option value="any">Tipo restaurante</option>
                <option value="espanol">Español</option>
                <option value="tapas">Bar Tapas</option>
                <option value="chino">Chino</option>
                <option value="italiano">Italiano</option>
                <option value="indio">Indio</option>
                <option value="rapida">Comida Rápida</option>
                <option value="buffet">Buffet</option>
            </select>
        </div>

        <div id="content-input-province">
            <img src="images/icons/icon-white-town.png"/>
            <select name="idProvincia" id="provincia">
                <option value="0">Provincia</option>
                <c:forEach var="provincia" items="${requestScope.listaProvincias}">
                    <option value="${provincia.idprovincia}">${provincia.provincia}</option>
                </c:forEach>
            </select>
        </div>

        <div id="content-input-town">
            <img src="images/icons/icon-white-location.png"/>
            <input type="text" list="localidad" name="localidad" id="poblacion" disabled/>
            <datalist id="localidad"></datalist>
            <input type="hidden" name="idPoblacion" id="idPoblacion" value="0"/>
        </div>

        <input id="icon-search" type="image" src="images/icons/icon-white-search.png" alt="submit">
    </form>
</div>