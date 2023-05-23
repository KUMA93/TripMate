<template>
  <div><table class="table table-boardered">
  <tr>
    <td>제목</td>
    <td><input type="text" id="subject" ref="subject" v-model="article.subject"/></td>
  </tr>
  <tr>
    <td>작성자</td>
    <td>{{ userInfo.id }}</td>
  </tr>
  <tr>
      <td colspan="2">내용</td>
  </tr>
  <tr>
    <td colspan="2">
      <textarea id="content" cols="46" rows="10" ref="content" v-model="article.content"></textarea>
    </td>
  </tr>
  <tr>
    <td colspan="2">
      <div class="text-center"> 
        <button  class="btn btn-primary"  @click="createHandler">게시글 등록</button>
        <button  class="btn btn-primary"  @click="moveHandler">목록</button>
      </div>            
    </td>
  </tr>
</table></div>
</template>

<script>
import http from "@/api/http";
import { mapState, mapGetters } from "vuex";

const UserStore = "UserStore";

export default {
  data() {
    return {
      
      article: {
        articleNo: '',
        userId: '',
        subject: '',
        content: '',
        hit: '',
        registerTime: '',
      }
    };
  },

  computed: {
    ...mapState(UserStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  methods: {
    moveHandler() {
    this.$router.push({name:"BoardList"})
    },
    createHandler() {
      let err = false
      let msg = ""

      !this.article.subject &&
        ((msg = "글 제목을 입력해주세요"), (err = true), this.$refs.subject.focus())
      !err & this.article.content.length==0 &&
        ((msg = "글 내용을 입력해주세요"), (err = true), this.$refs.content.focus())
      if (err) {
        alert(msg);
      } else {
        this.article.userId = this.userInfo.id
        http.post('rest/board', this.article)
          .then(({ data }) => {
            if (data == 'success') {
              alert("등록 완료");
              this.moveHandler();
              }
          })
          .catch(err => {
            alert(err.response.data)
          })
      }
    }
  }
};
</script>

<style></style>
