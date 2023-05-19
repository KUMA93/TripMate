
markers = [];

function moveCenter(lat, lng) {
    map.setCenter(new kakao.maps.LatLng(lat, lng));
}

function displayMarker() {
    // 마커 이미지의 이미지 주소입니다
    var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
    
    markers.forEach(function(marker){
    	marker.setMap(null);
    });
    
    markers = [];
    for (var i = 0; i < positions.length; i++) {
    	let idx = i;
        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35);
    
        // 마커 이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
    
        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커를 표시할 위치
            title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            image: markerImage, // 마커 이미지
        });
        
        // 마커에 커서가 오버됐을 때 마커 위에 표시할 인포윈도우를 생성합니다
        var iwContent = `<div style="padding:5px;">${positions[idx].title}</div>`; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

        // 인포윈도우를 생성합니다
        var infowindow = new kakao.maps.InfoWindow({
            content : iwContent
        });

        // 마커에 마우스오버 이벤트를 등록합니다
        kakao.maps.event.addListener(marker, 'mouseover', function() {
          // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
            infowindow.open(map, marker);
        });

        // 마커에 마우스아웃 이벤트를 등록합니다
        kakao.maps.event.addListener(marker, 'mouseout', function() {
            // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
            infowindow.close();
        });
        
     // 마커에 클릭이벤트를 등록합니다
        
        kakao.maps.event.addListener(marker, 'click', function() {
        	console.log(idx)
        	document.getElementById(`detailButton${idx}`).focus()
        });
        
        markers.push(marker);
    }
    
    // 첫번째 검색 정보를 이용하여 지도 중심을 이동 시킵니다
    if (positions.length != 0)
    	map.setCenter(positions[0].latlng);
}