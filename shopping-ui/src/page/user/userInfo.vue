<template>
  <div>
    <a href="javaScript:0;" @click="updateUser"><i class="el-icon-edit"></i> 编辑个人资料</a>
    <div>
      <img :src="urlimg+user.img" style="width:50px;height: 50px;border-radius:50%;"/>
    </div>
    <div>
      {{user.username}}
    </div>
    <div>
      <span v-for="(item,index) in userSexList" :key="index">
        <span v-if="item.dictValue==user.sex">
          性别：{{item.dictLabel}}
        </span>
      </span>
    </div>
    <div>
      <span v-for="(item,index) in userStatuList" :key="index">
        <span v-if="item.dictValue==user.statu">
          用户状态：{{item.dictLabel}}
        </span>
      </span>
    </div>
    <div>
      用户余额：{{user.balance}} 元
    </div>
    <div>
      手机号：{{user.mobile}}
    </div>
    <div>
      创建时间：{{user.createtime}}
    </div>
    <el-dialog :visible.sync="isShow" title="编辑个人资料" width="600px" :close-on-click-modal='false'
               :before-close="dialogClose">
      <el-form ref="form" :model="user" label-width="100px">
        <el-form-item label="上传图片：" prop="img">
          <img :src="image" style="width:50px;height: 50px;border-radius:50%;"/>
          <el-upload :show-file-list="false"
            :before-upload="beforeUpload"
            action="/api/user/updateUserImage"
            class="upload-demo"
            :on-success="handleAvatarSuccess"
            list-type="picture">
            <el-button size="small" type="primary">更改头像</el-button>
            <div slot="tip" class="el-upload__tip">只能上传图片类型文件，且不超过500kb</div>
          </el-upload>
        </el-form-item>
        <el-form-item label="账号：" prop="loginname">
          <el-input type="text"
                    v-model="user.loginname"
                    placeholder="请输入账号"
                    disabled
                    style="width: 300px"/>
        </el-form-item>
        <el-form-item label="手机号码：" prop="mobile">
          <el-input type="text"
                    v-model="user.mobile"
                    placeholder="请输入手机号码"
                    disabled
                    style="width: 300px"/>
        </el-form-item>
        <el-form-item label="用户名称：" prop="username">
          <el-input type="text"
                    v-model="user.username"
                    placeholder="请输入用户名称"
                    clearable
                    style="width: 300px"/>
        </el-form-item>
        <el-form-item label="性别：" prop="sex">
          <el-radio-group v-model="user.sex">
            <el-radio label="1">男</el-radio>
            <el-radio label="0">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="用户状态：" prop="statu">
          <span v-for="(item,index) in userStatuList" :key="index">
            <span v-if="item.dictValue==user.statu">
              {{item.dictLabel}}
            </span>
          </span>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submit">确定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {getUserInfo, update} from "../../api/user";
  import {dictionaryList} from "../../api/dictionary";

  export default {
    name: "userInfo",
    data(){
      return{
        userStatuList:[],
        userSexList: [],
        user: {},
        isShow: false,
        image: ''
      }
    },methods:{
      getUserInfo(){
        getUserInfo().then(result => {
          this.user = result.user
          this.image = 'static/images/'+this.user.img
        })
      },
      updateUser(){
        this.isShow = true
      },
      cancel(){
        this.isShow = false
        this.image='static/images/'+this.user.img
      },
      submit(){
        let userInfo = {
          userid: this.user.userid,
          username: this.user.username,
          sex: this.user.sex
        }
        update(userInfo).then(result => {
          if(result.code == "1000"){
            this.msgSuccess("修改成功！")
          }else{
            this.msgError("修改失败！")
          }
          this.isShow = false
        })
      },
      handleAvatarSuccess(response, file, fileList){
        if(response.code == "1000"){
          this.msgSuccess(response.retMsg)
          this.getUserInfo()
        }else{
          this.msgError(response.retMsg)
        }
      },
      dialogClose(){
        this.isShow = false
        this.image='static/images/'+this.user.img
      },
      beforeUpload(file){
        if (file.type.indexOf("image/") == -1) {
          this.msgError("文件格式错误，请上传图片类型,如：JPG，PNG后缀的文件。");
          return false
        } else {
          if(file.size/1024<=500){
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = () => {
              this.image = reader.result;
            };
          }else{
            this.msgError("上传文件不能大于500kb");
            return false
          }
        }
      }
    },
    created() {
      dictionaryList('user_statu').then(result => {
        if(result.code == "1000"){
          this.userStatuList = result.dictionaryList
        }
      })
      dictionaryList('user_sex').then(result => {
        if(result.code == "1000"){
          this.userSexList = result.dictionaryList
        }
      })
      this.getUserInfo()
    }
  }
</script>

<style scoped>
a{
  text-decoration: none;
  color: black;
}
a:hover{
  color: #ff4e00;
}
</style>
