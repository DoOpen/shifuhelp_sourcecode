<template>
  <div class="level-root" v-if="!hide">
    <div :class="isNull(title)?'':'title'" >
      <p>{{title}}</p>
    </div>
    <div class="level-content">
      <input v-model="show" type="text" :disabled="disable" :class="disable?'gray':'white'" :placeholder="tip"  @click="inputClick">
      <div class="select" v-if="isShow">
        <div class="header">
          <h1 v-for="(head,headIndex) in lables.split(',')" @click="headClick(headIndex)" :class="headIndex==index?'head-select':''">{{head}}</h1>
        </div>
        <div class="child-content">
          <h1 v-for="item in childBeans" @click="itemClick(item)">{{item[showValue]}}</h1>
        </div>
        <div class="close" >
          <h1 @click="menu(false)">关闭</h1>
          <h1 @click="menu(true)">清空</h1>
        </div>
      </div>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
  /**
   * 多级选择组件
   * title:组件左侧显示的文字
   * dataSource:数据源
   * selectValue：选择后取值的字段
   * shoeValue：选择后显示值的字段
   * disable:为true时组件禁用
   * initValue：初始值
   * childName：多层级选择的时候子数据源字段名
   * filed:组件所属表单的字段名
   * lables:导航栏文本，逗号分隔，分别对应每一层级
   * result:返回值类型  one：只返回最后一次选择的值  many：将多次选择的值用-拼接返回
   * hide：为true是隐藏组件
   * tip：输入框默认提示文本
   */
  export default {
  props:[
    'title','dataSource','selectValue','showValue','disable','initValue','childName','filed','lables','result','hide','tip'
  ],
  data(){
    return{
      childBeans:[],
      index:0,
      show:'',
      select:'',
      stack:[],
      isShow:false
    }
  },
  watch:{
    dataSource(){
      this.init();
      this.buildShowValue(this.dataSource);
    },
    initValue(){
      this.show=this.isNull(this.show)?this.initValue:this.show;
      this.select=this.isNull(this.select)?this.initValue:this.select;
    }
  },
 mounted(){
   this.show=this.initValue;
   this.select=this.initValue;
 },
  methods:{
   buildShowValue(data){
     data.forEach((item,index)=>{
       if(item[this.selectValue]==this.initValue){
         this.show=item[this.showValue];
       }
       if(!this.isNull(item[this.childName])){
         this.buildShowValue(item[this.childName]);
       }
     });
   },
    inputClick(){
      this.isShow=true;
    },
    headClick(headIndex){
      if(headIndex<=this.index){
        for(let i=this.index;i>headIndex;i--){
          this.stack.pop();
        }
        this.index=headIndex;
        if(headIndex==0){
          this.childBeans=this.stack[this.stack.length-1];
          this.show=this.select='';
        }else{
          this.childBeans=this.stack[this.stack.length-1][this.childName];
        }
      }
    },
    itemClick(item){
      this.stack.push(item);
      if(this.index==0){
        this.show=this.select='';
        if(this.result&&this.result=='one'){
          this.show=item[this.showValue];
          this.select=item[this.selectValue];
        }else{
          this.show+=item[this.showValue];
          this.select+=item[this.selectValue];
        }
      }else{
        if(this.result&&this.result=='one'){
          this.show=item[this.showValue];
          this.select=item[this.selectValue];
        }else{
          this.show+='-'+item[this.showValue];
          this.select+='-'+item[this.selectValue];
        }
      }
      this.$emit('change',this.filed,this.select);
      if(item[this.childName].length>0){
        this.childBeans=item[this.childName];
        this.index++;
      }else{
        this.isShow=false;
        setTimeout(()=>{
          this.init();
        },200);
      }

    },
    init(){
      this.childBeans=this.dataSource;
      this.stack=[];
      this.index=0;
      this.stack.push(this.dataSource);
    },
    menu(select){
      if(select){
        this.select=this.show='';
      }
      this.isShow=false;
      this.init();
    }
  }
}
</script>
<style scoped>
.level-root{
  display: flex;
  align-content: center;
  align-items: center;
  justify-content: flex-start;
}
.title{
  width:150px;
  text-align: right;
  margin-right:10px;
}
.level-content{
  position: relative;
}
.level-content>input{
  height:25px;
  width:200px;
  border:1px #2d6da3 solid;
  border-radius: 5px;
  color:#666;
  text-indent: 5px;
}
.gray{
  background:gray;
}
.white{
  background: #fff;
}
.select{
  position: absolute;
  top:28px;
  width:200px;
  border:1px #2d6da3 solid;
  border-radius:10px;
  z-index:55555;
  background:#fff;
}
  .header,.close{
    height:30px;
    line-height: 30px;
    background-image: -webkit-gradient(linear, 0% 0%, 0% 90%, from(rgba(28, 91, 155, 0.8)), to(rgba(108, 191, 255, 0.9)));
    box-shadow:5px 5px 30px #2d6da3;
    display: flex;
    align-content: center;
    justify-content: center;
    align-items: center;
  }
  .head-select{
    background:#419641;
  }
  .header>h1,.close>h1{
    flex:1;
    text-align: center;
    font-size:16px;
    color:#fff;
    border-right: 1px #fff solid;
    cursor: pointer;
  }
.header>h1:hover,.close>h1:hover{
  background: #2d6da3;
}
.header:nth-last-child{
 border-right: none;
}
.child-content{
  overflow: auto;
  min-height:150px;
  max-height:250px;
}
  .child-content>h1{
    display: inline-block;
    cursor: pointer;
    padding:3px 5px;
    border-radius: 5px;
  }
.child-content>h1:hover{
  background: #DDD;
}
</style>
