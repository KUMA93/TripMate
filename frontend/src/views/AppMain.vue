<template>
  <div class="main">
      <MainHeader></MainHeader>
  </div>
</template>

<script>
import MainHeader from '@/components/main/MainHeader';

export default {
  name: 'AppMain',
  components: {
    MainHeader,
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