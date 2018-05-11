$(document).ready(function () {

    jQuery.validator.addMethod("extensionFile", function (value, element) {
        var extensions = ["jpg","jpeg","gif","png"];
        
        var files = $("#file")[0].files;
        var valide = true;
        
        for (var i = 0; i < files.length; i++) {
            var nameFile = files[i].name;
            var extension = nameFile.split(".");
            var size = extension.length;
            if(!extensions.includes(extension[size-1])){
             valide = false;   
            }
        }

        return valide;
        
    }, "Algunos de los formatos de foto introducidos son incorrectos");


    $("#form-photo-restaurant").validate({

        rules: {
            file: {
                required:true,
                extensionFile: true
            }
        },
        messages: {
            file:{
                required : "Debes introducir al menos una foto"
            }
        }
    });

});


