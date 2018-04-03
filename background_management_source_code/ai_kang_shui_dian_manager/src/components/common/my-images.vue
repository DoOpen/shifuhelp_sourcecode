<template>
  <div class="image-root" v-if="!hide">
    <div class="splitter">
      <p>{{header}}</p>
    </div>
    <div class="image-content">
      <div class="image-child" v-for="(src,index) in images">
        <a :href="homeUrl+src" target="_blank">
          <img :src="buildSrc(src)" @error="imgError" :style="isNull(options)?{'width':'100px','height':'100px'}:options">
        </a>
        <my-button name="删除" @click="removeImg(index)"/>
      </div>
      <div class="image-child">
        <img :src="homeUrl+'/images/common/add.jpg'" @click="beginUp" @error="imgError" :style="isNull(options)?{'width':'100px','height':'100px'}:options">
        <input type="file" ref="fileInput" @change="uploadImage" multiple="multiple" >
      </div>
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
    'header',
    'disable',
    'filed',
    'options',
    'hide'
  ],
  data(){
    return{
      images:!this.isNull(this.initValue)?this.initValue.split(','):[]
    }
  },
  watch:{
    initValue(){
      this.images=!this.isNull(this.initValue)?this.initValue.split(','):[];
    },
    images(){
      this.$emit('change',this.filed,this.images.join(','));
    }
  },
  methods:{
    uploadImage(e) {
      let formData = new FormData();
      for(let i=0;i<e.target.files.length;i++){
        formData.append('file', e.target.files[i]);
      }
      formData.append("path", this.isNull(this.path)?'':this.path);
      this.ajaxFileUpload(1, formData);
    },
    doSuccess(index, data) {
      this.showTip("上传成功");
      this.images=this.images.concat(data);
      this.$emit('change',this.filed,this.images.join(','));
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
    removeImg(id){
      let temp=[];
      this.images.forEach((item,index)=>{
        if(index!=id){
          temp.push(item);
        }
      });
      this.images=temp;
    },
    buildSrc(src){
      if(src.indexOf('.jpg')==-1&&src.indexOf('.png')==-1&&src.indexOf('.jpeg')==-1&&src.indexOf('.gif')==-1){
        return this.homeUrl+'/images/common/file_default_img.jpg';
      }else{
        return this.homeUrl+src;
      }
    }
  }
}
</script>
<style scoped lang="less">
  .image-content{
    display: flex;
    flex-flow: row wrap;
    margin-bottom: 10px;
  }
  .image-child{
    cursor: pointer;
    border-radius: 5px;
    margin:5px;
    display: flex;
    flex-flow: column wrap;
    text-align: center;
    >a{
      margin-bottom:5px;
    }
  }
  .image-child img{
    border:1px #2d6da3 solid;
    border-radius: 5px;
  }
  input{
    display: none;
  }
</style>
