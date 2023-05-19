<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert variant="secondary" show><h3>회원가입</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input
                id="userid"
                ref="id"
                v-model="user.id"
                required
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="password">
              <b-form-input
                type="password"
                id="password"
                ref="pass"
                v-model="user.pass"
                required
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호 확인:" label-for="passwordCheck">
              <b-form-input
                type="password"
                id="passwordCheck"
                ref="pwch"
                v-model="passwordCheck"
                required
                @keyup.enter="confirm"
              ></b-form-input>
            </b-form-group>
            <b-form v-if="pwFlag==false && passwordCheck.length!=0">비밀번호가 일치하지 않습니다.</b-form>
            <b-form-group label="이름:" label-for="name">
              <b-form-input
                id="name"
                ref="name"
                v-model="user.name"
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
            <b-button type="button" variant="primary" class="m-1" @click="confirm">회원가입</b-button>
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
  name: "UserRegister",

  data() {
    return {
      user: {
        id: null,
        pass: null,
        name: null,
        email: null,
      },
      passwordCheck: '',
      pwFlag: false,
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
      !err & this.user.pass &&
        ((msg = "비밀번호를 입력해주세요"), (err = true), this.$refs.pass.focus())
      !err & this.passwordCheck &&
        ((msg = "비밀번호확인을 입력해주세요"), (err = true), this.$refs.pwch.focus())
      !err & this.user.name &&
        ((msg = "이름을 입력해주세요"), (err = true), this.$refs.name.focus())
      !err & this.user.email &&
        ((msg = "이메일을 입력해주세요"), (err = true), this.$refs.email.focus())
      if (err) {
        alert(msg);
      } else {
        http.post('rest/user/regist', this.user)
          .then(({ data }) => {
            if (data == 'success') {
              alert("가입 완료");
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
