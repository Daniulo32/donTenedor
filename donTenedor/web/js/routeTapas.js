/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {


    var url = document.URL;
    var urld = url.split("/");
    if (urld[4] != "index.jsp?view=routeTapas&notLocation=1") {
        if (!navigator.geolocation) {
            $(location).attr("href", "index.jsp?view=routeTapas&notLocation=1");
        } else {
            var latitude = 36.741791;
            var longitude = -5.166280;

            var parameters = {longitude: longitude, latitude: latitude};

            $.ajax({
                url: 'routeTapas',
                data: parameters,
                success: function (datos) {
                    //get api uses
                    var directionsService = new google.maps.DirectionsService;
                    var directionsDisplay = new google.maps.DirectionsRenderer;
                    var waypts = [];

                    for (var i = 0; i < 4; i++) {
                        waypts.push({location: {lat: datos[i][8], lng: datos[i][6]}, stopover: true});
                    }

                    var directionsDisplay;
                    var directionsService = new google.maps.DirectionsService();
                    var map;
                    map = new google.maps.Map(document.getElementById('mapRoute'), {
                        center: {lat: latitude, lng: longitude},
                        zoom: 5,
                        mapTypeId: 'roadmap'
                    });
                    directionsDisplay = new google.maps.DirectionsRenderer();

                    directionsDisplay.setMap(map);

                    var start = { lat: waypts[0].location.lat, lng: waypts[0].location.lng };
                    var end = { lat: waypts[0].location.lat, lng: waypts[0].location.lng };
                    var request = {
                        origin: start,
                        destination: end,
                        waypoints: waypts,
                        travelMode: 'WALKING'
                    };
                    directionsService.route(request, function (result, status) {
                        if (status == 'OK') {
                            directionsDisplay.setDirections(result);
                        }
                    });

                }
            });
        }
    }


});