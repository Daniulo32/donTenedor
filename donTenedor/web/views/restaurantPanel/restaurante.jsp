<%-- 
    Document   : restaurante
    Created on : 03-may-2018, 19:15:25
    Author     : danieljimenez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="/restaurantSV"/>
<link href="css/restaurantPanelStyle/restaurantStyle.css" rel="stylesheet" type="text/css"/>
<script src="js/restaurantJS.js" type="text/javascript"></script>
<section id="section-restaurante">
    <h1>Datos Restaurante</h1>
    <form  method="POST" action="resgisterUpdateRestaurant" id="form-restaurant" role="form">
        <article>
            <div>
                <label>Nombre Restaurante</label>
                <input type="text" name="nameRestaurant" value="${sessionScope.restaurante.nameRestaurant}"/>
                <input type="hidden" name="idRestaurant" value="${sessionScope.restaurante.idRestaurant}"/>
            </div>
            <div>
                <c:set var="tipo" value="${sessionScope.restaurante.type}"/>
                <label>Tipo</label>
                <select name="type" id="type">
                    <option value="espanol" ${tipo eq 'espanol' ? 'selected':''}>Español</option>
                    <option value="tapas" ${tipo eq 'tapas' ? 'selected':''}>Bar Tapas</option>
                    <option value="chino" ${tipo eq 'chino' ? 'selected':''}>Chino</option>
                    <option value="italiano" ${tipo eq 'italiano' ? 'selected':''}>Italiano</option>
                    <option value="indio" ${tipo eq 'indio' ? 'selected':''}>Indio</option>
                    <option value="rapida" ${tipo eq 'rapida' ? 'selected':''}>Comida Rápida</option>
                    <option value="buffet" ${tipo eq 'buffet' ? 'selected':''}>Buffet</option>
                </select>
            </div>
        </article>

        <article>
            <div>
                <label>Provincia</label>
                <select name="idProvincia" id="provincia">
                    <c:forEach var="provincia" items="${requestScope.listaProvincias}">
                        <c:choose>
                            <c:when test="${provincia.idprovincia == sessionScope.restaurante.province.idprovincia}">
                                <option value="${provincia.idprovincia}" selected>${provincia.provincia}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${provincia.idprovincia}">${provincia.provincia}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label>Localidad</label>
                <input list="localidad" type="text" name="localidad" id="poblacion" value="${sessionScope.restaurante.town.poblacion}"/>
                <datalist id="localidad"></datalist>
                <input type="hidden" name="idPoblacion" id="idPoblacion" value=""/>
            </div>
            <div>
                <label>Precio Medio</label>
                <c:choose>
                    <c:when test="${sessionScope.restaurante.halfPrice == null}">
                        <input type="number" name="halfPrice" step="0.01" value="0.00"/>
                    </c:when>
                    <c:otherwise>
                        <fmt:formatNumber var="formatQuantity" value="${sessionScope.restaurante.halfPrice}" 
                                          minFractionDigits="2"/>
                        <c:set var="dateParts" value="${fn:split(formatQuantity, ',')}" />
                        <c:set var = "quantity" value = "${fn:join(dateParts, '.')}" />
                        <input type="number" name="halfPrice" value="${quantity}"/>
                    </c:otherwise>
                </c:choose>

            </div>
        </article>

        <article>
            <div>
                <label>Teléfono</label>
                <input type="text" name="telefono" value="${sessionScope.restaurante.phone}" maxlength="9"/>
            </div>
            <div>
                <label>Dirección</label>
                <input type="text" name="direccion" value="${sessionScope.restaurante.address}"/>
            </div>
            <div>
                <label>E-mail</label>
                <input type="email" name="email" value="${sessionScope.restaurante.email}"/>
            </div>
        </article>

        <article>
            <div id="diasApertura">
                <label>Dias Apertura</label>
                <div>
                    <span>L</span>
                    <input type="checkbox" name="daysOpen" value="L"/>
                </div>
                <div>
                    <span>M</span>
                    <input type="checkbox" name="daysOpen" value="M"/>
                </div>
                <div>
                    <span>X</span>
                    <input type="checkbox" name="daysOpen" value="X"/>
                </div>
                <div>
                    <span>J</span>
                    <input type="checkbox" name="daysOpen" value="J"/>
                </div>
                <div>
                    <span>V</span>
                    <input type="checkbox" name="daysOpen" value="V"/>
                </div>
                <div>
                    <span>S</span>
                    <input type="checkbox" name="daysOpen" value="S"/>
                </div>
                <div>
                    <span>D</span>
                    <input type="checkbox" name="daysOpen" value="D"/>
                </div>
            </div>
            <div id="diasAperturaMovil">
                <label>Dias Apertura</label>
                <div>
                    <span>L</span>
                    <div class="slideCheck">	
                        <input type="checkbox" name="diasApertura" value ="L" id="lunes"/>
                        <label for="lunes"></label>
                    </div>
                </div>
                <div>
                    <span>M</span>
                    <div class="slideCheck">	
                        <input type="checkbox" name="diasApertura" value ="M" id="martes"/>
                        <label for="martes"></label>
                    </div>
                </div>
                <div>
                    <span>X</span>
                    <div class="slideCheck">	
                        <input type="checkbox" name="diasApertura" value ="X" id="miercoles"/>
                        <label for="miercoles"></label>
                    </div>
                </div>
                <div>
                    <span>J</span>
                    <div class="slideCheck">	
                        <input type="checkbox" name="diasApertura" value ="J" id="jueves"/>
                        <label for="jueves"></label>
                    </div>
                </div>
                <div>
                    <span>V</span>
                    <div class="slideCheck">	
                        <input type="checkbox" name="diasApertura" value ="V" id="viernes"/>
                        <label for="viernes"></label>
                    </div>
                </div>
                <div>
                    <span>S</span>
                    <div class="slideCheck">	
                        <input type="checkbox" name="diasApertura" value ="S" id="sabado"/>
                        <label for="sabado"></label>
                    </div>
                </div>
                <div>
                    <span>D</span>
                    <div class="slideCheck">	
                        <input type="checkbox" name="diasApertura" value ="D" id="domingo"/>
                        <label for="domingo"></label>
                    </div>
                </div>
            </div>
            <div>
                <label>Hora Apertura</label>
                <c:choose>
                    <c:when test="${!empty sessionScope.restaurante.scheduleOpen}">
                        <fmt:formatDate var="hourOpen" pattern = "HH:mm" value = "${sessionScope.restaurante.scheduleOpen}" />
                        <input type="time" name="hourOpen" value="${hourOpen}"/>
                    </c:when>
                    <c:otherwise>
                        <input type="time" name="hourOpen"/>
                    </c:otherwise>
                </c:choose>
            </div>
            <div>
                <label>Hora Cierre</label>
                <c:choose>
                    <c:when test="${!empty sessionScope.restaurante.scheduleClose}">
                        <fmt:formatDate var="hourClose" pattern = "HH:mm" value = "${sessionScope.restaurante.scheduleClose}" />
                        <input type="time" name="hourClose" value="${hourClose}"/>
                    </c:when>
                    <c:otherwise>
                        <input type="time" name="hourClose" />
                    </c:otherwise>
                </c:choose>
            </div>
        </article>

        <article id="article-service">
            <h2>Servicios</h2>
            <div>
                <label id="text-type-register">Servicio a Domicilio</label>
                <div class="slideCheck">
                    <c:set var="homeService" value="${sessionScope.restaurante.homeService}"/>
                    <input type="checkbox" name="homeService" value ="1" id="homeService" ${homeService eq '1' ? 'checked':''}/>
                    <label for="homeService"></label>
                </div>
            </div>
            <div>
                <label id="text-type-register">Internet Wifi</label>
                <div class="slideCheck">
                    <c:set var="wifi" value="${sessionScope.restaurante.wifi}"/>
                    <input type="checkbox" name="wifi" value ="1" id="wifi" ${wifi eq '1' ? 'checked':''}/>
                    <label for="wifi"></label>
                </div>
            </div>
            <div>
                <label id="text-type-register">Servicio Terraza</label>
                <div class="slideCheck">
                    <c:set var="terrace" value="${sessionScope.restaurante.terrace}"/>
                    <input type="checkbox" name="terrace" value ="1" id="terrace" ${terrace eq '1' ? 'checked':''}/>
                    <label for="terrace"></label>
                </div>
            </div>
            <div>
                <label id="text-type-register">Pago con Tarjeta</label>
                <div class="slideCheck">
                    <c:set var="card" value="${sessionScope.restaurante.cardPayment}"/>
                    <input type="checkbox" name="tarjeta" value ="1" id="tarjeta" ${card eq '1' ? 'checked':''}/>
                    <label for="tarjeta"></label>
                </div>
            </div>
            <div>
                <label id="text-type-register">Adaptado Minusvalidos</label>
                <div class="slideCheck">
                    <c:set var="handicapped" value="${sessionScope.restaurante.handicapped}"/>
                    <input type="checkbox" name="minusvalidos" value ="1" id="minusvalidos" ${handicapped eq '1' ? 'checked':''}/>
                    <label for="minusvalidos"></label>
                </div>
            </div>
        </article>
        <article>
            <div id="content-textArea">
                <label>Observaciones</label>
                <textarea id="Observation" name="observation">${sessionScope.restaurante.observations}</textarea>
            </div>
        </article>

        <article>
            <input type="submit" value="Guardar Datos" id="botonSubmit" class="boton-aceptar"/>
        </article>

    </form>
    <c:set var="error" value="${null}" scope="session"/>


