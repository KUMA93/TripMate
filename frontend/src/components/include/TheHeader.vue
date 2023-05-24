<template>
    <div class="container mt-4">
        <h2 class="title">{{ title }}</h2>
    </div>
</template>

<script>
export default {
    name: 'TheHeader',
    components: {},
    props: {
        title: String,
    },
    created() {},
    methods: {},
};
</script>

<style scoped>
.container {
    height: 10vh;
}

.title {
    line-height: 10vh;
}
</style>