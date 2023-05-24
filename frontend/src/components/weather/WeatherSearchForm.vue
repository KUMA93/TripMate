<template>
  <div>
    <b-container class="bv-example-row mt-3 text-center">
    <div class="input-group mb-1">
      <label class="input-group-text" for="sidoCode">시/도</label>
      <b-form-select id="sidoCode" v-model="sidoCode" :options="sidoOptions"></b-form-select>
      <div class="col-1"></div>
      <button class="btn btn-primary" @click="search">검색</button>
    </div>
  </b-container>
  </div>
</template>

<script>
import http from "@/api/http";
import { mapActions } from "vuex";

export default {
  name: 'WeatherSearchForm',
  components: {},
  data() {
    return {
      sidoCode: '0',
      gugunCode: 0,
      sidoOptions: [
        { value: '0', text: '시도 선택' },
        { value: 'Seoul', text: '서울'},
        { value: 'Incheon', text: '인천'},
        { value: 'Daejeon', text: '대전'},
        { value: 'Daegu', text: '대구'},
        { value: 'Gwangju', text: '광주'},
        { value: 'Busan', text: '부산'},
        { value: 'Ulsan', text: '울산'},
        { value: 'Sejong', text: '세종특별자치시'},
        { value: 'Suwon', text: '경기도'},
        { value: 'Gangneung', text: '강원도'},
      ],
      gugunOptions: [
        { value: '0', text: '구군 선택' },
      ],
    };
  },
  created() {
    // this.getSidoOptions();
  },
  watch: {
    sidoCode(val) {
      // this.gugunOptions = [{ value: '0', text: '구군 선택' }];
      // this.gugunCode = 0;
      // if (val != 0) {
      //   this.getGugunOptions();
      // }
      if (val != '0') {
        this.getCurrentWeatherInfo(val);
        this.getForecastInfo(val);
      }
    },
  },
  methods: {
    ...mapActions("WeatherStore", ["getCurrentWeatherInfo", "getForecastInfo"]),
    getSidoOptions() {
      const url = `https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=${process.env.VUE_APP_TRIP_API_KEY}&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json`;
      http.get(url)
        .then(({ data }) => {
          data.response.body.items.item.forEach((area) => {
            this.sidoOptions.push({ value: area.code, text: area.name });
          })
        })
        .catch(err => {
          console.log(err);
        })
    },

    getGugunOptions() {
      const url = `https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=${process.env.VUE_APP_TRIP_API_KEY}&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&areaCode=${this.sidoCode}`;
      // console.log(url);
      http.get(url)
        .then(({ data }) => {
          // console.log(data)
          if (data.response.body.items.item.length > 0) {
            data.response.body.items.item.forEach((area) => {
              this.gugunOptions.push({ value: area.code, text: area.name });
            })
          }

        })
        .catch(err => {
          console.log(err);
        })
    },

    search() {
      if (this.sidoCode == '0') {
        alert('시/도를 선택하세요.')
      } else {
        
        this.getCurrentWeatherInfo(this.sidoCode);
        this.getForecastInfo(this.sidoCode);

      }
    }
  },
};
</script>

<style scoped></style>