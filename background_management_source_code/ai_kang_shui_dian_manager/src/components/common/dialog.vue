<template>
  <transition name="transtion">
    <div class="dialog-root"  v-if="isShow">
      <div class="dialog-content">
        <div class="dialog-message">
          <p>{{message}}</p>
        </div>
        <div v-if="type=='input'" class="dialog-input">
          <input  v-model="value" v-focus="true"/>
        </div>
        <div class="dialog-operation">
          <p @click="dialogClick(true)">确认</p>
          <p @click="dialogClick(false)">取消</p>
        </div>
      </div>
    </div>
  </transition>
</template>
<script type="text/ecmascript-6">
  /**
   * 对话框组件
   * message:对话框的提示消息内容
   * 对话框与父组件的数据交互采用的单独的Vue组件作为通讯中介，调用对话框后父组件会监听bus中的事件
   */
 export default {
   data(){
     return{
        isShow:true,
        value:''
     }
   },
   methods:{
     dialogClick(select){
       this.isShow=false;
       let elements=document.getElementsByClassName('layout-root');
       elements[0].style.filter='none';
       this.bus.$emit('dialogClick',select,this.value);
     }
   },
   mounted(){
     let elements=document.getElementsByClassName('layout-root');
     elements[0].style.filter='blur(30px)';
   }
 }
</script>
<style scoped>
.dialog-root{
  position: fixed;
  top:0;
  z-index: 6666;
  width:100%;
  height:100%;
}
 .dialog-content{
   display: flex;
   flex-flow: column;
   position: absolute;
   transform: translate(-50%,-50%);
   top: 50%;
   left:50%;
   min-width: 300px;
   min-height: 200px;
   border-radius: 10px;
   background-image: -webkit-gradient(linear, 0% 0%, 0% 90%, from(rgba(28, 91, 155, 0.8)), to(rgba(108, 191, 255, 0.9)));
 }
 .dialog-message{
   flex: 4;
   display: flex;
   align-items: center;
   justify-content: center;
   text-align: center;
 }
 .dialog-message>p{
   flex:1;
   color:#fff;
 }
 .dialog-operation{
   flex: 1;
   border-top:1px #fff solid;
   display: flex;
   width:100%;
   max-height: 40px;
   align-items: center;
   justify-content: center;
   text-align: center;
 }
 .dialog-operation>p{
   flex:1;
   line-height:40px;
   color:#fff;
   cursor: pointer;
 }
 .dialog-operation>p:nth-child(1){
   border-right:1px #fff solid;
 }
.dialog-operation>p:hover{
  background: -webkit-gradient(linear, 0% 0%, 0% 90%, from(rgba(91,192,222, 0.8)), to(rgba(51,122,183, 0.9)));
}
.transtion-enter-active,.transtion-leave-active{
    transition: opacity 0.5s;
  }
  .transtion-enter,.transtion-leave-to{
    opacity: 0;
  }
  .dialog-input{
    display: flex;
    justify-content: center;
    align-content: center;
    align-items: center;
    margin-bottom: 10px;
  }
.dialog-input>input{
  width:250px;
  height:30px;
  border-radius: 10px;
  border:1px #2d6da3 solid;
  outline: none;
}
</style>
