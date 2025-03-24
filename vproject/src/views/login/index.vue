<template>
    <!--使用elementui中的el-form组件，model绑定的是表单数据对象-->
  <el-form :model="loginForm" class="login-container">
    <h3>系统登录</h3>
    <el-form-item>
      <!--我们用两个输入框，并用 v-model双向绑定，就绑定到loginForm的属性上-->
      <el-input
        type="input"
        placeholder="请输入账号"
        v-model="loginForm.username"
      >
      </el-input>
    </el-form-item>
    <el-form-item>
      <el-input
        type="password"
        placeholder="请输入密码"
        v-model="loginForm.password"
      >
      </el-input>
    </el-form-item>
    <el-form-item>
           <!--点击这个按钮定义触发登录方法-->
      <el-button type="primary" @click="login"> 登录 </el-button>
    </el-form-item>
  </el-form>
</template>
<script setup>
//getCurrentInstance 用于获取组件实例对象
import { reactive,getCurrentInstance } from "vue";
//useRouter 获取路由器对象的方法
import { useRouter } from "vue-router";
import { useStore } from "vuex";
  const store = useStore();

//loginForm 表单数据对象，使用reactive包裹就可以变成响应式数据
const loginForm = reactive({
   username: "admin",
   password: "admin",
 });
 //获取路由器对象
const router = useRouter();
//获取组件实例对象
const { proxy } = getCurrentInstance();
//定义登录方法
const login = async () => {
        const res = await proxy.$api.getMenu(loginForm);
        //请求成功后，调用store的setmenu，修改menu的值
        store.commit("setMenu", res.data.menu);
     
        router.push({
          path: "/home",
        });
      };



</script>

<style lang="less" scoped>
.login-container {
  width: 350px;
  background-color: #fff;
  border: 1px solid #eaeaea;
  border-radius: 15px;
  padding: 35px 35px 15px 35px;
  box-shadow: 0 0 25px #cacaca;
  margin: 180px auto;
  h3 {
    text-align: center;
    margin-bottom: 20px;
    color: #505450;
  }
  :deep(.el-form-item__content) {
    justify-content: center;
  }
}
</style>
