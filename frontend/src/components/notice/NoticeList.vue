<template>
  <b-container>
    <div class="row">
      <div class="col-2" style="padding-bottom: 2vh">
        <b-button
          variant="primary"
          @click="goWrite"
          v-if="userInfo && userInfo.position == 'admin'"
          >공지사항 등록</b-button
        >
      </div>
    </div>
    <b-table
      id="NoticeList"
      hover
      striped
      :items="articles"
      :fields="fields"
      @row-clicked="goDetail"
    >
      <template #cell(subject)="data">
        <router-link
          :to="{
            name: 'NoticeView',
            params: { articleNo: data.item.articleNo },
          }"
        >
          {{ data.item.subject }}
        </router-link>
      </template>
    </b-table>
    <b-pagination
      pills
      align="center"
      v-model="pageNo"
      :total-rows="total"
      :per-page="10"
      aria-controls="NoticeList"
    ></b-pagination>
  </b-container>
</template>

<script>
import http from "@/api/http";
import { mapState, mapGetters } from "vuex";

const UserStore = "UserStore";

export default {
  data() {
    return {
      articles: [],
      fields: [
        { key: "articleNo", label: "공지 번호" },
        { key: "subject", label: "제목" },
        { key: "userId", label: "작성자" },
        { key: "registerTime", label: "공지 일자" },
        { key: "hit", label: "조회수" },
      ],
      pageNo: "1",
      total: 0,
    };
  },
  created() {
    this.searchArticle();
  },
  computed: {
    ...mapState(UserStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  watch: {
    pageNo: function () {
      this.searchArticle();
    },
  },
  methods: {
    goDetail(item) {
      this.$router.push({
        name: "NoticeView",
        params: { articleNo: item.articleNo },
      });
    },
    goWrite() {
      this.$router.push({ name: "NoticeWrite" });
    },

    searchArticle() {
      http
        .get(
          `rest/notice?pageNo=${this.pageNo}&word=${this.word}&key=${this.key}`
        )
        .then((response) => {
          console.log(response.data);
          if (response.data.notices) {
            this.articles = response.data.notices;
            let page = response.data.page;
            this.pageNo = page.pageNo;
            this.key = page.key;
            this.word = page.word;
            this.total = page.total;
          } else {
            this.articles = [];
            alert("조회한 데이타 정보가 없습니다.");
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style scoped></style>