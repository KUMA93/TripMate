<template>
  <b-card
    class="card"
    overlay
    :img-src="hotplaceItem.first_image"
    img-alt="Card Image"
    text-variant="card-title"
  >
    <b-card-title class="align-bottom">{{ hotplaceItem.title }}</b-card-title>
    <b-card-text v-if="userInfo && isLike" @click="unLike">
      💗{{ hotplace.likes }}
    </b-card-text>
    <b-card-text v-else-if="userInfo && !isLike" @click="doLike">
      🤍{{ hotplace.likes }}
    </b-card-text>
    <b-card-text v-else> 
      🤍{{ hotplace.likes }} 
    </b-card-text>
    <b-card-text> 👁‍🗨{{ hotplace.hit }} </b-card-text>
    <b-button @click="moveHandler" class="card-button">자세히 보기</b-button>
  </b-card>
</template>

<script>
import http from "@/api/http";
import { mapState, mapGetters } from "vuex";
const UserStore = "UserStore";

export default {
  name: "HotPlaceListItem",
  components: {},
  computed: {
    ...mapState(UserStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  data() {
    return {
      hotplaceItem: {},
      likeItem: {
        id: "",
        articleNo: "",
      },
      isLike: false,
    };
  },
  props: {
    hotplace: Object,
  },
  created() {
    this.getAttractionInfo();
  },

  watch: {
    isLike: function () {
      this.getAttractionInfo();
    },
  },

  methods: {
    moveHandler() {
      this.$router.push({
        name: "HotPlaceView",
        params: { articleNo: this.hotplace.articleNo },
      });
    },

    getAttractionInfo() {
      http
        .get(`/rest/trip/search/${this.hotplace.contentId}`)
        .then(({ data }) => {
          this.hotplaceItem = data;
        })
        .catch((err) => {
          console.log(err);
        });
      http
        .get(`/rest/hotplace/isLike`)
        .then(({ data }) => {
          if (data == 1) {
            this.isLike = true;
          } else {
            this.isLike = false;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    doLike() {
      this.likeItem.articleNo = this.hotplace.articleNo;
      this.likeItem.id = this.userInfo.id;
      http
        .put(`/rest/hotplace/like`, this.likeItem)
        .then(({ data }) => {
          console.log(data);
        })
        .catch((err) => {
          console.log(err);
        });
      this.isLike = true;
    },

    unLike() {
      this.likeItem.articleNo = this.hotplace.articleNo;
      this.likeItem.id = this.userInfo.id;
      http
        .put(`/rest/hotplace/unLike`, this.likeItem)
        .then(({ data }) => {
          console.log(data);
        })
        .catch((err) => {
          console.log(err);
        });
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