<template>
  <b-card
    class="card"
    overlay
    :img-src="hotplaceItem.first_image"
    img-alt="Card Image"
    text-variant="card-title"
  >
    <b-card-title class="align-bottom">{{ hotplaceItem.title }}</b-card-title>
    <b-card-text class="like" v-if="userInfo && isLike" @click="unLike">
      💗{{ likes }}
    </b-card-text>
    <b-card-text class="like" v-else-if="userInfo && !isLike" @click="doLike">
      🤍{{ likes }}
    </b-card-text>
    <b-card-text class="like" v-else @click="goLogin"
      >🤍{{ likes }}
    </b-card-text>
    <b-card-text class="like"> 👁‍🗨{{ hotplace.hit }} </b-card-text>
    <div class="detail">
      <b-button @click="moveHandler" class="card-button">자세히 보기</b-button>
    </div>
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
      likes: "",
    };
  },
  props: {
    hotplace: Object,
  },
  created() {
    this.getAttractionInfo();

    console.log("첫 hotplace 정보", this.hotplace);

    if (this.userInfo) {
      this.likeItem.articleNo = this.hotplace.articleNo;
      this.likeItem.id = this.userInfo.id;
      http
        .post(`/rest/hotplace/isLike`, this.likeItem)
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
    }

    http
      .get(`/rest/hotplace/getLike/${this.hotplace.articleNo}`)
      .then(({ data }) => {
        this.likes = data;
        console.log(this.likes);
      })
      .catch((err) => {
        console.log(err);
      });
  },

  watch: {
    isLike: function () {
      http
        .get(`/rest/hotplace/getLike/${this.hotplace.articleNo}`)
        .then(({ data }) => {
          this.likes = data;
          console.log(this.likes);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },

  methods: {
    moveHandler() {
      this.$router.push({
        name: "HotPlaceView",
        params: { articleNo: this.hotplace.articleNo },
      });
    },

    goLogin() {
      alert("로그인이 필요한 페이지입니다.");
      this.$router.push({
        name: "login",
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
    },

    doLike() {
      this.likeItem.articleNo = this.hotplace.articleNo;
      this.likeItem.id = this.userInfo.id;
      http
        .put(`/rest/hotplace/like`, this.likeItem)
        .then(({ data }) => {
          console.log("좋아요 개수 증가", data);
        })
        .catch((err) => {
          console.log(err);
        });

      http
        .post(`/rest/hotplace/insertLike`, this.likeItem)
        .then(({ data }) => {
          console.log("좋아요 리스트에 추가", data);
          this.isLike = true;
        })
        .catch((err) => {
          console.log(err);
        });
    },

    unLike() {
      this.likeItem.articleNo = this.hotplace.articleNo;
      this.likeItem.id = this.userInfo.id;
      http
        .put(`/rest/hotplace/unLike`, this.likeItem)
        .then(({ data }) => {
          console.log("좋아요 개수 감소", data);
        })
        .catch((err) => {
          console.log(err);
        });
      http
        .delete(
          `/rest/hotplace/deleteLike/${this.likeItem.id}/${this.likeItem.articleNo}`
        )
        .then(({ data }) => {
          console.log("좋아요 리스트에서 제거", data);
          this.isLike = false;
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

.like {
  color: #ffffff;
  text-shadow: 2px 2px rgba(0, 0, 0, 0.5);
  display: inline;
}
.card-button {
  background-color: white;
  color: #0f0f0f;
  box-shadow: 2px 2px rgba(0, 0, 0, 0.5);
  border: 0px;
  display: inline-block;
}

.card {
  position: relative;
}
.detail {
  position: absolute;
  left: 40%;
  bottom: 5%;
}
</style>