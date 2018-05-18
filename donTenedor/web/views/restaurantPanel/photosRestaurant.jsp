<%-- 
    Document   : photosRestaurant
    Created on : 10-may-2018, 17:30:46
    Author     : danieljimenez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/restaurantPanelStyle/photoRestaurant.css" rel="stylesheet" type="text/css"/>
<script src="js/validateForm/validateFormPhoto.js" type="text/javascript"></script>
<script src="js/photoRestaurant.js" type="text/javascript"></script>
<c:set var="restau" value="${sessionScope.restaurante}"/>
<section id="photo-restaurant">
    <article>
        <h1>Fotos</h1>
        <form id="form-photo-restaurant" action="uploadFilePhoto" method="POST" enctype="multipart/form-data" role="form">
            <c:if test="${empty sessionScope.restaurante}">
                <label class="message-error">Debes indicar los datos del restaurante para subir fotos</label>
            </c:if>
            <input type="file" name="file" id="file" multiple="multiple"/>
            <label for="file" id="buttonUpload">Elegir Archivos</label>
            <div id="filesUpload">
                <label id="labelUpload">Archivos para subir</label>
                <input type="submit" id="guardar" value="Guardar" class="boton-aceptar"
                       ${empty sessionScope.restaurante ? "disabled":""}/>
            </div>
        </form>
    </article>
    <hr>
    <article id="listPhoto">
        <h2>Lista de fotos</h2>
        <div id="container-photo">
            <c:choose>
                <c:when test="${empty restau.photoRestaurantList}">
                    <label class="nothingPhotos">Actualmente el restaurante no tiene fotos</label>
                </c:when>
                <c:otherwise>
                    <c:forEach var="foto" items="${restau.photoRestaurantList}">
                        <div class="photoStyle">
                            <span class="delete-icon" data-delete="${foto.photoRestaurantPK.namePhoto}"></span>
                            <c:choose>
                                <c:when test="${foto.principalPhoto == 1}">
                                    <span class="favorite-icon-color" data-favorite="${foto.photoRestaurantPK.namePhoto}"></span>
                                </c:when>
                                <c:otherwise>
                                    <span class="favorite-icon" data-favorite="${foto.photoRestaurantPK.namePhoto}"></span>
                                </c:otherwise>
                            </c:choose>
                            <img src="images/photoRestaurant/${restau.idAdmin.name}${restau.idAdmin.subname}-${restau.idAdmin.idUser}/${foto.photoRestaurantPK.namePhoto}"/>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </article>
    <hr>
</section>
