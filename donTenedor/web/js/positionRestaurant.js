$(function () {

    var longitude;
    var latitude;
    var principalLongitude;
    var principalLatitude;
    var map;
    $("#savePosition").click(savePosition);
    console.log($("#longitude").val());
    navigator.geolocation.getCurrentPosition(showPosition);
    
    function showPosition(position) {
        if ($("#longitude").val() != "" && $("#latitude").val() != "") {
            principalLatitude = parseFloat($("#latitude").val());
            principalLongitude = parseFloat($("#longitude").val());
            getMap(20);
            createMarker();
        } else {
            if (position.coords.latitude !== null && position.coords.longitude !== null) {
                principalLatitude = parseFloat(position.coords.latitude);
                principalLongitude = parseFloat(position.coords.longitude);
                getMap(13);
            } else {
                principalLatitude = parseFloat("-33.8688");
                principalLongitude = parseFloat("151.2195");
                getMap(13);
            }
        }
    }

    function getMap(zoom) {
        map = new google.maps.Map(document.getElementById('map'), {
            center: {lat: principalLatitude, lng: principalLongitude},
            zoom: zoom,
            mapTypeId: 'roadmap'
        });

// Crear input cargando datos de españa
        var input = document.getElementById('pac-input');
        var options = {
            componentRestrictions: {country: 'es'}
        };
        var searchBox = new google.maps.places.SearchBox(input);

// Bias the SearchBox results towards current map's viewport.
        map.addListener('bounds_changed', function () {
            searchBox.setBounds(map.getBounds());
        });

        var markers = [];
// Listen for the event fired when the user selects a prediction and retrieve
// more details for that place.
        searchBox.addListener('places_changed', function () {
            var places = searchBox.getPlaces();

            if (places.length == 0) {
                return;
            }

            // Clear out the old markers.
            markers.forEach(function (marker) {
                marker.setMap(null);
            });
            markers = [];

            // For each place, get the icon, name and location.
            var bounds = new google.maps.LatLngBounds();
            places.forEach(function (place) {
                if (!place.geometry) {
                    console.log("Returned place contains no geometry");
                    return;
                }
                var icon = {
                    url: place.icon,
                    size: new google.maps.Size(1000, 1000),
                    origin: new google.maps.Point(0, 0),
                    anchor: new google.maps.Point(17, 34),
                    scaledSize: new google.maps.Size(25, 25)
                };

                // Create a marker for each place.
                marker = new google.maps.Marker({
                    map: map,
                    icon: icon,
                    title: place.name,
                    position: place.geometry.location,
                    draggable: true,
                    id: "marker"
                });
                markers.push(marker);

                longitude = place.geometry.location.lng();
                latitude = place.geometry.location.lat();

                if (place.geometry.viewport) {
                    // Only geocodes have viewport.
                    bounds.union(place.geometry.viewport);
                } else {
                    bounds.extend(place.geometry.location);
                }
            });
            map.fitBounds(bounds);

            google.maps.event.addListener(marker, 'dragend', function (event) {
                latitude = this.getPosition().lat();
                longitude = this.getPosition().lng();
            });

        });

    }

    function createMarker() {
        marker = new google.maps.Marker({
            position: {lat: principalLatitude, lng: principalLongitude},
            map: map,
            title: 'Hello World!'
        });
    }

    function savePosition() {
        var splitLongitude = longitude.toString().split(".");
        var lng = splitLongitude[0] + "." + splitLongitude[1].substring(0, 6);

        var splitLatitude = latitude.toString().split(".");
        var lat = splitLatitude[0] + "." + splitLatitude[1].substring(0, 6);

        $.ajax({
            type: "post",
            url: "savePositionRestaurant",
            data: {longitude: lng, latitude: lat},
            success: function (respuesta) {
                console.log(respuesta);
                $("#container-message").append("<label class='message-successful'>Localización guardada correctamente</label>");
            },
            error: function () {
                $("#container-message").append("<label class='message-error'>Error al guardar la localización</label>");
            }
        });
    }

});