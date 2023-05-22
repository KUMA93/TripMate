<template>
  <div><div>
    <table class="table table-bordered">
      <tr>
        <th>게시글 번호</th><td v-text="article.articleNo"></td>
      </tr>
      <tr>
        <th>제목</th><td v-text="article.subject"></td>
      </tr>
      <tr>
        <th>작성자</th><td v-text="article.userId"></td>
      </tr>
      <tr>
        <th>조회수</th><td v-text="article.hit"></td>
      </tr>
      <tr>
        <th colspan="2">내용</th>
      </tr>
      <tr>
        <th colspan="2"><pre v-text="article.content"></pre></th>
      </tr>
      <tr>
        <td colspan="2">
        <div >
          <button class="btn btn-primary" @click="moveHandler">목록</button>
          <button class="btn btn-primary" @click="updateHandler">수정</button>
          <button class="btn btn-primary" @click="removeHandler" v-if="userInfo.id == article.userId || userInfo.position == 'admin'">삭제</button>
        </div>
        </td>
      </tr>
    </table>
  </div></div>
</template>

<script>
import http from '@/api/http'
import { mapState, mapGetters } from "vuex";

const UserStore = "UserStore";

export default {
  data() {
      return {
        articleNo: '',
        article: {},
      };
  },
  created() {
  
  this.articleNo = this.$route.params.articleNo;
  console.log(this.articleNo)
  this.boardDetail()
  },

  computed: {
    ...mapState(UserStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },

methods: {
  boardDetail() {
    http.get(`rest/board/${this.articleNo}`)
      .then(({data}) => {
        console.log(data)
        this.article = data;
      })
      .catch(err => {
      console.log(err)
    })
  },

  moveHandler() {
    this.$router.push({name:"BoardList"})
  },
  updateHandler() {
    this.$router.push({name:"BoardModify", query: {articleNo:this.articleNo}})
  },
  removeHandler() {
    http.delete(`rest/board?articleNo=${this.articleNo}`)
    //   .then(response => {
    //     console.log(response)
    // })
      .then(({ data }) => {
        if (data == 'success') {
          alert("삭제 완료")
          this.moveHandler()
        }
      })
      .catch(err => {
        console.log(err)
        alert(err.response.data)
      })
      
  },
},
};
</script>

<style scoped></style>