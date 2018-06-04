/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function(){
    var idRestaurant = $("#idRestaurant").val();
    var error = $("#error").val();
    var tabOneRestaurant = window.localStorage.getItem("tabOneRestaurant");
    
    if (tabOneRestaurant === null) {
        $("#content-restaurant").load("views/oneRestaurant/mapRestaurant.jsp?idRestaurant="+idRestaurant, {"error": error});
    } else {
        resetBackground();
        $("#"+tabOneRestaurant).css({"background": "#F7A89B"});
        $("#content-restaurant").load("views/oneRestaurant/" + tabOneRestaurant + ".jsp?idRestaurant="+idRestaurant, {"error": error});
    }
    
    $("#tab-menu-restaurant ul li").each(function () {
        $(this).click(loadPage);
    });

    function loadPage() {
        resetBackground();
        $(this).css({"background": "#F7A89B"});
        var page = $(this).attr("data-view");
        window.localStorage.setItem("tabOneRestaurant", page);
        var error = $("#error").val();

        $("#content-restaurant").load("views/oneRestaurant/" + page + ".jsp?idRestaurant="+idRestaurant, {"error": error});

    }

    function resetBackground() {
        $("#tab-menu-restaurant ul li").each(function () {
            $(this).css({"background": "#DFDFDF"});
        });
    }
   
    
});

