<template>
  <div id="nav" shadow>
    <b-navbar toggleable="lg">
      <b-navbar-brand href="/">
        <router-link :to="{ name: 'main' }">
          <b-img
            :src="require('@/assets/img/logos/logo_long.png')"
            id="logo"
            class="d-inline-block align-top"
            alt="logo"
          ></b-img>
        </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item
            ><router-link class="nav-link" to="/attraction"
              ><span class="nav-menu"
                ><b-icon icon="pin-map-fill"></b-icon> 지역별여행지</span
              ></router-link
            ></b-nav-item
          >
          <b-nav-item
            ><router-link class="nav-link" to="/tripplan"
              ><span class="nav-menu"
                ><b-icon icon="cloud-sun-fill"></b-icon> 지역별날씨정보</span
              ></router-link
            ></b-nav-item
          >
          <b-nav-item
            ><router-link class="nav-link" to="/hotplace"
              ><span class="nav-menu"
                ><b-icon icon="bookmark-heart-fill"></b-icon> 핫플자랑하기</span
              ></router-link
            ></b-nav-item
          >
          <b-nav-item
            ><router-link class="nav-link" to="/board"
              ><span class="nav-menu"
                ><b-icon icon="chat-quote-fill"></b-icon> 여행정보공유</span
              ></router-link
            ></b-nav-item
          >
          <b-nav-item
            ><router-link class="nav-link" to="/notice"
              ><span class="nav-menu"
                ><b-icon icon="megaphone-fill"></b-icon> 공지사항</span
              ></router-link
            ></b-nav-item
          >
        </b-navbar-nav>

        <!-- after login -->
        <b-navbar-nav class="ml-auto" v-if="userInfo">
          <b-nav-item class="align-self-center">
            <b-avatar variant="info" size="2rem"></b-avatar>
            <span class="nav-menu"> {{ userInfo.name }}님 환영합니다. </span>
          </b-nav-item>
          <b-nav-item class="align-self-center" @click="onClickMypage">
            <span class="nav-menu"> 내정보보기 </span>
          </b-nav-item>
          <b-nav-item
            class="align-self-center link"
            @click.prevent="onClickLogout"
            ><span class="nav-menu">로그아웃</span></b-nav-item
          >
        </b-navbar-nav>
        <!-- before login -->
        <b-navbar-nav class="ml-auto nav-menu" v-else>
          <b-nav-item-dropdown right>
            <template #button-content>
              <b-icon icon="people" font-scale="2" class="nav-menu"></b-icon>
            </template>
            <b-dropdown-item href="#">
              <router-link :to="{ name: 'join' }" class="link">
                <span class="nav-menu"
                  ><b-icon icon="person-circle"></b-icon> 회원가입</span
                >
              </router-link>
            </b-dropdown-item>
            <b-dropdown-item href="#" class="nav-menu">
              <router-link :to="{ name: 'login' }" class="link">
                <span class="nav-menu"
                  ><b-icon icon="key"></b-icon> 로그인</span
                >
              </router-link>
            </b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";

const UserStore = "UserStore";

export default {
  name: "NavBar",
  components: {},
  data() {
    return {};
  },
  computed: {
    ...mapState(UserStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },

  created() {
    console.log(this.userInfo);
  },

  methods: {
    ...mapActions(UserStore, ["userLogout"]),
    onClickLogout() {
      console.log(this.userInfo.id);
      this.userLogout(this.userInfo.id);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push({ name: "main" });
    },

    onClickMypage() {
      this.$router.push({ name: "mypage" });
    },
  },
};
</script>

<style scope>
img {
  width: 150px;
}

.nav {
}

.header {
  padding: 30px;
  text-align: center;
  box-shadow: 0px 1px 10px rgba(159, 157, 157, 0.3);
}

.nav-menu {
  font-weight: bold;
  color: #252525;
  /* opacity: 0.85; */
}

.nav-menu:hover {
  color: #58b7dc;
}
</style>
