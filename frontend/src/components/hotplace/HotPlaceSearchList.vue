<template>
  <b-tr @click="moveMap()">
    <b-td><img :src="attraction.first_image" alt="image" /></b-td>
    <b-td>{{ attraction.title }}</b-td>
    <b-td>{{ attraction.addr1 }} {{ attraction.addr2 }}</b-td>
    <b-td
      ><button class="btn btn-primary" @click="setHotplace">
        선택 하기
      </button></b-td
    >
  </b-tr>
</template>
  
  <script>
import { mapActions } from "vuex";

export default {
  name: "HotPlaceSeachList",
  components: {},
  data() {
    return {};
  },
  props: {
    attraction: Object,
  },
  created() {},
  methods: {
    ...mapActions(["setHotplaceItem", "setMapCenter"]),
    setHotplace() {
      // console.log("selected", this.attraction);
      this.setHotplaceItem(this.attraction);
    },
    moveMap() {
      this.setMapCenter({
        lat: this.attraction.latitude,
        lon: this.attraction.longitude,
      });
    },
  },
};
</script>
  
  <style scoped></style>