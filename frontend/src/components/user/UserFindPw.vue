<template>
    <b-container class="bv-example-row mt-3">
      <b-row>
        <b-col>
          <b-alert variant="secondary" show><h3>정보확인</h3></b-alert>
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
                  v-model="user.id"
                  required
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-form-group>
              <b-form-group label="이메일:" label-for="email">
                <b-form-input
                  id="email"
                  ref="email"
                  v-model="user.email"
                  required
                  @keyup.enter="confirm"
                ></b-form-input>
              </b-form-group>
              <b-button type="button" variant="primary" class="m-1" @click="confirm">확인</b-button>
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
  
  export default {
    name: "UserFindPw",
  
    data() {
      return {
        user: {
          id: null,
          email: null,
        },
      };
    },
  
    watch: {
      passwordCheck() {
        return this.pwFlag = this.passwordCheck == this.user.pass
      }
    },
  
    methods: {
      moveHandler() {
        this.$router.push({ name: "login" });
      },
      confirm() {
        let err = false
        let msg = ""
  
        !this.user.id &&
          ((msg = "아이디를 입력해주세요"), (err = true), this.$refs.id.focus())
        !err & this.user.email &&
          ((msg = "이메일을 입력해주세요"), (err = true), this.$refs.email.focus())
        if (err) {
          alert(msg);
        } else {
          http.post('rest/user/findPw', this.user)
            .then(({ data }) => {
              if (data == 'success') {
                this.$router.push({ name: "changePw", params: {id:this.user.id}});

            }})
            .catch(err => {
                alert(err.response.data.message)
            })
        }
      },
    }
  };
  </script>
  
  <style></style>
  