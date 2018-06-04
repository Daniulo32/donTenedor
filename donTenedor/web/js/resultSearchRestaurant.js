/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {

    var itemsCount = $("#container-restaurant .showRestaurant").length;

    $("#domicilio").click(sortItems);
    $("#wifi").click(sortItems);
    $("#terraza").click(sortItems);
    $("#minusvalidos").click(sortItems);
    $("#tarjeta").click(sortItems);
    $("#precioBajo").click(sortItems);
    $("#typeRestaurant").change(sortItems);

    function filters() {
        var filtros = {};
        filtros.homeservice = $("#domicilio:checked").val();
        filtros.wifi = $("#wifi:checked").val();
        filtros.terrace = $("#terraza:checked").val();
        filtros.handicapped = $("#minusvalidos:checked").val();
        filtros.paycard = $("#tarjeta:checked").val();
        filtros.type = $("#typeRestaurant").val();

        resetFilters();
        $.each(filtros, function (element) {

            if (filtros[element] != undefined && filtros[element] != "any") {
                if (element != "type") {
                    $("#container-restaurant .showRestaurant").filter(function () {
                        if (!$(this).data(element) == 1) {
                            $(this).addClass("hideRestaurant");
                        }
                    });
                } else {
                    $("#container-restaurant .showRestaurant").filter(function () {
                        if ($(this).data("type") != filtros.type) {
                            $(this).addClass("hideRestaurant");
                            console.log("entrando");
                        }
                    });
                }
            }
        });

        window.scrollTo(0, 0);

    }

    function resetFilters() {

        $("#container-restaurant .showRestaurant").filter(function () {
            $(this).removeClass("hideRestaurant");
        });

        if (!$("#precioBajo").is(":checked")) {
            $('.showRestaurant').sort(function (a, b) {
                return parseInt($(a).data('id')) - parseInt($(b).data('id'));
            }).appendTo('#container-restaurant');
        }
    }

    function sortItems() {

        if ($(this).is(":checked")) {
            $('.showRestaurant').sort(function (a, b) {
                return parseFloat($(a).data('price')) - parseFloat($(b).data('price'));
            }).appendTo('#container-restaurant');
            filters();
        } else {
            filters();
        }
    }

});
