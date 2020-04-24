<template>
  <div class="content">
    <div class="text">
      <h1>我的收藏</h1>
    </div>
    <div class="table">
      <div>
        <el-button class="back-home" @click="$router.push({path: '/home'})">返回首页</el-button>
      </div>
      <div class="mar_20">
        <div class="l_list">
          <div class="list_c">
            <ul class="cate_list">
              <li v-for="(item,index) in collectibleList" :key="index">
                <div>
                  <div class="img">
                    <router-link  :to="{path: '/productdeatil',query:{id:item.product.id}}">
                      <img :src="urlimg+item.product.filename" width="210" height="185"/>
                    </router-link>
                  </div>
                  <div class="name"><router-link :to="{path: '/productdeatil',query:{id:item.product.id}}">{{item.product.name}}</router-link></div>
                  <div class="price">
                    <font>￥<span>{{item.product.price}}</span></font>
                  </div>
                </div>
              </li>
              <div v-show="collectibleCount==0">
                暂无商品
              </div>
            </ul>
          </div>
          <el-pagination
            background
            :hide-on-single-page="collectibleCount==0"
            @size-change="selectCollectible"
            @current-change="selectCollectible"
            :current-page.sync="collectible.pageNo"
            :page-sizes="[4, 8, 12, 16]"
            :page-size.sync="collectible.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="collectibleCount">
          </el-pagination>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import {list} from "../../api/collectible";

  export default {
    name: "index",
    data() {
      return {
        collectible:{
          pageNo:1,
          pageSize:8
        },
        collectibleList:[],
        collectibleCount:0
      };
    },
    methods: {
      selectCollectible(){
        list(this.collectible).then(result => {
          if(result.code == "1000"){
            this.collectibleList = result.collectibleList
            this.collectibleCount = result.collectibleCount
          }else{
            this.collectibleList = []
            thsi.collectibleCount = 0
          }
        })
      }
    },
    created() {
      this.selectCollectible()
    }
  }
</script>

<style scoped>
  .content{
     width: 100%
   }
  .back-home{
    margin-bottom: 2%;
  }
  .a,a{
    text-decoration: none;
    color: black;
  }
  a:hover{
    color: #FF4E00;
  }
  .tab tr td:nth-child(1){
    width: 100px;
  }
  .text{
    width: 100%;
    text-align: center;
  }
  .table{
    width: 900px;
    margin: auto;
  }
  .mar_20 {
    text-align: center;
  }
  .l_list {
    width: 972px;
    overflow: hidden;
    padding-bottom: 30px;
  }

  .list_c {
    overflow: hidden;
  }

  .list_c ul {
    overflow: hidden;
  }
  ul.cate_list {
    padding: 5px 0;
  }

  ul.cate_list li {
    width: 220px;
    height: 290px;
    overflow: hidden;
    float: left;
    display: inline;
    margin: 0 5px;
  }
  ul.cate_list li div{
    margin-top: 20px;
  }

  ul.cate_list li:hover {
    background-color: #FFF;
    -webkit-box-shadow: 0 0 5px #e0e0e0;
    -moz-box-shadow: 0 0 5px #e0e0e0;
    box-shadow: 0 0 5px #e0e0e0;
  }

  ul.cate_list li .img {
    width: 208px;
    height: 185px;
    overflow: hidden;
  }

  ul.cate_list li .price {
    width: 208px;
    height: 25px;
    line-height: 25px;
    overflow: hidden;
    color: #999999;
    text-transform: uppercase;
    margin-top: 10px;
    text-align: left;
    text-indent: 2em;
  }

  ul.cate_list li .price font {
    color: #ff4e00;
    font-size: 14px;
  }

  ul.cate_list li .price span {
    font-size: 18px;
  }

  ul.cate_list li .name {
    width: 208px;
    height: 25px;
    line-height: 25px;
    overflow: hidden;
    font-size: 14px;
    text-align: left;
    text-indent: 2em;
  }
  ul.cate_list li .name a{
    text-decoration: none;
    color: #333333;
  }
  ul.cate_list li .name a:hover{
    color: #ff4e00;
  }
  .price{
    float: left;
  }
</style>
