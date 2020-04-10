<template>
  <div class="i_bg">
    <div class="price">
      请输入：<el-input v-model="product.price1" @change="getselectproductlist" style="width: 100px;"/>&nbsp;
      ￥&nbsp; &nbsp;到 &nbsp; &nbsp;
      <el-input v-model="product.price2" @change="getselectproductlist" style="width: 100px;"/>&nbsp;￥
      之间的金额
    </div>
    <div class="content mar_20">
      <div class="l_list">
        <div class="list_t">
          <span class="fr">共发现{{productCount}}件</span>
        </div>
        <div class="list_c">
          <ul class="cate_list">
            <li v-for="(item,index) in productlist" :key="index">
              <div>
                <div class="img">
                  <router-link  :to="{path: '/productdeatil',query:{id:item.id}}">
                    <img :src="urlimg+item.filename" width="210" height="185"/>
                  </router-link>
                </div>
                <div class="name"><router-link :to="{path: '/productdeatil',query:{id:item.id}}">{{item.name}}</router-link></div>
                <div class="price">
                  <font>￥<span>{{item.price}}</span></font>
                </div>
              </div>
            </li>
            <div v-show="productCount==0">
              暂无商品
            </div>
          </ul>
        </div>
        <el-pagination
          background
          :hide-on-single-page="productCount==0"
          @size-change="getselectproductlist"
          @current-change="getselectproductlist"
          :current-page.sync="product.pageNo"
          :page-sizes="[4, 8, 12, 16]"
          :page-size.sync="product.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="productCount">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
  import {selectproductlist} from "../../api/product";

  export default {
    name: "productlist",
    data(){
      return{
        product: {
          categoryId: this.$route.query.categoryId,
          name: this.$route.query.productname,
          pageNo:1,
          pageSize:4,
          price1: undefined,
          price2: undefined
        },
        productlist: undefined,
        productCount: undefined
      }
    },
    methods: {
      getselectproductlist(){
        selectproductlist(this.product).then(response => {
          this.productlist = response.productList
          this.productCount = response.productCount
        }).catch(error => {
          this.msgError("异常："+error)
        })
      }
    },
    created() {
      this.getselectproductlist();
    }
  }
</script>

<style scoped>
  .i_bg {
    width: 100%;
    min-width: 1200px;
    overflow: hidden;
  }
  .content {
    width: 1200px;
    overflow: hidden;
  }
  .mar_20 {
    margin-top: 0px;
  }
  .l_list {
    width: 972px;
    overflow: hidden;
    padding-bottom: 30px;
  }
  .list_t {
    height: 39px;
    line-height: 39px;
    overflow: hidden;
    font-family: "宋体";
    border-bottom: 1px solid #eaeaea;
  }

  .fr {
    float: right;
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
