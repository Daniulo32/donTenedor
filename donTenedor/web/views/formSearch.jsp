<%-- 
    Document   : formSearch
    Created on : 26-abr-2018, 17:31:59
    Author     : danieljimenez
--%>

<div id="content-form-search">
    <form id="form-search" method="POST" action="#" >
        <div id="content-select-type">
            <img src="images/icons/icon-white-restaurant.png"/>
            <select>
                <option value="0">Tipo Restaurante</option>
                <option value="0">Español</option>
                <option value="0">Bar Tapas</option>
                <option value="0">Chino</option>
                <option value="0">Italiano</option>
                <option value="0">Indio</option>
                <option value="0">Comida Rápida</option>
                <option value="0">Buffet</option>
            </select>
        </div>
        
        <div id="content-input-province">
            <img src="images/icons/icon-white-town.png"/>
            <input type="text" name="province" id="input-province"/>
        </div>
        
        <div id="content-input-town">
            <img src="images/icons/icon-white-location.png"/>
            <input type="text" name="town" id="input-town"/>
        </div>
        
        <input id="icon-search" type="image" src="images/icons/icon-white-search.png" alt="Submit">
    </form>
</div>