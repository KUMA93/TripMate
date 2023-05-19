import Vue from 'vue'
import Vuex from 'vuex'
import http from "@/api/http"

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    attractions: [],
    attraction: null,
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

    searchAttraction({ commit }, contentType, sidoCode, gugunCode, word) {
      let url = `/rest/trip/search?contentType=${contentType}&sido=${sidoCode}&gugun=${gugunCode}&keyword=${word}`
      console.log("searchUrl", url);
      http.get(url)
        .then(({ data }) => {
          // console.log(data);
          commit("SET_ATTRACTION_LIST", data);
        })
        .catch(err => {
          console.log(err);
        })
    },
    
    setAttractionDetail({ commit }, attraction) {
      commit("SET_ATTRACTION_DETAIL", attraction);
    }
    ///////////////////////// Attraction End //////////////////////////////////
  },
  modules: {
  }
})
