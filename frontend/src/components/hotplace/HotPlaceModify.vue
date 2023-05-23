<template>
  <div>
    <b-container class="bv-example-row mt-3 text-center">
      <b-row>
        <b-col>
          <b-row>
            <b-col>
              <!-- 검색 폼 -->
              <trip-search-form></trip-search-form>
            </b-col>
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
          <table class="table table-bordered">
            <tr>
              <td>선택한 장소</td>
              <td v-if="this.selectedHotplace">
                {{ this.selectedHotplace.title }}
              </td>
              <td v-else>{{ this.attraction.title }}</td>
            </tr>
            <tr>
              <td>제목</td>
              <td>
                <input type="text" v-model="article.subject" ref="subject" />
              </td>
            </tr>
            <tr>
              <td colspan="2">내용</td>
            </tr>
            <tr>
              <td colspan="2">
                <textarea
                  id="content"
                  cols="46"
                  rows="10"
                  ref="content"
                  v-model="article.content"
                ></textarea>
              </td>
            </tr>
            <tr>
              <td colspan="2">
                <div class="text-center">
                  <button class="btn btn-primary" @click="updateHandler">
                    수정
                  </button>
                  <button class="btn btn-primary" @click="moveHandler">
                    목록
                  </button>
                </div>
              </td>
            </tr>
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
      console.log(this.selectedHotplace.contentId);
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
        }

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

<style scoped></style>