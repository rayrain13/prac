<template>
    <el-header>
            <!--l-context 是左侧导航栏的内容 -->
      <div class="l-context">
       <!--这个按钮，单击会触发handleriscoll，这个方法中我们会调用store的updateIsCollapse改变菜单的折叠情况 -->
        <el-button @click="handleriscoll">
            <el-icon ><Menu /></el-icon>
            </el-button>
       <!--我们要使用el-breadcrumb  做一个面包屑的效果 separator就是面包屑之间的分割符-->
        <el-breadcrumb separator="/" >
         <!--第一个面包屑默认就是首页，点击后会触发store中的selectMenu方法，这个方法我们等会再说用处-->
        <el-breadcrumb-item :to="{ path: '/home' }" @click=" store.commit('selectMenu',{path: '/home'})">首页</el-breadcrumb-item>
        <!--第二个面包屑就是当前所在的路由，current就是当前所在的路由信息，等会定义，需要用v-if判断如果不存在就不展示-->
        <el-breadcrumb-item v-if="current.label" :to="current.path">{{current.label}}</el-breadcrumb-item>
    
    
      </el-breadcrumb>
      
      </div>
     <!--r-context 是右侧导航栏的内容 -->
      <div class="r-context">
         <!--el-dropdown是elementui的下拉框组件 -->
        <el-dropdown>
          <!--这个是正常展示的内容-->
        <span class="el-dropdown-link user">
           <!--getUserImage返回一个图片的路径，传入图片的名字-->
          <img :src="getUserImage('user')" alt="">
        </span>
        
        <!--template #dropdown 定义下拉框的内容-->
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>个人中心</el-dropdown-item>
            <!--点击退出登录后，触发一个方法-->
            <el-dropdown-item @click="hanlego">退出登录</el-dropdown-item>
           
          </el-dropdown-menu>
        </template>
      </el-dropdown>
      </div>
    </el-header>
    </template>
<script setup>
import {useStore} from 'vuex'
import {computed} from 'vue'
import { useRouter } from "vue-router";
let router=useRouter()
let store=useStore()
//这个属性就是当前路由页面的信息，从store的currentMenu上获取，等会定义
let current=computed(()=>{
 return  store.state.currentMenu||{}
})

//getUserImage返回一个图片的信息，这个图片是保存在src下的assets中，在资源中获取
let getUserImage=( user)=>{
  return new URL(`../../assets/img/${user}.png`,import.meta.url).href
}
//handleriscoll 就是触发store的updateIsCollapse方法，传入取反的isCollapse，改变菜单的折叠状态
function handleriscoll(){
  store.commit("updateIsCollapse",!store.state.isCollapse)
}

function hanlego(){
 //退出登录的时候，跳转到login页面
  router.push({
    path:"/login"
  })

}
</script>
<style lang="less" scoped>

header{
  display: flex;
  justify-content: space-between;
    justify-content: space-between;
    align-items: center;
    width: 100%;
    background-color: #333;
    .el-breadcrumb{
      /deep/ span {
        color: #fff !important;
    }
    
  }
}
.l-context{
  margin-left: 20px;
  display: flex;
  align-items: center;
  .el-button{
    margin-right: 10px;
  }
  h3{
    color: white;
  }
}
.r-context{
 .user img{
  width: 50px;
  height: 50px;
  border-radius: 50%;

 }
}
</style>
    