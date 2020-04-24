<template>
  <div class="content">
    <div style="text-align: center;margin-top:30px;margin-bottom: -50px">
      <img src="static/images/img1.jpg"/>
    </div>
    <div class="table">
      <div>
        <el-button class="back-home" @click="$router.push({path: '/home'})">返回首页</el-button>
      </div>
      <el-table
        ref="shopCat"
        v-loading="false"
        :data="shopCartList"
        row-key="id"
      >
        <el-table-column width="80px" prop="isselected">
          <template slot="header" slot-scope="scope">
            <el-checkbox v-model="allchecked" @change="handleSelectionChange"/> 全选
          </template>
          <template slot-scope="scope">
            <el-checkbox v-model="scope.row.isselected" @change="changeChecked(scope.row)"/>
          </template>
        </el-table-column>
        <el-table-column label="商品" align="center">
          <template slot-scope="scope">
            <div>
              <img :src="urlimg+JSON.parse(scope.row.productSku.imgarray).img[0]" width="100px" height="100px"/>
              <div>
                <router-link :to="{path: '/productDeatil',query:{id : scope.row.product.id}}">{{scope.row.product.name}}</router-link>
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="商品属性" align="left" width="120px">
          <template slot-scope="scope">
            <div v-for="(item,index) in JSON.parse(scope.row.productSku.productspecs)" :key="index">
              <div>
                {{item.title}} ： {{item.items}}
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="单价" prop="price" align="center" >
          <template slot-scope="scope">
            <span style="color: #FF4E00">￥{{scope.row.price}}</span>
          </template>
        </el-table-column>
        <el-table-column label="数量" prop="number" align="center">
          <template slot-scope="scope">
            <el-input-number v-model="scope.row.number" :min="1" :max="200" @change="changeNumber(scope.row)" style="width: 150px"/>
          </template>
        </el-table-column>
        <el-table-column label="小计" prop="cost" align="center">
          <template slot-scope="scope">
            <span style="color: #FF4E00">￥{{scope.row.number*scope.row.price}}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button size="mini" @click="delShopCat(scope.row)"> 移除</el-button>
            <el-button size="mini" @click="collectShop(scope.row)">收藏</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="group">
        <div class="group-div1">
          已选择 <span style="color: #FF4E00">{{shopCatNumber}}</span> 件商品
          <span style="margin-left: 20px">
            <el-link :underline="false" :class="{'a':shopCatNumber!=0}" @click="batchDel" :disabled="shopCatNumber==0">批量移除</el-link>
            <el-link :underline="false" :class="{'a':shopCatNumber!=0}" @click="batchCollect" :disabled="shopCatNumber==0">收藏</el-link>
          </span>
        </div>
        <div class="group-div2">
          总价格：<span style="color: #FF4E00">￥{{shopCatPrice}}</span>
        </div>
        <div class="group-div3">
          <el-button @click="$router.push({path: '/fillOrderInfo'})" :class="{'btn':shopCatNumber!=0,'button-disabled':shopCatNumber==0,}" :disabled="shopCatNumber==0">
            结算
          </el-button>
        </div>
      </div>
      <!--<el-pagination style="text-align: center;margin-bottom: 50px"
                     background
                     :hide-on-single-page="true"
                     :current-page.sync="shopCat.pageNo"
                     :page-sizes="[2,4,6,8]"
                     :page-size.sync="shopCat.pageSize"
                     layout="total, sizes, prev, pager, next, jumper"
                     :total="shopCatCount"
                     @size-change="getselectShopCart"
                     @current-change="getselectShopCart"/>-->
    </div>
  </div>
</template>

<script>
  import {addCollectible, delShopCart, selectShopCart, updateShopCart} from "../../api/shopCart";
  import router from "../../router";

  export default {
    name: "index",
    data() {
      return {
        shopCatCount: 0,
        shopCatNumber: 0,
        allchecked:false,
        shopCat:{},
        shopCartList: []
      }
    },
    computed:{
      shopCatPrice:function () {
        var cost = 0;
        var number = 0
        for (let i = 0; i < this.shopCartList.length; i++) {
          if(this.shopCartList[i].isselected){
            cost += this.shopCartList[i].price * this.shopCartList[i].number
            number +=1
          }
        }
        this.shopCatNumber= number
        if(this.shopCatNumber==this.shopCartList.length && this.shopCartList != ''){
          this.allchecked = true
        }else{
          this.allchecked = false
        }
        return cost
      }
    },
    methods:{
      reset(){
        this.shopCat = {
          id: undefined,
          number: undefined,
          isselected: undefined,
          cost: undefined
        }
      },
      handleSelectionChange() {
          this.shopCartList.forEach(shopCat => {
            shopCat.isselected = this.allchecked
            this.reset()
            this.shopCat.id = shopCat.id
            this.shopCat.isselected = shopCat.isselected
            updateShopCart(this.shopCat)
          })
      },
      changeChecked(row){
        this.reset()
        this.shopCat.id = row.id
        this.shopCat.isselected = row.isselected
        updateShopCart(this.shopCat)
      },
      delShopCat(value){
        delShopCart(value.id).then(result => {
          if(result.code == "1000"){
            this.msgSuccess('移除购物车成功！')
            this.getselectShopCart()
          }else{
            this.msgError("移除购物车失败！")
          }
        })
      },
      collectShop(shopCart){
        this.$confirm('收藏后选中的商品将不在购物车显示！', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          addCollectible(shopCart).then(result => {
            if(result.code == "1000"){
              this.msgSuccess("收藏成功！")
              this.getselectShopCart()
            }else{
              this.msgError("收藏失败！")
            }
          })
        });
      },
      batchDel(){
        let i = 0;
        this.shopCartList.forEach(shopCat => {
          if(shopCat.isselected == true){
            delShopCart(shopCat.id)
            i++;
          }
        })
        if(i>0){
          this.msgSuccess("批量移除成功！")
        }else{
          this.msgError("批量移除失败！")
        }
        this.getselectShopCart()
      },
      batchCollect(){
        let i = 0;
        this.$confirm('收藏后选中的商品将不在购物车显示！', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.shopCartList.forEach(shopCat => {
            if(shopCat.isselected == true){
              addCollectible(shopCat)
              i++
            }
          })
          if(i>0){
            this.msgSuccess("收藏成功！")
          }else{
            this.msgError("收藏失败！")
          }
          this.getselectShopCart()
        });
      },
      changeNumber(row){
        this.reset()
        this.shopCat.id = row.id
        this.shopCat.number = row.number
        this.shopCat.cost = row.number*row.price
        updateShopCart(this.shopCat)
      },
      getselectShopCart(){
        this.shopCartList = []
        this.shopCatCount = 0
        selectShopCart().then(result => {
          console.info(result)
          if(result.code == "1000"){
            if(result.shopCatCount>0){
              this.shopCartList = result.shopCartList
              this.shopCatCount = result.shopCatCount
            }else{
              this.shopCartList = []
              this.shopCatCount = 0
            }
          }
        })
      }
    },
    created() {
     this.getselectShopCart()
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
  .a:hover{
    color: #FF4E00;
  }
  a:hover{
    color: #FF4E00;
  }
  .table{
    width: 1200px;
    margin: auto;
    margin-top: 100px
  }
  .group{
    width: 1200px;
    border: 1px rgba(216,216,216,0.5) solid;
    background-color: rgba(238, 238, 238, 0.5);
    margin-top: 20px;
    margin-bottom: 20px;
    height: 40px;
  }
  .group-div1{
    width: 30%;
    margin-left: 20px;
    display: inline-block;
  }
  .group-div2{
    width: 40%;
    text-align: right;
    display: inline-block;
  }
  .group-div3{
    width: 26%;
    display: inline-block;
    margin-left: 18px;
    text-align: right;
  }
  .group-div3>.btn{
    background-color: #FF4E00;
    color: white;
    width: 100px;
  }
  .group-div3>.button-disabled{
    background-color: #dcdcdc;
    color: #9b9b9b;
    width: 100px;
  }
  .el-table__header .el-table-column--selection .cell .el-checkbox:after {
    content: "全选";
  }
</style>
