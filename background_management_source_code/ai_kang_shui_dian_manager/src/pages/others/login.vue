<template>
  <div class="content">
    <div class="login_window">
      <h1>师傅上门Pro管理平台</h1>
      <div class="form-row">
        <img :src="require('../../assets/images/common/member.png')"/>
        <input  type="text" v-model="system_account" v-focus="focusIndex==1?true:false"/>
      </div>
      <div class="form-row">
        <img :src="require('../../assets/images/common/password.png')"/>
        <input type="password" v-model="system_password" v-focus="focusIndex==2?true:false"/>
      </div>
      <span class="login_button" @click="login">登录</span>
    </div>
    <div class="bottom">
      <a href="http://www.miitbeian.gov.cn/">浙ICP备05088716号-1</a>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
export default {
  data(){
    return{
      system_account:'',
      system_password:'',
      focusIndex:1
    }
  },
  methods:{
    login(){
      if(!/^[a-z0-9]{1,50}$/.test(this.system_account)){
        this.showTip('账号格式出错','waring');
        this.focusIndex=1;
        return;
      }
      if(!/^[a-z0-9]{1,50}$/.test(this.system_password)){
        this.showTip('密码格式出错','waring');
        this.focusIndex=2;
        return;
      }
      let params={};
      params.system_account=this.system_account;
      params.system_password=this.system_password;
      this.post(1,'systemController.api?systemAccountLogin',params);
    },
    doSuccess(index,data){
      sessionStorage.systemAccountBean=JSON.stringify(data);
      this.$router.push('/home');
    },
    doFiled(index,error){
      this.showTip(this.isNull(error)?'登录失败!':error,'info');
      this.focusIndex=1;
    }
  },
  mounted(){
//    setTimeout(()=>{
//      this.login();
//    },200);
  }
}
</script>
<style lang="less" scoped>
  .content {
    background:url(../../assets/images/common/login_background.jpg) top center no-repeat;
    background-size:cover;
    position: absolute;
    height:100%;
    width:100%;
  }
  .login_window{
    width:350px;
    height:300px;
    position: fixed;
    top:50%;
    left:50%;
    background:white;
    opacity: 0.6;
    border-radius: 15px;
    transform: translate(-50%,-50%);
    flex-flow: column nowrap;
    display: flex;
    align-items: center;
    align-content: center;
    justify-content: center;
  }
  .login_window>h1{
    font-size:30px;
    font-weight: bold;
    color:#419641;
  }
  .login_window>.form-row{
    margin:10px 0;
    display: flex;
    align-items: center;
    align-content: center;
    justify-content: center;
  }
  .login_window>.form-row>input{
    width:200px;
    height:25px;
    border:1px #419641 solid;
    outline: none;
    border-radius:5px;
    margin:0 0 0 5px;
  }
  .login_window>.login_button{
    width:245px;
    height:30px;
    background:#ff4400;
    color:#fff;
    border-radius: 5px;
    text-align: center;
    line-height:30px;
    cursor: pointer;
  }
  .login_button:hover{
    background:#f22d00;
  }
  .bottom{
    position: fixed;
    width:100%;
    text-align: center;
    bottom: 30px;
    >a{
      color:#fff;
      &:hover{
       color:#ccc;
      }
    }
  }
</style>
