<template>
    <b-container class="bv-example-row mt-3">
      <b-row>
        <b-col>
          <b-alert variant="secondary" show><h3>회원정보수정</h3></b-alert>
        </b-col>
      </b-row>
      <b-row>
        <b-col></b-col>
        <b-col cols="8">
          <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
            <b-form class="text-left">
              <b-form-group label="아이디:" label-for="id">
                <b-form-input
                  id="id"
                  ref="id"
                  v-model="newUser.id"
                  required
                  @keyup.enter="confirm"
                  readonly
                ></b-form-input>
              </b-form-group>
              <b-form-group label="이름:" label-for="name">
                <b-form-input
                  id="name"
                  ref="name"
                  v-model="newUser.name"
                  required
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-form-group>
              <b-form-group label="이메일:" label-for="email">
                <b-form-input
                  id="email"
                  ref="email"
                  v-model="newUser.email"
                  required
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-form-group>
              <b-form-group label="비밀번호:" label-for="pass" hidden>
                <b-form-input
                  id="pass"
                  ref="pass"
                  v-model="newUser.pass"
                  required
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-form-group>
              <b-button type="button" variant="primary" class="m-1" @click="confirm">수정</b-button>
              <b-button type="button" variant="danger" class="m-1" @click="moveHandler">취소</b-button>
            </b-form>
          </b-card>
        </b-col>
        <b-col></b-col>
      </b-row>
    </b-container>
  </template>
  
  <script>
  import http from '@/api/http'
  import { mapActions, mapState, mapGetters } from "vuex";

  const UserStore = "UserStore";


  export default {
    name: "UserUpdate",
  
    data() {
      return {
        newUser: {
          id: null,
          name: null,
          email: null,
          pass: null,
        },
        pwFlag: false,
      };
    },
    
    created() {
        http.get(`/rest/user/search/${this.userInfo.id}`)
            .then(({data}) => {
                this.newUser = data
            })
            .catch(err => {
                console.log(err)
            })
    },

    computed: {
        ...mapState(UserStore, ["userInfo"]),
        ...mapGetters(["checkUserInfo"]),
    },
  
    methods: {
        ...mapActions(UserStore, ["userLogout"]),
      moveHandler() {
        this.$router.push({ name: "login" });
      },
      confirm() {
        let err = false
        let msg = ""
  
        !this.newUser.id &&
          ((msg = "아이디를 입력해주세요"), (err = true), this.$refs.id.focus())
        !err & this.newUser.name &&
          ((msg = "이름을 입력해주세요"), (err = true), this.$refs.name.focus())
        !err & this.newUser.email &&
          ((msg = "이메일을 입력해주세요"), (err = true), this.$refs.email.focus())
        if (err) {
          alert(msg);
        } else {
          http.put('rest/user/update', this.newUser)
            .then(({ data }) => {
              if (data == 'success') {
                alert("수정 완료");
                this.userLogout(this.userInfo.id);
                sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
                sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
                this.moveHandler();
              }
            })
            .catch(err => {
              alert(err.response.data)
            })
        }
      },
    }
  };
  </script>
  
  <style></style>
  