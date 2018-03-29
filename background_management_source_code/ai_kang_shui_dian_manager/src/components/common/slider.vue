<template>
  <div>
    <div class="slider-show">
      <a :href="sliders[nowIndex][href]">
        <img :src="sliders[nowIndex][src]" class="slider-img" :title="sliders[nowIndex][title]">
      </a>
      <div class="slider-footer">
        <ul class="slider-ul">
          <li @click="changeIndex('&lt;')">&lt;</li>
          <li v-for="(item,index) in sliders" @click="changeIndex(index)" :style="nowIndex==index?{background:'#fff','border-radius':'12.5px',color:'#000'}:{}">{{index+1}}</li>
          <li @click="changeIndex('&gt;')">&gt;</li>
        </ul>
      </div>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
  /**
   * 轮播图
   * dataSource:数据源
   * src：图片取值字段
   * href：跳转链接字段
   * title：图片提示文本
   */
  export default {
    props:['dataSource','src','href','title'],
    data(){
      return {
        nowIndex:0,
        isChange:false
      }
    },
    methods:{
      changeIndex(index) {
        if (index == '<') {
          this.nowIndex = this.nowIndex <= 0 ? this.sliders.length - 1 : --this.nowIndex;
        } else if (index == '>') {
          this.nowIndex = this.nowIndex >= this.sliders.length - 1 ? 0 : ++this.nowIndex;
        } else {
          this.nowIndex = index;
        }
        this.isChange=true;
      }
    },
    mounted(){
      setInterval(()=>{
        if(!this.isChange){
          this.nowIndex=this.nowIndex>=this.sliders.length-1?0:++this.nowIndex;
        }else{
          this.isChange=false;
        }
      },1500);
    },
    beforeDestory(){
      this.clearImmediate;
    }
  }
</script>
<style scoped>
.slider-show{
  width:100%;
  height:100%;
  position: relative;
}
  .slider-footer{
    position: absolute;
    bottom:0;
    background: #000;
    opacity: 0.6;
    height:40px;
    width:100%;
  }
  .slider-ul{
    float:right;
    margin-right:30px;
  }
  .slider-ul>li{
    float:left;
    color:#fff;
    margin: 7px;
    width:25px;
    height:25px;
    line-height: 25px;
    cursor:pointer;
  }
  .slider-ul>li:hover{
    font-size:25px;
  }
</style>
