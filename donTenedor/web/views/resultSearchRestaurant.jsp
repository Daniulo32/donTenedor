<%-- 
    Document   : resultSearchRestaurant
    Created on : 16-may-2018, 18:54:27
    Author     : danieljimenez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/resultSearchRestaurant.css" rel="stylesheet" type="text/css"/>
<script src="js/resultSearchRestaurant.js" type="text/javascript"></script>
<!DOCTYPE html>
<aside id="aside-filters">
    <div id="filters">
        <h2>Filtros</h2>
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
        <div>
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
                    <c:forEach var="foto" items="${restaurante.photoRestaurantList}">
                        <c:if test="${foto.principalPhoto == 1}">
                            <c:set var="photo" value="${foto.photoRestaurantPK.namePhoto}"/>
                        </c:if>
                    </c:forEach>
                    <div class="showRestaurant">
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
                                    <span>Precio medio ${price} / Persona</span>
                                </label>
                                <a href="#">Ver más</a>
                            </div>
                            <div class="voting"><label>5/10</label></div>
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