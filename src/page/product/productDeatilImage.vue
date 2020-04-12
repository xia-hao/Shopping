<template>
    <div>
      <div v-if="productSku.imgarray">
        <div class="img" v-for="(item,index) in productSku.imgarray.img" :key="index">
          <img :src="urlimg+item"/>
        </div>
      </div>
      <div v-else>
        <div class="img" v-for="(item,index) in slideShowList" :key="index">
          <img :src="urlimg+item.picurl"/>
        </div>
      </div>
    </div>
</template>

<script>
import {slideShowlist} from "../../api/slideShow";
import {getProductById} from "../../api/productSku";

export default {
  name: "productDeatilImage",
  data(){
    return{
      slideShow: {
        pid: this.$route.query.id,
        type: 'product'
      },
      slideShowList: undefined,
      skuid: this.$route.query.skuid,
      productSku : {
        productid: undefined,
        productspecs:''
      }
    }
  },
  created() {
    if(this.skuid){
     getProductById(this.skuid).then(result => {
       if(result.code=="1000"){
         this.productSku = result.productSku
         this.productSku.imgarray = JSON.parse(result.productSku.imgarray)
       }
     })
    }
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
