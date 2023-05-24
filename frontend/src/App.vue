<template>
  <div id="app">
    <nav-bar></nav-bar>
    <router-view></router-view>
  </div>
</template>

<script>
import NavBar from "@/components/include/NavBar";
export default {
  name: "App",
  components: {
    NavBar,
  },
};
</script>

<style>
@font-face {
  font-family: "SBAggroB";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2108@1.1/SBAggroB.woff")
    format("woff");
  font-weight: normal; /* 300, 500, 700 */
  font-style: normal;
}

@font-face {
  font-family: "GongGothicMedium";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-10@1.0/GongGothicMedium.woff")
    format("woff");
  font-weight: normal; /* 300, 400, 700 */
  font-style: normal;
}

#app {
  font-family: "SBAggroB";
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

a {
  font-weight: bold !important;
  color: #686767 !important;
}

a:hover {
  font-weight: bold;
  color: #58b7dc !important;
  text-decoration: none;
}
</style>
