$(function () {

    var error = $("#error").val();
    var tabUser = window.localStorage.getItem("tabUser");

    if ($("#tab-user-panel").data('content') != "") {
        resetBackground();
        tabUser = $("#tab-user-panel").data('content');
        $("#" + tabUser).css({"background": "#F7A89B"});
        $("#content-user").load("views/userPanel/" + tabUser + ".jsp", {"error": error});
        $("#tab-user-panel").removeData('content');
    } else {
        if (tabUser === null) {
            $("#content-user").load("views/userPanel/misDatos.jsp", {"error": error});
        } else {
            resetBackground();
            $("#" + tabUser).css({"background": "#F7A89B"});
            $("#content-user").load("views/userPanel/" + tabUser + ".jsp", {"error": error});
        }
    }

    $("#tab-user-panel ul li").each(function () {
        $(this).click(loadPage);
    });

    function loadPage() {
        resetBackground();
        $(this).css({"background": "#F7A89B"});
        var page = $(this).attr("data-view");
        window.localStorage.setItem("tabUser", page);
        var error = $("#error").val();

        $("#content-user").load("views/userPanel/" + page + ".jsp", {"error": error});

    }

    function resetBackground() {
        $("#tab-user-panel ul li").each(function () {
            $(this).css({"background": "#DFDFDF"});
        });
    }

});

