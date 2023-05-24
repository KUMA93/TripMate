import weather from "@/api/weather";

const weatherOptions = {
  Clear: {
    iconName: 'brightness-high',
    title: '맑음',
  },
  Clouds: {
    iconName: 'cloud',
    title: '흐림'
  },
  Thunderstorm: {
    iconName: 'cloud-lightning-rain',
    title: '천둥번개'
  },
  Drizzle: {
    iconName: 'cloud-drizzle',
    title: '이슬비'
  },
  Rain: {
    iconName: 'cloud-rain',
    title: '비'
  },
  Snow: {
    iconName: 'snow',
    title: '눈'
  },
  Haze: {
    iconName: 'cloud-haze',
    title: '안개'
  },
  Mist: {
    iconName: 'cloud-haze',
    title: '안개'
  },
  Fog: {
    iconName: 'cloud-haze',
    title: '안개'
  },
  Dust: {
    iconName: 'emoji-angry',
    title: '미세먼지'
  },
  Sand: {
    iconName: 'emoji-angry',
    title: '황사'
  },
}

const weatherStore = {
  namespaced: true,
  state: {
    current: {},
    forecast: [],
  },
  getters: {
    getCurrentWeather(state) {
      return state.current;
    }
  },
  mutations: {
    SET_CURRENT_WEATHER(state, current) {
      state.current = current;
    },

    SET_FORECAST(state, forecast) {
      state.forecast = forecast;
    }
  },
  actions: {
    getCurrentWeatherInfo({ commit }, city) {
      weather.get(`/weather?q=${city}&appid=63214dab9cc58f7d6e13fa6796c86792&units=metric`)
        .then(({ data }) => {
          const info = {
            weather: data.weather[0].main,
            temp: Math.round(data.main.temp),
            feels_like: Math.round(data.main.feels_like),
            humidity: data.main.humidity,
            ...weatherOptions[data.weather[0].main],
          }
          commit("SET_CURRENT_WEATHER", info);
        })
        .catch(err => {
          console.log(err);
      })
    },

    getForecastInfo({ commit }, city) {
      // console.log("forecast", city);
      weather.get(`/forecast?q=${city}&appid=83882eb76613ce7f7dedac8da3d3d70f&units=metric`)
        .then(({ data }) => {
          let arr = [];
          data.list.forEach(item => {
            const info = {
              date: item.dt_txt.slice(5,16),
              weather: item.weather[0].main,
              temp: Math.round(item.main.temp),
              ...weatherOptions[item.weather[0].main],
            }
            arr.push(info);
          });

          commit("SET_FORECAST", arr);
        })
        .catch(err => {
          console.log(err);
      })
    }
  },

}

export default weatherStore;