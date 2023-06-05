import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import  '@/assets/css/global.css'
import 'element-ui/lib/theme-chalk/index.css';
import axios from "axios";
import ECharts from 'vue-echarts';

//导出aliplayer
import 'aliyun-aliplayer'
// 导出样式
import 'aliyun-aliplayer/dist/skins/default/aliplayer-min.css'

//配置请求拦截器 加上负载token
axios.interceptors.request.use(config=>{
  config.headers.Authorization=window.sessionStorage.getItem("uid")
  // console.log(config)
  return config;
})
axios.defaults.baseURL="http://localhost:3000"

Vue.config.productionTip = false

Vue.component("ECharts",ECharts)
Vue.use(ElementUI)
Vue.prototype.$http=axios

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
