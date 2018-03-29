<template>
  <div class="image-root" v-if="!hide">
    <div :class="isNull(title)?'':'image-title'">
      <p>{{title}}</p>
    </div>
    <div class="image-content">
      <img :src="src.indexOf('http')>-1?src:(homeUrl+src)" @click="beginUp" @error="imgError" :style="isNull(options)?{'width':'200px','height':'60px'}:options"
        @mouseover="img_class='over'" @mouseleave="img_class='leave'" :class="not_hover==false?'':img_class">
      <input type="file" :accept="isNull(type)||type=='image'?'image/png,image/jpg,image/jpeg,image/gif':''" ref="fileInput" @change="uploadImage" >
    </div>
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
      img_class:'leave'
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
      formData.append(e.target.value, e.target.files[0]);
      formData.append("path", this.isNull(this.path)?'':this.path);
      this.ajaxFileUpload(1, this.uploadPath, formData);
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
    }
  }
}
</script>
<style scoped>
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
  .image-content>img{
    border:1px #2d6da3 solid;
    border-radius: 5px;
  }
  .over{
    transform: scale(8.0);
  }
  input{
    display: none;
  }
</style>
