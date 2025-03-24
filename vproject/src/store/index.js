import {createStore} from "vuex"
//createStore创建store实例
export default createStore({
	  //state则是保存一些数据的
		state:{
		menu:[],
        isCollapse:false,
        currentMenu:null
		},
		//mutations则是一些修改state的方法，这里我们定义了一个修改menu的方法，在登录后调用，设置menu的值
		mutations:{
  			setMenu(state, val) {
                state.menu = val
               
              },
              //updateIsCollapse修改isCollapse的方法 
		updateIsCollapse(state,value){
            state.isCollapse=value
        },
         //selectMenu改变selectMenu方法，传入一个路由信息
  selectMenu(state,value){
    //如果传入的路由信息是到home页面，则把currentMenu置为空，不是的话就把传入的路由信息赋值为currentMenu
      if(value.path=="/home"||value.path=="home"){
          state.currentMenu=null
      }else{
          state.currentMenu=value
          
      }
  }

        }
		
})
