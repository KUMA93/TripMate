<template>
  <div>
    <div id="map"></div>
    <div class="button-group">
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import pin_attraction from "@/assets/img/map/pin_attraction.png";
import pin_restaurant from "@/assets/img/map/pin_restaurant.png";
import pin_shopping from "@/assets/img/map/pin_shopping.png"
import pin_festival from "@/assets/img/map/pin_festival.png";
import pin_trip from "@/assets/img/map/pin_trip.png";
import pin_accommodation from "@/assets/img/map/pin_accommodation.png";
import pin_leports from "@/assets/img/map/pin_leports.png";
import pin_culture from "@/assets/img/map/pin_culture.png";


export default {
  name: 'TripMap',
  components: {},
  data() {
    return {
      map: null,
      positions: [],
      markers: [],
      infowindow: null,
      imgSrc: {
        12: pin_attraction, // 관광지
        14: pin_culture, // 문화시설
        15: pin_festival, // 축제/공연/행사
        25: pin_trip, // 여행코스
        28: pin_leports, // 레포츠
        32: pin_accommodation, // 숙박
        38: pin_shopping, // 쇼핑
        39: pin_restaurant,

      }
    };
  },
  props: {
    attractions: [],
  },
  computed: {
    ...mapState(["mapCenter"])
  },
  watch: {
    attractions() {
      console.log("attractions", this.attractions);
      this.displayMarker();
    },
    mapCenter() {
      this.map.setCenter(new window.kakao.maps.LatLng(this.mapCenter.lat, this.mapCenter.lon));
    },
  },
  created() {},
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.loadMap();
    } else {
      this.loadScript();
    }
  },
  methods: {
    loadScript() {
      const script = document.createElement("script");
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=" +
        process.env.VUE_APP_KAKAO_MAP_API_KEY +
        "&autoload=false";
      /* global kakao */
      script.onload = () => window.kakao.maps.load(this.loadMap);

      document.head.appendChild(script);
    },
    loadMap() {
      console.log("mapCenter", this.mapCenter);
      const container = document.getElementById("map");
      const options = {
        center: new window.kakao.maps.LatLng(this.mapCenter.lat, this.mapCenter.lon),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new window.kakao.maps.Map(container, options);
      this.displayMarker();
    },
    changeSize(size) {
      const container = document.getElementById("map");
      container.style.width = `100vh`;
      container.style.height = `${size}vh`;
      this.map.relayout();
    },
    createMarkerImage(src, size) {
      const markerImage = new kakao.maps.MarkerImage(src, size);
      return markerImage;            
    },
    makeMarker() {           
      var imageSize = new kakao.maps.Size(24, 36); 
      this.positions = [];
      this.attractions.forEach((attraction) => {
        if (attraction.contentTypeId == 39)
          console.log(this.imgSrc[attraction.contentTypeId]);
        
        let obj = {};
        obj.title = attraction.title;
        obj.latlng = new kakao.maps.LatLng(attraction.latitude, attraction.longitude);
        obj.image = this.createMarkerImage(this.imgSrc[attraction.contentTypeId], imageSize);
        this.positions.push(obj);
      });
    },
    deleteMarker() {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }
    },
    displayMarker() {
      // 현재 지도에 표시된 marker 제거
      this.deleteMarker();

      // 마커를 생성합니다
      this.makeMarker();

      this.markers = [];
      this.positions.forEach((position) => {
        const marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: position.latlng, // 마커를 표시할 위치
          title: position.title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          image: position.image, // 마커의 이미지
          clickable: true,
        });

        // var iwContent = `<div style="padding:5px;">${position.title}</div>`;
        // var infowindow = new kakao.maps.InfoWindow({
        //   content: iwContent
        // });

        // // 마커에 마우스오버 이벤트를 등록합니다
        // kakao.maps.event.addListener(marker, 'click', function() {
        //   // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
        //     infowindow.open(this.map, marker);
        // });

        this.markers.push(marker);

      });

      // 4. 지도를 이동시켜주기
      // 배열.reduce( (누적값, 현재값, 인덱스, 요소)=>{ return 결과값}, 초기값);
      const bounds = this.positions.reduce(
        (bounds, position) => bounds.extend(position.latlng),
        new kakao.maps.LatLngBounds()
      );

      this.map.setBounds(bounds);

      

    },
    displayInfoWindow(marker) {
      if (this.infowindow && this.infowindow.getMap()) {
        //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
        this.map.setCenter(this.infowindow.getPosition());
        return;
      }

      var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = marker.position, //인포윈도우 표시 위치입니다
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

      this.infowindow = new kakao.maps.InfoWindow({
        map: this.map, // 인포윈도우가 표시될 지도
        position: iwPosition,
        content: iwContent,
        removable: iwRemoveable,
      });

      this.map.setCenter(iwPosition);
    },
  },
};
</script>

<style scoped>
#map {
  width: 100%;
  height: 50vh;
}

</style>