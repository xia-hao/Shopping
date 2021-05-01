<template>
  <div class="content">
    <div class="head">
      <el-form @submit.native.prevent :inline="true">
        <el-form-item label="商品名称" label-width="68px">
          <el-input  v-model="productname"
            placeholder="请输入商品名称" clearable
            size="small" style="width: 240px">
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button
            class="filter-item"
            type="primary"
            icon="el-icon-search"
            style="width: 80px;height: 30px"
            :disabled="productname== ''"
            @click="search"
          >搜索</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="container">
      <div class="left-group">
        <div class="menu">
          <div class="nav-box">
            <div class="nav-top">全部商品分类</div>
            <ul class="ul">
             <li class="li" v-for="(item,index1) in categorylist" :key="index1">
                <span>{{item.productCategory.name}}</span>
                 <div class="zj_l_c">
                  <span v-for="(vo,index2) in item.productCategoryVoList" :key="index2">
                     <h2 >
                       <router-link  :to="{path:'/product', query:{categoryId:vo.productCategory.id}}">{{vo.productCategory.name}}</router-link>
                     </h2>
                     <span v-for="(vo2,index3) in vo.productCategoryVoList" :key="index3">
                       <router-link :to="{path:'/product', query:{categoryId:vo.productCategory.id}}">{{vo2.productCategory.name}}</router-link>|
                     </span>
                  </span>
                 </div>
              </li>
            </ul>
          </div>
          <ul class="menu_r">
            <li><router-link to="/home">首页</router-link></li>
            <li v-for="(item,index) in categorylist" :key="index">
              <router-link :to="{path:'/product', query:{categoryId:item.productCategory.id}}">
                {{item.productCategory.name}}
              </router-link>
            </li>
          </ul>
        </div>
      </div>
     <div class="show">
       <router-view
         v-if="$route.name === 'product'"
         :key="$route.fullPath"
       />
       <router-view v-else />
     </div>
    </div>
  </div>
</template>

<script>
  import {productAll} from "@/api/home";

  export default {
    name: "homemain",
    data(){
      return{
        productname: '',
        categorylist: undefined
      }
    },
    methods: {
      getproductAll(){
        productAll().then(reponse => {
          this.categorylist = reponse.productCategoryVoList
        }).catch(error => {
          this.msgError("异常："+error)
        })
      },
      search(){
        this.$router.push({path: "/product",query:{productname: this.productname}})
      }
    },
    created() {
      this.getproductAll();
    }
  }
</script>

<style scoped>
  .show{
     margin-top: 50px;
     margin-left: 20px;
  }
  .menu {
    width: 1200px;
    line-height: 43px;
    color: #3e3e3e;
    font-size: 16px;
    z-index: 800;
  }
  .menu_r {
    width: 740px;
    overflow: hidden;
    display: inline;
    background-color: white;
  }

  ul.menu_r li {
    height: 40px;
    line-height: 40px;
    overflow: hidden;
    text-align: center;
    float: left;
    display: inline;
    margin-right: 10px;
  }

  ul.menu_r li a {
    height: 40px;
    overflow: hidden;
    float: left;
    color: #3e3e3e;
    font-size: 20px;
    margin-left: 20px;
  }


  .head{
    height: 100px;
    width: 1300px;
    text-align: center;
  }
  .container{
    width: 1300px;
    display: flex;
  }
  .left-group{
    width: 275px;
    text-align: center;
  }
  .content {
    width: 1300px;
    overflow: hidden;
  }
  .content a:hover{
    color: #ff4e00;
  }
  a{text-decoration:none;}

  .nav-box{
    width:220px;
    float:left;
    margin-left: 50px;
  }

  .nav-top {
    display:block;
    height:45px;
    font-size:16px;
    line-height:45px;
    padding:0 10px;
    background: #79847e;
    color:#fff;

  }
   *{ padding:0;
    margin: 0;}
  .ul{background: #8c8c8e;list-style:none;padding-bottom:1px;height:455px;}
  ul li{
    height:45px;
    font-size:14px;
    color:#fff;
    position:relative;
    padding-left: 5px;
  }
  .nav-box ul li:hover div{
    display: block;
  }
  .nav-box ul li:hover {
    background-color: rgba(86, 255, 229, 0.5);
  }
  ul li a{color:#fff;}
  .nav-box ul li .zj_l_c{
    display: none;
    position: absolute;
    left: 220px;
    text-align: left;
    top: -10px;
    background: rgba(255, 255, 255, 0.9);
    z-index: 100;
  }
  .zj_l_c a{
    margin-left: 30px;
    color: #333333;
  }
  .zj_l_c a:hover {
    color: #ff4e00;
  }

   .zj_l_c {
    width: 738px;
    height: 370px;
    overflow: hidden;
    background-color: #FFF;
    position: absolute;
    left: 210px;
    top: 0;
  }
  .zj_l_c {
    overflow: hidden;
    float: left;
    display: inline;
  }

  .zj_l_c h2 {
    height: 25px;
    line-height: 25px;
    color: #3e3e3e;
    font-size: 14px;
    font-weight: bold;
    margin-bottom: 5px;
    margin-left: 10px;
  }
  .zj_l_c>span{
    float: left;
    height: 100px;
    margin-right: 100px;
    color: #b8b8b8;
  }
  .zj_l_c a {
    line-height: 22px;
    font-size: 12px;
    font-family: "宋体";
    padding: 0 10px;
    margin: 0;
  }


</style>
