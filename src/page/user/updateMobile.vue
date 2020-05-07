<template>
    <div>
      <el-form :model="form" status-icon :rules="rules" ref="form" label-width="100px" style="width: 500px">
        <h2 style="text-align: center">修改手机号码</h2>
        <el-form-item label="手机号码" prop="mobile">
          <el-input type="text" v-model="form.mobile" placeholder="请输入原手机号" clearable></el-input>
        </el-form-item>
        <el-form-item label="短信验证码" prop="noteCode">
          <el-input type="text" v-model="form.noteCode" style="width: 160px" placeholder="请输入短信验证码" clearable></el-input>&nbsp;&nbsp;&nbsp;&nbsp;
          <el-button @click="getVerify" v-if="isShow" :disabled="disabled">获取验证码</el-button>
          <el-button disabled v-else>请{{countTime}}s后重试</el-button>
        </el-form-item>
        <el-form-item label="新手机号码" prop="newMobile">
          <el-input type="text" v-model="form.newMobile" placeholder="请输入新手机号码" clearable></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm">提交</el-button>
          <el-button @click="resetForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
</template>

<script>
  import {addVerify} from "../../api/verify";
  import {getUser, ifMobileExist, updateMobile} from "../../api/user";
  export default {
    name: 'accountSecurity',
    data() {
      var phone1 = (rule, value, callback) => {
        if(value!='' && value!= undefined) {
          if (!(/^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$/.test(value))) {
            this.disabled = true
            callback(new Error('请输入正确手机号'));
          } else {
            getUser().then(result => {
              if(result.user.mobile == value){
                this.disabled = false
                callback();
              }else{
                this.disabled = true
                callback(new Error('手机号错误！'));
              }
            })
          }
        }else{
          callback('请输入手机号')
        }
      };
      var phone2 = (rule, value, callback) => {
        if(value!='' && value!= undefined) {
          if (!(/^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$/.test(value))) {
            this.disabled = true
            callback(new Error('请输入正确手机号'));
          } else {
            getUser().then(result => {
              if(result.user.mobile == value){
                this.disabled = true
                callback(new Error('新手机号码不能是原手机号！'));
              }else{
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
            })
          }
        }else{
          callback('请输入手机号')
        }
      };
      return {
        verify:{
          mobile: '',
          describes: '4'
        },
        isShow: true,
        disabled:true,
        form: {
          mobile:'',
          newMobile: '',
          noteCode:''
        },
        rules: {
          mobile: [
            { validator: phone1, trigger: 'blur' }
          ],
          newMobile: [
            { validator: phone2, trigger: 'blur' }
          ],
          noteCode: [
            { required: true,message: '请输入短信验证码', trigger: 'blur' }
          ]
        },
        countTime: undefined,
        isShow: true
      };
    },
    methods: {
      submitForm() {
        this.$refs['form'].validate((valid) => {
          if (valid) {
            let user ={
              mobile:this.form.mobile,
              newMobile:this.form.newMobile,
              noteCode:this.form.noteCode
            }
            updateMobile(user).then(result => {
              if(result.code == "1000"){
                this.msgSuccess("修改成功，请重新登录！")
                this.$router.push({path:'/home'})
              }else{
                if(result.retMsg){
                  this.msgError(result.retMsg)
                }else{
                  this.msgError("修改失败！")
                }
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
      },
      getVerify(){
        this.verify.mobile = this.form.mobile
        addVerify(this.verify).then(response => {
          if(response.retCode == '1000'){
            this.nySuccess('获取更换手机号验证码成功',"您的验证码为："+response.verify.notecode+"，" +
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
