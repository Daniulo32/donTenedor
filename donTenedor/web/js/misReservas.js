/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function () {
    $(".cancel").click(deleteReservation);
    $("input[name='estrellas']").click(sendVote);
    function deleteReservation() {
        var tr = $(this).closest("tr");
        var idreservation = $(tr).attr("data-idReserve");
        var confirmation = confirm("¿Estas seguro de cancelar la reserva?");

        if (confirmation) {
            $(tr).fadeOut(500);
            $.ajax({
                url: 'cancelReservation',
                data: {idReservation: idreservation}
            });
        }
    }

    function sendVote() {
        var value = $(this).val();
        var p = $(this).closest("p");
        var idReserve = $(p).attr("data-idReserve");
        var tr = $(p).closest("tr");
        $.ajax({
            url: 'sendVote',
            data: {idReservation: idReserve, valueVote: value},
            success:function(){
                $(tr).fadeOut(500);
            }
        });
    }
});

