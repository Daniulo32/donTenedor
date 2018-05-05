<%-- 
    Document   : register
    Created on : 30-abr-2018, 17:00:49
    Author     : danieljimenez
--%>
<link href="css/formRegisterStyle.css" rel="stylesheet" type="text/css"/>
<script src="js/validateForm/validateFormUser.js" type="text/javascript"></script>
<section id="section-register">
    <h1>Formulario de Registro</h1>
    <form  method="POST" action="registerUser" id="form-register" role="form">
        <article>
            <div>
                <input type="text" name="name" placeholder="Nombre"/>
            </div>
            <div>
                <input type="text" name="subname" placeholder="Apellidos"/>
            </div>
            <div>
                <input type="text" name="mail" placeholder="E-mail">
            </div>
        </article>

        <article>
            <div>
                <input type="date" name="date"/>
            </div>
            <div>
                <input type="text" name="dni" placeholder="DNI"/>
            </div>
            <div>
                <label id="text-type-register">¿Registrar como restaurante?</label>
                <div class="slideCheck">	
                    <input type="checkbox" name="restaurant" value ="1" id="slideCheck"/>
                    <label for="slideCheck"></label>
                </div>
            </div>
        </article>

        <article>
            <div>
                <input type="password" name="password" placeholder="Contraseña">
            </div>
            <div>
                <input type="password" name="rpassword" id="rpassword" placeholder="Repetir Contraseña">
            </div>
        </article>

        <article>
            <div id="politica">
                <input type="checkbox" name="accept" value="1">
                <label>He leído y acepta la política de privacidad y las condiciones legales de Don Tenedor</label>
            </div>
        </article>

        <article>
            <input type="submit" value="Aceptar" class="boton-aceptar">
        </article>
    </form>
    <div id="condiciones-politica">
        <p>
            Las presentes Condiciones de Uso y la Política de Privacidad 
            constituyen su acuerdo con La Fourchette SAS (si es usted 
            propietario de un restaurante que acepta reservas a través del 
            Sitio ElTenedor, estas disposiciones también le son de 
            aplicación, además de las Condiciones Generales de Venta y de 
            Uso y las condiciones especiales aceptadas en el momento de su 
            registro). Léalas con atención; estas disposiciones son 
            vinculantes para usted si usa el Sitio ElTenedor.
            Atención: Si está en desacuerdo con la totalidad o parte de las 
            presentes Condiciones de Uso, no deberá usar el Sitio ElTenedor.
        </p>
        <p>
            La Fourchette ofrece al Usuario un servicio de búsqueda de 
            restaurantes y de reserva online a cambio de la aceptación 
            incondicional de las presentes Condiciones de Uso.
            El Usuario declara y reconoce haber leído las presentes 
            Condiciones de Uso en su integridad. Asimismo, al usar los 
            servicios ofrecidos en el Sitio ElTenedor, los Usuarios 
            aceptan sin reservas las presentes Condiciones de Uso.
            La Fourchette se reserva el derecho de modificar en 
            cualquier momento las presentes Condiciones de Uso, ya sea 
            de manera total o parcial. Por consiguiente, es 
            responsabilidad del Usuario consultar periódicamente la 
            versión más actualizada de las Condiciones de Uso que estará
            publicada en el Sitio ElTenedor. Se entenderá que los 
            Usuarios han aceptado la versión más actualizada cada vez 
            que vuelvan a usar el Sitio ElTenedor.
            Al acceder o usar de cualquier modo el Sitio ElTenedor, el 
            Usuario acepta someterse a las presentes Condiciones de Uso.
        </p>
    </div>
</section>