<template>
  <b-container class="mt-4" v-if="userInfo">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>내정보</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-jumbotron>
          <template #header>My Page</template>

          <template #lead> 내 정보 확인페이지입니다. </template>

          <hr class="my-4" />

          <b-container class="mt-4">
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">아이디</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.id }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이름</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.name }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이메일</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.email }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
          </b-container>
          <hr class="my-4" />

          <b-button variant="primary" class="mr-1" @click="updateHandler">정보수정</b-button>
          <b-button variant="danger" @click.prevent="onClickWithdraw">회원탈퇴</b-button>
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapState, mapGetters } from "vuex";

const UserStore = "UserStore";

export default {
  name: "UserMyPage",

  components: {},
  computed: {
    ...mapState(UserStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },

  methods: {
    ...mapActions(UserStore, ["userWithdraw"]),
    updateHandler() {
      this.$router.push({name:"update"})
    },
    onClickWithdraw() {
      if (confirm("정말 탈퇴 하시겠습니까?") == true){//확인
        this.userWithdraw(this.userInfo.id)
        sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
        sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
        alert("탈퇴가 완료되었습니다.");
        this.$router.push({name:"main"})
      }else{//취소
        return;
      }      
    }
  },
};

</script>

<style></style>
