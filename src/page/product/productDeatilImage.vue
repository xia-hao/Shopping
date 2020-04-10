<template>
    <div>
      <div class="img" v-for="(item,index) in slideShowList" :key="index">
        <img :src="urlimg+item.picurl"/>
      </div>
    </div>
</template>

<script>
import {slideShowlist} from "../../api/slideShow";

export default {
  name: "productDeatilImage",
  data(){
    return{
      slideShow: {
        pid: this.$route.query.id,
        type: 'product'
      },
      slideShowList: undefined
    }
  },
  created() {
    slideShowlist(this.slideShow).then(response => {
      this.slideShowList = response.slideShowList
    })
  }
}
</script>

<style scoped>
.img{
  margin-top: 50px;
  margin-left: 50px;
}
</style>
