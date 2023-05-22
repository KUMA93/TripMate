<template>
  <div>
    <b-container fluid>
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
                <b-button variant="primary"  @click="goWrite">핫플 등록</b-button>
            </div>
            <div class="col-2"></div>
        </div>
      <div v-if="hotplaces.length > 0">
        <b-card-group columns>
          <hot-place-list-item v-for="hotplace in hotplaces" :key="hotplace.contentId"
            :hotplace="hotplace"></hot-place-list-item>
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
      word: '',
    key: 'all',
    keys: [
      { value: 'all', text: '----선택하세요----' },
      { value: 'title', text: '핫플이름' },
      { value: 'userId', text: '작성자' },
    ],
    };
  },
  created() {
    this.getHotPlaceList();
    console.log("hotplaces", this.hotplaces);
  },
  methods: {
    getHotPlaceList() {
      http.get(`rest/hotplace`)
        .then(({ data }) => {
          console.log("data", data);
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