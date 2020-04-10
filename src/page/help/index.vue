<template>
  <div>
    <el-container style="height: 600px; border: 1px solid #eee">
      <el-aside width="230px" style="background-color: rgba(234, 234, 234, 0.1);border: 1px darkgray solid">
        <h3 class="center">帮助中心</h3>
        <router-link to="/newbie" class="list-group-item sello">新手入门</router-link>
        <router-link to="/integralHelp" class="list-group-item sello">积分计划</router-link>
        <router-link to="/account" class="list-group-item sello">账号管理</router-link>
        <router-link to="/saleService" class="list-group-item sello">售后服务</router-link>
        <router-link to="/home" class="list-group-item sello">返回首页</router-link>
      </el-aside>
      <el-container>
        <el-header style="border: 1px darkgray solid;">
          <h1>帮助中心</h1>
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
    name: 'index',
    data () {
      return {

      }
    }
  };
</script>

<style>
  .list-group-item{
    top: 5%;
    text-decoration:none;
    color: #303133;
    position: relative;
    display: block;
    padding: 10px 15px;
    margin-bottom: -1px;
    border: 1px solid #ddd;
  }
  .sello:hover{
    background: rgba(234, 234, 234, 0.4);
  }
  .el-header{
    background-color: rgba(234, 234, 234, 0.1);
    color: #333;
    text-align: center;
  }
  .center{
    text-align: center;
  }

</style>
