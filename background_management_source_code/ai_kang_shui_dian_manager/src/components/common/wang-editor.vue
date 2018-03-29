<template>
  <div class="wang-root" v-if="!hide">
    <div>
      <div class="splitter">
        <p>{{header}}</p>
      </div>
      <div ref="menu" class="menu">

      </div>
      <div ref="editor" class="wang-editor">

      </div>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
  /**
   * WangEditor富文本组件
   * url：富文本内容所属网页url
   * filed：组件所属表单字段名
   * header：顶部标题栏文本，默认不显示
   * path：图片上传后保存路径，后台默认：/images/others
   * hide:为true时隐藏组件
   */
  import WangEditor from 'wangeditor'

  export default {
    props: ['url','filed','header','path','hide'],
    data() {
      return {
        editor: {},
        imgUrl: this.imgUrl
      }
    },
    mounted() {
      let imageUrl = this.homeUrl;
      let editor = new WangEditor(this.$refs.menu,this.$refs.editor);
      editor.customConfig.uploadImgServer = this.homeUrl + this.uploadPath;
      editor.customConfig.uploadImgMaxSize = 5 * 1024 * 1024;//默认限制图片大小是 5M
      editor.customConfig.uploadImgMaxLength = 5;//限制一次最多能传几张图片
      editor.customConfig.zIndex = 1;//编辑器层级
      editor.customConfig.menus = [
        'head',  // 标题
        'bold',  // 粗体
        'italic',  // 斜体
        'underline',  // 下划线
        'strikeThrough',  // 删除线
        'foreColor',  // 文字颜色
        'backColor',  // 背景颜色
        'link',  // 插入链接
        'justify',  // 对齐方式
        'quote',  // 引用
        'emoticon',  // 表情
        'image',  // 插入图片
        'undo',  // 撤销
        'redo'  // 重复
      ]
      editor.customConfig.uploadImgParams = {//额外参数
        path: this.isNull(this.path)?'':this.path,
        showTip:this.showTip
      };
      editor.customConfig.uploadImgHooks = {
        before: function (xhr, editor, files) {
          // 图片上传之前触发
          // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，files 是选择的图片文件
          // 如果返回的结果是 {prevent: true, msg: 'xxxx'} 则表示用户放弃上传
          // return {
          //     prevent: true,
          //     msg: '放弃上传'
          // }
        },
        success: function (xhr, editor, result) {
          // 图片上传并返回结果，图片插入成功之后触发
          // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
        },
        fail: function (xhr, editor, result) {
          editor.customConfig.uploadImgParams.showTip('插入失败', "danger");
          // 图片上传并返回结果，但图片插入错误时触发
          // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象，result 是服务器端返回的结果
        },
        error: function (xhr, editor) {
          editor.customConfig.uploadImgParams.showTip('上传失败', "danger");
          // 图片上传出错时触发
          // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
        },
        timeout: function (xhr, editor) {
          editor.customConfig.uploadImgParams.showTip('上传超时', "danger");
          // 图片上传超时时触发
          // xhr 是 XMLHttpRequst 对象，editor 是编辑器对象
        },
        // 如果服务器端返回的不是 {errno:0, data: [...]} 这种格式，可使用该配置
        // （但是，服务器端返回的必须是一个 JSON 格式字符串！！！否则会报错）
        customInsert: function (insertImg, result, editor) {
          // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
          // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果
          // 举例：假如上传图片成功后，服务器端返回的是 {url:'....'} 这种格式，即可这样插入图片：
          if (result.status == "ok") {
            result.data.forEach((item, index) => {
              insertImg(imageUrl + item);
            })
          }
        }
      }
      // 使用 onchange 函数监听内容的变化，并实时更新到 state 中
      editor.customConfig.onchange = html => {
        this.$emit('change', this.filed,html);
      }
      editor.create();
      this.editor = editor;
    },
    watch: {
      url() {
        if (!this.isNull(this.url)) {
          this.post(1, 'settingController.api?getHtmlDesc', {url: this.url});
        }
      }
    },
    methods: {
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.editor.txt.html(data);
            this.$emit('change', this.filed,data);
            break;
        }
      }
    },
    beforeMount(){
      if (!this.isNull(this.url)) {
        this.post(1, 'settingController.api?getHtmlDesc', {url: this.url});
      }
    }
  }
</script>
<style scoped>
  .menu{
    border: 1px solid #ccc;
  }
.wang-editor{
  border: 1px solid #ccc;
  height: 600px;
}
</style>
