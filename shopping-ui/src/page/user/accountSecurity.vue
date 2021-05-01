<template>
  <div>
    <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
      <el-menu-item index="1">修改手机号码</el-menu-item>
      <el-menu-item index="2">修改登录密码</el-menu-item>
    </el-menu>
    <router-view/>
  </div>
</template>

<script>

  export default {
    name: 'accountSecurity',
    data() {
      return {
        activeIndex: '1'
      };
    },
    methods: {
      handleSelect(value){
        if(value == '1'){
          this.$router.push({path:'/updateMobile'})
        }else{
          this.$router.push({path:'/updatePwd'})
        }
      }
    }
  }
</script>
