<template>
  <div class="m_content">
      <el-button size="mini" @click="back">返回上一级</el-button>
      <p></p>
      <div class="mem_tit">公告列表</div>
      <el-table :data="newsList"
                row-key="id"
                @row-click="rowClick">
        <el-table-column label="文章标题" prop="title" align="center"/>
        <el-table-column label="创建时间" prop="createtime" align="center"/>
      </el-table>
    <div class="m_right">
      <el-pagination
          background
          :hide-on-single-page="newsCount==0"
          @size-change="selectNewsList"
          @current-change="selectNewsList"
          :current-page.sync="news.pageNo"
          :page-sizes="[10, 20, 30, 40]"
          :page-size.sync="news.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="newsCount">
        </el-pagination>
    </div>
  </div>
</template>

<script>
  import {newslist,newsById} from "../../api/news";

  export default {
        name: "newsList",
      data(){
          return{
            newsList: undefined,
            newsCount: 0,
            news: {
              pageNo: 1,
              pageSize: 10
            }
          }
      },
      methods: {
        selectNewsList(){
          newslist(this.news).then(response => {
            this.newsList = response.newsList
            this.newsCount = response.newsCount
          }).catch(error => {
            this.msgError('异常：'+error);
          })
        },
        rowClick(row){
          this.$router.push({path: '/newsDeatil',query:{newsId:row.id}})
        },
        back(){
          this.$router.go(-1)
        }
      },
    created() {
          this.selectNewsList();
    }
  }
</script>

<style scoped>
  .m_content {
    width: 1200px;
    overflow: hidden;
    margin: 100px;
  }
  .mem_tit{
    float: left;
    margin-left: 50px;
  }
  a{
    text-decoration: none;
    color: #333333;
  }
  a:hover{
    color: #ff4e00;
  }
  .m_right{
    text-align: center;
  }
</style>
