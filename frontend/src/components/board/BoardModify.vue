<template>
  <div><div>
    <table class="table table-bordered">
      <tr>
        <th>게시글 번호</th><td id="articleNo" v-text="article.articleNo"></td>
      </tr>
      <tr>
        <th>제목</th><td><input type="text" id="subject" refs="subject" v-model="article.subject"/></td>
      </tr>
      <tr>
        <th>작성자</th><td id="userId" v-text="article.userId"></td>
      </tr>
      <tr>
        <th colspan="2">내용</th>
      </tr>
      <tr>
        <th colspan="2"><pre><input type="text" id="content" refs="content" v-model="article.content"/></pre></th>
      </tr>
      <tr>
        <td colspan="2">
        <div >
          <button class="btn btn-primary" @click="moveHandler">목록</button>
          <button class="btn btn-primary" @click="updateHandler">수정</button>
        </div>
        </td>
      </tr>
    </table>
  </div></div>
</template>

<script>
import http from '@/api/http'
export default {
  data() {
  return {
    articleNo:'',
    article: {}
  };
},
created() {
  this.articleNo = this.$route.query.articleNo;
  http.get(`rest/board/${this.articleNo}`)
    .then(({ data }) => { 
      console.log(data)
      this.article = data;
    })
},
methods: {
  moveHandler() {
    this.$router.push({name:'BoardList'})
  },
  updateHandler() {
    let err = false;
    let msg = "";
    !this.article.subject &&
        ((msg = "글 제목을 입력해주세요"), (err = true), this.$refs.subject.focus())
      !err & this.article.content &&
        ((msg = "글 내용을 입력해주세요"), (err = true), this.$refs.content.focus())
      if (err) {
        alert(msg);
      } else {
      http.put('rest/board', this.article)
        .then(({ data }) => { 
          if (data == 'success') { 
            alert("수정 완료");
            this.moveHandler();
          }
        })
        .catch(err => { 
          alert(err)
        })
    }
  },
},
};
</script>

<style scoped></style>
