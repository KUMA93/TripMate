import Vue from 'vue'
import Vuex from 'vuex'
import http from "@/api/http"
import createPersistedState from "vuex-persistedstate";

import UserStore from "@/store/modules/UserStore"

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    attractions: [],
    attraction: null,
    mapCenter: { lat: 37.500613, lon: 127.036431 }
  },
  getters: {
    allAttractions(state) {
      return state.attractions;
    },
  },
  mutations: {
    ///////////////////////// Attraction Start ////////////////////////////////
    SET_ATTRACTION_LIST(state, attractions) {
      state.attractions = attractions;
    },
    SET_ATTRACTION_DETAIL(state, attraction) {
      state.attraction = attraction;
    },
    SET_MAP_CENTER(state, mapCenter) {
      state.mapCenter = mapCenter;
    }
    ///////////////////////// Attraction End ////////////////////////////////
  },
  actions: {
    ///////////////////////// Attraction Start ////////////////////////////////
    getAttractionList({ commit }) {
      http.get('/rest/trip/search')
        .then(({ data }) => {
          // console.log(data);
          commit("SET_ATTRACTION_LIST", data);
        })
        .catch(err => {
          console.log(err);
        })
    },

    searchAttraction({ commit }, payload) {
      const searchParam = {
        contentType: 0,
        sidoCode: 0,
        gugunCode: 0,
        word: '',
        ...payload
      }

      let url = `/rest/trip/search?contentType=${searchParam.contentType}&sido=${searchParam.sidoCode}&gugun=${searchParam.gugunCode}&keyword=${searchParam.word}`
      console.log("searchUrl", url);
      http.get(url)
        .then(({ data }) => {
          console.log(data);
          commit("SET_ATTRACTION_LIST", data);
        })
        .catch(err => {
          console.log(err);
        })
    },
    
    setAttractionDetail({ commit }, attraction) {
      commit("SET_ATTRACTION_DETAIL", attraction);
    },

    setMapCenter({ commit }, { lat, lon }) {
      commit("SET_MAP_CENTER", {lat: lat, lon: lon});
    }
    ///////////////////////// Attraction End //////////////////////////////////
  },
  ///////////////////////// JWT 활용을 위한 모듈 ///////////////////////////////
  modules: {
    UserStore,
  },
  ///////////////////////// 토큰 저장을 위한 sessionStorage 활용 //////////////////////////
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
})
