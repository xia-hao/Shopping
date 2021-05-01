<template>
  <div class="content">
    <div class="table">
      <el-menu :default-active="order.orderstatu" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-menu-item index="">全部</el-menu-item>
        <el-menu-item index="1">待付款</el-menu-item>
        <el-menu-item index="4">待收货</el-menu-item>
        <el-menu-item index="5">已完成</el-menu-item>
        <el-menu-item index="3">已取消</el-menu-item>
        <el-menu-item index="6">已退款</el-menu-item>
      </el-menu>
      <el-table
        :data="orderInfoList"
      >
        <el-table-column type="expand">
          <template slot-scope="scope">
            <table v-for="(orderDetail,index) in scope.row.orderDetailList" :key="index" class="tab">
              <tr>
                <td>商品信息</td>
                <td>
                  <div class="display">
                    <img :src="urlimg+JSON.parse(orderDetail.productSku.imgarray).img[0]" width="60px" height="60px" style="display: inline-block"/>
                  </div>
                  <div class="display">
                    <div>
                      <router-link :to="{path: '/productDeatil',query:{id : orderDetail.product.id}}">{{orderDetail.product.name}}</router-link>
                    </div>
                    <div>
                      <span v-for="(specs,ind2) in JSON.parse(orderDetail.productSku.productspecs)" :key="ind2">
                        {{specs.title}}：{{specs.items}}
                      </span>
                    </div>
                  </div>
                </td>
              </tr>
              <tr>
                <td>商品金额</td>
                <td><span >
                  {{orderDetail.price}} 元
                </span></td>
              </tr>
              <tr>
                <td>商品数量</td>
                <td>
                  {{orderDetail.number}} 个
                </td>
              </tr>
              <tr>
                <td>总 价 格</td>
                <td>
                  {{orderDetail.cost}} 元
                </td>
              </tr>
              <tr>
                <td>订单创建时间</td>
                <td>
                  {{orderDetail.createtime}}
                </td>
              </tr>
            </table>
          </template>
        </el-table-column>
        <el-table-column label="订单号" align="center" prop="orderno" width="305px"/>
        <el-table-column label="收货地址" align="center" width="240x">
          <template slot-scope="scope">
            {{scope.row.userAddress.detailedAddress}}
          </template>
        </el-table-column>
        <el-table-column label="收货人" align="center" width="209px">
          <template slot-scope="scope">
            {{scope.row.userAddress.consignee}}（{{scope.row.userAddress.mobile}}）
          </template>
        </el-table-column>
        <el-table-column label="配送方式" align="center" prop="distribution" width="146px">
          <template slot-scope="scope">
            <span v-for="(item,index) in dispatchingWayList" :key="index">
              <span v-if="item.dictValue == scope.row.distribution">
                {{item.dictLabel}}
              </span>
            </span>
          </template>
        </el-table-column>
        <el-table-column label="支付方式" align="center" prop="payway" width="76px">
          <template slot-scope="scope">
            <span v-for="(item,index) in payWayList" :key="index">
              <span v-if="item.dictValue == scope.row.payway">
                {{item.dictLabel}}
              </span>
            </span>
          </template>
        </el-table-column>
        <el-table-column label="订单金额" align="center" prop="orderprice">
          <template slot-scope="scope">
            <span style="color: #ff4e00">￥{{scope.row.orderprice}}</span>
          </template>
        </el-table-column>
        <el-table-column label="订单状态" align="center" prop="orderstatu" width="76px">
          <template slot-scope="scope">
            <span v-for="(item,index) in orderStatuList" :key="index">
              <span v-if="item.dictValue == scope.row.orderstatu">
                {{item.dictLabel}}
              </span>
            </span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.orderstatu == 1">
              <el-button size="mini" @click="pay(scope.row)">付&#12288;款</el-button>
              <el-button size="mini" @click="updateAddress(scope.row)">修改地址</el-button>
              <el-button size="mini" @click="cancelOrder(scope.row)">取消订单</el-button>
            </span>
            <span v-if="scope.row.orderstatu == 4">
              <el-button size="mini" @click="onrefund(scope.row)">退款</el-button>
              <el-button size="mini" @click="receipt(scope.row)">确认收货</el-button>
            </span>
            <span v-if="scope.row.orderstatu == 3 || scope.row.orderstatu == 5 || scope.row.orderstatu == 6">
              <el-button size="mini" @click="delOrder(scope.row)">删除订单</el-button>
            </span>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination style="text-align: center;margin-bottom: 50px"
           background
           :hide-on-single-page="true"
           :current-page.sync="order.pageNo"
           :page-sizes="[5,10,15,20]"
           :page-size.sync="order.pageSize"
           layout="total, sizes, prev, pager, next, jumper"
           :total="orderCount"
           @size-change="getOrder"
           @current-change="getOrder"/>
      <el-dialog :title="title" :visible.sync="isShow" width="800px" :close-on-click-modal='false'>
        <div :class="{radio:true,border: item.id == order.userAddressId}"
             v-for="(item,index) in userAddressList" :key="index" @change="isborder(index)">
          <input type="radio" name="radio" :checked="item.id == order.userAddressId"  @change="userAddressRadio(item)"/>
          {{item.provinceName}} {{item.cityName}} {{item.districtName}} {{item.detailedAddress}} （{{item.consignee}} 收） {{item.mobile}}
        </div>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submit">{{btn}}</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>

      <el-dialog title="退款原因" :visible.sync="refund.orderno != undefined" width="400px" :close-on-click-modal='false'>
        <div class="notice" v-for="(item,index) in backNoticeList" :key="index" @click="clickNotice(item)">
          {{index+1}}.{{item}}
        </div>
        <el-input type="textarea" v-model="refund.backNotice" placeholder="其他" style="margin-top: 50px"/>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submit" :disabled="refund.backNotice==undefined && refund.backNotice != ''">{{btn}}</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
  import {updateOrder, delOrder, orderlist} from "../../api/order";
  import {dictionaryList} from "@/api/dictionary";
  import {userAddressList} from "../../api/userAddress";
  import {recede} from "../../api/refund";

  export default {
    name: "index",
    data() {
      return {
        border: undefined,
        orderInfoList:[],
        order:{
          pageNo: 1,
          pageSize: 5,
          orderno: undefined,
          orderstatu: '',
          userAddressId: undefined
        },
        dispatchingWayList:[],
        payWayList:[],
        orderStatuList: [],
        orderCount: 0,
        userAddressList: undefined,
        btn: '确定',
        isShow: false,
        title: '收货地址',
        backNoticeList: ['钱包没钱了','地址填错了','不想要了','付款遇到问题','买错了','信息填写错误'],
        refund: {
          orderno:undefined,
          backNotice:undefined
        }
      };
    },
    methods: {
      submit(){
        if(this.order.orderno!=undefined){
          let orderInfo = {
            orderno : this.order.orderno,
            userAddressId :  this.order.userAddressId
          }
          updateOrder(orderInfo).then(result => {
            console.info(result)
            if(result.code=="1000"){
              this.msgSuccess("修改地址成功！")
              this.getOrder()
            }else{
              this.msgError("修改地址失败！")
            }
          })
        }
        if(this.refund.orderno != undefined){
          recede(this.refund).then(result => {
            if(result.code == "1000"){
              this.msgSuccess(result.retMsg)
              this.getOrder()
              this.refund.orderno = undefined
            }else{
              this.msgError(result.retMsg)
            }
          })
        }
        this.isShow = false
      },
      onrefund(row){
        this.refund.backNotice = undefined
        this.refund.orderno = row.orderno
      },
      clickNotice(value){
        this.refund.backNotice = value
      },
      isborder(index){
        this.border = index
      },
      userAddressRadio (row){
        this.order.userAddressId = row.id
      },
      cancel(){
        this.isShow = false
        this.refund.orderno = undefined
        this.refund.backNotice = undefined
      },
      handleSelect(key) {
        this.order.orderstatu=key
        this.getOrder()
      },
      pay(row){
        this.$router.push({path: '/pay',query:{orderNo:row.orderno}})
      },
      delOrder(row){
        delOrder(row.orderno).then(result => {
          if(result.code == "1000"){
            this.msgSuccess(result.retMsg)
            this.getOrder()
          }else{
            this.msgError(result.retMsg)
          }
        })
      },
      updateAddress(row){
        this.order.orderno = row.orderno
        this.order.userAddressId = row.userAddressId
        this.isShow = true
        this.getUserAddressList()
      },
      cancelOrder(row){
        let orderInfo = {
          orderno : row.orderno,
          orderstatu : '3'
        }
        updateOrder(orderInfo).then(result => {
          if(result.code=="1000"){
            this.msgSuccess("已取消订单！")
            this.getOrder()
          }else{
            this.msgError("取消订单失败！")
          }
        })
      },
      receipt(row){
        let orderInfo = {
          orderno : row.orderno,
          orderstatu : '5'
        }
        updateOrder(orderInfo).then(result => {
          if(result.code =="1000"){
            this.msgSuccess("已收货！")
            this.getOrder()
          }else{
            this.msgError("收货失败！")
          }
        })
      },
      getOrder(){
        orderlist(this.order).then(result => {
          this.orderInfoList = result.orderInfoList
          this.orderCount = result.orderCount
        })
      },
      getUserAddressList(){
        userAddressList().then(result => {
          if(result.code == "1000"){
            this.userAddressList = result.userAddressList
          }else{
            this.userAddressList = []
          }
        })
      }
    },
    created() {
      dictionaryList('order_dispatching').then(result => {
        if(result.code == "1000"){
          this.dispatchingWayList = result.dictionaryList
        }
      })
      dictionaryList('order_payWay').then(result => {
        if(result.code == "1000"){
          this.payWayList = result.dictionaryList
        }
      })
      dictionaryList('order_statu').then(result => {
        if(result.code == "1000"){
          this.orderStatuList = result.dictionaryList
        }
      })
      this.getOrder()
    }
  }
</script>

<style scoped>
  .border{
    border: 1px #ff4e00 solid;
    background-color: rgba(0,0,0,0.2);
    color: #ff4e00;
  }
  .radio:hover{
    background-color: rgba(0,0,0,0.2);
  }
  .notice{
    width: 100px;
    margin-bottom:20px;
    margin-left: 5px;
    margin-right: 5px;
    display: inline-block;
  }
  .notice:hover{
    cursor:default
  }
  .radio{
    margin: auto;
    margin-top: 10px;
    height: 25px;
  }
  .display{
    display: inline-block;
  }.content{
    width: 100%
  }
  .a,a{
    text-decoration: none;
    color: black;
  }
  a:hover{
    color: #FF4E00;
  }
  .tab{
    margin-left: 30px;
    margin-right: 30px;
    margin-bottom: 30px;
    display: inline-block;
  }
  .tab tr td:nth-child(1){
    width: 100px;
  }
  .table{
    width: 1300px;
    margin: auto;
  }
</style>
