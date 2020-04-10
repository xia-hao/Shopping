<template>
  <div>
    <el-form :model="form" status-icon :rules="rules" ref="form" label-width="100px" class="register-form">
      <h2 style="text-align: center">商城购物系统注册</h2>
      <el-form-item label="手机号码" prop="mobile">
        <el-input type="text" v-model="form.mobile" placeholder="请输入手机号"></el-input>
      </el-form-item>
      <el-form-item label="短信验证码" prop="noteCode">
        <el-input type="text" v-model="form.noteCode" style="width: 150px" placeholder="请输入短信验证码"></el-input>&nbsp;&nbsp;&nbsp;&nbsp;
        <el-button @click="getVerify" v-if="isShow" :disabled="disabled">获取验证码</el-button>
        <el-button disabled v-else>请{{countTime}}s后重试</el-button>
      </el-form-item>
      <el-form-item label="用户账号" prop="loginname">
        <el-input type="text" v-model="form.loginname" placeholder="请输入用户账号"></el-input>
      </el-form-item>
      <el-form-item label="用户名称" prop="username">
        <el-input type="text" v-model="form.username" placeholder="请输入用户名称"></el-input>
      </el-form-item>
      <el-form-item label="设置密码" prop="password">
        <el-input type="password" v-model="form.password" placeholder="请输入密码" show-password></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="pwd">
        <el-input type="password" v-model="form.pwd" placeholder="请输入重复密码" show-password></el-input>
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-radio-group v-model="form.sex">
          <el-radio label="1">男</el-radio>
          <el-radio label="0">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="验证码" prop="code">
        <el-input type="text" v-model="form.code" style="width: 150px"  placeholder="请输入验证码"></el-input>
        <el-image :src="url" @click="getImg" style="float: right;margin-right: 120px"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">注册</el-button>
        <el-button @click="resetForm">重置</el-button>
        <el-button @click="$router.go(-1)">返回</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import {register} from "../api/login";
  import {addVerify} from "../api/verify";
  import {ifMobileExist,ifLoginNameExist} from "../api/user";

  export default {
    name: 'register',
    data() {
      var phone = (rule, value, callback) => {
        if(value!='' && value!= undefined) {
          if (!(/^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$/.test(value))) {
            this.disabled = true
            callback(new Error('请输入正确手机号'));
          } else {
            ifMobileExist(value).then(response => {
              if(response.retCode == "1000"){
                this.disabled = false
                callback();
              }else{
                this.disabled = true
                callback(new Error(response.retMsg));
              }
            })
          }
        }else{
          callback('请输入手机号')
        }
      };
      var loginName = (rule, value, callback) => {
        if(value!='' && value!= undefined) {
          ifLoginNameExist(value).then(response => {
            if(response.retCode == "1000"){
              callback();
            }else{
              callback(new Error(response.retMsg));
            }
          })
        }else{
          callback('请输入用户账号')
        }
      };
      var password = (rule,value,callback) => {
        if(value!='' && value!= undefined){
          if(/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$/.test(value)){
            callback();
          }else{
            callback(new Error('密码为数字和字母混合组合，长度必须是 6-12  位，不能为纯字母和数字。'))
          }
        }else{
          callback(new Error('请输入密码'))
        }
      };
      var ifpwd = (rule,value,callback) => {
        if(value!='' && value!= undefined){
          if(value == this.form.password){
            callback();
          }else{
            callback(new Error('两次密码输入不一致'))
          }
        }else{
          callback(new Error('请输入确认密码'))
        }
      };
      return {
        disabled: true,
        verify: {
          describes: '1',
          mobile: ''
        },
        form: {
          mobile: '',
          loginname: '',
          username: '',
          password: '',
          pwd: '',
          code: '',
          noteCode: '',
          sex: '1'
        },
        rules: {
          mobile: [
            { validator: phone, trigger: 'blur' }
          ],
          loginname: [
            { validator: loginName, trigger: 'blur' }
          ],
          username: [
            { required: true,message: '请输入用户名称', trigger: 'blur' }
          ],
          password: [
            { validator: password, trigger: 'blur' }
          ],
          pwd: [
            { validator: ifpwd, trigger: 'blur' }
          ],
          code: [
            { required: true,message: '请输入验证码', trigger: 'blur' }
          ],
          noteCode: [
            { required: true,message: '请输入短信验证码', trigger: 'blur' }
          ]
        },
        url: 'http://localhost:8090/img',
        countTime: undefined,
        isShow: true
      }
    },
    methods: {
      submitForm() {
        this.$refs['form'].validate((valid) => {
          if (valid) {
            register(this.form).then(response => {
              if(response.retCode == "1000"){
                this.msgSuccess(response.retMsg)
                this.$router.push({path: response.url})
              }else{
                this.msgError(response.retMsg)
                this.getImg();
                this.$router.push({path: response.url})
              }
            }).catch(error => {
              this.msgError('异常：'+error.message)
            })
          } else {
            this.msgError("请填写正确的注册内容！")
            return false;
          }
        });
      },
      resetForm() {
        this.$refs['form'].resetFields();
      },
      getImg() {
        this.url = 'http://localhost:8090/img?d='+new Date()*1;
      },
      getVerify(){
        this.verify.mobile = this.form.mobile
        addVerify(this.verify).then(response => {
          if(response.retCode == '1000'){
            this.nySuccess('获取注册验证码成功',"您的验证码为："+response.verify.notecode+"，" +
              "使用期限为："+response.verify.validtime+"秒，请尽快使用！",response.verify.validtime*1000)
            const TIME_COUNT = response.verify.validtime;
            if(!this.timer) {
              this.countTime = TIME_COUNT;
              this.isShow = false;
              this.timer = setInterval(() => {
                if (this.countTime > 0 && this.countTime <= TIME_COUNT) {
                  this.countTime--;
                } else {
                  this.isShow = true;
                  clearInterval(this.timer);
                  this.timer = null;
                }
              }, 1000)
            }
          }else{
            this.nyError('获取验证码失败！')
          }
        })
      }
    }
  }
</script>

<style>
  .register-form{
    width: 500px;
    /* 上下嫌隙 160px，左右自动居中 */
    margin: auto;
    padding: 28px;
    border-radius: 20px;
    /* border: solid 1px black; */
    box-shadow: 0 0 30px 3px rgba(119,118,118,0.33);
  }
  .el-button{
    margin-right: 40px;
  }
</style>
