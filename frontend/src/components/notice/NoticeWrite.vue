<template>
  <b-container>
    <h3 class="title">
      <b-form-input
        type="text"
        size="lg"
        placeholder="제목"
        id="subject"
        ref="subject"
        v-model="article.subject"
      ></b-form-input>
    </h3>
    <div class="mt-4"></div>
    <hr />
    <!-- divider -->
    <div class="mt-2 mb-2">
      <b-form-textarea
        id="content"
        ref="content"
        v-model="article.content"
        placeholder="내용을 입력하세요."
      ></b-form-textarea>
    </div>
    <hr />
    <div class="button-container">
      <button class="btn btn-secondary mr-3" @click="moveHandler">취소</button>
      <button class="btn btn-primary" @click="createHandler">등록</button>
    </div>
  </b-container>
</template>

<script>
import http from "@/api/http";
import { mapGetters, mapState } from "vuex";

const UserStore = "UserStore";

export default {
  data() {
    return {
      article: {
        articleNo: "",
        userId: "",
        subject: "",
        content: "",
        hit: "",
        registerTime: "",
      },
    };
  },

  computed: {
    ...mapState(UserStore, ["userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },

  methods: {
    moveHandler() {
      this.$router.push({ name: "NoticeList" });
    },
    createHandler() {
      let err = false;
      let msg = "";
      !this.article.subject &&
        ((msg = "글 제목을 입력해주세요"),
        (err = true),
        this.$refs.subject.focus());
      !err & (this.article.content.length == 0) &&
        ((msg = "글 내용을 입력해주세요"),
        (err = true),
        this.$refs.content.focus());
      if (err) {
        alert(msg);
      } else {
        this.article.userId = this.userInfo.id;
        http
          .post("/rest/notice", this.article)
          .then((response) => {
            console.log("data", response);
            if (response.data == "success") {
              alert("등록 완료");
              this.moveHandler();
            }
          })
          .catch((err) => {
            alert(err.response.data);
          });
      }
    },
  },
};
</script>

<style scoped>
#content {
  height: 40vh;
}

.button-container {
  float: right;
  clear: both;
}
</style>
