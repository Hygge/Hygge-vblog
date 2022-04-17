import axios from 'axios'
import Element from "element-ui"
import store from "@/store/store"
import router from "@/router/index"
import baseUrl from '/src/baseUrl'

axios.defaults.baseURL = baseUrl.BaseURL
axios.interceptors.request.use(
    config => {
        if (store.state.token) {
            config.headers.Authorization = `${store.state.token}`
        }
        //console.log("前置拦截")
        // 可以统一设置请求头
        // config = {
        //     headers:{
        //         'X-Requested-With': 'XMLHttpRequest',
        //         'Content-Type': 'application/json; charset=UTF-8'
        //     }
        // }

        return config
    },
    err => {
        return Promise.reject(err)
    },
)

axios.interceptors.response.use(response => {
        const res = response.data;
        console.log("后置拦截")
        console.log(res.repCode)
        // 当结果的code是否为200的情况
        if (res.code === 200 || res.code === 404 || res.repCode === '0000'
        || res.repCode === '0011'
        ) {
            return response
        } else {
            // 弹窗异常信息
            Element.Message({
                message: res.msg,
                type: 'error',
                duration: 2 * 1000
            })
            // 直接拒绝往下面返回结果信息
            return Promise.reject(response.data.msg)
        }
    },
    error => {
        // 处理被拒绝的Promise对象
        console.log(error.message)
        // 如果错误信息存在，赋值
        if (error.response.data.msg){
            error.message = error.response.data.msg
        }
        if (error.response) {
            switch (error.response.status) {
                case 500:
                    Element.Message.error("服务器繁忙。。。", {duration: 2*1000})
                    // 方法返回一个带有拒绝原因的Promise对象
                    return Promise.reject(error)
                case 401:
                    store.commit("REMOVE_INFO")
                    router.push("/login")
                    return
                // 401 清除token信息并跳转到登录页面
                case 404:
                    // 404 跳转错误页面
                    router.push("/error")
                    return
                default :
                    // 状态码错误弹窗提示消息    设置2s消失
                    Element.Message.error(error.message,{duration: 2*1000})
                    return Promise.reject(error)
            }
        }
        // console.log(JSON.stringify(error));//console : Error: Request failed with status code 402
        return Promise.reject(error.response.data)
    })
export default axios
