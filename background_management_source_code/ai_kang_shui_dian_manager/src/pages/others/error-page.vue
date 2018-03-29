<template>
  <div class="error_content">
    <div class="error_left">
      <span class="sp_con">赶紧修，大家等着呢。</span>
    </div>
    <div class="error_right">
      <div class="error_detail">
        <p class="error_p_title">哎呦~ 访问的模块没有找到!</p>
        <p class="error_p_con">●别急，程序猿正在加班干活，马上就好。</p>
        <p class="error_p_con">●您可访问其他的模块，还有更多精彩等着你呢!</p>
        <p class="error_p_con">●若对您造成了心灵伤害，我们可以杀程序员祭天!</p>
      </div>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">

</script>
<style scoped>
  .error_content {
   display: flex;
    align-items: center;
    align-content: center;
    justify-content: center;
  }

  .error_left {
    width: 330px;
    height: 345px;
    background: url(../../assets/images/common/404.png) no-repeat;
    background-size: 100%;
    float: left;
  }

  .error_detail {
    width:100%;
    height: auto;
  }

  .error_detail .error_p_title {
    font-size: 28px;
    color: #eb8531;
  }

  .error_detail .error_p_con {
    font-size: 14px;
    margin-top: 10px;
    line-height: 20px;
  }

  .sp_con {
    margin-left: 128px;
    color: #1A4EC0;
    margin-top: 39px;
    position: absolute;
    font-size: 18px;
  }

  .btn_error a {
    padding: 5px;
    border: 1px solid #CCCCCC;
  }
</style>
