<template>
  <div class="image-root" v-if="!hide">
    <div :class="isNull(title)?'':'image-title'">
      <p>{{title}}</p>
    </div>
    <div class="image-content">
      <img class="show"  :src="src.indexOf('http')>-1?src:(homeUrl+src)" @click="changeBig" @error="imgError" :style="isNull(options)?{'width':'200px','height':'60px'}:options" title="点击放大">
      <input type="file" :accept="isNull(type)||type=='image'?'image/png,image/jpg,image/jpeg,image/gif':''" ref="fileInput" @change="uploadImage" >
      <img :src="require('../../assets/images/common/upload_icon.png')" class="upload-icon" @click="beginUp" v-if="!disable&&!not_hover">
    </div>
    <img v-if="isClick&&!not_hover" :src="src.indexOf('http')>-1?src:(homeUrl+src)" @click="changeSmall" @error="imgError" class="imgClick">
  </div>
</template>
<script type="text/ecmascript-6">
  /**
   * 图片组件（只支持单张上传）
   * initValue:初始值
   * path：图片上传后保存路径，后台代码默认为：/images/others
   * title：组件左侧文本
   * disable:为true时禁用组件
   * filed:所属表单字段名
   * options:json格式，自定义组件宽度高度，默认：{'width':'200px','height':'60px'}
   * hide:为true时隐藏组件
   * type: 文件选择器类型  image:图片，其他全为文件
   */
  export default {
  props:[
    'initValue',
    'path',
    'title',
    'disable',
    'filed',
    'options',
    'hide',
    'type',
    'not_hover'
  ],
  data(){
    return{
      src:!this.isNull(this.initValue)?this.initValue:this.defaultImg,
      img_class:'leave',
      isClick:false
    }
  },
  watch:{
    initValue(){
      this.src=!this.isNull(this.initValue)?this.initValue:this.defaultImg;
    }
  },
  methods:{
    uploadImage(e) {
      let formData = new FormData();
      formData.append("path", this.isNull(this.path)?'':this.path);
      if(e.target.value.indexOf('.jpg')>-1||e.target.value.indexOf('.jpeg')>-1||e.target.value.indexOf('.png')>-1||e.target.value.indexOf('.gif')>-1){
        this.photoCompress(e.target.files[0], {
            quality: 0.2
          },(base64Codes)=>{
            formData.append(e.target.value, base64Codes,e.target.value);
            this.ajaxFileUpload(1, formData);
          }
        )
      }else{
        formData.append(e.target.value, e.target.files[0],e.target.value);
        this.ajaxFileUpload(1, formData);
      }
    },
    doSuccess(index, data) {
      this.showTip("上传成功");
      this.src=data[0];
      this.$emit('change',this.filed,data[0]);
    },
    doFailed(index, error) {
      this.showTip(error);
    },
    imgError(e){
      e.target.src=this.homeUrl+this.defaultImg;
    },
    beginUp(){
      if(!this.disable){
        this.$refs.fileInput.click();
      }
    },
    changeBig(e){
      if(this.not_hover){
        this.beginUp();
      }
      this.isClick=!this.isClick;
    },
    changeSmall(e){
      this.isClick=!this.isClick;
    }
  }
}
</script>
<style scoped lang="less">
.image-root{
  display: flex;
  align-content: center;
  align-items: center;
  justify-content: flex-start;
}
.image-title{
  width:150px;
  text-align: right;
  margin-right:10px;
  float:left;
}
  .image-content{
    cursor: pointer;
    border-radius: 5px;
  }
  .image-content>.show{
    border:1px #2d6da3 solid;
    border-radius: 5px;
  }
.image-content{
  .upload-icon{
    width:30px;
    height:30px;
  }
}
  input{
    display: none;
  }
  .imgClick{
    width:800px;
    height:800px;
    border:1px #2d6da3 solid;
    border-radius: 5px;
    position: fixed;
    top:50%;
    left:50%;
    transform: translate(-50%,-50%);
  }
</style>
