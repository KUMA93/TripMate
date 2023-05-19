<template>
  <b-modal v-model="show" id="detailModal" scrollable title="상세 정보">
    <div class="card w-100">
      <img class="card-img-top" :src="attraction?.first_image" alt="image" width="100%">
      <div class="card-body">
        <b-table-simple>
          <b-tr>
            <b-th class="col-sm-3">관광지명</b-th>
            <b-td class="col-sm-9">{{ attraction?.title }}</b-td>
          </b-tr>
          <b-tr>
            <b-th class="col-sm-3">주소</b-th>
            <b-td class="col-sm-9">{{ attraction?.addr1 }} {{ attraction?.addr2 }}</b-td>
          </b-tr>
          <b-tr>
            <b-th class="col-sm-3">세부정보</b-th>
            <b-td class="col-sm-9">{{ attraction?.overview }}</b-td>
          </b-tr>
        </b-table-simple>
      </div>
    </div>
    <template #modal-footer>
      <div class="w-100">
        <b-button variant="primary" class="float-right" @click="show = false">
          닫기
        </b-button>
      </div>
    </template>

  </b-modal>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: 'AttractionModal',
  components: {},
  data() {
    return {
      show: false,
    };
  },
  computed: {
    ...mapState(["attraction"]),
  },
  created() { },
  methods: {},
};
</script>

<style scoped></style>