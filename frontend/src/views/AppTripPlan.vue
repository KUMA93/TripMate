<template>
  <div>
    <the-header title="나의 여행계획"></the-header>
  </div>
</template>

<script>
import TheHeader from "@/components/include/TheHeader.vue";

export default {
  name: 'AppTripPlan',
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