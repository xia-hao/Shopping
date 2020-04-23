<template>
  <div>
    <div class="content">
      <div id="tsShopContainer">

        <el-carousel height="380px" v-if="productSku.imgarray">
          <el-carousel-item v-for="(img,index) in productSku.imgarray.img" :key="index">
            <img :src="urlimg+img"/>
          </el-carousel-item>
        </el-carousel>

        <el-carousel height="380px" v-else>
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
        <div v-if="productSku.productprice&&productSku.productstock">
          <div class="des_price" >
            本店价格：<b>￥{{productSku.productprice}}</b><br/>
          </div>
          <div class="des_price">
            库存：<b>{{productSku.productstock}}</b><br/>
          </div>
        </div>
        <div v-else>
          <div class="des_price" >
            本店价格：<b>￥{{product.price}}</b><br/>
          </div>
          <div class="des_price">
            库存：<b>{{product.stock}}</b><br/>
          </div>
        </div>
        <div class="des_choice" v-for="(item1,index) in this.product.attributelist" :key="index">
          <span class="fl">{{item1.title}}：</span>
          <ul>
            <li :class="{checked:sel[index] == ind}" v-for="(item2,ind) in item1.items" :key="ind" @click="clickClass(index,ind,item1)">
              {{item2}}
              <div class="ch_img"></div>
            </li>
          </ul>
        </div>
        <div class="des_join">
          <div class="j_nums">
            数量： <el-input-number v-model="quantity" :min="1" :max="200"/>
          </div>
          <div id="fl">
            <img src="static/images/j_car.png" @click="onaddcart"/>
            <span class="btn">
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
               <router-link v-if="productSku.id != undefined" :to="{path: '/productDeatilImage',query:{id: id,skuid: productSku.id}}">详情</router-link>
                <router-link v-else :to="{path: '/productDeatilImage',query:{id:id}}">详情</router-link>
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
  import {BySpecsAndPid} from "../../api/productSku";
  import {addShopCart, getCount} from "../../api/shopCart";
  import PubSub from 'pubsub-js'

  export default {
    name: "productDeatil",
    data(){
      return{
        quantity: 1,
        id: this.$route.query.id,
        product: [],
        slideShowList: undefined,
        slideShow: {
          pid: this.$route.query.id,
          type: 'product'
        },
        productSpecsArray:[],
        sel: [],
        productSku : {
          productid: undefined,
          productspecs:''
        },
        productSkuParams : {
          productid: undefined,
          productspecs:''
        },
        shopCat:{}
      }
    },
    methods: {
      getProductById(){
        productById(this.id).then(response => {
          this.product = response.getproduct
          if(response.getproduct.attributelist != ''){
            this.product.attributelist = JSON.parse(response.getproduct.attributelist)
            this.product.defaultspecs = JSON.parse(response.getproduct.defaultspecs)
            this.defaultSpecs()
          }else{
            this.product.attributelist = [
              {title: "容量", items: ["128GB","265GB","512GB"]},
              {title: "颜色", items: ["红色","黑色","蓝色"]}
            ]
          }
        }).catch(error => {
          this.msgError("异常："+error)
        })
      },
      clickClass(index,ind,value){
        this.sel[index] = ind; //让数组sel的第index+1的元素的值等于ind
        this.sel = this.sel.concat([]); //因为数组是引用类型，对其中一个变量直接赋值不会影响到另一个变量（并未操作引用的对象），使用concat（操作了应用对象）
        this.productSpecsArray[index] = '{"title":'+'"'+value.title +'","items":'+'"'+value.items[ind]+'"}'
        this.productSkuParams.productspecs = ''
        this.productSpecsArray.forEach(item => {
          this.productSkuParams.productspecs += item+','
        })
        this.productSkuParams.productspecs=this.productSkuParams.productspecs.substring(0, this.productSkuParams.productspecs.lastIndexOf(','))
        this.productSkuParams.productid = this.product.id
        this.$router.push({path: '/productDiscuss',query:{id: this.id}})
        BySpecsAndPid(this.productSkuParams).then(response => {
          if(response.code == "1000"){
            this.productSku = response.productSku
            this.productSku.imgarray = JSON.parse(response.productSku.imgarray)
            this.$router.push({path: '/productDeatilImage',query:{id: this.id,skuid: this.productSku.id}})
          }
        })
      },
      onaddcart(){
        if(this.product.attributelist.length ==  this.productSpecsArray.length){
          this.shopCat.productid = this.product.id
          this.shopCat.skuid = this.productSku.id
          this.shopCat.price = this.productSku.productprice
          this.shopCat.number = this.quantity
          this.shopCat.cost = this.quantity*this.productSku.productprice
          addShopCart(this.shopCat).then(result => {
            if(result.code == "1000"){
              this.msgSuccess("添加购物车成功！")
              getCount().then(result => {
                PubSub.publish('getShopCatCount', result) // 发布消息
              })
            }else{
              this.msgError("添加购物车失败！")
            }
          })
          console.info(this.shopCat)
        }else{
          this.msgError('请选择规模！')
        }
      },
      collect(){
        this.msgSuccess("收藏成功");
      },
      getSlideShow(){
        slideShowlist(this.slideShow).then(response => {
          this.slideShowList = response.slideShowList
        })
      },
      defaultSpecs(){
        this.product.defaultspecs.forEach(item => {
          this.clickClass(item.titleIndex,item.itemsIndex,this.product.attributelist[item.titleIndex])
        })
      }
    },
    created() {
      this.$router.push({path: '/productDeatilImage',query: {id:this.id}})
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
    margin-top: 10px;
    float: right;
    margin-right: 80px;
  }
  .btn button{
    margin: auto;
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
