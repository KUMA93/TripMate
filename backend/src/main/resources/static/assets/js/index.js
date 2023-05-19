let serviceKey = "cJagVsfBy49VapDTg63AmaKb7V4tRvJ1bZskifD61o%2BboFKKnWDLnGeh1dp19DyrtHu0WSTBB6E3r4lM9uRqRw%3D%3D"

fetch(`https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=${serviceKey}&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=R&contentTypeId=12&areaCode=1`)
        .then((response) => response.json())
        .then((data) => makeTripList(data))

    fetch(`https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=${serviceKey}&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=R&contentTypeId=25&areaCode=1`)
        .then((response) => response.json())
        .then((data) => makeHotPlaceList(data))

function makeTripList(data) {
    // console.log(data);
    let trips = data.response.body.items.item;
    let idx = [];

    let num;
    let len = trips.length;

    while (idx.length < 8) {
        num = Math.floor(Math.random() * len);

        if (!idx.includes(num)) {
            idx.push(num);
        }
    }


    let content = ``;
    idx.forEach(function (i) {
        content += `
        	<div class="card" style="background-image: url(${trips[i].firstimage});">
        	<p class="card-title">${trips[i].title}</p>
        	</div>
        	`;
        

    })

    document.getElementById('destination').innerHTML = content;
}

function makeHotPlaceList(data) {
    // console.log(data);
    let trips = data.response.body.items.item;
    let idx = [];

    let num;
    let len = trips.length;

    while (idx.length < 8) {
        num = Math.floor(Math.random() * len);

        if (!idx.includes(num)) {
            idx.push(num);
        }
    }


    let content = ``;
    idx.forEach(function (i) {
        content += `
        	<div class="card" style="background-image: url(${trips[i].firstimage});">
        	<p class="card-title">${trips[i].title}</p>
        	</div>
        	`;
        

    })

    document.getElementById('hotPlace').innerHTML = content;
}