//引入二次封装的请求方法
import request from "./request"

//默认暴露出一个对象，因为我们不止一个请求方法，所以要写在一个对象中
export default{
  //定义登录要发送的请求
  getMenu(params) {
    return request({
      url: '/permission/getMenu',
      method: 'post',
      data: params
    })
  },
  //在原来暴露出的对象中添加
 getTableData(params){
    return request({
        url:"/home/getTableData",
        method:"get",
        data:params,
    })
   },
   getCountData(params){
    return request({
        url:"/home/getCountData",
        method:"get",
        data:params,
    })
   },
   getEchartsData(params){
    return request({
        url:"/home/getEchartsData",
        method:"get",
        data:params,
    })
   } ,
   getUserData(params){
    return request({
        url:"/user/getUserData",
        method:"get",
        data:params,
    })
   } ,
  addUser(params){
    return request({
        url:"/user/addUser",
        method:"post",
        data:params,
    })
   } ,
   updateUser(params){
    return request({
        url:"/user/updateUser",
        method:"post",
        data:params,
    })
   } ,
   deleteUser(params){
    return request({
        url:"/user/deleteUser",
        method:"post",
        data:params,
    })
   },



}

