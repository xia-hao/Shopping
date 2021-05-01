<template>
    <div class="content">
      <div style="text-align: center;margin-top:30px;margin-bottom: -50px">
        <img src="static/images/img3.jpg"/>
      </div>
      <div style="margin-top: 100px;">
        <el-button class="back-home" @click="$router.push({path: '/home'})">返回首页</el-button>
      </div>
      <div v-if="countDown != undefined">
        <div style="margin-top: 50px;height: 100px;border-bottom: 1px darkgray solid">
          <div style="margin-left: 40px">
            订单提交成功，请尽快付款！订单号：{{this.order.orderno}}
          </div>
          <div style="margin-left: 40px">
            请您在 <span style="color: red">{{countDown}}</span> 内完成支付，否则订单会被自动取消
          </div>
          <div class="right">
            应付金额： <span style="color:red;">￥{{this.order.orderprice}}</span> 元
          </div>
        </div>
        <div style="line-height: 25px;margin-top: 20px">
          <div class="order-info">
            收货地址：{{this.order.userAddress.detailedAddress}}&#12288;收货人：{{this.order.userAddress.consignee}}&#12288;{{this.order.userAddress.mobile}}
          </div>
          <div class="order-info">
            商品名称：
            <div v-for="(orderDetail,index) in this.order.orderDetailList" :key="index"
                 style="margin-left: 70px;margin-top: -25px;margin-bottom: 25px">
              {{orderDetail.product.name}}&#12288;
              <span v-for="(specs,ind2) in JSON.parse(orderDetail.productSku.productspecs)" :key="ind2">
                {{specs.title}}：{{specs.items}}
              </span>&#12288;
              <span >
                  商品金额： {{orderDetail.price}} 元
              </span>&#12288;
              <span >
                  数量： {{orderDetail.number}}
              </span>&#12288;
              <span >
                  总价格： {{orderDetail.cost}}
              </span>&#12288;
              <span >
                  订单创建时间： {{orderDetail.createtime}}
              </span>
            </div>
          </div>
        </div>
        <div class="des_choice">
          <ul>
            <li :class="{checked:payMent.paytype == item.id}"
                v-for="(item,ind) in payType" :key="ind" @click="clickClass(item.id)">
              {{item.way}}
              <div class="ch_img"></div>
            </li>
          </ul>
        </div>
        <div style="line-height: 25px;margin-top: 20px">
          <el-button style="background-color: #FF4E00;color: white;width: 200px;height: 50px" @click="pay">
            立即支付
          </el-button>
        </div>
      </div>
      <div v-else>
        订单已经超时被取消，请重新下单进行支付
      </div>
    </div>
</template>

<script>
import {ByOrderNo} from "../../api/order";
import {pay} from "../../api/payMent";

export default {
  name: "index",
  data(){
    return {
      payType: [{id:1,way:'微信'},{id:2,way:'支付宝'}],
      countDown: undefined,
      timer: true,
      order: {
        orderno: this.$route.query.orderNo
      },
      payMent: {
        orderno: undefined,
        paytype: 1
      }
    }
  },
  methods: {
    pay(){
      this.payMent.orderno = this.order.orderno
      pay(this.payMent).then(result => {
        if(result.code == "1000"){
          this.msgSuccess(result.retMsg)
          this.$router.push({path: '/home'})
        }else{
          this.msgError(result.retMsg)
        }
      })
    },
    clickClass(id){
      this.payMent.paytype = id; //让数组sel的第index+1的元素的值等于ind
    },
    countTime: function () {
      // 获取当前时间
      var date = new Date();
      var now = date.getTime();
      //设置截止时间
      var endDate = new Date(this.order.deadlinetime);
      var end = endDate.getTime();
      //时间差
      var leftTime = end -now  ;
      //定义变量 d,h,m,s保存倒计时的时间
      if (leftTime >= 0) {
        this.countDown= Math.floor(leftTime / 1000 / 60 / 60 / 24) + "天" +Math.floor(leftTime / 1000 / 60 / 60 % 24) + "时"
          +Math.floor(leftTime / 1000 / 60 % 60)+ "分"+Math.floor(leftTime / 1000 % 60) + "秒"
      }else{
        this.timer = false
        this.countDown = undefined
      }
      // console.log(this.s);
      //递归每秒调用countTime方法，显示动态时间效果
      if(this.timer){
        setTimeout(this.countTime, 1000);
      }
    }
  },
  created() {
    ByOrderNo(this.order.orderno).then(result => {
      if(result.code == "1000"){
        this.order = result.orderInfo
        this.countTime()
      }else{
        this.msgError("订单异常！")
      }
    })
  }
}
</script>

<style scoped>
.content{
  width: 1000px;
  margin: auto;
}
.right{
  float: right;
  margin-top: -30px;
  margin-right: 10px;
}
.order-info{
  font-size: 14px;color: #5b5b5b
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
  height: 70px;
  margin-top: 50px;
  border-top: 1px darkgray solid;
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
  font-size: 15px;
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
</style>
