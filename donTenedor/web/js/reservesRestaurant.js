/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    $("select[name='status']").change(function () {
        var value = $(this).val();
        var idReservation = $(this).closest("tr").attr("data-idReserve");
        
        $(this).removeClass();
        
        if (value == "Sin Confirmar") {
            $(this).addClass("sinConfirmar");
        } else if (value == "Confirmado") {
            $(this).addClass("confirmado");
        } else {
            $(this).addClass("rechazado");
        }

        $.ajax({
            type: "post",
            url: "updateStatusReservation",
            data: {idReservation: idReservation, status: value}
        });
    });
});
