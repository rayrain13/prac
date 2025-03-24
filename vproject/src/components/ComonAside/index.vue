<template>
    <!--我们使用el-aside的菜单组件-->
    
    <!--这里我们使用一个store的属性（等会定义）来控制菜单的宽度，因为菜单在导航栏有一个按钮，点击按钮可以折叠或展开菜单-->
   <el-aside :width="$store.state.isCollapse?'64px':'180px'">
    <!--el-menu的collapse表示是否折叠菜单，也是和isCollapse绑定-->
     <el-menu
       class="el-menu-vertical-demo"
       background-color="#545c64"
       :collapse="$store.state.isCollapse"
     >
       <!--如果不折叠则展示后台管理，折叠的话只展示后台-->
       <h3 v-show="!$store.state.isCollapse">后台管理</h3>
       <h3 v-show="$store.state.isCollapse">后台</h3>
      <!--菜单数据中会有两种情况，一种是有children，一种是没有的，这两种情况需要做不同的展示-->
       <!--noChildren方法会返回没有children的菜单，cilckmenu表示点击后跳转的方法，等一下定义
 -->
       <el-menu-item
         :index="item.name"
         v-for="item in noChildren()"
         :key="item.path"
         @click="cilckmenu(item)"
       >
        <!--使用component展示菜单对应的icon-->
         <component
           class="icons"
           :is="item.icon"
         ></component>
         <!--label 菜单的名称-->
         <span>{{item.label}}</span>
       </el-menu-item>
       
       <!--hasChildren方法会返回有children的菜单,如果没有用过el-menu的el-sub-menu可以到官网了解详细介绍-->
       <el-sub-menu
         v-for="item,index in hasChildren()"
         :index="item.label"
         :key="index"
       >
         <template #title>
           <el-icon>
             <component
             class="icons"
             :is="item.icon"
           ></component>
           </el-icon>
           
           <span>{{item.label}}</span>
         </template>
 
         <el-menu-item-group>
           <el-menu-item
             :index="subItem.name"
             v-for="subItem,subIndex  in item.children"
             :key="subIndex"
         @click="cilckmenu(subItem)"
           >
             <component
               class="icons"
               :is="subItem.icon"
             ></component> <span>{{subItem.name}}</span>
           </el-menu-item>
         </el-menu-item-group>
 
       </el-sub-menu>
 
     </el-menu>
   </el-aside>
 </template>
 
 <script setup>
 import { ref, computed, reactive, watch } from "vue";
 import { useRouter } from "vue-router";
 import { useStore } from "vuex";
 let router=useRouter()
 let store=useStore()
 
 //获取到vuex中保存的menu
 let asyncList=store.state.menu
 
 //noChildren 筛选出没有子菜单的菜单
 const noChildren = () => {
   return asyncList.filter((item) => !item.children);
 };
 //hasChildren 筛选出有子菜单的菜单
 const hasChildren = () => {
   return asyncList.filter((item) => item.children);
 };
 
 //点击菜单触发的方法
 function cilckmenu(item){
 //点击菜单触发的方法，跳转到菜单对应的路由页面
     router.push({
       path:item.path
     })
 
 
 }
 
 
 </script>
 
 <style lang='less' scoped>
 .icons {
   width: 20px;
   height: 20px;
   margin-right: 5px;
 
 }
 .el-menu {
   h3 {
     text-align: center;
     color: white;
     line-height: 36px;
   }
 }
 .el-menu-vertical-demo {
   border-right: 0;
 }
 .el-menu-item,.el-sub-menu__title *{
   color: white;
 }
 </style>
 
   