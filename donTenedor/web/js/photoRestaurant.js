$(document).ready(function () {
    var eventoChange;
    $("#file").change(function (e) {
        eventoChange = e;
        var files = $("#file")[0].files;
        $(".containerFile").remove();
        for (var i = 0; i < files.length; i++) {

            var nameFile = files[i].name;
            var label = "<label class='filePhoto'>" + nameFile + "</label>";
            var img = "<img src='images/icons/icon-paper.png' id='paper' data-delete='" + i + "'/>";
            $("#filesUpload").append("<div class='containerFile'>" + label + img + "</div>");
        }
        addFunctionDelete();
    });

    function addFunctionDelete() {
        $("#filesUpload img").click(deletePhotoFromInput);
    }

    function deletePhotoFromInput() {
        var fileArray = [];
        console.log(eventoChange.target.files);
        var newFileList = Array.from(eventoChange.target.files);
        var elementDelete = $(this).data("delete");
        newFileList.splice(elementDelete,1);
        var files = $("#file")[0].files;
        $("#file").val(newFileList);
        $(this).parent().remove();
        console.log($("#file")[0].files.length);
    }

});