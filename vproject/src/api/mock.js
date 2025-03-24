import Mock from "mockjs"
//引入获取数据的对象
import permissionApi from "./mockData/permission"
import homeApi from "./mockData/home"
import userApi from "./mockData/user"
//拦截指定接口，返回一个回调函数的返回值
Mock.mock(/home\/getTableData/,homeApi.getTableData)
Mock.mock(/home\/getCountData/,homeApi.getCountData)
Mock.mock(/home\/getEchartsData/,homeApi.getEchartsData)

Mock.mock(/user\/getUserData/,userApi.getUserList)
Mock.mock(/user\/addUser/,"post",userApi.createUser)
Mock.mock(/user\/updateUser/,"post",userApi.updateUser)
Mock.mock(/user\/deleteUser/,"post",userApi.deleteUser)




//拦截指定接口，返回一个回调函数的返回值
//第一个参数使用正则的方式匹配拦截请求，第二个是请求方式，第三个是拦截后调用的方法
Mock.mock(/permission\/getMenu/,"post",permissionApi.getMenu)
