<template>
  <div>
    <the-header title="지역별 여행지"></the-header>
    <b-container class="bv-example-row mt-3 text-center">
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
      <b-row class="mt-3"></b-row>
      <b-row>
        <b-col>
          <!-- 검색 결과 List -->
          <div v-if="attractions.length > 0">
            <attraction-list></attraction-list>
          </div>
          <div v-else>
            관광지 정보가 없습니다.
          </div>

        </b-col>
      </b-row>
    </b-container>
    <!-- 상세정보 Modal -->
    <attraction-modal></attraction-modal>
  </div>
</template>

<script>
import TheHeader from "@/components/include/TheHeader.vue";
import TripMap from "@/components/common/TripMap.vue";
import AttractionList from "@/components/attraction/AttractionList.vue";
import TripSearchForm from "@/components/common/TripSearchForm.vue";
import AttractionModal from "@/components/attraction/AttractionModal.vue";

import { mapActions, mapState } from "vuex";

export default {
  name: 'AppAttraction',
  components: {
    TheHeader,
    TripMap,
    AttractionList,
    TripSearchForm,
    AttractionModal,
  },
  data() {
    return {
    };
  },
  created() {
    this.getAttractionList();
  },
  computed: {
    ...mapState(["attractions"])
  },
  methods: {
    ...mapActions(["getAttractionList"])
  },
};
</script>

<style scoped></style>