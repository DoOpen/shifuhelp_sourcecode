<template>
  <div class="select-root" v-if="!hide">
    <div :class="isNull(title)?'':'select-title'">
      <p>{{title}}</p>
    </div>
    <div class="select-content" :style="isNull(options)?{'width':'200px'}:options">
      <label v-for="item in dataSource">
        <input type="checkbox" :value="item.value" :disabled="disable||item.disable" v-model="check">{{item.key}}
      </label>
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
    'dataSource','initValue','selectValue','showValue','title','filed','disable','hide','options'
  ],
  data(){
    return{
      check:[]
    }
  },
  watch:{
    initValue(){
      this.check=!this.isNull(this.initValue)?this.initValue.split(','):[];
    },
    check(){
      this.$emit('change',this.filed,this.check.join(','));
    }
  },
  mounted(){
    this.check=!this.isNull(this.initValue)?this.initValue.split(','):[];
    this.$emit('change',this.filed,this.check.join(','));
  }
}
</script>
<style scoped lang="less">
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
  .select-content{
    display: flex;
    flex-flow: row wrap;
    justify-content: space-between;
    align-items: center;
    align-content: center;
    label{
      input{
        margin-right:5px;
      }
    }
  }
</style>
