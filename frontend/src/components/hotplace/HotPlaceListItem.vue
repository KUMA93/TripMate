<template>
  <b-card 
    class="card" 
    overlay 
    :img-src="hotplace.first_image" 
    img-alt="Card Image" 
    text-variant="card-title">
    <b-card-title class="align-bottom">{{ hotplace.title }}</b-card-title>
    <b-button @click="moveHandler" class="card-button">자세히 보기</b-button>
  </b-card>
</template>

<script>
export default {
  name: 'HotPlaceListItem',
  components: {},
  data() {
    return {
    };
  },
  props: {
    hotplace: Object,
  },
  created() {},
  methods: {
    moveHandler() {
      this.$router.push({ name: "HotPlaceView", params: {articleNo: this.hotplace.articleNo} });
    },
  },
};
</script>

<style scoped>
.card-title {
  font-size: 1.2em;
  color: #ffffff;
  text-shadow: 2px 2px rgba(0, 0, 0, 0.5);

}

.card-button {
  background-color: white;
  color: #0f0f0f;
  box-shadow: 2px 2px rgba(0, 0, 0, 0.5);
  border: 0px;
}
</style>