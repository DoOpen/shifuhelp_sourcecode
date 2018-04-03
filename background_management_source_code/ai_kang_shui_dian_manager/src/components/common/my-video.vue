<template>
  <div class="image-root" v-if="!hide">
    <div :class="isNull(title)?'':'image-title'">
      <p>{{title}}</p>
    </div>
    <div class="image-content">
      <video controls="controls" @click="beginUp" :style="isNull(options)?{'width':'200px','height':'60px'}:options">
        <source :src="this.homeUrl+src" type="video/mp4" />
        <source :src="this.homeUrl+src" type="video/flv" />
        <source :src="this.homeUrl+src" type="video/avi" />
        <source :src="this.homeUrl+src" type="video/mov" />
        <source :src="this.homeUrl+src" type="video/mkv" />
      </video>
      <input type="file" accept="video/mp4,video/flv,video/avi，video/mov,video/mkv" ref="fileInput" @change="uploadImage" >
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
    'hide'
  ],
  data(){
    return{
      src:!this.isNull(this.initValue)?this.initValue:this.defaultImg
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
      this.ajaxFileUpload(1, formData);
    },
    doSuccess(index, data) {
      this.showTip("上传成功");
      this.src=data[0];
      this.$emit('change',this.filed,data[0]);
    },
    doFailed(index, error) {
      this.showTip(error);
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
  width:100px;
  text-align: right;
  margin-right:10px;
}
  .image-content{
    cursor: pointer;
    border-radius: 5px;
    border:1px #2d6da3 solid;
  }

  input{
    display: none;
  }
</style>
