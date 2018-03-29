<template>
  <div class="app-root">
    <top class="top"></top>
    <left-menu class="left-content"></left-menu>
    <router-view class="right-content"></router-view>
  </div>
</template>
<script type="text/ecmascript-6">
  import LeftMenu from './left-menu.vue'
  import Top from './top.vue'
  export default {
    components: {
      LeftMenu,Top
    }
  }
</script>
<style>
  .app-root {
    background: #fff;
    height:100%;
    width:100%;
  }
  .top{
    height:30px;
    position: fixed;
    top: 0;
    left: 0;
    z-index: 999;
    width:100%;
  }
  .left-content {
    position: fixed;
    top: 30px;
    left: 0;
    width: 150px;
    height: 100%;
  }

  .right-content {
    padding-top: 30px;
    padding-left:150px;
  }
</style>
