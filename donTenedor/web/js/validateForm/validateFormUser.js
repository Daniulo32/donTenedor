
$(document).ready(function () {
    $('#form-register input').tooltipster({
        trigger: 'custom', // default is 'hover' which is no good here
        onlyOne: false, // allow multiple tips to be open at a time
        position: 'top'  // display the tips to the right of the element
    });
    
    jQuery.validator.addMethod("mayorEdad", function (value, element) {
        var hoy = new Date();
        var cumpleanos = new Date(value);
        var edad = hoy.getFullYear() - cumpleanos.getFullYear();
        var m = hoy.getMonth() - cumpleanos.getMonth();

        if (m < 0 || (m === 0 && hoy.getDate() < cumpleanos.getDate())) {
            edad--;
        }

        if (edad >= 18) {
            return true;
        } else {
            return false;
        }
    }, "Debes ser mayor de edad");

    jQuery.validator.addMethod("formatoDni", function (value, element) {

        var expresion_regular_dni = /^\d{8}[a-zA-Z]$/;

        if (expresion_regular_dni.test(value) == true) {
            return true;
        } else {
            return false;
        }
    }, "Formato del DNI incorrecto");

    jQuery.validator.addMethod("validarDni", function (value, element) {

        var numero;
        var letr;
        var letra;

        numero = value.substr(0, value.length - 1);
        letr = value.substr(value.length - 1, 1);
        numero = numero % 23;
        letra = 'TRWAGMYFPDXBNJZSQVHLCKET';
        letra = letra.substring(numero, numero + 1);

        if (letra != letr.toUpperCase()) {
            return false;
        } else {
            return true;
        }

    }, "La letra no se corresponde");
    

    $("#form-register").validate({

        rules: {
            name: "required",
            subname: "required",
            mail: {
                required: true,
                email: true,
                remote: {
                    url: "checkEmail",
                    type: "post",
                    data: {
                        email: function(){
                            return $("#email").val();
                        }
                    }
                }
            },
            dni: {
                required: true,
                minlength: 9,
                formatoDni: true,
                validarDni: true
            },
            password: {
                required: true,
                minlength: 8,
                equalTo: "#rpassword"
            },
            rpassword: "required",
            accept: {required: true},
            date: {
                required: true,
                mayorEdad: true
            }

        },
        messages: {
            name: "El nombre es obligatorio",
            subname: "Los apellidos son oligatorios",
            mail: {
                required: "El E-mail es obligatorio",
                email: "El formato del Email no es correcto",
                remote:"El Email ya se encuentra registrado"
            },
            dni: {
                required: "El DNI es obligatorio",
                minlength: "El DNI debe tener minimo 9 digitos"
            },
            password: {
                required: "Debes insertar una contraseña",
                minlength: "La contraseña al menos debe tener 8 digitos",
                equalTo: "Las Contraseñas no coinciden"
            },
            rpassword: "Debes repetir la contraseña",
            accept: "Debes aceptar las condiciones de la página",
            date: {
                required: "Debes indicar la fecha de nacimiento"
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
