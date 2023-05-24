<template>
  <div>
    <b-container fluid class="bv-example-row mt-3 text-center">
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
                  <button class="btn btn-primary" @click="createHandler">
                    게시글 등록
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
  name: "HotPlaceWrite",
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
      article: {
        articleNo: "",
        userId: "",
        subject: "",
        content: "",
        hit: "",
        registerTime: "",
        contentId: "",
      },
    };
  },
  created() {
    this.setHotPlaceOri();
  },
  methods: {
    ...mapActions(["setMapCenter", "setHotplaceNull"]),

    moveHandler() {
      this.$router.push({ name: "HotPlaceList" });
    },

    setHotPlaceOri() {
      this.setHotplaceNull();
    },

    createHandler() {
      let err = false;
      let msg = "";
      let selectedHotplace = this.selectedHotplace;
      console.log(selectedHotplace);

      !this.article.subject &&
        ((msg = "글 제목을 입력해주세요"),
        (err = true),
        this.$refs.subject.focus());
      !err & (this.article.content.length == 0) &&
        ((msg = "글 내용을 입력해주세요"),
        (err = true),
        this.$refs.content.focus());
      !err & (this.selectedHotplace == null) &&
        ((msg = "장소를 선택해주세요"), (err = true));
      if (err) {
        alert(msg);
      } else {
        this.article.userId = this.userInfo.id;
        this.article.contentId = selectedHotplace.contentId;

        console.log(this.article);
        http
          .post("rest/hotplace", this.article)
          .then(({ data }) => {
            if (data == "success") {
              this.setHotplaceNull();
              alert("등록 완료");
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
#trip-list{
  height: 40vh;
  overflow-y: scroll;
}
</style>