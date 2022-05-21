import axios from 'axios'
import Element from "element-ui"
import store from "@/store/store"
import router from "@/router/index"
import baseUrl from '/src/baseUrl'
import { Loading } from 'element-ui';

axios.defaults.baseURL = baseUrl.BaseURL


let loading = null //定义loading变量

//开始 加载loading
let startLoading=()=>{
    loading = Loading.service({
        lock: true,
        text: 'loading……',
        background: 'rgba(0, 0, 0, 0.7)'
    })
}
//结束 取消loading加载
let endLoading=()=>{
    loading.close()
}

//showFullScreenLoading() 与 tryHideFullScreenLoading() 目的是合并同一页面多个请求触发loading

let needLoadingRequestCount = 0 //声明一个变量

let showFullScreenLoading=()=> {
    if (needLoadingRequestCount === 0) { //当等于0时证明第一次请求 这时开启loading
        startLoading()
    }
    needLoadingRequestCount++ //全局变量值++
}

let tryHideFullScreenLoading=()=> {
    if (needLoadingRequestCount <= 0) return //小于等于0 证明没有开启loading 此时return
    needLoadingRequestCount-- //正常响应后 全局变量 --
    if (needLoadingRequestCount === 0) {  //等于0 时证明全部加载完毕 此时结束loading 加载
        endLoading()
    }
}

axios.interceptors.request.use(
    config => {
        if (store.state.token) {
            config.headers.Authorization = `${store.state.token}`
        }
        //开启loading加载
        showFullScreenLoading()
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
        //关闭loading加载
        tryHideFullScreenLoading()
        const res = response.data;
        console.log("后置拦截")
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
