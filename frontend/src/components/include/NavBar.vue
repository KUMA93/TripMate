<template>
  <div id="nav">
  <b-navbar toggleable="lg" type="dark" variant="info">
    <b-navbar-brand href="/">EnjoyTrip</b-navbar-brand>

    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

    <b-collapse id="nav-collapse" is-nav>
      <b-navbar-nav>
        <b-nav-item><router-link class="nav-link" to="/attraction">지역별여행지</router-link></b-nav-item>
        <b-nav-item><router-link class="nav-link" to="/tripplan">나의여행계획</router-link></b-nav-item>
        <b-nav-item><router-link class="nav-link" to="/hotplace">핫플자랑하기</router-link></b-nav-item>
        <b-nav-item><router-link class="nav-link" to="/board">여행정보공유</router-link></b-nav-item>
        <b-nav-item><router-link class="nav-link" to="/notice">공지사항</router-link></b-nav-item>
      </b-navbar-nav>

      <!-- Right aligned nav items -->
      <b-navbar-nav class="ml-auto">
        <b-nav-item-dropdown right>
          <!-- Using 'button-content' slot -->
          <template #button-content>
            <span>User</span>
            <b-avatar variant="info"></b-avatar>
          </template>
          <b-dropdown-item href="#">마이페이지</b-dropdown-item>
          <b-dropdown-item href="#">Sign Out</b-dropdown-item>
        </b-nav-item-dropdown>
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>
</div>
</template>

<script>
export default {
  name: 'NavBar',
  components: {},
  data() {
    return {
      message: '',
    };
  },
  created() {},
  methods: {},
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
