<template>
    <div>
      <el-form :model="form" status-icon :rules="rules" ref="form" label-width="100px" style="width: 500px">
        <h2 style="text-align: center">修改登录密码</h2>
        <el-form-item label="旧密码" prop="password">
          <el-input type="password" v-model="form.password" placeholder="请输入旧密码" show-password clearable></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newPassword">
          <el-input type="password" v-model="form.newPassword" placeholder="请输入新密码" show-password clearable></el-input>
        </el-form-item>
        <el-form-item label="重复密码" prop="ifpwd">
          <el-input type="password" v-model="form.ifpwd" placeholder="请输入重复密码" show-password clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm">提交</el-button>
          <el-button @click="resetForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
</template>

<script>
  import {ifPassword, updatePwd} from "../../api/user";

  export default {
    name: 'updatePwd',
    data() {
      var password = (rule,value,callback) => {
        if(value!='' && value!= undefined){
          if(/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$/.test(value)){
            ifPassword(value).then(result => {
              if (result.code == "1000"){
                callback();
              }else{
                callback(new Error('密码错误！'))
              }
            })
          }else{
            callback(new Error('密码为数字和字母混合组合，长度必须是 6-12  位，不能为纯字母和数字。'))
          }
        }else{
          callback(new Error('请输入密码'))
        }
      };
      var newPassword = (rule,value,callback) => {
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
          if(value == this.form.newPassword){
            callback();
          }else{
            callback(new Error('两次密码输入不一致'))
          }
        }else{
          callback(new Error('请输入确认密码'))
        }
      };
      return {
        form: {
          password: '',
          newPassword: '',
          ifpwd:''
        },
        rules: {
          password: [
            { validator: password, trigger: 'blur' }
          ],
          newPassword: [
            { validator: newPassword, trigger: 'blur' }
          ],
          ifpwd: [
            { validator: ifpwd, trigger: 'blur' }
          ]
        }
      };
    },
    methods: {
      submitForm() {
        this.$refs['form'].validate((valid) => {
          if (valid) {
            let user ={
              password:this.form.newPassword
            }
            updatePwd(user).then(result => {
              if(result.code == "1000"){
                this.msgSuccess("修改密码成功，请重新登录！")
                this.$router.push({path:'/home'})
              }else{
                this.msgError("修改失败！")
              }
            })
          } else {
            this.msgError("请填写正确的内容！")
            return false;
          }
        });
      },
      resetForm() {
        this.$refs['form'].resetFields();
      },
      getImg() {
        this.url = 'http://localhost:8090/img?d='+new Date()*1;
      }
    }
  }
</script>
