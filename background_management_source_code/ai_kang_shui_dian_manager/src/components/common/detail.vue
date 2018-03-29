<template>
  <div class="list-root">
    <div class="splitter">
      <p>{{header}}</p>
    </div>
    <div class="detail-content">
      <div v-for="(base,index) in baseData" class="list-detail" v-if="!base.hide">
        <my-image v-if="base.type=='img'" :initValue="dataSource[base.key]" :title="base.name" v-bind="base" :filed="base.key" @change="change" :options="{'width':'60px','height':'60px'}"></my-image>
        <my-video v-else-if="base.type=='video'" :initValue="dataSource[base.key]" :title="base.name" v-bind="base" :filed="base.key" @change="change"></my-video>
        <my-select v-else-if="base.type=='select'" :initValue="dataSource[base.key]" :title="base.name" :dataSource="base.dataSource" :filed="base.key" v-bind="base" @change="change"></my-select>
        <check-box v-else-if="base.type=='check'" :initValue="dataSource[base.key]" :title="base.name" :dataSource="base.dataSource" :filed="base.key" v-bind="base" @change="change"></check-box>
        <search-bar v-else-if="base.type=='search'" :initValue="dataSource[base.key]" :title="base.name" :dataSource="base.dataSource" :filed="base.key" v-bind="base" @change="change"></search-bar>
        <level v-else-if="base.type=='level'" :initValue="dataSource[base.key]" :title="base.name" :dataSource="base.dataSource" :filed="base.key" v-bind="base" @change="change"></level>
        <date-picker v-else-if="base.type=='date'||base.type=='datetime'" :initValue="dataSource[base.key]" :title="base.name" :type="base.type" :filed="base.key" v-bind="base" @change="change"></date-picker>
        <editor v-else="" :initValue="dataSource[base.key]" :title="base.name" :type="base.type" :filed="base.key" v-bind="base" @change="change"></editor>
      </div>
    </div>
    <div class="operation-root">
      <my-button v-for="(item,index) in toolData" v-bind="item" v-if="!item.hide" @click="toolClick(index)" :key="Math.random()" class="operation-button"></my-button>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
  /**
   * 详情组件（遍历对象属性，生成表单，三等分）
   * baseData中type的类型包含：img：图片 select:下拉框 search：搜索框 level:多层级选择器 date:日期选择器
   *    datetime：时间选择器 textarea：文本域 input：文本输入框 text：只读文本框（相当于禁用组件）
   * baseData:组件遍历的模板
   * dataSource:组件遍历的数据源
   * header:顶部标题栏的文字，默认不显示标题栏
   */
  export default {
    props: [
      'baseData','dataSource','header','toolData'
    ],
    data() {
      return {

      }
    },
    methods:{
      change(filed,value){
        this.$emit('change',filed,value);
      },
      toolClick(index){
        this.$emit('toolClick',index);
      }
    }
  }
</script>
<style scoped>
  .list-root{
    display: flex;
    flex-flow: column wrap;
    justify-content: flex-start;
  }
  .detail-content{
    display: flex;
    flex-flow: row wrap;
    justify-content: flex-start;
  }
  .list-detail{
    width:33%;
    margin:10px 0;
  }
  .operation-root{
    display: flex;
    flex-flow: row nowrap;
    justify-content: center;
    align-items: center;
    margin-top: 20px;
  }
  .operation-button{
    margin: 0 10px;
  }
</style>
