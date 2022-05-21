<template>
  <div>
    <div class="login-title">
      <h5><a href="#/login">登录</a> | 注册 </h5>
      <div class="register-box">
        <div class="input-box">
          <input type="text" v-model="user.userName" placeholder="用户名">
          <input type="text" v-model="user.email" placeholder="邮箱">
          <input type="password" v-model="user.password" placeholder="密码">
        </div>
        <button type="submit" @click="submit">注册</button>
      </div>

    </div>
    <br/>
  </div>
</template>

<script>
import Element from "element-ui"

export default {
  name: "Register",
  data(){
    return {
      user:{
        userName:'',
        password:'',
        email:'',
      }

    }
  },
  methods:{
    submit(){
      console.log("校验参数")
      let _this = this
      let user = _this.user
      if (user.userName.trim() === "" || user.email.trim() === "" || user.password.trim() === ""){
        Element.Message.error("用户名、邮箱、密码不能为空！！！")
        return false;
      }
      if (!_this.isEmail(user.email)){
        Element.Message.error("邮箱格式不对！！！")
        return false;
      }

      // 注册
      _this.$http.post("/register", user).then(res => {
        console.log(res)
      })

    },
    // 校验邮箱
    isEmail(str){
      let reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
      return reg.test(str);
    }
  }

}
</script>

<style scoped>
.register-box{
  width: 25%;
  height: 50%;
  position: absolute;
  z-index: 1;
  top: 35%;
  left: 50%;
  transform: translate(-50%,-50%);
  transition: 0.3s ease;
}
.register-title{
  color: #fff;
  font-size: 27px;
  text-align: center;
}
.register-title span{
  color: rgba(0,0,0,0.4);
  display: none;
}
.register-box .input-box{
  background-color: #fff;
  border-radius: 15px;
  overflow: hidden;
  margin-top: 50px;
  opacity: 1;
  visibility: visible;
  transition: 0.6s ease;
}
.register-box input{
  width: 100%;
  height: 50px;
  border: none;
  border-bottom: 1px solid rgba(0,0,0,0.1);
  font-size: 12px;
  padding: 8px 0;
  text-indent: 15px;
  outline: none;
}
.register-box input:last-child{
  border-bottom: none;
}
.register-box input::placeholder{
  color: rgba(0,0,0,0.4);
}
.register-box button{
  width: 100%;
  padding: 15px 45px;
  margin: 15px 0;
  background: rgba(0,0,0,0.4);
  border: none;
  border-radius: 15px;
  color: rgba(255,255,255,0.8);
  font-size: 13px;
  font-weight: bold;
  cursor: pointer;
  opacity: 1;
  visibility: visible;
  transition: 0.3s ease;
}
.register-box button:hover{
  background-color: rgba(0,0,0,0.8);
}


/* 注册、登录区域收起 */
.register-box.slide-up .register-title{
  font-size: 16px;
  cursor: pointer;
}
.register-box.slide-up .register-title span{
  margin-right: 5px;
  display: inline-block;
}
.register-box.slide-up .input-box,
.register-box.slide-up .button{
  opacity: 0;
  visibility: hidden;
}
.register-box.slide-up{
  top: 6%;
  transform: translate(-50%,0%);
}
</style>
