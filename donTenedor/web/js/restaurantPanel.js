/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {

    var error = $("#error").val();

    if (error !== null) {
        $("#content-restaurant").load("views/restaurantPanel/misDatos.jsp", {"error": error});
    } else {
        $("#content-restaurant").load("views/restaurantPanel/misDatos.jsp");
    }

    $("#tab-menu-restaurant ul li").each(function () {
        $(this).click(loadPage);
    });

    function loadPage() {
        resetBackground();
        $(this).css({"background": "#F7A89B"});
        var page = $(this).attr("data-view");
        var error = $("#error").val();
        if (error !== null) {
            $("#content-restaurant").load("views/restaurantPanel/" + page + ".jsp", {"error": error});
        } else {
            $("#content-restaurant").load("views/restaurantPanel/" + page + ".jsp");
        }
    }

    function resetBackground() {
        $("#tab-menu-restaurant ul li").each(function () {
            $(this).css({"background": "#DFDFDF"});
        });
    }

});
