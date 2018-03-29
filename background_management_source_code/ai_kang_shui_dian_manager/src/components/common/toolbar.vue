<template>
  <div class="tool-root">
   <div class="tool-left">
     <div v-for="(item,index) in titles" class="tool-child">
       <h1 v-if="index!=0">&gt;</h1>
       <a @click="go(index)">{{item}}</a>
     </div>
   </div>
    <div class="operation-root">
      <my-button v-for="(item,index) in toolData" v-bind="item" v-if="!item.hide" @click="toolClick(index)" :key="Math.random()" class="operation-button"></my-button>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
  /**
   * 详情页工具栏
   * title：链接的显示文本
   * toolData：工具栏右侧按钮的遍历模板
   */
export default {
  props:[
    'title',
    'toolData'
  ],
  data(){
    return{
      titles:[]
    }
  },
  watch:{
    title(){
      this.addTitle();
    }
  },
  mounted(){
    this.addTitle();
  },
  methods:{
    go(index){
      this.$router.go(-(this.titles.length-index-1));
    },
    addTitle(){
      let titles=[];
      if(!this.isNull(sessionStorage['titles'])){
        titles=JSON.parse(sessionStorage['titles']);
      }
      let index=titles.indexOf(this.title);
      if(index<0){
        titles.push(this.title);
        sessionStorage['titles']=JSON.stringify(titles);
      }else{
        if(titles.length-index-1>0){
          titles.splice(index+1,titles.length-index-1);
          sessionStorage['titles']=JSON.stringify(titles);
        }
      }
      this.titles=titles;
    },
    toolClick(index){
      this.$emit('toolClick',index);
    }
  },
}
</script>
<style scoped>
.tool-root{
  height:30px;
  display: flex;
  padding-left:30px;
  justify-content: space-between;
  align-content: center;
  align-items: center;
  background: #DDD;
}
  .tool-child{
    float:left;
  }
  .tool-child>h1{
    display: inline-block;
    margin:0 5px;
  }
  .tool-child>a{
    margin:0 5px;
    cursor: pointer;
  }
  .tool-child>a:hover{
    color:#419641;
  }
  .operation-root{
    float: right;
    margin-right:20px;
  }
  .operation-button{
    margin-left: 10px;
  }
</style>
