<template>
  <div class="login-container">
    <el-form ref="form" :model="form" :rules="rules" label-width="80px" class="login-form">
      <h2 class="login-title" >商城购物系统登录</h2>
      <el-form-item label="账号" prop="loginname">
        <el-input v-model="form.loginname"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="form.password" type="password" show-password></el-input>
      </el-form-item>
      <el-form-item label="验证码" prop="code">
        <el-input v-model="form.code" style="width: 150px"></el-input>
        <el-image :src="url" @click="getImg" style="float: right;margin-right: 80px"/>
      </el-form-item>
      <el-form-item>
        <el-button class="cancle" type="primary" @click="onSubmit">登录</el-button>
        <el-button class="cancle" @click="clean">重置</el-button>
        <el-button class="cancle" @click="$router.push({path: '/home'})">返回首页</el-button>
      </el-form-item>
      <el-form-item style=" margin-left: 70px;">

      </el-form-item>
      <div>
        <span>
           <router-link to="/register" style="text-decoration: none;color: #3c8899;">
           还没有账号?点击注册
          </router-link></span>
        <span style="margin-left: 140px;">
          <router-link to="/findLoginName" style="text-decoration: none;color: #747476;">
            忘记用户名
          </router-link>
        </span>
        <span style="float: right;">
          <router-link to="/findPwd" style="text-decoration: none;color: #747476;">
            忘记密码
          </router-link>
        </span>
      </div>
    </el-form>

  </div>
</template>


<script>
import {login,loginout} from '@/api/login'
export default {
  name: "Login",
  data() {
    return {
      url: 'http://localhost:8090/img',
      form: {
        loginname: "",
        password: "",
        code: ""
      },
      rules: {
        loginname: [
          {required: true, message: '账号不能为空', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '密码不能为空', trigger: 'blur'}
        ] ,
        code: [
          { required: true, message: '验证码不能为空', trigger: 'blur' }
        ]
      },
    };
  },
  methods: {
    onSubmit() {
      //定位到表单，再进行校验
      this.$refs['form'].validate(valid => {
        if (valid) {
          // 提交表单给后台进行验证是否正确
          login(this.form).then(response => {
            if (response.retMsg && response.retCode == "1000") {
              this.msgSuccess(response.retMsg)
              this.$router.push({path:'/home'})
            } else {
              this.msgError(response.retMsg)
              this.getImg()
            }
          }).catch(error => {
            this.msgError('异常：' + error.message)
          })
        } else {
          this.msgError('文本框内容不能为空！')
          return false
        }
      })
    },
    clean() {
      this.$refs['form'].resetFields();
    },
    getImg() {
      this.url = 'http://localhost:8090/img?d='+new Date()*1;
    }
  },
  watch:{

  }
}
</script>



<style scoped>
  .login-form {
    width: 450px;
    /* 上下嫌隙 160px，左右自动居中 */
    margin: 50px auto;
    padding: 28px;
    border-radius: 20px;
    /* border: solid 1px black; */
    box-shadow: 0 0 30px 3px rgba(119,118,118,0.33);
  }

  .login-container {
    position: absolute;
    width: 100%;
    height: 100%;
  }
  .login-title {
    color: #303133;
    text-align: center;
  }
  .cancle{
    margin-left: 20px;
    margin-right: 20px;
  }
</style>
