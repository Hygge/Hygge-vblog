<template>
  <div class="login">
    <div class="login-title">
      <h5>登录 <span v-if="showRegister">| <a href="#/register" >注册</a></span></h5>
    </div>
    <br/>
    <el-form :model="loginForm" :rules="rules" status-icon :ref="loginForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="登录名" prop="userName">
        <el-input type="text" v-model="loginForm.userName" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="loginForm.password" autocomplete="off"
                  @keyup.enter.native="useVerify"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary"
                   @click.prevent="useVerify"

        >登录</el-button>
<!--        @click="submitForm(loginForm)"-->
      </el-form-item>
      <Verify
          ref="verify"
          :captchaType="'blockPuzzle'"
          :imgSize="{ width: '330px', height: '155px' }"
          @success="submitForm"
        ></Verify>
    </el-form>
  </div>
</template>

<script>
import Verify from "@/components/verifition/Verify"
export default {
  name: "login",
  components: { Verify},
  data() {
    return {
      loginForm: {
        userName: '',
        password: '',
        captchaVerification: '',
      },
      showRegister: false,
      rules: {
        userName: [
          {required: true, message: '请输入用户名或邮箱', trigger: 'blur'},
          {min: 3, max: 25, message: '长度在 6 到 25 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'}
        ],
      },
    };
  },
  methods: {
    useVerify(){
      this.$refs.verify.show()
      console.log(this.loginForm.userName +"12"+"验证码显示")
    },
    // 登录
    submitForm(params) {
      let formName = this.loginForm
      formName.captchaVerification = params.captchaVerification
      console.log(formName)
      let _this = this
      _this.$refs[formName].validate((valid) => {
          if (valid) {
            _this.$http.post("/login", formName).then(res => {
              console.log(res.data.code)
              let token = res.headers['authorization']
              // console.log(token)
              let userInfo = res.data.data
              _this.$store.commit("SET_TOKEN", token)
              _this.$store.commit("SET_USERINFO", userInfo)
              //  登录成功跳转
              // 消息提示成功
              _this.$message({
                message: res.data.msg,
                type: 'success',
                duration: 2 * 1000,
              });
              _this.$router.push("/admin")
            });
          } else {
            // console.log('error submit!!');
            return false;
          }
      });

    },

  },
}
</script>

<style scoped>
.login {
  width: 20%;
  height: 30%;
  margin: 0 auto;
}

.login-title {
  text-align: center;
  margin-left: 40px;
}

.login-title a {
  text-decoration: none;
  color: grey;
}

.login-title a:hover {
  color: red;
}

.el-button {
  width: 100%;
}

</style>
