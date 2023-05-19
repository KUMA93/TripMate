<template>
  <div>
    <b-table-simple hover responsive>
      <b-thead>
        <b-tr>
          <b-th>대표이미지</b-th>
          <b-th>관광지명</b-th>
          <b-th>주소</b-th>
          <!-- <th class="visually-hidden">위도</th>
          <th class="visually-hidden">경도</th> -->
          <b-th></b-th>
        </b-tr>
      </b-thead>

      <b-tbody id="trip-list">
          <attraction-list-item v-for="attraction in attractions" :key="attraction.contentId"
            :attraction="attraction"></attraction-list-item>
      </b-tbody>
    </b-table-simple>
    
  </div>
</template>

<script>
import { mapState } from "vuex";
import AttractionListItem from "./AttractionListItem.vue";

export default {
  name: 'AttractionList',
  components: {
    AttractionListItem,
  },
  data() {
    return {
    };
  },
  computed: {
    ...mapState(["attractions"]),
  },
  props: {    
  },
  created() { },
  methods: {},
};
</script>

<style scoped></style>