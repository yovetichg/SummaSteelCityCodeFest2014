

function FindEventOnMap(address) {
    
    var lat = 40.4417;
    var lon = 80.0000;

    var currentPosition = new google.maps.LatLng(lat, lon);
    var map = new google.maps.Map(document.getElementById('map_canvas'), {
        zoom: 15,
        center: currentPosition,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    });

    if (address === null || address == undefined) {
        address = "Pittsburgh, PA";
    }

    var geocoder = new google.maps.Geocoder();

    if (geocoder) {
        geocoder.geocode({ 'address': address }, function (results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                map.setCenter(results[0].geometry.location);
                var marker = new google.maps.Marker({
                    map: map,
                    position: results[0].geometry.location,
                    title: address
                });
            }
            else {

                alert("invalid address" + status);
            }
        });
    }
    
    #map_canvas
     $.mobile.changePage("#map_canvas", { transition: "slide"});
    return true;
}