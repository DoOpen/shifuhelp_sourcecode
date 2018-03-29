<template>
  <div class="editor-root">
    <toolbar title="系统模块详情" :toolData="toolData" @toolClick="toolClick"></toolbar>
    <detail :baseData="baseData" :dataSource="moduleBean" @change="change"></detail>
  </div>
</template>
<script type="text/ecmascript-6">

  export default {

    data() {
      return {
        baseData: [],
        moduleBean:{},
        toolData:[{name:'保存'}],
        parent_id:this.$route.params.parent_id
      }
    },
    beforeMount() {
      if(this.$route.params.module_id!=0){
        this.post(1, 'systemController.api?getSystemModuleDetail', {module_id: this.$route.params.module_id});
      }
      this.baseData = [
        {key: 'module_name', name: '名称'},
        {key: 'module_url', name: '路径'},
        {key: 'sort', name: '权重'}
      ];
      if(this.$route.params.parent_id==-1){
        this.baseData[1].disable=true;
      }
    },
    methods: {
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.moduleBean = data;
            break;
          case 2:
            this.showTip('保存成功', 'success');
            this.$router.back();
            break;
          case 3:
            this.showTip('保存成功', 'success');
            this.$router.back();
            break;
        }
      },
      change(key, value) {
        this.moduleBean[key]=value;
      },
      toolClick(index) {
        switch(index){
          case 0:
            if (this.isNull(this.moduleBean.module_id)) {
              this.moduleBean.parent_id=this.parent_id;
              this.post(2, 'systemController.api?insertSystemModule', this.moduleBean);
            } else {
              this.post(3, 'systemController.api?updateSystemModule', this.moduleBean);
            }
            break;
        }
      },
    }
  }
</script>
<style scoped>

</style>
