<template>
  <div style="text-align: center">
    <el-table :data="userDealrecordList">
      <el-table-column label="商品" align="center">
        <template slot-scope="scope">
          {{scope.row.productname}}×{{scope.row.number}}
        </template>
      </el-table-column>
      <el-table-column label="支出 / 收入" prop="price" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.price>0">
            +{{scope.row.price}}
          </span>
          <span v-else>
            {{scope.row.price}}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="用户余额" prop="userprice" align="center"/>
      <el-table-column label="创建日期" prop="createtime" align="center"/>
    </el-table>
    <el-pagination
      background
      :hide-on-single-page="userDealrecordCount==0"
      @size-change="selectUserDealrecord"
      @current-change="selectUserDealrecord"
      :current-page.sync="userDealrecord.pageNo"
      :page-sizes="[10, 20, 30, 40]"
      :page-size.sync="userDealrecord.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="userDealrecordCount">
    </el-pagination>
  </div>
</template>

<script>
  import {userDealrecordList} from "../../api/userDealrecord";

  export default {
    name: "userDealrecord",
    data(){
      return{
        userDealrecordList: [],
        userDealrecordCount:0,
        userDealrecord:{
          pageNo:1,
          pageSize:10
        }
      }
    },
    methods:{
      selectUserDealrecord(){
        userDealrecordList(this.userDealrecord).then(result => {
          if(result.code == "1000"){
            this.userDealrecordList = result.userDealrecordList
            this.userDealrecordCount = result.userDealrecordCount
          }
        })
      }
    },
    created() {
      this.selectUserDealrecord()
    }
  }
</script>

<style scoped>

</style>
