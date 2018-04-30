<%-- 
    Document   : index
    Created on : 25-abr-2018, 17:24:39
    Author     : danieljimenez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <link href="css/generalStyle.css" rel="stylesheet" type="text/css"/>
        <link href="css/headerStyle.css" rel="stylesheet" type="text/css"/>
        <link href="css/formSearchStyle.css" rel="stylesheet" type="text/css"/>
        <link href="css/indexStyle.css?v=1" rel="stylesheet" type="text/css"/>
        <link href="css/footerStyle.css" rel="stylesheet" type="text/css"/>
        <script src="js/libraries/jquery-3.3.1.js" type="text/javascript"></script>
        <script src="js/headerJS.js" type="text/javascript"></script>
        <title>Don Tenedor, tu eliges, tu disfrutas...</title>
    </head>
    <body>
        <jsp:include page="views/header.jsp"/>
        <aside id="aside-index">
            <img src="images/tueliges.png" id="tu_eliges" >
            <jsp:include page="views/formSearch.jsp"/>
        </aside>
        <section>

            <article>
                <img id="register-restaurant" src="images/registra_restaurante.png"/>
                <img id="reserve" src="images/reserva.png"/>
            </article>

            <article id="presentacion">
                <h1>¿Cómo funciona?</h1>
                <div class="caracteristicas">
                    <div class="face front">
                        <img src="images/icons/icon-register.png"/>
                        <label>Registrate</label>
                    </div>
                    <div class="face back">
                        <label>Registrate</label>
                        <p>Regístrate en nuestra web y  podrás disfrutar  las 
                            comodidades de buscar y reservar restaurantes de tu 
                            zona o lugar que visites.</p>
                    </div>
                </div>
                <div class="caracteristicas">
                    <div class="face front">
                        <img src="images/icons/icon-search-animate.png"/>
                        <label>Busca</label>
                    </div>
                    <div class="face back">
                        <label>Busca</label>
                        <p>
                            Busca los restaurantes que mas te gusten, eligiendo 
                            tipo, lugar, etc.  Podrás elegir entre múltiples 
                            sitios donde disfrutar de diferentes gastronomías.
                        </p>
                    </div>
                </div>
                <div class="caracteristicas">
                    <div class="face front">
                        <img src="images/icons/icon-choose.png"/>
                        <label>Elige</label>
                    </div>
                    <div class="face back">
                        <label>Elige</label>
                        <p>
                            Elige el restaurante que más te guste, realice la 
                            reserva y ya solo tiene que estar atento  a la 
                            confirmación del restaurante elegido. Esta 
                            notificación le llegara por mail y por la misma 
                            página web.
                        </p>
                    </div>
                </div>
                <div class="caracteristicas">
                    <div class="face front">
                        <img src="images/icons/icon-ok.png"/>
                        <label>Disfruta</label>
                    </div>
                    <div class="face back">
                        <label>Disfruta</label>
                        <p>
                            Una vez realizado todos los pasos anteriores, solo 
                            debe disfrutar de su reserva y del restaurante 
                            elegido, es rápido y sencillo ¡Que aproveche!
                        </p>
                    </div>
                </div>
            </article>
        </section>
        <jsp:include page="views/footer.jsp"/>
    </body>
</html>
