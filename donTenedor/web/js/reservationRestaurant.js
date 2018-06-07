/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {

    var idRestaurant = $("#idRestaurant").val();

    $("#dateReserve").change(getHours);
    $("#dateReserve").change(checkOffer);
    function getHours() {
        var date = $("#dateReserve").val();

        var datas = {idRestaurant: idRestaurant, date: date};

        $.ajax({
            url: 'getHoursRestaurant',
            data: datas,
            success: function (datos) {
                $("#hourReservation").empty();
                $("#hourReservation").append("<option value='hour'>Hora</option>");
                var horas = datos.split(",");

                if (horas !== null) {
                    for (var i = 0; i < horas.length; i++) {
                        $("#hourReservation").append("<option value=" + horas[i] + ">" + horas[i] + "</option>");
                    }
                } else {
                    $("#hourReservation").append("<option value='noHoras'>Si Horas</option>");
                }

            }
        });

    }
    
    function checkOffer(){
        $("#labelOffer").remove();
        var date = $("#dateReserve").val();
        var datas = {idRestaurant: idRestaurant, date: date};
        
        $.ajax({
            url: 'checkOffer',
            data: datas,
            success: function (datos) {
                console.log(datos);
                if(datos !== ""){
                    $("#form-reserve").append("<label id='labelOffer'>Este día tiene un descuento del "+datos+"% en su factura</label>");
                }
            }
        });
    }


});

