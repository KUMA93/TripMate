<template>
  <b-container class="bv-example-row mt-3 text-center">
    <div class="input-group mb-1">
      <label class="input-group-text" for="contentType">관광지 유형</label>
      <b-form-select id="contentType" v-model="contentType" :options="options"></b-form-select>
    </div>
    <div class="input-group mb-1">
      <label class="input-group-text" for="sidoCode">시/도</label>
      <b-form-select id="sidoCode" v-model="sidoCode" :options="sidoOptions"></b-form-select>
      <label class="input-group-text" for="gugunCode">구/군</label>
      <b-form-select id="gugunCode" v-model="gugunCode" :options="gugunOptions"></b-form-select>
    </div>
    <div class="input-group mb-1">
      <label class="input-group-text" for="word">검색어</label>
      <b-form-input type="text" id="word" v-model="word"></b-form-input>
      <button class="btn btn-primary" @click="search">검색</button>
    </div>
  </b-container>
</template>

<script>
import http from "@/api/http";
import { mapActions } from "vuex";

export default {
  name: 'TripSearchForm',
  components: {},
  data() {
    return {
      contentType: 0,
      sidoCode: 0,
      gugunCode: 0,
      word: '',
      options: [
        { value: '0', text: '관광지 유형 선택' },
        { value: '12', text: '관광지' },
        { value: '14', text: '문화시설' },
        { value: '15', text: '축제/공연/행사' },
        { value: '25', text: '여행코스' },
        { value: '28', text: '레포츠' },
        { value: '32', text: '숙박' },
        { value: '38', text: '쇼핑' },
        { value: '39', text: '음식점' },
      ],
      sidoOptions: [
        { value: '0', text: '시도 선택' },

      ],
      gugunOptions: [
        { value: '0', text: '구군 선택' },
      ],
    };
  },
  created() {
    this.getSidoOptions();
  },
  watch: {
    sidoCode(val) {
      console.log("sido", val);
      this.gugunOptions = [{ value: '0', text: '구군 선택' }];
      this.gugunCode = 0;
      if (val != 0) {
        this.getGugunOptions();
      }
    },
  },
  methods: {
    ...mapActions(["searchAttraction"]),
    search() {
      console.log(this.contentType, this.sidoCode, this.gugunCode, this.word);
      this.searchAttraction({
        contentType: this.contentType,
        sidoCode: this.sidoCode,
        gugunCode: this.gugunCode,
        word: this.word
      });
    },
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
    }
  },
};
</script>

<style scoped></style>