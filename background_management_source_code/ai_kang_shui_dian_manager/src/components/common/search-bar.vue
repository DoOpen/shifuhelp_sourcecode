<template>
  <div class="search-root" v-if="!hide">
    <div :class="isNull(title)?'':'search-title'">
      <p>{{title}}</p>
    </div>
    <div class="search-content">
      <input v-model="value" type="text" :disabled="disable" @focus="changeShow(true)" :placeholder="tip" @blur="changeShow(false)">
      <div class="search-option" v-if="isShow">
        <p v-for="item in dataSourceTemp" @click="selectOk(item[selectValue],item[showValue])">{{item[showValue]}}</p>
      </div>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
  /**
   * 搜索框组件
   * title:组件左侧文本
   * initValue:初始值
   * filed：组件所属表单字段名
   * disable:为true时禁用组件
   * selectValue：选择时返回值从哪个字段取值
   * showValue：选择时显示值从哪个字段取值
   * dataSource:数据源
   * hide：为ture时隐藏
   * tip：搜索框默认显示文本（内容改变是自动隐藏）
   */
  export default {
  props: ['title','initValue','filed','disable','selectValue','showValue','dataSource','hide','tip'],
  data(){
    return{
      value:this.initValue,
      isShow:false,
      dataSourceTemp:this.dataSource
    }
  },
  watch:{
    dataSource(){
      this.dataSourceTemp=this.dataSource;
      for(let i in this.dataSource){
        if(this.dataSource[i][this.selectValue]==this.initValue){
          this.value=this.dataSource[i][this.showValue];
          break;
        }
      }
    },
    initValue(){
      this.value=this.isNull(this.value)?this.initValue:this.value;
    },
    value(){
      let data=[];
      if(!this.isNull(this.dataSource)){
        this.dataSource.forEach((item,index)=>{
          if(item[this.showValue]&&item[this.showValue].indexOf(this.value)>-1){
            data.push(item);
          }
        });
        this.dataSourceTemp=data;
      }
    },
    selectValue(){
      this.value='';
    }
  },
  methods:{
    changeShow(show){
      if(!show){
        setTimeout(()=>{
          this.isShow=show;
        },300);
      }else{
        this.isShow=show;
      }
    },
    selectOk(selectValue,showValue){
      this.value=showValue;
      this.isShow=false;
      this.$emit('change',this.filed,selectValue);
    }
  }
}
</script>
<style scoped>
.search-root{
  display: flex;
  align-content: center;
  align-items: center;
  justify-content: flex-start;
}
.search-title{
  width:150px;
  text-align: right;
  margin-right:10px;
}
.search-content{
  position: relative;
}
.search-content>input{
  height:25px;
  width:200px;
  border:1px #2d6da3 solid;
  border-radius: 5px;
  color:#666;
  text-indent: 5px;
}
  .search-option{
    position: absolute;
    top:28px;
    width:200px;
    overflow: auto;
    min-height:100px;
    max-height:200px;
    border:1px #2d6da3 solid;
    border-radius:10px;
    z-index:55555;
    background:#fff;
  }
.search-option>p{
  margin:0 0 0 10px;
  cursor: pointer;
}
.search-option>p:hover{
  color:#ff00ff;
}
</style>
