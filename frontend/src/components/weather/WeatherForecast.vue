<template>
  <div class="mt-5" v-if="visible">
    <swiper class="swiper" :options="swiperOption">
      <swiper-slide v-for="(item, index) in forecast" :key="index">
        <b-card class="slide">
          <b-card-text>{{ item.date }}</b-card-text>
          <p class="icon"><b-icon :icon="item.iconName"></b-icon></p>
          <b-card-title>{{ item.temp }}°C</b-card-title>
        </b-card>
      </swiper-slide>
      <div class="swiper-pagination" slot="pagination">
      </div>
      <div class="swiper-button-prev" slot="button-prev"></div>
      <div class="swiper-button-next" slot="button-next"></div>
    </swiper>
  </div>
</template>

<script>
import { mapState } from "vuex";
import { Swiper, SwiperSlide } from 'vue-awesome-swiper';
import "swiper/css/swiper.css";

export default {
  name: 'WeatherForecast',
  components: {
    Swiper,
    SwiperSlide,
  },
  data() {
    return {
      visible: false,
      swiperOption: {
        slidesPerView: 5,
        spaceBetween: 30,
        loop: false,
        pagination: {
          el: '.swiper-pagination',
          clickable: true
        },
        navigation: {
          nextEl: '.swiper-button-next',
          prevEl: '.swiper-button-prev'
        }
      },
    };
  },
  computed: {
    ...mapState("WeatherStore", ["forecast"]),
  },
  watch: {
    forecast() {
      this.visible = true;
    }
  },
  created() {},
  methods: {},
};
</script>

<style scoped>

.swiper {
  height: 36vh;
  padding-left: 10%;
  padding-right: 10%;
  padding-bottom: 4%;
}

.icon {
  font-size: 4.5rem;
}

.slide {
  height: 100%;
  padding: 3%;
}

</style>