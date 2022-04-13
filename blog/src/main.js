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


Vue.use(mavonEditor)
Vue.prototype.$http = axios
Vue.use(ElementUI)
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
