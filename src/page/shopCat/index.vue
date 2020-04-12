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
        :data="shopCatList"
        row-key="id"
      >
        <el-table-column width="80px" prop="isSelected">
          <template slot="header" slot-scope="scope">
            <el-checkbox v-model="allchecked" @change="handleSelectionChange"/> 全选
          </template>
          <template slot-scope="scope">
            <el-checkbox v-model="scope.row.isSelected" @change="changeChecked(scope.row)"/>
          </template>
        </el-table-column>
        <el-table-column label="商品" prop="name" align="center">
          <template slot-scope="scope">
            <div>
              <img :src="urlimg+scope.row.filename"/>
              <div>
                <router-link :to="{path: '/productDeatil',query:{id : scope.row.id}}">{{scope.row.name}}</router-link>
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
            <el-input-number v-model="scope.row.number" :min="1" :max="200" @change="changeNumber(scope.row)"/>
          </template>
        </el-table-column>
        <el-table-column label="小计" prop="cost" align="center">
          <template slot-scope="scope">
            <span style="color: #FF4E00">￥{{scope.row.number*scope.row.price}}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button size="mini" @click="delShopCat(scope.row)"> 删除</el-button>
            <el-button size="mini" @click="collectShop(scope.row)">收藏</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="group">
        <div class="group-div1">
          已选择 <span style="color: #FF4E00">{{shopCatNumber}}</span> 件商品
          <span style="margin-left: 20px">
            <el-link :underline="false" :class="{'a':shopCatNumber!=0}" @click="batchDel" :disabled="shopCatNumber==0">批量删除</el-link>
            <el-link :underline="false" :class="{'a':shopCatNumber!=0}" @click="batchCollect" :disabled="shopCatNumber==0">收藏</el-link>
          </span>
        </div>
        <div class="group-div2">
          总价格：<span style="color: #FF4E00">￥{{shopCatPrice}}</span>
        </div>
        <div class="group-div3">
          <el-button @click="$router.push({path: '/fillOrderInfo',query:{shopCatList:shopCatList,shopCatNumber:shopCatNumber}})" :class="{'btn':shopCatNumber!=0,'button-disabled':shopCatNumber==0,}" :disabled="shopCatNumber==0">
            结算
          </el-button>
        </div>
      </div>
      <el-pagination style="text-align: center;margin-bottom: 50px"
                     background
                     :hide-on-single-page="true"
                     :current-page.sync="shopCat.pageNo"
                     :page-sizes="[2,4,6,8]"
                     :page-size.sync="shopCat.pageSize"
                     layout="total, sizes, prev, pager, next, jumper"
                     :total="shopCatCount"/>
    </div>
  </div>
</template>

<script>
  export default {
    name: "index",
    data() {
      return {
        shopCatCount:3,
        shopCatNumber: 0,
        allchecked:undefined,
        shopCat:{
          pageNo:1,
          pageSize:2
        },
        shopCatList: [{
          id:752,
          filename: 'A7436BC607E74C81B392DCFE69D4AEAB.jpg',
          name: '莫里斯按',
          price: 58,
          number: 2,
          isSelected: true,
          cost: 58
        }, {
          id:753,
          filename: '3C465E7B8A324A8DA2A2EEE202E36166.jpg',
          name: '三鹿奶粉',
          price: 58,
          number: 1,
          isSelected: true,
          cost: 58
        }]
      }
    },
    computed:{
      shopCatPrice:function () {
        var cost = 0;
        var number = 0
        for (let i = 0; i < this.shopCatList.length; i++) {
          if(this.shopCatList[i].isSelected){
            cost += this.shopCatList[i].price * this.shopCatList[i].number
            number +=1
          }
        }
        this.shopCatNumber= number
        if(this.shopCatNumber==this.shopCatList.length){
          this.allchecked = true
        }else{
          this.allchecked = false
        }
        return cost
      }
    },
    methods:{
      handleSelectionChange() {
          this.shopCatList.forEach(shopCat => {
            shopCat.isSelected = this.allchecked
            alert(shopCat.isSelected+"，"+shopCat.id)
          })
      },
      changeChecked(row){
        alert("id："+row.id+"，是否前往结算："+row.isSelected)
      },
      delShopCat(value){
        this.msgSuccess("已将商品 ‘"+value.name+"’ 移除购物车"+value.id)
      },
      collectShop(value){
        this.msgSuccess("已收藏 ‘"+value.name+"’"+value.id)
      },
      batchDel(){
        var i = 0
        this.shopCatList.forEach(shopCat => {
          if(shopCat.isSelected == true){
            i++;
          }
        })
        if(i>0){
          this.msgSuccess("已移除购物车")
        }else{
          this.msgSuccess("移除商品失败！")
        }
      },
      batchCollect(){
        var i = 0
        this.shopCatList.forEach(shopCat => {
          if(shopCat.isSelected == true){
            i++;
          }
        })
        if(i>0){
          this.msgSuccess("收藏成功！")
        }else{
          this.msgError("收藏失败！")
        }
      },
      changeNumber(row){
        this.msgSuccess('数量更改后价格，购物车信息更改前往数据库‘ 购物车表 ’更新数据：'+row.number*row.price)
      }
    },
    created() {

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
