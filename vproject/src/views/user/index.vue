<template>
    <!--整体分为三个部分-->
    <!--user-header  头部的搜索框-->
   <div class="user-header">
       <!--handleEa方法是新增数据，传入add表示新增-->
     <el-button
       type="primary"
       @click="handleEa('add')"
     >+新增</el-button>
     <!--form表单的model保存搜索的信息-->
     <el-form
       :inline="true"
       :model="formInline"
     >
       <el-form-item label="请输入">
         <el-input
           v-model="formInline.keyword"
           placeholder="请输入用户名"
         />
       </el-form-item>
       <el-form-item>
        <!--handleSerch搜索方法-->
         <el-button
           type="primary"
           @click="handleSerch"
         >搜索</el-button>
       </el-form-item>
     </el-form>
   </div>
    <!--表格部分内容-->
   <div class="table">
    <!--table的 data和列的信息都是等会定义-->
     <el-table
       :data="list"
       style="width: 100%"
       height="500px"
     >
       <el-table-column
         v-for="item in tableLabel"
         :key="item.label"
         :prop="item.prop"
         :label="item.label"
         :width="item.width?item.width:125"
       >
       </el-table-column>
 
       <el-table-column
         label="操作"
         fixed="right"
         min-width="180"
       >
        <!--这一列我们使用插槽自定义数据，还可以获取到行和列的信息-->
         <template #default="scope">
          <!--其中定义编辑和删除按钮，传入行和列的信息，给对应的方法进行处理-->
          <!--编辑方法的第一个参数需要是"edit"，因为他和新增公用一个方法，需要参数来区分-->
           <el-button size="small"
       @click="handleEa('edit',scope)"
           >编辑</el-button>
           <el-button
             size="small"
             type="danger"
       @click="deleteUser(scope)"
           >删除</el-button>
         </template>
       </el-table-column>
     </el-table>
       <!--分页器，total表示总条数,默认一页10条,@current-change是页数发生改变时触发-->
     <el-pagination
       background
       small
       layout="prev, pager, next"
       :total="config.total"
       @current-change="changePage"
     />
   </div>
 
 <!--el-dialog是一个弹出窗，在里面我们可以新增或编辑用户-->
 <!--v-model是否显示，handleClose关闭触发的方法，title显示的标题-->
   <el-dialog
     v-model="dialogVisible"
     :before-close="handleClose"
     :title="action=='add'?'新增用户':'编辑用户'"
     width="50%"
   >
   <!--ref是获取组件实例的关键-->
     <el-form
       :model="formUser"
       label-width="60px"
       ref="userFrom"
     >
       <el-row>
         <el-col :span="12">
               <!--el-form-item上必须要有一个prop其中的值就是内部表单的v-model绑定的属性，比如formUser.name，那这个prop的值就是name-->
           <el-form-item label="姓名" prop="name" :rules="[{ required: true, message: '姓名是必填项' }]">
             <el-input
               placeholder="请输入姓名"
               v-model="formUser.name"
             />
           </el-form-item>
         </el-col>
         <el-col :span="12">
           <el-form-item label="年龄" prop="age"  :rules="[{ required: true, message: '年龄是必填项' },
         { type:'number', message: '请输入数字' }]">
             <el-input
               placeholder="请输入年龄"
               v-model.number="formUser.age"
             />
           </el-form-item>
         </el-col>
       </el-row>
       <el-row>
         <el-col :span="12">
           <el-form-item label="性别" prop="sex" :rules="[{ required: true, message: '性别是必选项' }]">
             <el-select 
               v-model="formUser.sex"
               class="m-2"
               placeholder="请选择"
               size="large"
             >
               <el-option 
                 label="男"
                 value="1"
               />
               <el-option
                 label="女"
                 value="0"
               />
             </el-select>
           </el-form-item>
         </el-col>
         <el-col :span="12">
           <el-form-item label="时间" prop="birth" :rules="[{ required: true, message: '时间是必填项' }]">
             <el-date-picker
               v-model="formUser.birth"
               type="date"
               label="Pick a date"
               placeholder="请选择"
               style="width: 100%"
             />
           </el-form-item>
         </el-col>
       </el-row>
       <el-row>
         <el-form-item label="地址" prop="addr" :rules="[{ required: true, message: '地址是必填项' }]">
         <el-input
               placeholder="请输入地址"
               v-model="formUser.addr"
             />
           </el-form-item>
       </el-row>
       <el-row style="justify-content: right;">
         <el-form-item >
          <!--submitForm点击提交触发的方法-->
         <el-button type="primary"  @click="submitForm(formUser)">
         提交
       </el-button>
     </el-form-item> 
     <el-form-item >
         <!--handleclose点击取消触发的方法-->
         <el-button type="primary"  @click="handleclose">
         取消
       </el-button>
     </el-form-item>
       </el-row>
     
       
     </el-form>
     
   </el-dialog>
 
 </template>
 <script setup>
 import {
   ref,
   computed,
   reactive,
   watch,
   getCurrentInstance,
   onMounted,
 } from "vue";
 import { useRouter } from "vue-router";
 import { useStore } from "vuex";
 let { proxy } = getCurrentInstance();
 //list，table数据
 let list = reactive([]);
 //这个是分页器要用的，和请求用户列表的对象
 let config = reactive({
   total: 0,
   page: 1,
   name: "",
 });
 //changePage ，当分页器页数发生改变后触发，接收到修改后的页数
 let changePage = (page) => {
   //getUserData是请求use数据的方法，下面定义，把config中的page改变，并传入getUserData中
   config.page = page;
   getUserData(config);
 };
 //tableLabel，table的列数据
 const tableLabel = reactive([
   {
     prop: "name",
     label: "姓名",
   },
   {
     prop: "age",
     label: "年龄",
   },
   {
     prop: "sexLabel",
     label: "性别",
   },
   {
     prop: "birth",
     label: "出生日期",
     width: 200,
   },
   {
     prop: "addr",
     label: "地址",
     width: 320,
   },
 ]);
 //getUserData 获取user列表
 let getUserData = async (config) => {
   let res = await proxy.$api.getUserData(config);
   //获取到数据后，把总条数赋值为config的total
   config.total = res.data.count;
   //先把list清空
   list.splice(0, list.length);
   //格式化一下数据，接口的sex值是0或1
   list.push(
     ...res.data.list.map((item) => {
       item.sexLabel = item.sex == "0" ? "女" : "男";
       return item;
     })
   );
 };
 //搜索from的数据
 const formInline = reactive({
   keyword: "",
 });
 //点击搜索时，把搜索from的数据传递给config.name，然后调用getUserData(config)
 const handleSerch = () => {
   config.name = formInline.keyword;
   getUserData(config);
 };
 //弹出窗是否显示，默认不显示
 let dialogVisible = ref(false);
 //格式化时间的方法，添加数据和修改会有一个时间的控件选择
 const timeFormat = (time) => {
       var time = new Date(time);
       var year = time.getFullYear();
       var month = time.getMonth() + 1;
       var date = time.getDate();
       function add(m) {
         return m < 10 ? "0" + m : m;
       }
       return year + "-" + add(month) + "-" + add(date);
     };
 //formUser 是弹出窗中的form表单的数据
 let formUser = reactive({});
 //弹出窗关闭的方法
 const handleClose = (done) => {
   ElMessageBox.confirm("确认关闭吗")
     .then(() => {
     //确认关闭后，需要重置表单的数据
        proxy.$refs.userFrom.resetFields()
       done();
     })
     .catch(() => {
       // catch error
     });
 };
  //action是add和是edit，默认是add，表示新增
 let action=ref("add")
 //handleEa，当点击新增或者是编辑时触发
 let handleEa=(item,{row}={})=>{
   //首先就是判断第一个参数，更改action 的值
   item=="add"?action.value="add":action.value="edit"
   //然后把弹出窗显示出来
   dialogVisible.value=true
   //然后把弹出窗显示出来,如果是编辑则把row中的数据传递给formUser
   if( item=="edit"){
   //先格式化sex字段
   row.sex=='0'?row.sex='女':row.sex='男'
   //这个给对象赋值的操作要放在$nextTick方法中，以免数据的初始化会出现问题
   proxy.$nextTick(()=>{
     Object.assign(formUser,row)
 
   })
   }
 
 }
  //submitForm提交的方法
 let submitForm=(fromUser)=>{
  //先验证表单数据，是否符合规则
   proxy.$refs.userFrom.validate(async(flag)=>{
       //flag为true表示验证成功
     if(flag){
       //先把出生日期格式化一下
       formUser.birth = timeFormat(formUser.birth);
       let res;
        //判断action的值，可以知道是add的提交还是编辑用户的提交
       if(action.value=="add"){
        //如果是add则触发addUser请求
       res=await proxy.$api.addUser(fromUser)
       }else{
       //如果是edit则触发updateUser方法
       res=await proxy.$api.updateUser(fromUser)
       }
       //如果返回的数据的code为200，表示请求成功
       if(res.code==200){
       //我们需要重新获取用户列表，把弹出窗隐藏，然后清空表单
         getUserData(config);
       dialogVisible.value=false
       proxy.$refs.userFrom.resetFields()
       }
       }else{
         ElMessage({
           showClose:true,
           message:"请输入完整信息",
           type:'error'
         })
       }
 
   }
   )
    
 }
 
 //这个是点击取消触发的方法
 let handleclose=()=>{
   dialogVisible.value=false
   proxy.$refs.userFrom.resetFields()
 }
 
 
 
 //deleteUser点击删除用户触发的方法
 let deleteUser= async({row})=>{
   ElMessageBox.confirm("确认删除吗吗")
     .then(async() => {
      //调用deleteUser需要传入当前行的id
     let res=  await proxy.$api.deleteUser({id:row.id})
     if(res.code==200){
       ElMessage({
           showClose:true,
           message:"删除成功",
           type:"success"
         })
          //成功后重新获取数据
       getUserData(config);
     }
     })
     .catch(() => {
       // catch error
     });
 }
 
 onMounted(() => {
      //在Mounted时执行一下getUserData
   getUserData(config);
 });
 </script>
<style lang='less' scoped>
.table {
  height: 550px;
  position: relative;
  .el-pagination {
    position: absolute;
    right: 0;
    bottom: 0;
  }
}
.user-header {
  display: flex;
  justify-content: space-between;
}
</style>
 
 