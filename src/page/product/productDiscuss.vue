<template>
 <div>
   <el-form @submit.native.prevent :inline="true" class="form">
     <el-form-item label="评论：" prop="discuss" label-width="68px">
       <el-input type="textarea" v-model="discuss.content" placeholder="请输入你的评论"
                 rows="5"	style="width: 500px"/>
     </el-form-item>
     <el-form-item>
       <el-button type="primary" @click="onsubmit">发布</el-button>
     </el-form-item>
   </el-form>
   <div v-if="discussList!=undefined">
    <ul v-for="(discuss,index) in discussList" :key="index">
      <div class="name">
        {{discuss.loginname}}（{{discuss.username}}）
      </div>
      <li>
        {{discuss.content}}
      </li>
      <div class="time">
        发布日期：{{discuss.createtime}}
      </div>
    </ul>
     <el-pagination
       background
       :hide-on-single-page="discussCount==0"
       @size-change="getDiscussList"
       @current-change="getDiscussList"
       :current-page.sync="discuss.pageNo"
       :page-sizes="[3, 6, 9]"
       :page-size.sync="discuss.pageSize"
       layout="total, sizes, prev, pager, next, jumper"
       :total="discussCount">
     </el-pagination>
   </div>
   <div v-else>
     <ul>
       <li style="text-align: center">
         暂无评论！
       </li>
     </ul>
   </div>
 </div>
</template>

<script>
  import {addDiscuss, list} from "../../api/discuss";

export default {
  name: "productDiscuss",
  data(){
    return{
      discussCount:undefined,
      discuss: {
        pageNo:1,
        pageSize:3,
        productid: this.$route.query.id,
        content:undefined
      },
      discussList: undefined
    }
  },
  methods:{
    onsubmit(){
      if(this.discuss.content){
        addDiscuss(this.discuss).then(response => {
          if(response.code == "1000"){
            this.msgSuccess(response.retMsg)
            this.getDiscussList()
            this.discuss.content = undefined
          }else if(response.code =="901") {
            this.msgError(response.retMsg)
          }
        })
      }else{
        this.msgError("请输入发布内容！")
      }
    },
    getDiscussList(){
      list(this.discuss).then(response => {
        if(response.code == "1000"){
          this.discussList = response.discussList
          this.discussCount = response.discussCount
        }
      })
    }
  },
  created() {
    this.getDiscussList();
  }
}
</script>

<style scoped>
  ul, li, dl, dt, dd {
    list-style: none;
    margin: 0;
    padding: 0;
  }
  ul{
    text-align: left;
    margin-top: 10px;
    border-top: 1px darkgray solid;
  }
  ul .name,li{
    line-height: 50px;
    margin-left: 20px;
  }
  li{
    margin-left: 10%;
    margin-right: 10%;
    text-indent: 2em;
  }
  .time{
    margin-left: 70%;
    margin-bottom: 10px;
  }
  .form{
    margin-top: 30px;
  }
</style>
