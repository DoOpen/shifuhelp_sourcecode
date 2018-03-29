<template>
  <div class="list-root">
    <div class="splitter">
      <p>{{header}}</p>
    </div>
    <div class="list-header">
      <div v-for="(data,i) in baseData" class="list-header-child" :style="{'flex':data.type=='check'?'0.1':data.flex?data.flex:'1'}">
        <input v-if="data.type=='check'" type='checkbox' v-model="allCheck" value="true">
        <h1 v-else="">{{data.name}}</h1>
      </div>
    </div>
    <div v-for="(source,rowId) in dataSource" class="list-view">
      <div v-for="base in baseData" :class="buildClass" :style="{'flex':base.type=='check'?'0.1':base.flex?base.flex:'1'}">
        <input v-if="base.type=='input'" :value="source[base.key]" @change="change(rowId,base.key,$event)" class="editor"/>
        <vue-date-picker v-else-if="base.type=='datetime'" :date="initTime(source[base.key])" :option="option" @change="dateChange(rowId,base.key,$event)" ></vue-date-picker>
        <select v-else-if="base.type=='select'" @change="change(rowId,base.key,$event)" :disabled="base.disable">
          <option v-for="item in base.dataSource" :value="item[base.selectValue]" :selected="source[base.key]==item[base.selectValue]">{{item[base.showValue]}}</option>
        </select>
        <input v-else-if="base.type=='check'" type="checkbox" :name="rowId" v-model="checked" :value="source[base.key]">
        <div v-else-if="base.type=='img'" class="img">
          <img :src="isNull(source[base.key])?homeUrl+defaultImg:source[base.key].indexOf('http')>-1?source[base.key]:(homeUrl+source[base.key])" @click="beginUp(rowId)" @error="imgError">
          <input type="file" accept="image/png,image/jpg,image/jpeg,image/gif" ref="fileInput" @change="uploadImage(rowId,base.key,base.path,$event)" :disabled="base.disable">
        </div>
        <h1 v-else-if="base.type=='operation'" v-for="(operation,index) in operationData" @click="operationClick(index,rowId)"  :class="operation.hide?'hide':'operation'">
          <img v-if="!isNull(operation.img)" :title="operation.name" :src="homeUrl+operation.img" >
          <h1 v-else-if="!isNull(operation.flag)&&operation.flag=='work_order_lock'" class="operation_child_h1">[{{source['is_lock']==1?'解锁':'锁定'}}]</h1>
          <h1 v-else-if="!isNull(operation.flag)&&operation.flag=='send_work_order'&&(source['order_state']==2||source['order_state']==10)" class="operation_child_h1">[派单]</h1>
          <h1 v-else-if="isNull(operation.flag)" class="operation_child_h1">[{{operation.name}}]</h1>
        </h1>
        <h1 v-else="" class="list_child_h1">{{source[base.key]}}</h1>
      </div>
    </div>
    <page :total="total" :page="page" @goPage="goPage"/>
  </div>
</template>
<script type="text/ecmascript-6">
  /**
   * 列表遍历组件
   * baseData:组件遍历模板
   * dataSource：数据源
   * operationData:右侧操作栏遍历模板
   * total：总共多少条结果，分页用
   * page:当前第几页
   * header:顶部标题栏文本，默认不显示
   */
  export default {
    props: [
      'baseData','dataSource','operationData','total','page','header'
    ],
    data() {
      return {
        checked:[],
        allCheck:false,
        rowId:0,
        imgFiled:'',

        option: {
          type: this.isNull(this.type)?'min':this.type=='date'?'day':'min',
          week: ['一', '二', '三', '四', '五', '六', '日'],
          month: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
          format: this.isNull(this.type)?'YYYY-MM-DD HH:mm:ss':this.type=='date'?'YYYY-MM-DD':'YYYY-MM-DD HH:mm:ss',
          placeholder: '',
          inputStyle: {
            'display': 'inline-block',
            'width': '100%',
            'border': 'none',
            'color': '#666',
            'text-align':'center'
          },
          color: {
            header: '#2d6da3',
            headerText: '#fff'
          },
          buttons: {
            ok: '确定',
            clear:'清空',
            cancel: '取消'
          },
          overlayOpacity: 0.5, // 0.5 as default
          dismissible: true // as true as default
        },
        timeoption: {
          type: 'min',
          week: ['一', '二', '三', '四', '五', '六', '日'],
          month: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
          format: 'YYYY-MM-DD HH:mm'
        },
        multiOption: {
          type: 'multi-day',
          week: ['一', '二', '三', '四', '五', '六', '日'],
          month: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
          format:"YYYY-MM-DD HH:mm"
        },
        limit: [{
          type: 'weekday',
          available: [1, 2, 3, 4, 5,6,7,8]
        }]
      }
    },
    computed: {
      buildClass(){
        let buildClass='list-view-child';
        let haveImg=false;
        for (let index in this.baseData) {
          if (this.baseData[index].type == 'img') {
            haveImg=true;
            break;
          }
        }
        if(!haveImg){
          buildClass+=' list-view-child-padding';
        }
        if(this.operationData){
          buildClass+=' flex-operation';
        }
        return buildClass;
      }
    },
    methods:{
      operationClick(index,rowId){
        if(this.operationData[index].type=='dialog'||this.operationData[index].type=='input'){
          this.showdDialog(this.operationData[index].type,this.operationData[index].message,(select,val)=>{
            if(select){
              this.$emit('operationClick',index,rowId,val);
            }
          });
        }else{
          this.$emit('operationClick',index,rowId);
        }
      },
      goPage(page){
        this.$emit('goPage',page);
      },
      imgError(e){
        e.target.src=this.homeUrl+this.defaultImg;
      },
      dateChange(rowId,filed,value){
        this.$emit('change',rowId,filed,value);
      },
      change(rowId,filed,event){
        this.$emit('change',rowId,filed,event.target.value);
      },
      uploadImage(rowId,filed,path,e) {
        this.rowId=rowId;
        this.imgFiled=filed;
        let formData = new FormData();
        formData.append(e.target.value, e.target.files[0]);
        formData.append("path", this.isNull(path)?'':path);
        this.ajaxFileUpload(rowId, this.uploadPath, formData);
      },
      doSuccess(rowId, data) {
        this.showTip("上传成功");
        this.$emit('change',this.rowId,this.imgFiled,data[0]);
      },
      doFailed(index, error) {
        this.showTip(error);
      },
      beginUp(rowId){
        this.$refs.fileInput[rowId].click();
      },
      initTime(value){
        let initTime={};
        initTime.time=this.isNull(value)?'':value;
        return initTime;
      }
    },
    watch:{
      checked(val){
        this.$emit('checked',val);
      },
      allCheck(val){
        if(val){
          this.checked=[];
          this.dataSource.forEach((item,index)=>{
            this.checked.push(item[this.baseData[0].key]);
          })
        }else{
          this.checked=[];
        }
      }
    }
  }
</script>
<style scoped lang="less">
  .list-root {
    display: flex;
    flex-flow: column nowrap;
    align-content: center;
  }

  .list-header, .list-view {
    display: flex;
    flex-flow: row nowrap;
  }
  .splitter{
    margin-bottom: 5px;
  }
  .list-header {
    background-image: -webkit-gradient(linear, 0% 0%, 0% 90%, from(rgba(0,191,255, 0.8)), to(rgba(51,122,183, 0.9)));
  }
.list-header-child{
  padding:5px 5px;
}

  .list-header-child > h1 {
    color: #fff;
  }
  .hide{
    display: none;
  }
  .list-header-child, .list-view-child {
    border-bottom: 1px #DDD dotted;
    border-right: 1px #DDD solid;
    display: flex;
    width:0;
    flex:1;
    align-content: center;
    align-items: center;
    justify-content: center;
  }
  .list-view-child{
    text-align: center;
    padding: 0 5px;
  }
  .list-view-child-padding {
    padding: 5px;
  }

 .img>img{
    width: 30px;
    height: 30px;
    cursor: pointer;
  }
  .img>:hover{
    transform: scale(15.0);
    border: 1px #2d6da3 solid;
    border-radius: 2px;
  }
 select{
   text-align: center;
   border:none;
   padding:2px 30px;
 }
 option{
   text-align: center;
 }
 .img{
   width: 30px;
   height: 30px;
 }
 .img>input{
   display: none;
 }
  .list-view:hover {
    background: #EAF2FF;
  }
  .flex-operation{
    display: flex;
  }
  .operation{
    cursor: pointer;
    color:#2d6da3;
    flex: 1;
    display: flex;
    align-content: center;
    align-items: center;
    justify-content: center;
  }
  .operation:hover{
    color:#ff00ff;
  }
  .operation>img{
    width:25px;
    height:25px;
  }
  .editor{
    text-align: center;
    width:100%;
    border:none;
  }
  .url{
    color:#2d6da3;
    cursor:pointer;
    :hover{
      color:#ff00ff;
    }
  }
  .list_child_h1{
    overflow: hidden;
    font-size: 14px;
  }
  .operation_child_h1{
    font-size:14px;
  }
</style>
