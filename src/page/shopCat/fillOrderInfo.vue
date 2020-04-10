<template>
  <div class="content">
    <div style="text-align: center;margin-top:30px;margin-bottom: -50px">
      <img src="static/images/img2.jpg"/>
    </div>
    <div class="top">
      <div style="margin-bottom: 20px">
        <el-button class="back-home" @click="$router.push({path: '/home'})">返回首页</el-button>
      </div>
      <span style="color: #3e3e3e">确认收货地址</span>
      <a href="javaScript:0;" @click="addUserAddress">新增收货地址</a>
    </div>
    <div :class="{radio:true,border: border == index}"
         v-for="(item,index) in userAddressList" :key="index" @change="isborder(index)">
      <input type="radio" name="radio" :checked="item.isdefault==1"  @change="userAddressRadio(item)"/>
        {{item.provinceName}} {{item.cityName}} {{item.districtName}} {{item.detailedAddress}} （{{item.consignee}} 收） {{item.mobile}}
      <a href="javaScript:0;" :underline="false" v-if="border == index" @click="updateAddress(item)">修改收货地址</a>
      <el-link :underline="false" @click.native.prevent="defaultAddress(item.id)" v-if="item.isdefault!=1">设置为默认收货地址</el-link>
      <span v-else style="color: black;margin-left: 10px">默认地址</span>
    </div>

    <div class="bottom">
      <div class="des_choice" v-for="(item1,index) in wayList" :key="index">
        <div class="fl">{{item1.title}}：</div>
        <ul>
          <li :class="{checked:sel[index] == ind}"
              v-for="(item2,ind) in item1.items" :key="ind" @click="clickClass(index,ind,item2.id)">
            {{item2.name}}
            <div class="ch_img"></div>
          </li>
        </ul>
      </div>
      <span style="color: #3e3e3e">确认订单信息</span>
      <div>
        <el-table
          ref="shopCat"
          :data="shopCatList"
          row-key="id"
        >
          <el-table-column label="商品" prop="name" align="center" >
            <template slot-scope="scope">
              <div>
                <img :src="urlimg+scope.row.filename"/>
                <div>
                  <router-link :to="{path: '/productDeatil',query:{id : scope.row.id}}">{{scope.row.name}}</router-link>
                </div>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="单价" prop="price" align="center" >
            <template slot-scope="scope">
              <span style="color: #FF4E00">￥{{scope.row.price}}</span>
            </template>
          </el-table-column>
          <el-table-column label="数量" prop="number" align="center"/>
          <el-table-column label="小计" prop="cost" align="center">
            <template slot-scope="scope">
              <span style="color: #FF4E00">￥{{scope.row.number*scope.row.price}}</span>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div style="border:1px #e6e6e6 solid;background-color: #f1f1f1;text-align: right;height: 100px">
        <div style="margin-right: 20px;margin-top: 25px;">
          <div>
            <span style="font-size: 14px;color: #404040">应付总金额：</span>
            <span style="color: red;"><b>￥{{countPrice}}</b></span>
          </div>
          <div>
            <span style="font-size: 13px;color: #848484">
              寄存至：{{address}}&#12288; 收货人：{{userAddress.consignee}} {{userAddress.mobile}}
            </span>
          </div>
        </div>
      </div>
      <div style="text-align: right;margin-top: 10px">
        <el-button style="background-color: #e14145;color: white;width: 130px;margin-right: 20px" @click="$router.go(-1)">
          提交订单
        </el-button>
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
  import {updateUserAddressIsDefault, userAddressList} from "../../api/userAddress";
  import {district} from "../../api/district";
  import {addUserAddress,getUserAddress,updateUserAddress} from "../../api/userAddress";

export default {
  name: "fillOrderInfo",
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
      shopCatList: this.$route.query.shopCatList,
      shopCatNumber: this.$route.query.shopCatNumber,
      countPrice: 0,
      border: undefined,
      btn: undefined,
      sel: [],
      isShow: false,
      title: undefined,
      headline:undefined,
      userAddressList: undefined,
      isShowClose: false,
      options: [],
      districtIdArray: [],
      userAddress:{},
      address: undefined,
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
      },
      order: {
        useraddressid:undefined,
        distribution: undefined,
        payType: undefined
      },
      wayList:[
        {title: '配送方式', items: [{id:1,name:'快递送货(全国范围)'},{id:2,name:'物流送货(全国范围)'},{id:3,name:'自行上门提货'}]},
        {title: '支付方式', items: [{id:1,name:'支付宝'},{id:2,name:'微信'}]}
      ]
    }
  },
  methods: {
    clickClass(index,ind,id){
      this.sel[index] = ind; //让数组sel的第index+1的元素的值等于ind
      this.sel = this.sel.concat([]); //因为数组是引用类型，对其中一个变量直接赋值不会影响到另一个变量（并未操作引用的对象），使用concat（操作了应用对象）
      if(index==0){
        this.order.distribution = id
      }else if(index == 1){
        this.order.payType = id
      }
    },
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
    userAddressRadio (row){
      this.order.useraddressid = row.id
      this.address = row.provinceName+" "+ row.cityName+" " + row.districtName+" "+row.detailedAddress
      this.userAddress.consignee = row.consignee
      this.userAddress.mobile = row.mobile
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
    isborder(index){
      this.border = index
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
  mounted(){
    if(this.shopCatList == '' || this.shopCatList == undefined){
      this.$router.push({path: '/home'})
      this.msgError("获取购物车信息失败！")
    }
  },
  created() {
    this.getUserAddressList();
    this.shopCatList.forEach(shopCat => {
      this.countPrice += shopCat.number*shopCat.price
    })
  }
}
</script>

<style scoped>
  .content{
    width: 100%
  }
  .top{
    width: 1000px;
    margin: auto;
    margin-top: 70px;
    border-bottom: 2px darkgray solid;
  }
  .bottom{
    width: 1000px;
    margin: auto;
    margin-top: 30px;
  }
  .radio{
    width: 1000px;
    margin: auto;
    margin-top: 10px;
    height: 25px;
  }
  .el-link{
    display: none
  }
  .border{
    border: 1px #ff4e00 solid;
    background-color: rgba(0,0,0,0.2);
    color: #ff4e00;
  }
  .radio:hover{
    background-color: rgba(0,0,0,0.2);
  }
  a{
    color: #3e3e3e;float: right;text-decoration: none;font-size: 14px;
  }
  a:hover{
    color: #ff4e00;
  }
  .el-link:hover{
    color: #ff771f;
  }
  .des_choice ul li:hover {
    height: 26px;
    line-height: 26px;
    padding: 0 14px;
    overflow: hidden;
    border: 2px solid #ff4e00;
  }
  .des_choice ul li .ch_img {
    width: 18px;
    height: 18px;
    overflow: hidden;
    background: url(../../../static/images/ch.png) no-repeat center top;
    position: absolute;
    right: 0;
    bottom: 0;
    display: none;
  }
  .des_choice ul li.checked .ch_img {
    display: block;
  }
  .des_choice {
    overflow: hidden;
    font-family: "宋体";
    height: 120px;
    border-bottom: 1px darkgray solid;
    margin-bottom: 30px;
  }
  .fl {
    margin-top: 30px;
  }
  .des_choice ul li {
    height: 28px;
    line-height: 28px;
    overflow: hidden;
    font-size: 14px;
    text-align: center;
    font-family: "Microsoft YaHei";
    float: left;
    display: inline;
    margin-right: 10px;
    margin-top: 4px;
    margin-bottom: 2px;
    padding: 0 15px;
    border: 1px solid #cccccc;
    cursor: pointer;
    position: relative;
  }
  .des_choice ul li.checked {
    height: 26px;
    line-height: 26px;
    padding: 0 14px;
    overflow: hidden;
    border: 2px solid #ff4e00;
  }
  .radio:hover .el-link{
    display: block;
    float: right;
    margin-right: 260px;
  }

</style>
