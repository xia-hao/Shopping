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
            订单提交成功，请尽快付款！订单号：117191700323
          </div>
          <div style="margin-left: 40px">
            请您在 <span style="color: red">{{countDown}}</span> 内完成支付，否则订单会被自动取消
          </div>
          <div class="right">
            应付金额： <span style="color:red;">￥116</span> 元
          </div>
        </div>
        <div style="line-height: 25px;margin-top: 20px">
          <div class="order-info">
            收货地址：湖南邵阳市大祥区檀江乡湖南省邵阳市大祥区檀江乡清风村11组     收货人：*浩    ****7492
          </div>
          <div class="order-info">
            商品名称：十字勋章 瑞士双肩包运动带锁背包男士笔记本电脑包休闲商务书包女潮流旅游出差旅行包 15.6英寸升级版【USB充电+防盗锁】
          </div>
        </div>
        <div class="des_choice">
          <ul>
            <li :class="{checked:sel == ind}"
                v-for="(item,ind) in payType" :key="ind" @click="clickClass(ind)">
              {{item}}
              <div class="ch_img"></div>
            </li>
          </ul>
        </div>
        <div style="line-height: 25px;margin-top: 20px">
          <el-button style="background-color: #e14145;color: white;width: 200px;height: 50px">
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
export default {
  name: "index",
  data(){
    return {
      payType: ['支付宝','微信'],
      sel: 0,
      countDown: undefined,
      timer: true
    }
  },
  methods: {
    clickClass(ind){
      this.sel = ind; //让数组sel的第index+1的元素的值等于ind
    },
    countTime: function () {
      // 获取当前时间
      var date = new Date();
      var now = date.getTime();
      //设置截止时间
      var endDate = new Date("2020-04-11 16:02:00");
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
    this.countTime()
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
