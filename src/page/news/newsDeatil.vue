<template>
  <div class="content">
    <div class="m_right">
      <el-button size="mini" @click="back">返回上一级</el-button>
      <p></p>
      <h1 style="text-indent: 2em">公告详情</h1>
      <div class="m_title" style="text-align: center;">
        <h2>{{news.title}}</h2>发布时间：{{news.createtime}}
      </div>
      <div class="m_content" style="height: 300px;text-indent: 2em;text-align: left">
          <h4>{{news.content}}</h4>
      </div>
    </div>
  </div>
</template>

<script>
import {newsById} from "../../api/news";
export default {
  name: "newsDeatil",
  data(){
    return{
      id:this.$route.query.newsId,
      news:{}
    }
  },
  methods: {
    back(){
      this.$router.go(-1)
    },
    getnewsById(){
      newsById(this.id).then(response => {
        this.news = response.getnews
      }).catch(error => {
        this.msgError("异常："+error)
      })
    }
  },
  created() {
    this.getnewsById();
  }
}
</script>

<style scoped>
  .content {
    width: 800px;
    overflow: hidden;
    margin: auto;
    margin-top: 50px
  }
  .m_title,.m_content{
    border: 1px darkgray solid;
  }
</style>
