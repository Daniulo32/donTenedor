<%-- 
    Document   : resultSearchRestaurant
    Created on : 16-may-2018, 18:54:27
    Author     : danieljimenez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/resultSearchRestaurant.css" rel="stylesheet" type="text/css"/>
<script src="js/resultSearchRestaurant.js?1" type="text/javascript"></script>
<!DOCTYPE html>
<aside>
    <jsp:include page="formSearch.jsp"/>
</aside>
<aside id="aside-filters">
    <div id="filters">
        <h2>Filtros</h2>
        <select name="type" id="typeRestaurant">
            <option value="any">Tipo restaurante</option>
            <option value="espanol">Español</option>
            <option value="tapas">Bar Tapas</option>
            <option value="chino">Chino</option>
            <option value="italiano">Italiano</option>
            <option value="indio">Indio</option>
            <option value="rapida">Comida Rápida</option>
            <option value="buffet">Buffet</option>
        </select>
        <div class="filter">
            <span>Precio más bajo</span>
            <div class="slideCheck">	
                <input type="checkbox" id="precioBajo" value ="1"/>
                <label for="precioBajo"></label>
            </div>
        </div>
        <div>
            <span>Wifi</span>
            <div class="slideCheck">	
                <input type="checkbox" id="wifi" value ="1"/>
                <label for="wifi"></label>
            </div>
        </div>
        <div>
            <span>Terraza</span>
            <div class="slideCheck">	
                <input type="checkbox" id="terraza" value ="1"/>
                <label for="terraza"></label>
            </div>
        </div>
        <div>
            <span>Adaptado minusválidos</span>
            <div class="slideCheck">	
                <input type="checkbox" id="minusvalidos" value ="1"/>
                <label for="minusvalidos"></label>
            </div>
        </div>
        <div>
            <span>Pago con tarjeta</span>
            <div class="slideCheck">	
                <input type="checkbox" id="tarjeta" value ="1"/>
                <label for="tarjeta"></label>
            </div>
        </div>
        <div>
            <span>Servicio a domicilio</span>
            <div class="slideCheck">	
                <input type="checkbox" id="domicilio" value ="1"/>
                <label for="domicilio"></label>
            </div>
        </div>
    </div>
</aside>
<section id="setion-search-restaurant">
    <h1 class="h1-search">Busqueda Restaurantes</h1>
    <c:choose>
        <c:when test="${!empty requestScope.listarestaurantes}">
            <article id="container-restaurant">
                <c:forEach var="restaurante" items="${requestScope.listarestaurantes}">
                    <c:set var="photo" value=""/>
                    <c:forEach var="foto" items="${restaurante.photoRestaurantList}">
                        <c:if test="${foto.principalPhoto == 1}">
                            <c:set var="photo" value="${foto.photoRestaurantPK.namePhoto}"/>
                        </c:if>
                    </c:forEach>
                    <div class="showRestaurant"
                         data-wifi="${restaurante.wifi}"
                         data-terrace="${restaurante.terrace}"
                         data-type="${restaurante.type}"
                         data-paycard="${restaurante.cardPayment}"
                         data-handicapped="${restaurante.handicapped}"
                         data-homeService="${restaurante.homeService}"
                         data-price="${restaurante.halfPrice}"
                         data-id="${restaurante.idRestaurant}"
                         >
                        <div>
                            <c:choose>
                                <c:when test="${!empty photo}">
                                    <img src="images/photoRestaurant/${restaurante.idAdmin.name}${restaurante.idAdmin.subname}-${restaurante.idAdmin.idUser}/${photo}"/>
                                </c:when>
                                <c:otherwise>
                                    <img src="images/no_foto.jpg">
                                </c:otherwise>
                            </c:choose>
                            <div class="dataRestaurant">
                                <h2>${restaurante.nameRestaurant}</h2>
                                <label>
                                    <img src="images/icons/icon-address.png" class="icon-info">
                                    <span>${restaurante.address}</span>
                                </label>
                                <label>
                                    <img src="images/icons/icon-restaurant.png" class="icon-info">
                                    <span>Restaurante ${applicationScope.nameType[restaurante.type]}</span>
                                </label>
                                <label>
                                    <img src="images/icons/icon-coins.png" class="icon-info">
                                    <fmt:formatNumber var="price" type ="currency" maxIntegerDigits = "2" value = "${restaurante.halfPrice}" />
                                    <span>Precio medio ${price} / Pers.</span>
                                </label>
                                <a id="seeMore" href="index.jsp?view=viewOneRestaurant&controller=getOneRestaurant&idRestaurant=${restaurante.idRestaurant}">Ver más</a>
                            </div>
                            <div class="voting">
                                <c:set var="allVote" value="0"/>
                                <c:forEach var="vote" items="${restaurante.votingList}">
                                    <c:set var="allVote" value="${vote.vote + allVote}"/>
                                </c:forEach>
                                <fmt:formatNumber var="valoring"
                                                  value="${(allVote*2)/fn:length(restaurante.votingList)}"
                                                  maxFractionDigits="0" />
                                <c:if test="${allVote == 0}">
                                    <c:set var="valoring" value="0"/>
                                </c:if>
                                <label>${valoring}/10</label>
                            </div>
                        </div>
                        <br>
                    </div>
                </c:forEach>
            </article>
        </c:when>
        <c:otherwise>
            <article>
                <img src="images/img_face.png" id="unlucky"/>
                <div id="container-h1-h2">
                    <h1 class="oops-restaurant">Ooooooooops!!</h1>
                    <h2 class="nothing-restaurant">No se ha encontrado ningún restaurante</h2>
                </div>
            </article>
        </c:otherwise>
    </c:choose>
    <hr>
</section>