<template>
  <div class="content">

    <el-button @click="addUserAddress" style="margin-left: 35px">新增收货地址</el-button>
    <div class="userAddressBorder" v-for="(item,index) in userAddressList" :key="index">
      <div style="float:right;">
        <a href="javaScript:0;" @click="delAddress(item.id)">
          <i class="el-icon-close close"></i>
        </a>
      </div>
      <div>
        收货人： <span>{{item.consignee}}</span>
        <span style="background-color: white;margin-left: 40px"  v-if="item.isdefault==1">
          <i class="el-icon-success" style="color: #67C23A"></i>
          <span style="color: #67C23A;">
          默认地址
         </span>
        </span>
      </div>
      <div>
        所在地区： <span>{{item.provinceName}} {{item.cityName}} {{item.districtName}}</span>
      </div>
      <div>
        详细地址： <span>{{item.detailedAddress}}</span>
      </div>
      <div>
        手机号码： <span>{{item.mobile}}</span>
      </div>
      <div style="float: right;margin-right: 50px">
        <a href="javaScript:0;" @click="defaultAddress(item.id)" v-if="item.isdefault!=1">设置为默认收货地址</a>
        <a href="javaScript:0;" @click="updateAddress(item)">修改收货地址</a>
      </div>
    </div>

    <el-dialog :title="title" :visible.sync="isShow" :show-close="isShowClose" width="800px" :close-on-click-modal='false'>
      <div style="margin-left: 150px;margin-top: -10px;margin-bottom: 20px">
        <span style="color: #FF4E00;font-family: '微软雅黑'">{{headline}}</span>
      </div>
      <el-form ref="form" :model="userAddress" :rules="rules" label-width="100px" style="margin-left: 200px">
        <el-form-item label="地址信息：" prop="district">
          <el-cascader :options="options"
                       v-model="districtIdArray"
                       placeholder="请选择省/市/区"
                       clearable
                       style="width: 300px"/>
        </el-form-item>
        <el-form-item label="收货人：" prop="consignee">
          <el-input type="text"
                    v-model="userAddress.consignee"
                    placeholder="请输入收货人名称"
                    clearable
                    style="width: 300px"/>
        </el-form-item>
        <el-form-item label="详细地址：" prop="detailedAddress">
          <el-input type="textarea"
                    v-model="userAddress.detailedAddress"
                    placeholder="请输入详细地址信息，如道路、门牌号、小区、楼栋号、单元等信息"
                    clearable
                    style="width: 300px"/>
        </el-form-item>
        <el-form-item label="手机号码：" prop="mobile">
          <el-input type="text"
                    v-model="userAddress.mobile"
                    placeholder="请输入手机号码"
                    clearable
                    style="width: 300px"/>
        </el-form-item>
        <el-form-item prop="isdefault">
          <el-checkbox label="设置为默认收货地址" v-model="userAddress.isdefault == 1" style="width: 300px" @change="isDefault"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submit">{{btn}}</el-button>
        <el-button @click="cancel" v-show="isShowClose">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {delUserAddress, updateUserAddressIsDefault, userAddressList} from "../../api/userAddress";
  import {addUserAddress,getUserAddress,updateUserAddress} from "../../api/userAddress";
  import {district} from "../../api/district";

  export default {
    name: "userAddress",
    data(){
      var phone = (rule, value, callback) => {
        if(value!='' && value!= undefined) {
          if (!(/^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$/.test(value))) {
            callback(new Error('请输入正确手机号'));
          }else{
            callback()
          }
        }else{
          callback(new Error('请输入手机号'))
        }
      };
      var district = (rule, value, callback) => {
        if(this.userAddress.provinceid == undefined && this.userAddress.cityid == undefined && this.userAddress.districtid == undefined){
          callback(new Error('地址信息不能为空！'))
        }else{
          callback()
        }
      };
      return {
        btn: undefined,
        isShow: false,
        title: undefined,
        headline:undefined,
        userAddressList: undefined,
        isShowClose: false,
        options: [],
        districtIdArray: [],
        userAddress:{},
        rules: {
          district: [
            { validator: district , trigger: 'blur' }
          ],
          consignee: [
            { required:true ,message: '收货人姓名不能为空' , trigger: 'blur' }
          ],
          detailedAddress: [
            { required:true ,message: '详细地址不能为空' , trigger: 'blur' }
          ],
          mobile: [
            { validator: phone, trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      reset() { // 重置userAddress对象
        this.districtIdArray=[],
          this.userAddress= {
            id: undefined,
            provinceid: undefined,
            cityid: undefined,
            districtid: undefined,
            detailedAddress: undefined,
            consignee: undefined,
            mobile: undefined,
            isdefault: 0
          }
      },
      delAddress(id){
        delUserAddress(id).then(response => {
          if(response.code == "1000"){
            this.msgSuccess(response.retMsg)
            this.getUserAddressList(); //刷新收货地址
          }else{
            this.msgError(response.retMsg)
          }
        })
      },
      getUserAddressList(){
        userAddressList().then(result => {
          if(result.code == "1000"){
            this.userAddressList = result.userAddressList
            for (let userAddressListKey in this.userAddressList) {
              if(this.userAddressList[userAddressListKey].isdefault==1){
                this.userAddressRadio(this.userAddressList[userAddressListKey])
                this.border = userAddressListKey
                break;
              }
            }
          }else if(result.code== "901"){
            this.addUserAddress()
            this.isShowClose = false
          }
        })
      },
      submit(){
        this.userAddress.provinceid = this.districtIdArray[0]
        this.userAddress.cityid = this.districtIdArray[1]
        this.userAddress.districtid = this.districtIdArray[2]
        this.$refs['form'].validate(valid => {
          if(valid){
            if(this.userAddress.id==undefined){
              addUserAddress(this.userAddress).then(response => {
                if(response.code == "1000"){
                  this.msgSuccess(response.retMsg)
                  this.getUserAddressList(); //刷新收货地址
                }else{
                  this.msgError(response.retMsg)
                }
              })
            }else{
              updateUserAddress(this.userAddress).then(response => {
                if(response.code == "1000"){
                  this.msgSuccess(response.retMsg)
                  this.getUserAddressList(); //刷新收货地址
                }else{
                  this.msgError(response.retMsg)
                }
              })
            }
            this.reset()
            this.isShow = false
          }else{
            this.msgError("请输入正确内容！")
            return false
          }
        })
      },
      cancel(){
        this.reset()
        this.isShow = false
      },
      isDefault(value){
        if(value){
          this.userAddress.isdefault = 1
        }else{
          this.userAddress.isdefault = 0
        }
      },
      defaultAddress(id){
        updateUserAddressIsDefault(id).then(response => {
          if(response.code == "1000"){
            this.msgSuccess(response.retMsg)
            this.getUserAddressList(); //刷新收货地址
          }else{
            this.msgError(response.retMsg)
          }
        })
      },
      addUserAddress(){
        this.reset()
        district().then(result => {
          this.options = result.districtList
        })
        this.isShowClose = true
        this.btn="保存收货地址"
        this.title = "创建地址"
        this.headline = '新增收货地址'
        this.isShow = true
      },
      updateAddress(row){
        this.reset()
        district().then(result => {
          this.options = result.districtList
        })
        getUserAddress(row.id).then(response => {
          if(response.code == "1000"){
            this.userAddress = response.userAddress
            this.districtIdArray = [ this.userAddress.provinceid, this.userAddress.cityid, this.userAddress.districtid]
          }else{
            this.msgError("获取地址信息失败！")
          }
        })
        this.isShowClose = true
        this.btn="修改收货地址"
        this.title = "修改地址"
        this.headline = '编辑收货地址'
        this.isShow = true
      }
    },
    created() {
      this.getUserAddressList();
    }
  }
</script>

<style scoped>
  .content{
    width: 100%
  }
  .userAddressBorder{
    width: 800px;
    margin: auto;
    margin-top: 10px;
    font-size: 13px;
    line-height: 1.8em;
    color: darkgray;
    height: 120px;
    border: 1px darkgray solid;
  }
  .userAddressBorder div span{
    color: #696969;
  }
  a{
    color: #3e3e3e;
    text-decoration: none;
    font-size: 14px;
  }
  a:hover{
    color: #ff4e00;
  }
  .close:hover{
    color: red;
  }
</style>
