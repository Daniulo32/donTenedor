/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(main);

var contador = 1;

function main() {
    
    $('#boton-menu').click(function () {
        if (contador == 1) {
            $("nav").animate({
                left: '0%'
            });
            contador = 0;
        } else {
            contador = 1;
            $("nav").animate({
                left: '-100%'
            });
        }
    });
    
    $("#linkUser").click(mostrarLightBox);
    $("#cerrarLogin").click(ocultarLightBox);
    $("#botonLogin").click(comprobarUsuario);
    $("#iniciarSesionImg").bind('click', mostrarLightBox);

    function mostrarLightBox() {
        $("#lightBox").show(1000);
    }

    function ocultarLightBox() {
        $("#lightBox").hide(1000);
    }

    function comprobarUsuario() {

        var password = $("#password").val();
        var user = $("#usuario").val();

        var usuario = {usuario: user, password: password};
        
        $.ajax({
            url: 'comprobarUsuario',
            data: usuario,
            success: function (datos) {

                if (datos === "usuario" || datos === "restaurante") {
                    location.reload(true);
                } else {
                    $("#textoError").text(datos);
                }
            }
        });
    }

}