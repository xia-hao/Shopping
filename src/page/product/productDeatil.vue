<template>
  <div>
    <div class="content">
      <div id="tsShopContainer">
        <el-carousel height="380px">
          <el-carousel-item v-for="(slideShow,index) in slideShowList" :key="index">
            <img :src="urlimg+slideShow.picurl"/>
          </el-carousel-item>
        </el-carousel>
      </div>
      <div class="pro_des">
        <div class="des_name">
          <p>{{product.name}}</p>
          “开业巨惠，北京专柜直供”，不光低价，“真”才靠谱！
          {{product.filename}}
        </div>
        <div class="des_price">
          本店价格：<b>￥{{product.price}}</b><br/>
        </div>
        <div class="des_price">
          库存：<b>{{product.stock}}</b><br/>
        </div>
        <div class="des_choice" v-for="(item1,index) in skuList" :key="index">
          <span class="fl">{{item1.title}}：</span>
          <ul>
            <li :class="{checked:sel[index] == ind}" v-for="(item2,ind) in item1.items" :key="ind" @click="clickClass(index,ind)">
              {{item2.name}}
              <div class="ch_img"></div>
            </li>
          </ul>
        </div>
        <div class="des_join">
          <div class="j_nums">
            数量： <el-input-number v-model="quantity" :min="1" :max="product.stock"/>
          </div>
          <div id="fl">
            <img src="static/images/j_car.png" @click="onaddcart"/>
            <span class="btn">
              <el-button @click="buy">立即购买</el-button>
            </span>
          </div>
          <div style="margin-top: 20px;margin-right: 50px">
            <span>
              <el-button @click="collect" size="mini">收藏</el-button>
              <el-button @click="collect" size="mini">分享</el-button>
            </span>
          </div>
        </div>
      </div>
    </div>
    <div class="content">
      <div id="favoriteList">
      </div>
      <div class="l_list">
        <div class="des_border">
          <div class="des_tit">
            <ul>
              <li class="current">
                <router-link :to="{path: '/productDeatilImage',query:{id:id}}">详情</router-link>
              </li>
              <li class="current">
                <router-link :to="{path: '/productDiscuss',query:{id:id}}">评论</router-link>
              </li>
            </ul>
          </div>
          <router-view/>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import {productById} from "@/api/product";
  import {slideShowlist} from "../../api/slideShow";

  export default {
    name: "productDeatil",
    data(){
      return{
        quantity: 1,
        id: this.$route.query.id,
        product: undefined,
        slideShowList: undefined,
        slideShow: {
          pid: this.$route.query.id,
          type: 'product'
        },
        skuList:[
          {title: '型号选择', items: [{id:1,name:'30ml'},{id:2,name:'50ml'},{id:3,name:'100ml'}]},
          {title: '颜色选择', items: [{id:1,name:'红色'},{id:2,name:'白色'},{id:3,name:'黑色'}]}
          ],
        sel: []
      }
    },
    methods: {
      getProductById(){
        productById(this.id).then(response => {
          this.product = response.getproduct
        }).catch(error => {
          this.msgError("异常："+error)
        })
      },
      clickClass(index,ind){
        this.sel[index] = ind; //让数组sel的第index+1的元素的值等于ind
        this.sel = this.sel.concat([]); //因为数组是引用类型，对其中一个变量直接赋值不会影响到另一个变量（并未操作引用的对象），使用concat（操作了应用对象）
      },
      onaddcart(){
        this.msgSuccess("添加购物车成功")
      },
      buy(){
        this.msgSuccess("购买成功");
      },
      collect(){
        this.msgSuccess("收藏成功");
      },
      getSlideShow(){
        slideShowlist(this.slideShow).then(response => {
          this.slideShowList = response.slideShowList
        })
      }
    },
    created() {
      this.getProductById();
      this.getSlideShow();
    }
  }
</script>

<style scoped>

  .content {
    width: 1200px;
    overflow: hidden;
  }
  .pro_des {
    width: 520px;
    overflow: hidden;
    float: left;
    display: inline;
    margin-left: 30px;
    margin-bottom: 50px;
  }
  .des_name {
    height: 50px;
    line-height: 25px;
    overflow: hidden;
    color: #888888;
    font-size: 14px;
    text-align: left;
  }

  .des_name p {
    color: #3e3e3e;
    font-size: 18px;
    font-weight: bold;
    margin: 0;
    padding: 0;
  }

  .des_price {
    line-height: 20px;
    overflow: hidden;
    font-family: "宋体";
    text-align: left;
    margin-top: 25px;
    margin-bottom: 20px;
  }

  .des_price b {
    color: #ff3200;
    font-size: 18px;
    font-family: "Microsoft YaHei";
  }

  .des_price span {
    font-size: 14px;
    font-family: "Microsoft YaHei";
  }

  .des_choice {
    overflow: hidden;
    color: #888888;
    font-family: "宋体";
  }

  .fl {
    float: left;
    margin-top: 25px;
  }
  .des_choice ul li {
    height: 28px;
    line-height: 28px;
    overflow: hidden;
    font-size: 14px;
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

  ul.pay li.checked {
    height: 36px;
    line-height: 36px;
    padding: 0 24px;
    overflow: hidden;
    border: 2px solid #ff4e00;
  }

  ul.pay li.checked .ch_img {
    display: block;
  }
  .j_nums {
    margin-top: 10px;
    text-align: left;
    margin-bottom: 10px;
  }
  #fl{
    text-align: left;
    margin-top: 10px;
    height: 45px;
    width: 400px;
  }
  .btn{
    margin-top: 2px;
    float: right;
    margin-right: 100px;
  }
  .btn button{
    margin-left: 0px;
    margin-right: 0px;
  }
  .l_list {
    width: 972px;
    overflow: hidden;
    padding-bottom: 30px;
  }
  .des_border {
    overflow: hidden;
    margin-bottom: 10px;
    border: 1px solid #eaeaea;
  }
  .des_tit {
    height: 40px;
    background-color: #f6f6f6;
    border-bottom: 1px solid #eaeaea;
    position: relative;
  }
  a{
    text-decoration: none;
    color: #333333;
  }
  .des_tit ul {
    width: 100%;
    height: 29px;
    position: absolute;
    left: 0;
    top: 0;
  }

  .des_tit ul li {
    width: 110px;
    height: 29px;
    line-height: 27px;
    overflow: hidden;
    font-size: 14px;
    text-align: center;
    float: left;
  }

  .des_tit ul li.current {
    width: 109px;
    height: 23px;
    overflow: hidden;
    background-color: #FFF;
    border-top: 2px solid #ff4e00;
  }
  a:hover{
    color: #ff4e00;
  }

  .des_choice ul li.checked {
    height: 26px;
    line-height: 26px;
    padding: 0 14px;
    overflow: hidden;
    border: 2px solid #ff4e00;
  }

  .des_choice ul li.checked .ch_img {
    display: block;
  }

  #tsShopContainer{
    float:left;
    width: 500px;
    background-color: rgba(0,0,0,0);
  }

</style>
