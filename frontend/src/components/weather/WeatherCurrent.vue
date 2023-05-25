<template>
  <div class="mt-3">
    <div class="container" v-if="weatherInfo.temp != undefined">
    <p class="temp"><b-icon class="mr-3" :icon="weatherInfo.iconName"></b-icon>{{ weatherInfo.temp }}°C</p>
    <h3>{{ weatherInfo.title }}</h3>
    <h5>체감온도 : {{ weatherInfo.feels_like }}°C</h5>
    <h5>습도 : {{ weatherInfo.humidity }}%</h5>
  </div>
  <div class="container blank" v-else>
    <h3>지역을 선택해주세요!</h3>
  </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: 'WeatherCurrent',
  components: {},
  data() {
    return {
      weatherInfo: {},
    };
  },
  computed: {
    ...mapState("WeatherStore", ["current"]),
  },
  watch: {
    current(val) {
      this.weatherInfo = {
        ...val,
      }
    }
  },
  created() {
  },
  methods: {
  },
};
</script>

<style scoped>
.container {
  background-color: #58b7dc50;
  border-radius: 30px;
  align-items: center;
  justify-content: center;
  padding: 24px;
}

.blank {
  background-color: lightgray;
}

.temp {
  font-size: 70px;
}
</style>