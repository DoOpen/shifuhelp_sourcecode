<template>
  <div class="button-root" >
    <span @click="click" :class="endClass">{{name}}</span>
  </div>
</template>
<script type="text/ecmascript-6">
  /**
   * 按钮组件
   * 作者：彭方林
   * name:按钮显示的文字
   * disable：为true的时候按钮禁用，变为灰色
   * hide:为true时隐藏
   */
  export default {
  props:[
    'name','disable','hide'
  ],
  data(){
    return{
      endClass:'button-span span-hover'
    }
  },
  methods:{
    click(){
      this.disable?'':this.$emit('click');
    },
    buildClass(){
      this.endClass=this.hide?'hide':this.disable?'gray':'button-span span-hover';
    }
  },
  mounted(){
    this.buildClass();
  },
  watch:{
    disable(){
      this.buildClass();
    },
    hide(){
      this.buildClass();
    }
  }
}
</script>
<style scoped>
  .button-root{
    display: inline-block;
  }
  .button-span{
    padding:5px 15px;
    border-radius: 5px;
    text-align: center;
    color:#fff;
    cursor: pointer;
    background-image: -webkit-gradient(linear, 0% 0%, 0% 90%, from(rgba(28, 91, 155, 0.8)), to(rgba(108, 191, 255, 0.9)));
  }
  .gray{
    padding:5px 15px;
    border-radius: 5px;
    text-align: center;
    color:#fff;
    cursor: pointer;
    background:gray;
  }
  .button-span:hover{
    background: #2d6da3;
  }
  .hide{
    display: none;
  }
</style>
