/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(function () {
    $(".boton-report").click(deleteOffer);

    function deleteOffer() {
        var idOffer = $(this).closest("tr").attr("data-idOffer");
        var pregunta = confirm("¿Desea eliminar la oferta?");

        if (pregunta) {
            $.ajax({
                type: "post",
                url: "deleteOffert",
                data: {idOffer: idOffer}
            });
            location.reload();
        }
    }


});