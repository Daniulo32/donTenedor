/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    $('#form-contact input').tooltipster({
        trigger: 'custom', // default is 'hover' which is no good here
        onlyOne: false, // allow multiple tips to be open at a time
        position: 'top'  // display the tips to the right of the element
    });
    
    $('#form-contact textarea').tooltipster({
        trigger: 'custom', // default is 'hover' which is no good here
        onlyOne: false, // allow multiple tips to be open at a time
        position: 'top'  // display the tips to the right of the element
    });
    
    $("#form-contact").validate({

        rules: {
            name: {required: true},
            email: {required: true,email: true},
            menssage: {required: true}
        },
        messages: {
            name: {
                required: "Debes indicar su nombre"
            },
            email: {
                required: "Debes escribir un email",
                email: "Formato de email incorrecto"
            },
            menssage:{
                required: "Debes escribir un mensaje"
            }
        },
        errorPlacement: function (error, element) {
            var ele = $(element),
                    err = $(error),
                    msg = err.text();
            if (msg != null && msg !== '') {
                ele.tooltipster('content', msg);
                ele.tooltipster('open');
            }
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).removeClass(errorClass).addClass(validClass).tooltipster('close');
        }
    });
});


