<template>
  <div>
    <b-container fluid>
      <div  class="row">
            <div class="col-2"></div>
            <div class="col-2">
                <b-form-select  v-model="key"   :options="keys"></b-form-select>
            </div>
            <b-pagination       pills  align='center'
                            v-model="pageNo"  
                            :total-rows="total"
                            :per-page="10"
                            aria-controls="HotPlaceList"
    ></b-pagination>
            <div class="col-2">
                <b-button variant="primary"  @click="goWrite">핫플 등록</b-button>
            </div>
            <div class="col-2"></div>
        </div>
      <div v-if="hotplaces.length > 0">
        <b-card-group columns>
          <hot-place-list-item v-for="hotplace in hotplaces" :key="hotplace.contentId"
            :hotplace="hotplace" id="HotPlaceList"></hot-place-list-item>
        </b-card-group>
      </div>
      <div v-else>
        등록된 핫플레이스가 없습니다.
      </div>
    </b-container>

  </div>
</template>

<script>
import http from "@/api/http";
import HotPlaceListItem from "./HotPlaceListItem.vue";

export default {
  name: 'HotPlaceList',
  components: {
    HotPlaceListItem,
  },
  data() {
    return {
      hotplaces: [],
      key: 'likes',
      keys: [
        { value: 'likes', text: '----정렬 기준----' },
        { value: 'likes', text: '좋아요순' },
        { value: 'registerTime', text: '최신순' },
        { value: 'hit', text: '조회순' },
        ],
      pageNo: '1',
      total: 0
    };
  },

  watch: {
    pageNo: function () {
      this.getHotPlaceList();
    },

    key: function() {
      this.getHotPlaceList();  
    }
    
  },

  created() {
    this.getHotPlaceList();
  },
  methods: {
    getHotPlaceList() {
      http.get(`rest/hotplace?pageNo=${ this.pageNo }&key=${ this.key }`)
        .then(({ data }) => {
          this.hotplaces = data.hotplaces
          let page = data.page
          this.pageNo = page.pageNo
          this.key = page.key
          this.total = page.total
        })
        .catch(err => {
          console.log(err);
        })
    },

    goWrite() {
      this.$router.push({ name: 'HotPlaceWrite' });
    }
  },
};
</script>

<style scoped></style>