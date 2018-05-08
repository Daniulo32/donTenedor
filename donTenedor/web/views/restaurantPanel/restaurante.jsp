<%-- 
    Document   : restaurante
    Created on : 03-may-2018, 19:15:25
    Author     : danieljimenez
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/restaurantSV"/>
<link href="css/restaurantPanelStyle/restaurantStyle.css" rel="stylesheet" type="text/css"/>
<script src="js/restaurantJS.js" type="text/javascript"></script>
<section id="section-restaurante">
    <h1>Datos Restaurante</h1>
    <form  method="POST" action="resgisterUpdateRestaurant" id="form-restaurant" role="form">
        <article>
            <div>
                <label>Nombre Restaurante</label>
                <input type="text" name="nameRestaurant" value="${sessionScope.restaurante.name}"/>
                <input type="hidden" name="idRestaurant" value="${sessionScope.restaurante.idRestaurant}"/>
            </div>
            <div>
                <label>Tipo</label>
                <select name="type" id="type">
                    <option value="espanol">Español</option>
                    <option value="tapas">Bar Tapas</option>
                    <option value="chino">Chino</option>
                    <option value="italiano">Italiano</option>
                    <option value="indio">Indio</option>
                    <option value="rapida">Comida Rápida</option>
                    <option value="buffet">Buffet</option>
                </select>
            </div>
        </article>

        <article>
            <div>
                <label>Provincia</label>
                <select name="idProvincia" id="provincia">
                    <c:forEach var="provincia" items="${requestScope.listaProvincias}">
                        <option value="${provincia.idprovincia}">${provincia.provincia}</option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <label>Localidad</label>
                <input list="localidad" type="text" name="localidad" id="poblacion" value="${sessionScope.restaurante.town}"/>
                <datalist id="localidad"></datalist>
                <input type="hidden" name="idPoblacion" id="idPoblacion" value=""/>
            </div>
            <div>
                <label>Precio Medio</label>
                <input type="number" name="halfPrice" step="0.01" value="0.00"/>
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
                <input type="time" name="hourOpen" />
            </div>
            <div>
                <label>Hora Cierre</label>
                <input type="time" name="hourClose" />
            </div>
        </article>

        <article id="article-service">
            <h2>Servicios</h2>
            <div>
                <label id="text-type-register">Servicio a Domicilio</label>
                <div class="slideCheck">	
                    <input type="checkbox" name="homeService" value ="1" id="homeService"/>
                    <label for="homeService"></label>
                </div>
            </div>
            <div>
                <label id="text-type-register">Internet Wifi</label>
                <div class="slideCheck">	
                    <input type="checkbox" name="wifi" value ="1" id="wifi"/>
                    <label for="wifi"></label>
                </div>
            </div>
            <div>
                <label id="text-type-register">Servicio Terraza</label>
                <div class="slideCheck">	
                    <input type="checkbox" name="terrace" value ="1" id="terrace"/>
                    <label for="terrace"></label>
                </div>
            </div>
            <div>
                <label id="text-type-register">Pago con Tarjeta</label>
                <div class="slideCheck">	
                    <input type="checkbox" name="tarjeta" value ="1" id="tarjeta"/>
                    <label for="tarjeta"></label>
                </div>
            </div>
            <div>
                <label id="text-type-register">Adaptado Minusvalidos</label>
                <div class="slideCheck">	
                    <input type="checkbox" name="minusvalidos" value ="1" id="minusvalidos"/>
                    <label for="minusvalidos"></label>
                </div>
            </div>
        </article>
        <article>
            <div id="content-textArea">
                <label>Observaciones</label>
                <textarea id="Observation" name="observation"></textarea>
            </div>
        </article>

        <article>
            <input type="submit" value="Guardar Datos" id="botonSubmit" class="boton-aceptar"/>
        </article>

    </form>
    <c:set var="error" value="${null}" scope="session"/>


