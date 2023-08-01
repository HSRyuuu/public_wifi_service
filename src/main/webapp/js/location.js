function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            var latitude = position.coords.latitude;
            var longitude = position.coords.longitude;
            //빈칸에 값 넣기
            document.getElementById("lat").value = latitude;
            document.getElementById("lnt").value = longitude;
        });
    } else {
        alert("Geolocation is not supported by this browser.");
    }
}


function getLocationAndGoToDetail(key) {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            var latitude = position.coords.latitude;
            var longitude = position.coords.longitude;

            var url = "../wifi/detail.jsp?key=" + key + "&lat=" + latitude + "&lnt=" + longitude;
            window.location.href = url;
        });
    } else {
        window.location.href = "../wifi/detail.jsp?key=" + key + "&lat=0.0&lnt=0.0";
    }
}