/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {

    var error = $("#error").val();
    var tabRestaurant = window.localStorage.getItem("tabRestaurant");
    
    if (tabRestaurant === null) {
        $("#content-restaurant").load("views/restaurantPanel/misDatos.jsp", {"error": error});
    } else {
        resetBackground();
        $("#"+tabRestaurant).css({"background": "#F7A89B"});
        $("#content-restaurant").load("views/restaurantPanel/" + tabRestaurant + ".jsp", {"error": error});
    }

    $("#tab-menu-restaurant ul li").each(function () {
        $(this).click(loadPage);
    });

    function loadPage() {
        resetBackground();
        $(this).css({"background": "#F7A89B"});
        var page = $(this).attr("data-view");
        window.localStorage.setItem("tabRestaurant", page);
        var error = $("#error").val();

        $("#content-restaurant").load("views/restaurantPanel/" + page + ".jsp", {"error": error});

    }

    function resetBackground() {
        $("#tab-menu-restaurant ul li").each(function () {
            $(this).css({"background": "#DFDFDF"});
        });
    }

});
