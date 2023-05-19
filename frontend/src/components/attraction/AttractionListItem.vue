<template>
  <b-tr>
    <b-td><img :src="attraction.first_image" alt="image"></b-td>
    <b-td>{{ attraction.title }}</b-td>
    <b-td>{{ attraction.addr1 }} {{ attraction.addr2 }}</b-td>
    <b-td><button class="btn btn-primary" v-b-modal.detailModal @click="selectAttraction">상세 보기</button></b-td>
  </b-tr>

</template>

<script>
import { mapActions } from "vuex";

export default {
  name: 'AttractionListItem',
  components: {},
  data() {
    return {
      
    };
  },
  props: {
    attraction: Object,
  },
  created() {},
  methods: {
    ...mapActions(["setAttractionDetail"]),
    selectAttraction() {
      // console.log("selected", this.attraction);
      this.setAttractionDetail(this.attraction);
    }
  },
};
</script>

<style scoped></style>