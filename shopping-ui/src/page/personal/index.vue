<template>
  <div>
    <el-container style="height: 600px; border: 1px solid #eee">
      <el-aside width="230px" style="background-color: rgba(234, 234, 234, 0.1);border: 1px darkgray solid">
        <router-link to="/home" style="text-decoration: none;color: black"><i class="el-icon-s-home"></i>返回首页</router-link>
        <h3 class="center">个人中心</h3>
        <h4>我的钱包</h4>
        <router-link to="/userInfo" class="list-group-item1 sello1">用户信息</router-link>
        <router-link to="/userDealrecord" class="list-group-item1 sello1">交易明细</router-link>
        <h4>账户设置</h4>
        <router-link to="/accountSecurity" class="list-group-item1 sello1">账户安全</router-link>
        <router-link to="/userAddress" class="list-group-item1 sello1">收货地址</router-link>
        <h4>交易管理</h4>
        <router-link to="/order" class="list-group-item1 sello1">我的订单</router-link>
        <router-link to="/collectible" class="list-group-item1 sello1">我的收藏</router-link>
      </el-aside>
      <el-container>
        <el-header style="border: 1px darkgray solid;">
          <h1>个人中心</h1>
        </el-header>
        <el-main>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  export default {
    name: 'index'
  };
</script>

<style>
  h4{
    margin: 10px 10px;
  }
  .list-group-item1{
    text-decoration:none;
    color: #303133;
    position: relative;
    display: block;
    font-size: 14px;
    padding: 10px 60px;
    margin-bottom: -1px;
    border: 1px solid #ddd;
  }
  .sello1:hover{
    background: rgba(234, 234, 234, 0.4);
  }
  .el-header{
    background-color: rgba(234, 234, 234, 0.1);
    color: #333;
    text-align: center;
  }
  .center{
    margin-bottom: 50px;
    text-align: center;
  }

</style>
