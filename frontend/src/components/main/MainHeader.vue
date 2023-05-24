<template>
  <div>
    <b-carousel
      id="carousel-fade"
      fade
      v-model="slide"
      :interval="4000"
      controls
      indicators
      background="#ababab"
      img-width="1024"
      img-height="480"
      style="text-shadow: 2px 2px 4px #333; overflow: hidden"
      @sliding-start="onSlideStart"
      @sliding-end="onSlideEnd"
    >
      <!-- 지역별 여행지 검색 -->
      <b-carousel-slide>
        <div class="container">
          <h1>지역별 여행지</h1>
          <div class="">지역별 여행지를 검색하고 숨은 명소를 찾아보세요</div>
          <b-button @click="attractionHandler" pill variant="outline-light">
            자세히 보기</b-button
          >
        </div>

        <template #img>
          <img
            class="d-block img-fluid w-100"
            width="1024"
            height="480"
            src="@/assets/img/header-gamcheon.png"
            alt="image slot"
          />
        </template>
      </b-carousel-slide>

      <!-- 지역별 날씨 정보 -->
      <b-carousel-slide>
        <div class="container">
          <h1>지역별 날씨 정보</h1>
          <div class="">지역별 날씨를 알아보세요</div>
          <b-button @click="weatherHandler" pill variant="outline-light"
            >자세히 보기</b-button
          >
        </div>
        <template #img>
          <img
            class="d-block img-fluid w-100"
            width="1024"
            height="480"
            src="@/assets/img/cherryblossom.jpg"
            alt="image slot"
          />
        </template>
      </b-carousel-slide>

      <!-- 핫플자랑하기 -->
      <b-carousel-slide>
        <div class="container">
          <h1>핫플자랑하기</h1>
          <div class="">혼자 알고 있기 아까운 핫플레이스들을 자랑해주세요</div>
          <b-button @click="hotplaceHandler" pill variant="outline-light"
            >자세히 보기</b-button
          >
        </div>
        <template #img>
          <img
            class="d-block img-fluid w-100"
            width="1024"
            height="480"
            src="@/assets/img/Gyeongju-travel-information-intro.jpg"
            alt="image slot"
          />
        </template>
      </b-carousel-slide>

      <!-- 여행정보공유 -->
      <b-carousel-slide>
        <div class="container">
          <h1>여행정보공유</h1>
          <div class="">다른 사람들과 여행 정보를 공유해보세요</div>
          <b-button @click="boardHandler" pill variant="outline-light"
            >자세히 보기</b-button
          >
        </div>
        <template #img>
          <img
            class="d-block img-fluid w-100"
            width="1024"
            height="480"
            src="@/assets/img/nightview.png"
            alt="image slot"
          />
        </template>
      </b-carousel-slide>
    </b-carousel>
  </div>
</template>

<script>
export default {
  data() {
    return {
      slide: 0,
      sliding: null,
    };
  },
  methods: {
    onSlideStart() {
      this.sliding = true;
    },
    onSlideEnd() {
      this.sliding = false;
    },

    attractionHandler() {
      this.$router.push({ path: "/attraction" });
    },

    weatherHandler() {
      this.$router.push({ path: "/weather" });
    },

    hotplaceHandler() {
      this.$router.push({ path: "/hotplace" });
    },

    boardHandler() {
      this.$router.push({ path: "/board" });
    },
  },
};
</script>

<style>
.img-container {
  width: 1024;
  height: 480;
  overflow: hidden;
  margin: 0 auto;
  position: relative;
}

.img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  position: absolute;
}

.container {
  position: relative;
  bottom: 50%;
}
</style>