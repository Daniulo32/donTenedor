/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    $('.tooltip').tooltipster({
        theme: 'tooltipster-noir'
    });
    $('#form-restaurant input').tooltipster({
        trigger: 'custom', // default is 'hover' which is no good here
        onlyOne: false, // allow multiple tips to be open at a time
        position: 'top'  // display the tips to the right of the element
    });

    $("#botonSubmit").click(inserIdPoblacion);
    $("#provincia").on("change", loadListPoblacion);
    loadListPoblacion();

    function inserIdPoblacion() {
        var value = $('#poblacion').val();
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

