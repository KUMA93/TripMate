<template>
  <div>
    <the-header title="지역별 날씨정보"></the-header>
    <weather-search-form></weather-search-form>
    <weather-current></weather-current>
    <weather-forecast></weather-forecast>
  </div>
</template>

<script>
import TheHeader from "@/components/include/TheHeader.vue";
import WeatherSearchForm from "@/components/weather/WeatherSearchForm.vue";
import WeatherCurrent from "@/components/weather/WeatherCurrent.vue";
import WeatherForecast from "@/components/weather/WeatherForecast.vue";

export default {
  name: 'AppWeather',
  components: {
    TheHeader,
    WeatherSearchForm,
    WeatherCurrent,
    WeatherForecast
  },
  data() {
    return {
      message: '',
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped></style>