<template>
  <div>
    <b-container class="bv-example-row mt-3 text-center">
      <b-row>
        <b-col>
          <b-row>
            <!-- 검색 폼 -->
            <trip-search-form></trip-search-form>
          </b-row>
          <b-row class="mt-3"></b-row>
          <b-row>
            <b-col>
              <!-- 지도 -->
              <trip-map :attractions="attractions"></trip-map>
            </b-col>
          </b-row>
          <b-col id="trip-list">
            <hot-place-search-list
              v-for="attraction in attractions"
              :key="attraction.contentId"
              :attraction="attraction"
            ></hot-place-search-list>
          </b-col>
        </b-col>
        <b-col>
          <table class="table table-bordered table-content">
            <b-container>
              <h3>
                <b-col class="title">선택한 장소</b-col>
                <b-col class="place" v-if="this.selectedHotplace">
                  {{ this.selectedHotplace.title }}
                </b-col>
                <b-col class="place" v-else>
                  {{ this.attraction.title }}
                </b-col>
                <b-col hidden>{{ this.attraction.contentId }}</b-col>
              </h3>
              <h3 class="title">
                <b-form-input
                  type="text"
                  size="lg"
                  placeholder="제목"
                  id="subject"
                  ref="subject"
                  v-model="article.subject"
                ></b-form-input>
              </h3>
              <div class="mt-4"></div>
              <hr />
              <!-- divider -->
              <div class="mt-2 mb-2">
                <b-form-textarea
                  id="content"
                  ref="content"
                  v-model="article.content"
                  placeholder="내용을 입력하세요."
                ></b-form-textarea>
              </div>
              <hr />
              <div class="button-container">
                <button class="btn btn-secondary mr-3" @click="moveHandler">
                  취소
                </button>
                <button class="btn btn-primary" @click="updateHandler">
                  등록
                </button>
              </div>
            </b-container>
          </table>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>



<script>
import http from "@/api/http";
import TripMap from "@/components/common/TripMap.vue";
import TripSearchForm from "@/components/common/TripSearchForm.vue";
import HotPlaceSearchList from "@/components/hotplace/HotPlaceSearchList.vue";
import { mapActions, mapState, mapGetters } from "vuex";

const UserStore = "UserStore";

export default {
  name: "HotPlaceModify",
  components: {
    HotPlaceSearchList,
    TripMap,
    TripSearchForm,
  },

  computed: {
    ...mapState(UserStore, ["userInfo"]),
    ...mapState(["attractions", "selectedHotplace"]),
    ...mapGetters(["checkUserInfo", "getHotplace"]),
  },

  data() {
    return {
      articleNo: "",
      article: {
        articleNo: "",
        userId: "",
        subject: "",
        content: "",
        hit: "",
        registerTime: "",
        contentId: "",
      },
      attraction: {},
    };
  },
  created() {
    this.setHotPlaceOri();
    this.articleNo = this.$route.params.articleNo;
    this.getArticleInfo();
  },
  methods: {
    ...mapActions(["setMapCenter", "setHotplaceNull"]),

    moveHandler() {
      this.$router.push({ name: "HotPlaceList" });
    },

    setHotPlaceOri() {
      this.setHotplaceNull();
    },

    getArticleInfo() {
      http
        .get(`rest/hotplace/${this.articleNo}`)
        .then(({ data }) => {
          this.article = data;
          this.getAttractionInfo();
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getAttractionInfo() {
      http
        .get(`rest/trip/search/${this.article.contentId}`)
        .then(({ data }) => {
          this.attraction = data;
          console.log(data);
        })
        .catch((err) => {
          console.log(err);
        });
    },

    updateHandler() {
      let err = false;
      let msg = "";
      !this.article.subject &&
        ((msg = "글 제목을 입력해주세요"),
        (err = true),
        this.$refs.subject.focus());
      !err & (this.article.content.length == 0) &&
        ((msg = "글 내용을 입력해주세요"),
        (err = true),
        this.$refs.content.focus());
      if (err) {
        alert(msg);
      } else {
        this.article.userId = this.userInfo.id;
        if (this.selectedHotplace) {
          this.article.contentId = this.selectedHotplace.contentId;
        } else {
          this.article.contentId = this.attraction.contentId;
        }
        console.log(this.article.contentId);

        http
          .put("rest/hotplace", this.article)
          .then(({ data }) => {
            if (data == "success") {
              this.setHotplaceNull();
              alert("수정 완료");
              this.moveHandler();
            }
          })
          .catch((err) => {
            alert(err.response.data);
          });
      }
    },
  },
};
</script>

<style scoped>
#trip-list {
  height: 40vh;
  overflow-y: scroll;
}

#content {
  height: 40vh;
}

.table-content {
  border: none;
}

.place {
  font-size: 70%;
  padding-bottom: 2vh;
}

.button-container {
  float: right;
  clear: both;
}
</style>