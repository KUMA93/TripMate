<template>
  <div>
    <b-table-simple class="trip-table" hover responsive>
      <b-thead>
        <b-tr>
          <b-th>대표이미지</b-th>
          <b-th>관광지명</b-th>
          <b-th>주소</b-th>
          <b-th></b-th>
        </b-tr>
      </b-thead>

      <b-tbody id="trip-list">
          <attraction-list-item v-for="attraction in attractions" :key="attraction.contentId"
            :attraction="attraction" ></attraction-list-item>
      </b-tbody>
    </b-table-simple>
    
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
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
  methods: {
    ...mapActions(["setMapCenter"])
  },
};
</script>

<style scoped>
.trip-table{
  height: 50vh;
  overflow-y: scroll;
}
</style>