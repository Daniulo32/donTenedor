<%-- 
    Document   : contact
    Created on : 07-jun-2018, 17:37:18
    Author     : danieljimenez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link href="css/contactStyle.css" rel="stylesheet" type="text/css"/>
<script src="js/validateForm/validateFormContact.js" type="text/javascript"></script>
<section id="section-contact">
    <h1>Contactar</h1>
    <jsp:include page="/coreView/showMessage.jsp"/>
    <form id="form-contact" action="sendContact" method="POST">
        <label>Los campos marcados con asterisco (*) son obligatorios</label>
        <input type="text" name="name" placeholder="Nombre(*)">
        <input type="text" name="email" placeholder="E-mail(*)"/>
        <label>Escriba aquí si mensaje (*)</label>
        <textarea name="menssage"></textarea>
        <input type="submit" value="Enviar" class="boton-aceptar"/>
    </form>
</section>

