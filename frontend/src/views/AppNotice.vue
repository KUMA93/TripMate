<template>
  <div>
    <the-header title="공지사항"></the-header>
    <router-view></router-view>
  </div>
</template>

<script>
import TheHeader from "@/components/include/TheHeader.vue";

export default {
  name: 'AppNotice',
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