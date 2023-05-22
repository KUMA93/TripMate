<template>
  <div>

  </div>
</template>

<script>
import http from "@/api/http";

export default {
  name: 'HotPlaceView',
  components: {},
  data() {
    return {
      articleNo: 0,
      attraction: {},
    };
  },
  created() {
    this.articleNo = this.$route.params.articleNo;
    

  },
  props: {
    hotplace: Object,
  },
  methods: {
    getArticle() {
      http.get(`rest/hotplace/${this.articleNo}`)
        .then(({ data }) => {
          console.log(data);
        })
        .catch(err => {
          console.log(err);
      })
    },

    getAttraction() {
      http.get(`rest/trip/search/${this.attraction.contentId}`)
        .then(({ data }) => {
          console.log(data);
        })
        .catch(err => {
          console.log(err)
        })
      
    }
  },
};
</script>

<style scoped></style>