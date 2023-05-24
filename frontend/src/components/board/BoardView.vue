<template>
  <b-container>
    <h3 class="title">{{ article.subject }}</h3>
    <div class="mt-4">
      <b-avatar class="mr-2" variant="info" size="2rem"></b-avatar>
      <span class="mr-4">{{ article.userId }}</span>
      <span class="registerTime">{{ article.registerTime }}</span>
      <span class="hit"><b-icon icon="book"></b-icon> {{ article.hit }}</span>
    </div>
    <hr> <!-- divider -->
    <div class="mt-5 mb-5" v-html="article.content"></div>
    <hr>
    <div class="button-container">
        <button class="btn btn-primary mr-3" @click="moveHandler">목록</button>
        <button class="btn btn-primary mr-3" @click="updateHandler" v-if="userInfo && userInfo.id == article.userId">수정</button>
        <button class="btn btn-primary" @click="removeHandler" v-if="userInfo && (userInfo.id == article.userId || userInfo.position == 'admin')">삭제</button>
    </div>
  </b-container>
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
        // 개행문자를 <br/> 태그로 대체
        this.article.content = this.article.content.replace(/\n/g, '<br/>');
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

<style scoped>

* {
  text-align: left;
}

.registerTime {
  color: #0f0f0f78;
}

.button-container{
  float: right;
  clear: both;
}

.hit {
  justify-items: center;
  align-items: center;
  float: right;
  color: #0f0f0f78;
}

</style>