<template>
  <div class="editor-root">
    <toolbar title="图文详情" :toolData="toolData" @toolClick="toolClick"></toolbar>
    <detail :baseData="baseData" :dataSource="htmlBean" header="基本信息" @change="change"></detail>
    <wang-editor :url="htmlBean.html_url" header="图文详情" filed="html_url_content" @change="change"></wang-editor>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        baseData: [],
        toolData:[{name:'保存'}],
        htmlBean: {}
      }
    },
    beforeMount() {
      if(this.$route.params.html_id!=0){
        this.post(1, 'settingController.api?getHtmlDetail', {html_id: this.$route.params.html_id});
      }
      this.baseData = [
        {key: 'html_name', name: '名称'},
        {key: 'sort', name: '权重'},
        {key: 'create_time', name: '创建时间', type: 'text'}
      ];
    },
    methods: {
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.htmlBean = data;
            break;
          case 2:
            this.showTip('保存成功', 'success');
            this.$router.back();
            break;
        }
      },
      change(key, value) {
        this.htmlBean[key] = value;
      },
      toolClick(index) {
        switch (index){
          case 0:
            if (this.isNull(this.htmlBean.html_id)) {
              this.post(2, 'settingController.api?insertHtml', this.htmlBean);
            } else {
              this.post(2, 'settingController.api?updateHtml', this.htmlBean);
            }
            break;
        }
      },
    }
  }
</script>
<style scoped>

</style>
