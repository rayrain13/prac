//从vue中引出createApp创建vue实例
import { createApp } from 'vue'
import App from './App.vue'
//引入路由
import router from "./router/index.js"
//引入这个less文件，这个文件在项目的资源中获取，按照路径放置好
import "./assets/less/index.less"
//引入ElementPlusIconsVue 中所有的组件
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import store from "./store/index.js"



//引入mock，让其生效
import "./api/mock.js"

import api from './api/api'



let app= createApp(App)

//for循环，注册ElementPlusIconsVue 的组件
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
  }
  app.use(store)
  //使用一下路由
  app.use(router)

 //挂载节点
  app.mount('#app')
  //我们把暴露的请求方法对象，设置为app.config.globalProperties的一个属性$api(这个可以自己取名),app.config.globalProperties身上设置属性，可以在组件实例上访问，如果不了解在vue官网查阅
  app.config.globalProperties.$api=api
