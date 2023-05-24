<template>
  <div class="table-body mt-5" v-if="visible">
    <b-table-simple striped>
      <b-thead>
      <b-th>시간</b-th>
      <b-th>날씨</b-th>
      <b-th>온도</b-th>
    </b-thead>
    <b-tbody>
      <b-tr v-for="(item, index) in forecast" :key="index">
        <b-td>{{ item.date }}</b-td>
        <b-td><b-icon class="mr-3" :icon="item.iconName"></b-icon>{{ item.title }}</b-td>
        <b-td>{{ item.temp }}°C</b-td>
      </b-tr>
    </b-tbody>
    </b-table-simple>
  </div>
</template>

<script>
import { mapState } from "vuex";
export default {
  name: 'WeatherForecast',
  components: {},
  data() {
    return {
      visible: false,
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
  created() {
    
  },
  methods: {},
};
</script>

<style scoped>
.table-body {
  height: 40vh;
  overflow-y: scroll;
}
</style>