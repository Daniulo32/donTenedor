<%-- 
    Document   : photosRestaurant
    Created on : 10-may-2018, 17:30:46
    Author     : danieljimenez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link href="css/restaurantPanelStyle/photoRestaurant.css" rel="stylesheet" type="text/css"/>
<script src="js/validateForm/validateFormPhoto.js" type="text/javascript"></script>
<script src="js/photoRestaurant.js" type="text/javascript"></script>
<section id="photo-restaurant">
    <h1>Fotos</h1>
    <form id="form-photo-restaurant" action="" method="POST" enctype="multipart/form-data" role="form">
        <input type="file" name="file" id="file" multiple="multiple"/>
        <label for="file" id="buttonUpload">Elegir Archivos</label>
        <div id="filesUpload">
            <label id="labelUpload">Archivos para subir</label>
            <input type="submit" id="guardar" value="Guardar" class="boton-aceptar"/>
        </div>
    </form>
    <hr>
</section>
