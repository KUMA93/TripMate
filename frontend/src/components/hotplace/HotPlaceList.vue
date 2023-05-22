<template>
  <div>
    <b-container fluid>
    <b-card-group columns>
      <hot-place-list-item v-for="hotplace in attractions" :key="hotplace.contentId"
      :hotplace="hotplace"></hot-place-list-item>
    </b-card-group>
  </b-container>
  </div>
</template>

<script>
import { mapState } from "vuex";
import HotPlaceListItem from "./HotPlaceListItem.vue";

export default {
  name: 'HotPlaceList',
  components: {
    HotPlaceListItem,
  },
  data() {
    return {
      message: '',
    };
  },
  created() { },
  computed: {
    ...mapState(["attractions"]),
  },
  methods: {},
};
</script>

<style scoped></style>