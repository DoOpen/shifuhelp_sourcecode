<template>
  <div class="editor-root">
    <toolbar title="平台设置详情" :toolData="toolData" @toolClick="toolClick"/>
    <detail :baseData="baseData" :dataSource="settingBean" @change="change"/>
  </div>
</template>
<script type="text/ecmascript-6">

  export default {

    data() {
      return {
        baseData: [],
        settingBean:JSON.parse(decodeURIComponent(this.$route.params.settingBean)),
        toolData:[{name:'保存'}],
      }
    },
    beforeMount() {
      this.baseData = [
        {key: 'setting_desc', name: '简介',type:'textarea'},
        {key: 'setting_name', name: '标识',disable:true},
        {key: 'setting_value', name: '参数'},
        {key: 'setting_unit', name: '单位',type:'textarea'},
        {key: 'create_time', name: '创建时间',disable:true}
      ];
    },
    methods: {
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.showTip('保存成功', 'success');
            this.$router.back();
            break;
        }
      },
      change(key, value) {
        this.settingBean[key]=value;
      },
      toolClick(index) {
        switch(index){
          case 0:
            this.post(1, 'settingController.api?updateSetting', this.settingBean);
            break;
        }
      },
    }
  }
</script>
<style scoped>

</style>
