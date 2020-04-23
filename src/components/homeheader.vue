<template>
  <div>
    <el-row type="flex" class="row-bg">
      <el-col :span="10">
        <div class="grid-content bg-purple">
          <div v-if="user==undefined">
            您好，请<router-link to="/login" class="router-link font-color">登录</router-link>
            <router-link to="/register" class="router-link">免费注册</router-link>
          </div>
          <div v-else>
            欢迎您，
            <router-link to="/login" class="router-link">{{user.username}}</router-link>
            &nbsp;&nbsp;
            <router-link to="/login" class="router-link">个人中心</router-link>
            &nbsp;&nbsp;
            <a href="javaScript:;" class="router-link" @click="onLoginOut()">注销</a>
          </div>
        </div>
      </el-col>
      <el-col :span="14">
        <div class="grid-content bg-purple">
          <router-link to="/shopCat" class="router-link">
            <span class="el-icon-shopping-cart-2 font-color"/> 购物车<span class="font-color">[{{cartlength}}]</span>
          </router-link>&nbsp;&nbsp;
          <router-link to="/order" class="router-link">我的订单</router-link>&nbsp;&nbsp;
          <router-link to="/login" class="router-link">收藏夹</router-link>&nbsp;&nbsp;
          <router-link to="/help" class="router-link">帮助中心</router-link>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {userinfo} from "../api/user";
import {loginout} from "../api/login";
import {getCount, selectShopCart} from "../api/shopCart";
import PubSub from 'pubsub-js'

export default {
  name: "homeheader",
  data(){
    return({
      cartlength: 0,
      user: undefined
    })
  },
  methods: {
    getuserinfo(){
      userinfo().then(response => {
        this.user = response.user
      })
    },
    onLoginOut(){
      loginout();
      this.user = undefined
      this.cartlength = 0
      this.msgSuccess("退出登录！")
    }
  },
  mounted () {
    // 订阅消息
    PubSub.subscribe('getShopCatCount', (msg, index) => {
      this.cartlength = index
    })
  },
  created() {
    this.getuserinfo()
    getCount().then(result => {
      this.cartlength = result
    })
  }
}
</script>

<style scoped>
  .router-link{
    text-decoration: none;
    color: #6C6C6C;
  }
  .router-link:hover{
    color: #FF4E00;
  }
  .font-color{
    color: #FF4E00;
  }
  div{
    font-size: 13px;
  }
</style>
