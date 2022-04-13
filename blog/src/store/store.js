import Vue from "vue"
import Vuex from "vuex"

Vue.use(Vuex)

export default new Vuex.Store({
    state:{
        token: '',
        //方法一 localStorage.getItem("token")
        //反序列化获取session会话中的 userInfo对象
        userInfo:JSON.parse(sessionStorage.getItem("userInfo"))
    },
    mutations: {
        // 存储token
        SET_TOKEN: (state, token) => {
            state.token = token
            sessionStorage.setItem("token", token)
        },
        // 存储当前用户信息
        SET_USERINFO: (state, userInfo) => {
            state.userInfo = userInfo
            sessionStorage.setItem("userInfo", JSON.stringify(userInfo))
        },
        // 移除token 和 userInfo
        REMOVE_INFO: (state) => {
            state.token = ''
            state.userInfo = {}
            sessionStorage.setItem("token", '')
            sessionStorage.setItem("userInfo", JSON.stringify(''))
        }
    },
    getters: {
        //获取信息
        getUser: state => {
            return state.userInfo
        }
    },
    actions: {

    },
    modules: {

    }

})

