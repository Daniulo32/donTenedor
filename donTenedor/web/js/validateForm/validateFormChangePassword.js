/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$("#form-change-password").validate({
    rules: {
        passwordActual: {
            required: true,
            remote: {
                url: "changePassword",
                type: "post",
                data: {
                    password: function () {
                        return $("#passwordActual").val();
                    },
                    idUser: function () {
                        return $("#idUser").val();
                    },
                    check:function(){
                        return "check";
                    }
                }
            }
        },
        newPassword: {
            required: true,
            minlength: 9,
            equalTo: "#rNewPassword"
        },
        rNewpassword: "required"

    },
    messages: {
        passwordActual: {
            required: "Debes indicar la contraseña actual",
            remote: "La contraseña indicada no es la actual"
        },
        newPassword: {
            required: "La Contraseña nueva es Obligatoria",
            minlength: "La Contraseña no cumple el formato",
            equalTo: "Las Contraseñas no coinciden"
        },
        rNewpassword: "Debes repetir la nueva contraseña"
    }

});

