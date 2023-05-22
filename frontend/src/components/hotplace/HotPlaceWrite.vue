<template>
  <div>
    <b-container class="bv-example-row mt-3 text-center">
      <b-row>
        <b-col>
          <b-row>
        <b-col>
          <!-- 검색 폼 -->
          <trip-search-form></trip-search-form>
        </b-col>
      </b-row>
      <b-row class="mt-3"></b-row>
      <b-row>
        <b-col>
          <!-- 지도 -->
          <trip-map :attractions="attractions"></trip-map>
        </b-col>
      </b-row>

        </b-col>
        <b-col>
          <table class="table table-bordered">
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
          </table>
        </b-col>

      </b-row>
    
    </b-container>
  </div>
</template>

<script>
import TripMap from "../common/TripMap.vue";
import TripSearchForm from "../common/TripSearchForm.vue";

export default {
  name: 'HotPlaceWrite',
  components: {
    TripMap,
    TripSearchForm,
  },
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
  created() {},
  methods: {},
};
</script>

<style scoped></style>