<template>
  <div id="nav">
  <b-navbar toggleable="lg" type="light" variant="faded">
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
        <b-nav-item><router-link class="nav-link" to="/attraction">지역별여행지</router-link></b-nav-item>
        <b-nav-item><router-link class="nav-link" to="/hotplace">핫플자랑하기</router-link></b-nav-item>
        <b-nav-item><router-link class="nav-link" to="/weather">지역별 날씨정보</router-link></b-nav-item>
        <b-nav-item><router-link class="nav-link" to="/board">여행정보공유</router-link></b-nav-item>
        <b-nav-item><router-link class="nav-link" to="/notice">공지사항</router-link></b-nav-item>
      </b-navbar-nav>

      <!-- after login -->
      <b-navbar-nav class="ml-auto" v-if="userInfo">
        <b-nav-item class="align-self-center">
          <b-avatar variant="primary"></b-avatar>
          {{ userInfo.name }}({{ userInfo.id }})님 환영합니다.
        </b-nav-item>
        <b-nav-item class="align-self-center">
          <router-link :to="{ name: 'mypage' }" class="link align-self-center">내정보보기</router-link>
        </b-nav-item>
        <b-nav-item class="align-self-center link" @click.prevent="onClickLogout">로그아웃</b-nav-item>
      </b-navbar-nav>
      <!-- before login -->
      <b-navbar-nav class="ml-auto" v-else>
        <b-nav-item-dropdown right>
          <template #button-content>
            <b-icon icon="people" font-scale="2"></b-icon>
          </template>
          <b-dropdown-item href="#">
            <router-link :to="{ name: 'join' }" class="link">
              <b-icon icon="person-circle"></b-icon> 회원가입
            </router-link>
          </b-dropdown-item>
          <b-dropdown-item href="#">
            <router-link :to="{ name: 'login' }" class="link"> <b-icon icon="key"></b-icon> 로그인 </router-link>
          </b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";

const UserStore = "UserStore"

export default {
  name: 'NavBar',
  components: {},
  data() {
    return {};
  },
  computed: {
    ...mapState(UserStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },

  created() {

    console.log(this.userInfo)
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
  },
};
</script>

<style scope>
img {
  width: 150px;
}

#nav {
  font-weight: light;
}

.header {
  padding: 30px;
  text-align: center;
  box-shadow: 0px 1px 10px rgba(159, 157, 157, 0.3);
}

a {
  font-weight: bold;
  color: #2c3e50;
}

a:hover {
  color: #42b983;
}
</style>
