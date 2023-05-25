<template>
  <b-container>
    <div class="mt-4">
      <b-avatar class="mr-2" variant="info" size="2rem"></b-avatar>
      <span class="mr-4">{{ article.userId }}</span>
      <span class="registerTime">{{ article.registerTime }}</span>
      <span class="hit"
        ><b-icon icon="book" variant="primary"></b-icon> {{ article.hit }}</span
      >
      <span class="likes"
        ><b-icon icon="heart-fill" variant="danger"></b-icon> {{ article.like }}
      </span>
    </div>
    <div class="row">
      <div class="mt-5 mb-5 col-6">
        <h3 class="attraction-title">
          {{ attraction.title }}
        </h3>
        <img
          :src="attraction?.first_image"
          alt="image"
          class="attraction-img"
        />
      </div>
      <div class="mt-5 mb-5 col-6">
        <h3 class="title">{{ article.subject }}</h3>
        <div v-html="article.content"></div>
      </div>
    </div>
    <hr />
    <div class="button-container">
      <button class="btn btn-primary mr-3" @click="moveHandler">목록</button>
      <button
        class="btn btn-primary mr-3"
        @click="updateHandler"
        v-if="userInfo && userInfo.id == article.userId"
      >
        수정
      </button>
      <button
        class="btn btn-primary"
        @click="removeHandler"
        v-if="
          userInfo &&
          (userInfo.id == article.userId || userInfo.position == 'admin')
        "
      >
        삭제
      </button>
    </div>
  </b-container>
</template>


<script>
import http from "@/api/http";
import { mapState, mapGetters } from "vuex";

const UserStore = "UserStore";

export default {
  name: "HotPlaceView",
  components: {},
  computed: {
    ...mapState(UserStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },

  data() {
    return {
      articleNo: 0,
      article: {},
      attraction: {},
    };
  },
  created() {
    this.articleNo = this.$route.params.articleNo;
    this.getArticle();
  },
  methods: {
    getArticle() {
      http
        .get(`rest/hotplace/${this.articleNo}`)
        .then(({ data }) => {
          console.log("디테일 글 정보", data);
          this.article = data;
          // 개행문자를 <br/> 태그로 대체
          this.article.content = this.article.content.replace(/\n/g, "<br/>");
          this.getAttractionInfo();
        })
        .catch((err) => {
          console.log(err);
        });
    },

    getAttractionInfo() {
      http
        .get(`rest/trip/search/${this.article.contentId}`)
        .then(({ data }) => {
          this.attraction = data;
          console.log(data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    moveHandler() {
      this.$router.push({ name: "HotPlaceList" });
    },
    updateHandler() {
      this.$router.push({
        name: "HotPlaceModify",
        query: { articleNo: this.articleNo },
      });
    },
    removeHandler() {
      http
        .delete(`rest/hotplace?articleNo=${this.articleNo}`)
        .then(({ data }) => {
          if (data == "success") {
            alert("삭제 완료");
            this.moveHandler();
          }
        })
        .catch((err) => {
          console.log(err);
          alert(err.response.data);
        });
    },
  },
};
</script>

<style scoped>
* {
  text-align: left;
}

.attraction-img {
  width: 100%;
}

.registerTime {
  color: #0f0f0f78;
}

.button-container {
  float: right;
  clear: both;
}

.hit {
  justify-items: center;
  align-items: center;
  float: right;
  color: #0f0f0f78;
}

.likes {
  justify-items: center;
  align-items: center;
  float: right;
  color: #0f0f0f78;
  padding-right: 3vh;
}
</style>