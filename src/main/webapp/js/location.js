function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            var latitude = position.coords.latitude;
            var longitude = position.coords.longitude;
            //빈칸에 값 넣기
            document.getElementById("latitudeInput").value = latitude;
            document.getElementById("longitudeInput").value = longitude;

            // 감추어진 input 박스에 설정
            document.getElementById("latitudeHiddenInput").value = latitude;
            document.getElementById("longitudeHiddenInput").value = longitude;
        });
    } else {
        alert("Geolocation is not supported by this browser.");
    }
}