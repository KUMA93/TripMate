import axios from 'axios'

// axios.create({옵션}) : 옵션 정보를 갖는 axios 객체를 생성해서 리턴
export default axios.create({
    baseURL: `https://api.openweathermap.org/data/2.5`,
    headers: {
        "Content-Type": 'application/json',
    }
})