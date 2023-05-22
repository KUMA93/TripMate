<template>
    <div>
        <div  class="row">
            <div class="col-2"></div>
            <div class="col-2">
                <b-form-select  v-model="key"   :options="keys"></b-form-select>
            </div>
            <div class="col-4">
                <b-form-input   v-model="word" @keyup.enter="searchArticle" />
            </div>
            <div class="col-2">
                <b-button variant="primary"  @click="searchArticle">검색</b-button>
                <b-button variant="primary"  @click="goWrite">게시글 등록</b-button>
            </div>
            <div class="col-2"></div>
        </div>
        <b-table id='BoardList' hover striped :items="articles" :fields="fields" @row-clicked="goDetail">
          <template #cell(subject)="data">
            <router-link :to="{ name: 'BoardView', params: { articleNo: data.item.articleNo } }">
              {{ data.item.subject }}
            </router-link>
          </template>
        </b-table>
        <b-pagination       pills  align='center'
                            v-model="pageNo"  
                            :total-rows="total"
                            :per-page="5"
                            aria-controls="BoardList"
    ></b-pagination>

    </div>
</template>

<script>
import http from '@/api/http'

export default {
data() {
  return {
    articles: [],
    word: '',
    key: 'all',
    keys: [
      { value: 'all', text: '----선택하세요----' },
      { value: 'subject', text: '제목' },
      { value: 'content', text: '내용' },
      { value: 'userId', text: '작성자' },
    ],
    fields: [
                { key: 'articleNo',  label: '게시글 번호' },
                { key: 'subject', label: '제목' },
                { key: 'userId', label: '작성자' },
                { key: 'registerTime',label: '작성 시간' },
                { key: 'hit', label: '조회수' },
            ],
    pageNo: '1',
    total: 0
  }
},
created() { 
  this.searchArticle();
  },

  watch: {
    pageNo: function () {
      this.searchArticle();
  }
},
  methods: {
    goDetail(item) {   
            this.$router.push({ name: 'BoardView', params: {articleNo:item.articleNo} })
    },
    goWrite() {
      this.$router.push({ name: 'BoardWrite'})
    },
    
  searchArticle() {
    http.get(`rest/board?pageNo=${ this.pageNo }&word=${ this.word }&key=${ this.key }`)
      .then(response => {
        console.log(response.data)
        if (response.data.articles) {
            this.articles = response.data.articles;
            let page = response.data.page
            this.pageNo = page.pageNo
            this.key = page.key
            this.word = page.word
            this.total= page.total
        } else { 
            this.articles = []
            alert('조회한 데이타 정보가 없습니다.')
        }
      })
      .catch(err => {
        console.log(err)
      })
  }
},
};

</script>

<style scoped></style>