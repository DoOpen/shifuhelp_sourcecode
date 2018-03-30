<template>
  <div class="editor-root" v-if="!hide">
    <div :class="isNull(title)?'':'editor-title'">
      <p>{{title}}</p>
    </div>
    <div class="editor-content">
      <textarea v-model="value" type="text" :disabled="initDisable" v-if="type=='textarea'" :placeholder="tip" :style="isNull(options)?{'width':'200px','height':'60px'}:options"></textarea>
      <input v-model="value" type="text" :disabled="initDisable" v-else="" :placeholder="tip" :style="isNull(options)?{'width':'200px','height':'25px'}:options">
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
  /**
   * 文本框组件
   * type:文本框类型 textarea：文本域 text:禁用的文本框 input：文本框
   * title:组件左侧显示的文字
   * initValue：组件的初始值
   * filed:组件所属的表单字段
   * disable:为true时禁用组件
   * options:json格式，定义文本框的宽度和高度默认：{'width':'200px','height':'60px'}
   * hide：为true时隐藏
   * tip:文本框的默认提示内容（输入内容后自动消失）
   */
  export default {
    props: ['type','title','initValue','filed','disable','options','hide','tip'],
    data(){
      return{
        value:this.initValue,
        initDisable:this.disable
      }
    },
    mounted(){
      if(this.type=='text'){
        this.initDisable=true;
      }
    },
    watch:{
      initValue(){
        this.value=this.initValue;
      },
      disable(){
        this.initDisable=this.disable;
      },
      value(){
        this.$emit('change',this.filed,this.value);
      }
    }
  }
</script>
<style scoped>
.editor-root{
  display: flex;
  align-content: center;
  align-items: center;
  justify-content: flex-start;
}
  .editor-title{
    width:150px;
    text-align: right;
    margin-right:10px;
  }
  .editor-content{
  }
  .editor-content>input{
    border:1px #2d6da3 solid;
    border-radius: 5px;
    color:#666;
    text-indent: 5px;
  }
.editor-content>textarea{
  border:1px #2d6da3 solid;
  border-radius: 5px;
  color:#666;
  text-indent: 5px;
  overflow: hidden;
}
</style>
