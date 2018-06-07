/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    $("a[class='boton-report']").click(report);

    function report() {
        var idComment = $(this).closest("div[class='comment']").attr("data-idComment");
        var error = $(".message-error");
        var message = $(".message-successful");
        
        if (error.length != 0 || message.length != 0) {
            error.remove();
            message.remove();
        }
        
        $(this).parent().append("<label class='report'>Reportado</label>");
        $(this).remove();
        $.ajax({
            url: 'reportComment',
            data: {idComment: idComment},
            success: function (datos) {
                var message;
                var classStyle;

                if (datos === "true") {
                    message = "Se ha reportado el comentario, el administrador realizara las acciones pertinentes";
                    classStyle = "message-successful";
                } else {
                    message = "Se ha producido un error al reportar el comentario";
                    classStyle = "message-error";
                }
                $("#content-comment").prepend("<div class=" + classStyle + ">" + message + "</div>");
            },
            error: function () {
                var message = "Se ha producido un error al reportar el comentario";
                var classStyle = "message-error";
                $("#content-comment").prepend("<div class=" + classStyle + ">" + message + "</div>");
            }
        });
    }
});

