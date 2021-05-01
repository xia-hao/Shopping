<template>
<div>
  <div class="mid-group">
    <div class="">
      <el-carousel height="450px">
        <el-carousel-item v-for="(slideShow,index) in slideShowList" :key="index">
          <router-link :to="{path: slideShow.picurlTo, query:{id:slideShow.pid}}">
            <img :src="urlimg+slideShow.picurl"/>
          </router-link>
        </el-carousel-item>
      </el-carousel>
    </div>
  </div>
  <div class="right-group">
    <div class="inews">
      <div class="news_t">
        商城公告&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <span class="fr"><router-link to="/newsList">更多 ></router-link></span>
      </div>
      <ul class="ul">
        <li v-for="(item, index) in newsList" :key="index"><span>[ 公告 ]</span>
          <router-link :to="{path: '/newsDeatil',query:{newsId:item.id}}">{{item.title}}</router-link>
        </li>
      </ul>
    </div>
  </div>

  <div class="foot">
    <div class="content mar_20">
      <img src="static/images/mban_1.jpg" width="1200" height="110" />
    </div>
    <div v-for="(item,index) in categorylist">
      <div class="i_t mar_10">
        <span class="floor_num">{{index+1}}F</span> <span class="fl">{{item.productCategory.name}}</span>
      </div>
      <div class="content">
        <div class="fresh_left">
          <div class="fre_ban">
            <div id="imgPlay1">
              <ul class="imgs" id="actor1">
                <li><a href="#"><img src="static/images/fre_r.jpg" width="211" height="286" /></a></li>
                <li><a href="#"><img src="static/images/fre_r.jpg" width="211" height="286" /></a></li>
                <li><a href="#"><img src="static/images/fre_r.jpg" width="211" height="286" /></a></li>
              </ul>
              <div class="prevf">上一张</div>
              <div class="nextf">下一张</div>
            </div>
          </div>
          <div class="fresh_txt">
            <div class="fresh_txt_c">
                <span v-for="(vo,index2) in item.productCategoryVoList" :key="index2">
                  <router-link :to="{path:'/product', query:{categoryId:item.productCategory.id}}">{{vo.productCategory.name}}</router-link>
                </span>
            </div>
          </div>
        </div>
        <div class="fresh_mid">
          <ul>
            <li v-for="(product ,index2) in item.productList" :key="index2">
              <div class="name">
                <router-link :to="{path: '/productdeatil',query:{id:product.id}}">{{product.name}}</router-link>
              </div>
              <div class="price">
                <font>￥<span>{{product.price}}</span></font> &nbsp;
              </div>
              <div class="img">
                <router-link :to="{path: '/productdeatil',query:{id:product.id}}">
                  <img :src="urlimg+product.filename" width="185" height="155" />
                </router-link>
              </div>
            </li>
          </ul>
        </div>
        <div class="fresh_right">
          <ul>
            <li><a href="#"><img src="static/images/fre_b1.jpg" width="260" height="220" /></a></li>
            <li><a href="#"><img src="static/images/fre_b2.jpg" width="260" height="220" /></a></li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div>
</template>

<script>
  import {productAll} from "@/api/home";
  import {newslist} from "../../api/news";
  import {slideShowlist} from "../../api/slideShow";

  export default {
        name: "homeproduct",
      data(){
        return{
          img: ["adver01.jpg","adver02.jpg","adver03.jpg","adver04.jpg","adver05.jpg","adver06.jpg"],
          newsList: undefined,
          news: {
            pageNo: 1,
            pageSize: 10
          },
          categorylist: undefined,
          slideShowList: undefined,
          slideShow: {
            pid: null,
            type: 'home'
          }
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
        selectNewsList(){
          newslist(this.news).then(response => {
            this.newsList = response.newsList
          }).catch(error => {
            this.msgError('异常：'+error);
          })
        },
        getSlideShow(){
          slideShowlist(this.slideShow).then(response => {
            this.slideShowList = response.slideShowList
          })
        }
      },
      created() {
        this.getproductAll();
        this.selectNewsList();
        this.getSlideShow();
      }
    }
</script>

<style scoped>
  .mid-group{
    width:730px;
    margin-top: 0px;
    margin-left: 0px;
  }
  .inews {
    width: 225px;
    background-color: #FFF;
    float: right;
  }

  .news_t {
    height: 39px;
    line-height: 39px;
    overflow: hidden;
    color: #3e3e3e;
    font-size: 16px;
    padding: 0 10px;
  }


  .news_t span {
    font-size: 12px;
    color: #888888;
  }
  .inews a {
    color: #888888;
  }

  .inews a:hover {
    color: #ff4e00;
  }

  .inews .ul {
    height: 160px;
    margin: 10px 10px 20px 10px;
    background: white;
  }
  .inews .ul li {
    height: 30px;
    line-height: 30px;
    overflow: hidden;
    color: #888888;
    border: 1px #cdcdcf solid;
    text-align: left;
  }

  .inews .ul li span {
    color: #3e3e3e;
    margin-right: 10px;
  }
  .right-group{
    float: right;
    margin-top: -460px;
    margin-right: 20px;
  }
  .foot{
    width: 1300px;
    margin-left: -300px;
  }
  .content {
    width: 1300px;
    overflow: hidden;
  }
  .content a:hover{
    color: #ff4e00;
  }
  .mar_20 {
    margin-top: 20px;
  }
  .i_t {
    width: 1190px;
    height: 39px;
    line-height: 39px;
    overflow: hidden;
    color: #ff4e00;
    font-size: 18px;
    padding: 0 5px;
    border-bottom: 2px solid #ff4e00;
  }
  .fl {
    float: left;
  }

  .mar_10 {
    margin-top: 10px;
  }
  .fresh_left {
    width: 211px;
    height: 441px;
    overflow: hidden;
    background-color: #a6e5be;
    float: left;
  }
  .fre_ban {
    width: 211px;
    height: 286px;
    overflow: hidden;
  }

  .fre_ban #imgPlay1 {
    width: 211px;
    height: 286px;
    overflow: hidden;
    margin: 0 auto;
    zoom: 1;
    position: relative;
  }

  .fre_ban #imgPlay .imgs img {
    width: 211px;
  }
  .fresh_txt {
    width: 211px;
    height: 155px;
    background: url('../../../static/images/s_txt.png') no-repeat center top;
    padding-top: 1px;
  }

  .fresh_txt_c {
    height: 120px;
    line-height: 30px;
    overflow: hidden;
    margin-top: 10px;
  }

  .fresh_txt_c a {
    display: inline-block;
    color: #999999;
    margin-left: 20px;
    margin-right: 12px;
  }

  .fresh_mid {
    width: 800px;
    height: 441px;
    overflow: hidden;
    background-color: #FFF;
    float: left;
  }

  .fresh_mid ul li {
    width: 259px;
    height: 220px;
    overflow: hidden;
    float: left;
    border-left: 1px solid #eaeaea;
    border-bottom: 1px solid #eaeaea;
  }

  .fresh_mid ul li .name {
    height: 20px;
    line-height: 20px;
    overflow: hidden;
    font-size: 14px;
    text-indent: 50px;
    margin-top: 12px;
  }
  .name a{
    color: #999999;
  }
  .fresh_mid ul li .price {
    height: 20px;
    line-height: 20px;
    overflow: hidden;
    color: #999999;
    text-indent: 50px;
    text-transform: uppercase;
  }

  .fresh_mid ul li .price font {
    color: #ff4e00;
    font-size: 14px;
  }

  .fresh_mid ul li .price span {
    font-size: 18px;
  }

  .fresh_mid ul li .img {
    width: 188px;
    height: 155px;
    overflow: hidden;
    margin-top: 5px;
  }

  .fresh_mid ul li .img img {
    -webkit-transition: all .5s;
    -moz-transition: all .5s;
    transition: all .5s;
  }

  .fresh_mid ul li:hover .img img {
    -moz-transform: scale(1.1, 1.1);
    -webkit-transform: scale(1.1, 1.1);
    -o-transform: scale(1.1, 1.1);
    -ms-transform: scale(1.1, 1.1);
    transform: scale(1.1, 1.1);
  }

  .fresh_right {
    width: 260px;
    height: 441px;
    overflow: hidden;
    background-color: #FFF;
    float: right;
  }

  .fresh_right ul li {
    width: 260px;
    height: 220px;
    overflow: hidden;
    border-bottom: 1px solid #eaeaea;
  }
  .fre_ban #imgPlay1 .imgs li {
    position: relative;
    float: left;
    width: 211px;
    height: 286px;
  }

  .fre_ban #imgPlay1 .imgs {
    width: 5760px;
  }

  .fre_ban #imgPlay1 .btn {
    width: 112px;
    height: 29px;
    overflow: hidden;
    text-indent: -9999px;
    position: absolute;
    right: 12px;
    bottom: 12px;
  }

  .fre_ban #imgPlay1 .btn a {
    width: 46px;
    height: 71px;
    overflow: hidden;
    display: block;
    background: url('../../../static/images/b_left.png') no-repeat;
  }

  .fre_ban #imgPlay1 .btn a:hover {
    background: url('../../../static/images/b_left.png') no-repeat;
  }

  .fre_ban .prevf {
    width: 20px;
    height: 40px;
    overflow: hidden;
    background: url('../../../static/images/s_left.png') no-repeat;
    cursor: pointer;
    text-indent: -9999px;
    position: absolute;
    left: 0;
    top: 123px;
  }

  .fre_ban .nextf {
    width: 20px;
    height: 40px;
    overflow: hidden;
    background: url('../../../static/images/s_right.png') no-repeat;
    cursor: pointer;
    text-indent: -9999px;
    position: absolute;
    right: 0;
    top: 123px;
  }

  .floor_num {
    width: 35px;
    height: 35px;
    line-height: 40px;
    overflow: hidden;
    background: url('../../../static/images/floor.png') no-repeat center top;
    color: #FFF;
    font-size: 14px;
    text-transform: uppercase;
    text-indent: 6px;
    float: left;
    display: inline;
    margin-right: 15px;
  }


  a{text-decoration:none;}

  *{ padding:0;
    margin: 0;}


</style>
