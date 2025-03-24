//这个createRouter是用来创建router的，createWebHashHistory则是创建hash模式，如果使用hash模式则会在地址栏带有一个#号
import {createRouter,createWebHashHistory} from "vue-router"
//配置路由
const routes=[
		//这个路由时用于匹配所有不存在的路由，并重新定位到login路由
    {
        path:"/:catchAll(.*)",
        redirect:"/login"
    },
    //首先我们要写登录页面，所以先定义登录的路由
    {
        path:"/login",
        name:'login',
        //这里组件我们使用懒加载的方式引入，组件等会创建
        component:()=>import("../views/login/index.vue")
    },
    {
        path:"/",
        component: ()=>import("../views/main.vue"),
        name:'main',
        redirect:"/home",
        children: [
			{
			        path:"home",
			        component: ()=>import("../views/home/index.vue"),
			        name:'home'
			    },
                {
			        path:"user",
			        component: ()=>import("../views/user/index.vue"),
			        name:'user'
			    }
		]
    }
]
//使用createRouter创建路由器，并返回出去
export default createRouter({
     //history用于设置路由模式
    history:createWebHashHistory(),
     //routes则是路由信息
    routes
})
