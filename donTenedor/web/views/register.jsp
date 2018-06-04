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
                <input type="text" name="mail" id="email" placeholder="E-mail">
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
                <label id="text-type-register">�Registrar como restaurante?</label>
                <div class="slideCheck">	
                    <input type="checkbox" name="restaurant" value ="1" id="slideCheck"/>
                    <label for="slideCheck"></label>
                </div>
            </div>
        </article>

        <article>
            <div>
                <input type="password" name="password" placeholder="Contrase�a">
            </div>
            <div>
                <input type="password" name="rpassword" id="rpassword" placeholder="Repetir Contrase�a">
            </div>
        </article>

        <article>
            <div id="politica">
                <input type="checkbox" name="accept" value="1">
                <label>He le�do y acepta la pol�tica de privacidad y las condiciones legales de Don Tenedor</label>
            </div>
        </article>

        <article>
            <input type="submit" value="Aceptar" class="boton-aceptar">
        </article>
    </form>
    <div id="condiciones-politica">
        <p>
            Las presentes Condiciones de Uso y la Pol�tica de Privacidad 
            constituyen su acuerdo con La Fourchette SAS (si es usted 
            propietario de un restaurante que acepta reservas a trav�s del 
            Sitio ElTenedor, estas disposiciones tambi�n le son de 
            aplicaci�n, adem�s de las Condiciones Generales de Venta y de 
            Uso y las condiciones especiales aceptadas en el momento de su 
            registro). L�alas con atenci�n; estas disposiciones son 
            vinculantes para usted si usa el Sitio ElTenedor.
            Atenci�n: Si est� en desacuerdo con la totalidad o parte de las 
            presentes Condiciones de Uso, no deber� usar el Sitio ElTenedor.
        </p>
        <p>
            La Fourchette ofrece al Usuario un servicio de b�squeda de 
            restaurantes y de reserva online a cambio de la aceptaci�n 
            incondicional de las presentes Condiciones de Uso.
            El Usuario declara y reconoce haber le�do las presentes 
            Condiciones de Uso en su integridad. Asimismo, al usar los 
            servicios ofrecidos en el Sitio ElTenedor, los Usuarios 
            aceptan sin reservas las presentes Condiciones de Uso.
            La Fourchette se reserva el derecho de modificar en 
            cualquier momento las presentes Condiciones de Uso, ya sea 
            de manera total o parcial. Por consiguiente, es 
            responsabilidad del Usuario consultar peri�dicamente la 
            versi�n m�s actualizada de las Condiciones de Uso que estar�
            publicada en el Sitio ElTenedor. Se entender� que los 
            Usuarios han aceptado la versi�n m�s actualizada cada vez 
            que vuelvan a usar el Sitio ElTenedor.
            Al acceder o usar de cualquier modo el Sitio ElTenedor, el 
            Usuario acepta someterse a las presentes Condiciones de Uso.
        </p>
    </div>
</section>