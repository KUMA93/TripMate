<template>
  <div class="main-wrap">
  <b-carousel
    id="carousel-fade"
    fade
    v-model="slide"
    :interval="4000"
    controls
    indicators
    background="#ababab"
    img-width="100%"
    img-height="100%"
    style="text-shadow: 2px 2px 4px #333; overflow: hidden"
    @sliding-start="onSlideStart"
    @sliding-end="onSlideEnd"
  >
    <!-- 지역별 여행지 검색 -->
    <b-carousel-slide>
      <div>
        <h1 class="main-title">지역별 여행지</h1>
        <div class="main-text">
          지역별 여행지를 검색하고 숨은 명소를 찾아보세요
        </div>
        <b-button
          size="lg"
          class="main-button"
          @click="attractionHandler"
          pill
          variant="outline-light"
        >
          자세히 보기
        </b-button>
      </div>

      <template #img>
        <img
          class="fill"
          src="@/assets/img/header-gamcheon.png"
          alt="image slot"
        />
      </template>
    </b-carousel-slide>

    <!-- 지역별 날씨 정보 -->
    <b-carousel-slide>
      <div>
        <h1 class="main-title">지역별 날씨 정보</h1>
        <div class="main-text">지역별 날씨를 알아보세요</div>
        <b-button
          size="lg"
          class="main-button"
          @click="weatherHandler"
          pill
          variant="outline-light"
        >
          자세히 보기
        </b-button>
      </div>
      <template #img>
        <img
          class="fill"
          src="@/assets/img/cherryblossom.jpg"
          alt="image slot"
        />
      </template>
    </b-carousel-slide>

    <!-- 핫플자랑하기 -->
    <b-carousel-slide>
      <div>
        <h1 class="main-title">핫플자랑하기</h1>
        <div class="main-text">
          혼자 알고 있기 아까운 핫플레이스들을 자랑해주세요
        </div>
        <b-button
          size="lg"
          class="main-button"
          @click="hotplaceHandler"
          pill
          variant="outline-light"
        >
          자세히 보기
        </b-button>
      </div>
      <template #img>
        <img
          class="fill"
          src="@/assets/img/Gyeongju-travel-information-intro.jpg"
          alt="image slot"
        />
      </template>
    </b-carousel-slide>

    <!-- 여행정보공유 -->
    <b-carousel-slide>
      <div>
        <h1 class="main-title">여행정보공유</h1>
        <div class="main-text">다른 사람들과 여행 정보를 공유해보세요</div>
        <b-button
          size="lg"
          class="main-button"
          @click="boardHandler"
          pill
          variant="outline-light"
        >
          자세히 보기
        </b-button>
      </div>
      <template #img>
        <img class="fill" src="@/assets/img/nightview.png" alt="image slot" />
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

<style scoped>
.fill {
  width: 100%;
  height: 92vh;
  background-position: center;
  object-fit: cover;
  vertical-align: middle;
}

.main-wrap {
  position: relative;
  display: flex;
  justify-content: center;
}

.main-title {
  font-size: 9vh;
  bottom: 50px;
  position: relative;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -200%);
}

.main-text {
  font-size: 2vh;
  padding: 5px 10px;
  text-align: center;
  position: relative;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -300%);
}

.main-button {
  top: 50%;
  left: 50%;
  transform: translate(0%, -180%);
}

.container {
  position: relative;
  bottom: 50%;
  height: 100%;
}
</style>