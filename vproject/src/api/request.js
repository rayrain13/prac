import axios from "axios"
//从element-plus中引入一个提醒
import {ElMessage} from "element-plus"
//定义一个默认的报错信息
const NETWORK_ERROR="网络错误"
//axios.create，创建一个axios实例，可以在里面配置默认信息
let service=axios.create({
	//baseURL前缀，也就是说请求的接口前面都会再加一个api
	//比如请求/user,就会变为/api/user
    baseURL: "/api"
})

//请求前拦截器，请求发送前可以做一些操作，这里我们暂时没有
service.interceptors.request.use((req)=>{

    //需要把请求返回出去
    return req
})


//请求完成后拦截器
service.interceptors.response.use((res)=>{
      //从请求返回的数据中解构出 code(状态码)和msg(后端返回的一些信息)和data数据
    let {code,message,data} =res
     //如果状态码是200，或者请求的状态码是200则把数据返回
    if(code=="200"||res.status==200){
        return data
    }else{
        //如果失败我们使用ElMessage.error发送一个失败的提醒
        ElMessage.error(message||NETWORK_ERROR)
        //并且返回一个失败的promise
        return Promise.reject(message||NETWORK_ERROR)
    }
    
})

//二次封装请求 ，会接收到请求信息
function request(options){
    //如果没有设置默认请求方式为get
    options.method=options.method||"get"
    //如果请求方式为get
    if(options.method.toLowerCase()=="get"){
       //则要为请求信息添加一个params，因为axios中get请求的参数需要用params携带
        options.params=options.query||options.data
    }
    
    //函数的返回值就是，service（也就是axios实例）的返回值，需要把请求信息传进去
    return service(options)
}
//把二次封装的请求方法暴露出去
export default request
