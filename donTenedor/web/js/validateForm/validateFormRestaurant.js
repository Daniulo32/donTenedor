$(document).ready(function () {

    jQuery.validator.addMethod("formatPrice", function (value, element) {
        
        var datos = value.split(".");

        if (datos[0] === "0") {
            return false;
        } else {
            return true;
        }

    });

    $("#form-restaurant").validate({

        rules: {
            nameRestaurant: "required",
            email: {required: true, email: true},
            address: "require",
            halfPrice: {
                required: true,
                formatPrice: true
            },
            localidad: {
                required: true
//                remote: {
//                    url: "checkTown",
//                    type: "post",
//                    data: {
//                        localidad: function () {
//                            return $("#localidad").val();
//                        }
//                    }
//                }
            },
            hourOpen: "required",
            hourClose: "required"
        },
        messages: {
            nameRestaurant: "El nombre del restaurante es obligatorio",
            email: {
                required: "El E-mail es obligatorio",
                email: "El formato del Email no es correcto"
            },
            address: "La dirección es obligatoria",
            halfPrice: {
                required: "Debes insertar un precio medio",
                formatPrice: "Debes insertar un precio medio",
            },
            localidad: {
                required: "Debes indicar una población",
                remote: "La población es incorrecta"
            },
            hourOpen: "Debes indicar una hora de apertura",
            hourClose: "Debes indicar una hora de cierre"
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