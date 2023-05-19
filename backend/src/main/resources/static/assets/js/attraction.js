let map=null;

window.onload = function () {
 
    
	// nav 텍스트 색상 검정으로
	document.querySelectorAll('nav a').forEach(t => {
		t.setAttribute('style', 'color:black;');
	});
	
    // 카카오지도
    var mapContainer = document.getElementById("map") // 지도를 표시할 div
    mapContainer.setAttribute('style', 'width: 60%; height:800px');
    mapOption = {
      center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
      level: 5, // 지도의 확대 레벨
    };
    
    // 지도를 표시할 div와 지도 옵션으로 지도를 생성합니다
    map = new kakao.maps.Map(mapContainer, mapOption);
 	
 	console.log("map ", map);
	// 주소-좌표 변환 객체를 생성합니다
	geocoder = new kakao.maps.services.Geocoder();

    // 전체 관광지 조회
    // document.getElementById("btn-search").click()
    getData();
}

function getData() {
	let sidoCode = document.getElementById("sido").value;
    let gugunCode = document.getElementById("gugun").value;
    let contentTypeId = document.getElementById("search-content-id").value;
    let keyword = document.getElementById("search-keyword").value;
    
    console.log("map2", map);
    
    let center = map.getCenter()
    
    console.log("center : ", center);
    console.log("lat : ", center.Ma, "long : ", center.La);
    
    let searchUrl = `${root}/trip/data?lat=${center.Ma}&lon=${center.La}`;
    
    if (sidoCode != '' || contentTypeId != '' || keyword != '') {
    	if (parseInt(sidoCode)) searchUrl += `&sido=${sidoCode}`;
    	if (parseInt(gugunCode)) searchUrl += `&gugun=${gugunCode}`;
    	if (keyword) {
    		searchUrl += `&keyword=${keyword}`
    	}
    	if (parseInt(contentTypeId)) searchUrl += `&contentType=${contentTypeId}`;
    }
    
    console.log("searchUrl :", searchUrl);
   
     fetch(searchUrl)
       .then((response) => response.json())
       .then((data) => makeList(data));
}


// 검색 버튼을 누르면..
// 지역, 유형, 검색어 얻기.
// 위 데이터를 가지고 공공데이터에 요청.
// 받은 데이터를 이용하여 화면 구성.
document.getElementById("btn-search").addEventListener("click", getData);

var positions; // marker 배열.


function makeList(data) {
    document.querySelector("table").setAttribute("style", "display: ;");
    console.log("trips", data);
    
    let tripList = ``;
    positions = [];
    let idx=0
    data.forEach((attraction) => {
        tripList += `
        <tr onclick="moveCenter(${attraction.latitude}, ${attraction.longitude});">
								<td><img src="${attraction.first_image}" width="100px"></td>
								<td>${attraction.title}</td>
								<td>${attraction.addr1}${attraction.addr2}</td>
								<td class="visually-hidden">${attraction.latitude}</td>
								<td class="visually-hidden">${attraction.longitude}</td>
								<td>
									<button id='detailButton_${idx++}'
										onclick="setDetailModal('${attraction.first_image}', '${attraction.title}',
                    '${attraction.addr1}',
                    '${attraction.addr2}',
                    '${attraction.latitude}',
                    '${attraction.longitude}',
                    '${attraction.overview}',
                    )"
										type="button" class="btn btn-light" data-bs-toggle="modal"
										data-bs-target="#verticalycentered">상세보기</button>
								</td>
							</tr>
        `;

        let markerInfo = {
        title: attraction.title,
        latlng: new kakao.maps.LatLng(attraction.latitude, attraction.longitude),
        };
        positions.push(markerInfo);
    });
    document.getElementById("trip-list").innerHTML = tripList;
    displayMarker();
}


function setDetailModal(image, title, addr1, addr2, y, x, overview) {
    let detail_img = document.querySelector('#detail-modal-img')
    detail_img.setAttribute('src', image)
    let detail_title = document.querySelector('#detail-modal-title')
    detail_title.innerHTML = title;
    let detail_addr1 = document.querySelector('#detail-modal-addr1')
    detail_addr1.innerHTML = addr1;
    let detail_lat = document.querySelector('#detail-modal-lat')
    detail_lat.innerHTML = y;
    let detail_long = document.querySelector('#detail-modal-long')
    detail_long.innerHTML = x;
    let detail_overview = document.querySelector('#detail-modal-overview')
    detail_overview.innerHTML = overview;
}

// 브라우저가 열리면 시도정보 얻기.
sendRequest("sido");

// 전국 특별/광역시, 도
// https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=*00000000

//시도가 바뀌면 구군정보 얻기.
document.querySelector("#sido").addEventListener("change", function () {
    let regcode = this[this.selectedIndex].value;
    if (regcode) {
        sendRequest("gugun", regcode);
    }
    // else {
    //     initOption("gugun");
    // }
    
    // 주소로 좌표를 검색합니다
    geocoder.addressSearch( this[this.selectedIndex].text , function(result, status) {

        // 정상적으로 검색이 완료됐으면 
         if (status === kakao.maps.services.Status.OK) {

            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            map.setCenter(coords);
        } 
    });    
});

//구군이 바뀌면 위치로 이동
document.querySelector("#gugun").addEventListener("change", function () {
    // 주소로 좌표를 검색합니다
    geocoder.addressSearch( this[this.selectedIndex].text , function(result, status) {

        // 정상적으로 검색이 완료됐으면 
         if (status === kakao.maps.services.Status.OK) {

            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            map.setCenter(coords);
        } 
    });    
});

function sendRequest(selid, areaCode) {
    const url = "https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=IyDyZIXXo%2BWZjmJd8PDtKfRUWVZo%2FU5zFQEdigo5N9XxA7Tr37KGeATt5O5XbwUBmA12hxIeuPtgKrgS%2Bd1luw%3D%3D&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json";
    let params = '';
    if (areaCode != undefined) {
        params = "areaCode=" + areaCode;
    }
    fetch(`${url}&${params}`)
    .then((response) => response.json())
    .then((data) => addOption(selid, data));
}

function addOption(selid, data) {
    let opt = ``;
    initOption(selid);
    switch (selid) {
    case "sido":
        opt += `<option value="">시도선택</option>`;
        data.response.body.items.item.forEach(function (regcode) {
        opt += `
            <option value="${regcode.code}">${regcode.name}</option>
            `;
        });
        break;
    case "gugun":
        opt += `<option value="">구군선택</option>`;
        guguns = data.response.body.items.item;
        let name = "";
        guguns.forEach(function (regcode) {
            opt += `
                <option value="${regcode.code}">${regcode.name}</option>
                `;
            });
        break;
    }
    document.querySelector(`#${selid}`).innerHTML = opt;
}

function initOption(selid) {
    let options = document.querySelector(`#${selid}`);
    options.length = 0;
    // let len = options.length;
    // for (let i = len - 1; i >= 0; i--) {
    //   options.remove(i);
    // }
}