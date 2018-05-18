/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    $("#icon-search").click(inserIdPoblacion);
    $("#provincia").on("change", loadListPoblacion);

    function inserIdPoblacion() {
        var value = $('#poblacion').val();
        if (value != "") {
            $("#idPoblacion").val($('#localidad [value="' + value + '"]').data('value'));
        } else {
            $("#idPoblacion").val(0);
        }
    }

    function loadListPoblacion() {
        $('#poblacion').prop("disabled",false);
        $.getJSON("searchTown", {idProvincia: $("#provincia").val()}, function (result) {
            $("#localidad").empty();
            $.each(result, function (key, val) {

                $("#localidad").append('<option data-value=' + key + ' value=' + val + '/>');
            });
        });

    }
});
