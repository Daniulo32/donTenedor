/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {

    $('#form-reserve input').tooltipster({
        trigger: 'custom', // default is 'hover' which is no good here
        onlyOne: false, // allow multiple tips to be open at a time
        position: 'top'  // display the tips to the right of the element
    });

    $('#form-reserve select').tooltipster({
        trigger: 'custom', // default is 'hover' which is no good here
        onlyOne: false, // allow multiple tips to be open at a time
        position: 'top'  // display the tips to the right of the element
    });

    jQuery.validator.addMethod("checkDate", function (value, element) {

        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth() + 1; //January is 0!

        var yyyy = today.getFullYear();
        if (dd < 10) {
            dd = '0' + dd;
        }
        if (mm < 10) {
            mm = '0' + mm;
        }
        var dateToday = yyyy + '-' + mm + '-' + dd;


        var valuesStart = value.split("-");
        var valuesEnd = dateToday.split("-");
        
        // Verificamos que la fecha no sea posterior a la actual
        var dateStart = new Date(valuesStart[0], (valuesStart[1] - 1), valuesStart[2]);
        var dateEnd = new Date(valuesEnd[0], (valuesEnd[1] - 1), valuesEnd[2]);

        if (dateStart >= dateEnd) {
            console.log(dateStart + "--> mayor que -->" + dateEnd);
            return true;
        } else {
            console.log(dateStart + "--> menor que -->" + dateEnd);
            return false;
        }

    });

    jQuery.validator.addMethod("checkPeople", function (value, element) {

        if (value >= 1) {
            return true;
        } else {
            return false;
        }

    });

    jQuery.validator.addMethod("checkHour", function (value, element) {

        if (value != "hour") {
            return true;
        } else {
            return false;
        }

    });




    $("#form-reserve").validate({

        rules: {
            dateReserve: {required: true, checkDate: true},
            hourReservation: {checkHour: true},
            nPeople: {required: true, checkPeople: true}

        },
        messages: {
            dateReserve: {
                required: "Indica una Fecha",
                checkDate: "Fecha Incorrecta"
            },
            hourReservation: {
                checkHour: "Indica una Hora"
            },
            nPeople: {
                required: "Indica el numero de personas",
                checkPeople: "Numero de personas incorrectos"
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

