<template>
  <el-container>
    <el-header>
      <homeheader/>
    </el-header>
    <el-main>
      <homemain/>
    </el-main>
    <el-footer>
      <homefooter/>
    </el-footer>
  </el-container>

</template>

<script>
import homeheader from "../../components/homeheader";
import homemain from "../../components/homemain";
import homefooter from "../../components/homefooter";
export default {
  name: "index",
  components: {homeheader,homemain,homefooter}
}
</script>

<style scoped>
  .el-header, .el-footer {
    text-align: center;
    line-height: 60px;
  }

  .el-main {
    color: #333;
    text-align: center;
  }

  body > .el-container {
    margin-bottom: 40px;
  }

</style>
