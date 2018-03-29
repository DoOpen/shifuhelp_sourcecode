<template>
  <div class="select-root" v-if="!hide">
    <div :class="isNull(title)?'':'select-title'">
      <p>{{title}}</p>
    </div>
    <div class="select-content" >
      <select v-model="selected" :disabled="disable">
        <option v-for="(item,index) in dataSource" :value="item[selectValue]">{{item[showValue]}}</option>
      </select>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
  /**
   * 下拉选择框
   * dataSource：数据源
   * initValue:初始值
   * selectValue:选择时返回值从哪个字段取值
   * showValue：选择是显示值从哪个字段取值
   * title：组件左侧文本
   * filed:组件所属表单字段名
   * disable:为true时禁用组件
   * hide：为true时隐藏组件
   */
  export default {
  props:[
    'dataSource','initValue','selectValue','showValue','title','filed','disable','hide'
  ],
  data(){
    return{
      selected:!this.isNull(this.initValue)?this.initValue:!this.isNull(this.dataSource)?this.dataSource[0][this.selectValue]:0
    }
  },
  watch:{
    initValue(){
      this.selected=!this.isNull(this.initValue)?this.initValue:!this.isNull(this.dataSource)?this.dataSource[0][this.selectValue]:0;
    },
    dataSource(){

      this.selected=!this.isNull(this.initValue)?this.initValue:!this.isNull(this.dataSource)?this.dataSource[0][this.selectValue]:0;
    },
    selected(){
      this.$emit('change',this.filed,this.selected);
    }
  },
  mounted(){
    this.selected=!this.isNull(this.initValue)?this.initValue:!this.isNull(this.dataSource)?this.dataSource[0][this.selectValue]:0;
    this.$emit('change',this.filed,this.selected);
  }
}
</script>
<style scoped>
  .select-root{
    display: flex;
    align-content: center;
    align-items: center;
    justify-content: flex-start;
  }
  .select-title{
    width:150px;
    text-align: right;
    margin-right:10px;
  }
  .select-content{
  }
  .select-content>select{
    height:25px;
    width:200px;
    border:1px #2d6da3 solid;
    border-radius: 5px;
    color:#666;
    text-indent: 5px;
  }
  .select-content>select>option{
    font-size:16px;
    color:#666;
  }
</style>
