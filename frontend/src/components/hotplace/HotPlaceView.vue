<template>
  <div>
    <img :src="attraction?.first_image" alt="image" />
    <h3>{{ attraction.title }}</h3>
    <table class="table table-bordered">
      <tr>
        <th>제목</th>
        <td v-text="article.subject"></td>
      </tr>
      <tr>
        <th>작성자</th>
        <td v-text="article.userId"></td>
      </tr>
      <tr>
        <th>조회수</th>
        <td v-text="article.hit"></td>
      </tr>
      <tr>
        <th>좋아요</th>
        <td v-text="article.likes"></td>
      </tr>
      <tr>
        <th colspan="2">내용</th>
      </tr>
      <tr>
        <th colspan="2"><pre v-text="article.content"></pre></th>
      </tr>
      <tr>
        <td colspan="2">
          <div>
            <button class="btn btn-primary" @click="moveHandler">목록</button>
            <button
              class="btn btn-primary"
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
        </td>
      </tr>
    </table>
  </div>
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

<style scoped></style>