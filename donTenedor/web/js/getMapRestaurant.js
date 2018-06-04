/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function () {
    var longitude;
    var latitude;
    latitude = parseFloat($("#latitude").val());
    longitude = parseFloat($("#longitude").val());

    map =new google.maps.Map(document.getElementById('map'), {
        center: {lat: latitude, lng: longitude},
        zoom: 20,
        mapTypeId: 'roadmap'
    });

    var marker = new google.maps.Marker({
        position: {lat: latitude, lng: longitude},
        map: map,
        title: '! Nuestra Posicion ¡'
    });
});

