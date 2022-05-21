import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import 'jquery/dist/jquery.min'
import './assets/css/bootstrap.min.css'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/css/articles.css'
import './assets/css/main.css'
import axios from './plugins/axios'
import store from "@/store/store"
import "./permission"
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
// import LmLoading from '/src/components/loading'
// import { Loading } from 'element-ui';


Vue.use(mavonEditor)
Vue.prototype.$http = axios
Vue.use(ElementUI)
Vue.config.productionTip = false
// Vue.use(LmLoading)
// Vue.prototype.$lmLoading=LmLoading

Vue.prototype.openLoading = function() {
  const loading = this.$loading({           // 声明一个loading对象
    lock: true,                             // 是否锁屏
    text: '加载中',                         // 加载动画的文字
    spinner: 'el-icon-loading',             // 引入的loading图标
    background: 'rgba(0, 0, 0, 0.8)',       // 背景颜色
    target: '.el-table, .table-flex, .region',       // **需要遮罩的区域，这里写要添加loading的选择器**
    fullscreen: false,
    customClass: 'loadingclass'             // **遮罩层新增类名，如果需要修改loading的样式**
  })
  setTimeout(function () {                  // 设定定时器，超时5S后自动关闭遮罩层，避免请求失败时，遮罩层一直存在的问题
    loading.close();                        // 关闭遮罩层
  },3000)
  return loading;
}

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
