/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    $("#botonSubmit").click(inserIdPoblacion);
    $("#provincia").on("change", loadListPoblacion);
    loadListPoblacion();

    function inserIdPoblacion() {
        var value = $('#poblacion').val();
        alert($('#localidad [value="' + value + '"]').data('value'));
        $("#idPoblacion").val($('#localidad [value="' + value + '"]').data('value'));
    }

    function loadListPoblacion() {

        $.getJSON("searchTown", {idProvincia: $("#provincia").val()}, function (result) {
            $("#localidad").empty();
            $.each(result, function (key, val) {

                $("#localidad").append('<option data-value=' + key + ' value=' + val + '/>');
            });
        });

    }

});

