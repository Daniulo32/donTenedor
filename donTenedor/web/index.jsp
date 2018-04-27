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
        <link href="css/header/headerStyle.css" rel="stylesheet" type="text/css" media="only screen and (min-width:800px)"/>
        <link href="css/header/headerTablet.css" rel="stylesheet" type="text/css" media="only screen and (min-width:432px) and (max-width:800px)"/>
        <link href="css/header/headerMobile.css" rel="stylesheet" type="text/css" media="only screen and (max-width:432px)"/>
        <link href="css/formSearch/formSearchStyle.css" rel="stylesheet" type="text/css" media="only screen and (min-width:800px)"/>
        <link href="css/formSearch/formSearchTablet.css" rel="stylesheet" type="text/css" media="only screen and (min-width:432px) and (max-width:800px)"/>
        <link href="css/formSearch/formSearchMobile.css" rel="stylesheet" type="text/css" media="only screen and (max-width:432px)"/>
        <link href="css/index/indexStyle.css" rel="stylesheet" type="text/css" media="only screen and (min-width:800px)"/>
        <link href="css/index/indexTablet.css" rel="stylesheet" type="text/css"media="only screen and (min-width:432px) and (max-width:800px)"/>
        <link href="css/index/indexMobile.css" rel="stylesheet" type="text/css" media="only screen and (max-width:432px)"/>
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
            <img id="register-restaurant" src="images/registra_restaurante.png"/>
        </section>
    </body>
</html>
