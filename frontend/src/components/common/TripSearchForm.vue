<template>
  <div>
    <div class="row">
      <div class="col-2"></div> <!-- 여백 -->
      <div class="col-2">
        <b-form-select v-model="contentType" :options="options"></b-form-select>
      </div>
      <div class="col-4">
        <b-form-input type="text" v-model="word"></b-form-input>
      </div>
      <div class="col-2">
        <button class="btn btn-primary" @click="search">검색</button>
      </div>
      <div class="col-2"></div> <!-- 여백 -->
    </div>
    <div class="row">
      <div class="col-2">
        <b-form-select v-model="sidoCode" :options="sidoOptions"></b-form-select>
      </div>
      <div class="col-2">
        <b-form-select v-model="gugunCode" :options="gugunOptions"></b-form-select>
      </div>
    </div>
  </div>
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
        { value: '0', text: '선택하세요' },
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
      this.gugunOptions = [{ value: '0', text: '구군 선택' }];
      this.gugunCode = 0;
      if (val != 0) {
        this.getGugunOptions();
      }
    }
  },
  methods: {
    ...mapActions(["searchAttraction"]),
    search() {
      // console.log(this.contentType, this.sidoCode, this.gugunCode, this.word);
      // this.searchAttraction(this.contentType, this.sidoCode, this.gugunCode, this.word);
    },
    getSidoOptions() {
      const url = "https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=IyDyZIXXo%2BWZjmJd8PDtKfRUWVZo%2FU5zFQEdigo5N9XxA7Tr37KGeATt5O5XbwUBmA12hxIeuPtgKrgS%2Bd1luw%3D%3D&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json";
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
      const url = `https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=IyDyZIXXo%2BWZjmJd8PDtKfRUWVZo%2FU5zFQEdigo5N9XxA7Tr37KGeATt5O5XbwUBmA12hxIeuPtgKrgS%2Bd1luw%3D%3D&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&areaCode=${this.sidoCode}`;
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