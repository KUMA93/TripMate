<template>
  <div>
    <the-header title="공유 게시판"></the-header>
    <router-view></router-view>
  </div>
</template>

<script>
import TheHeader from "@/components/include/TheHeader.vue";

export default {
  name: 'AppBoard',
  components: {
    TheHeader
  },
  data() {
    return {
      message: '',
    };
  },
  created() {},
  methods: {},
};
</script>

<style scoped></style>