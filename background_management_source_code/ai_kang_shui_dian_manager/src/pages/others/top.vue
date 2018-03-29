<template>
  <div class="top-root" @mouseover="mouseOver" @mouseleave="mouseLeave">
    <div class="top-left">
      <p>师傅上门Pro管理平台</p>
    </div>
    <div class="top-right">
      <p>{{systemAccountBean.system_name}}</p>
      <p class="top-right-p2">&lt;</p>
      <div>
        <img :src="homeUrl+systemAccountBean.system_img" @error="imgError">
      </div>
      <div class="top-operation" v-show="mouse">
        <router-link to="/user_info" tag="p">个人信息</router-link>
        <p @click="exit">退出登录</p>
      </div>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data(){
      return{
        systemAccountBean:{},
        mouse:false
      }
    },
    methods:{
      imgError(e){
        e.target.src=this.homeUrl+this.defaultImg;
      },
      mouseOver(){
        this.mouse=true;
      },
      mouseLeave(){
        this.mouse=false;
      },
      exit(){
        sessionStorage.removeItem('systemAccountBean');
        this.$router.push('/');
      }
    },
    beforeMount(){
      this.systemAccountBean=JSON.parse(sessionStorage.systemAccountBean);
    }
  }
</script>
<style scoped>
  .top-root{
    background-image: -webkit-gradient(linear, 0% 0%, 0% 90%, from(rgba(28, 91, 155, 0.8)), to(rgba(108, 191, 255, 0.9)));
    box-shadow:30px 5px 30px #2d6da3;
  }
  .top-root p{
    color:#fff;
    line-height:30px;
  }
.top-left{
  float:left;
}
.top-left>p{
  margin-left:50px;
}
  .top-right{
    display: flex;
    float: right;
    margin-right:50px;
  }
  .top-operation{
    position: absolute;
    text-align: center;
    z-index: 5555;
    top:30px;
    right:30px;
    padding:15px;
    background-image: -webkit-gradient(linear, 0% 0%, 0% 90%, from(rgba(28, 91, 155, 0.9)), to(rgba(108, 191, 255, 0.9)));
    box-shadow:5px 5px 5px #2d6da3;
  }
  .top-operation>p{
    cursor: pointer;
  }
  .top-operation>p:hover{
    transform: scale(1.2);

  }
  .top-right>p{
    margin: 0 5px;
  }
  .top-right-p2{
    transform: rotate(-90deg);
  }
  .top-right-p2:hover{
    transform: rotate(90deg);
  }
  .top-right>div>img{
    width:30px;
    height:30px;
    border-radius: 15px;
  }
</style>
