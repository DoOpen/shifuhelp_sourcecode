<template>
  <div class="editor-root">
    <toolbar title="城市信息详情" :toolData="toolData" @toolClick="toolClick"></toolbar>
    <detail :baseData="baseData" :dataSource="cityBean" @change="change"></detail>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        baseData: [],
        cityBean:{},
        toolData:[{name:'保存'}],
        parent_id:this.$route.params.parent_id
      }
    },
    beforeMount() {
      if(this.$route.params.id!=0){
        this.post(1, 'settingController.api?getCityDetail', {id: this.$route.params.id});
      }
      this.baseData = [
        {key: 'name', name: '名称'},
        {key: 'code', name: '区号'},
        {key: 'create_time', name: '创建时间',type:'text'}
      ];
    },
    methods: {
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.cityBean = data;
            break;
          case 2:
            this.showTip('保存成功', 'success');
            this.$router.back();
            break;
        }
      },
      change(key, value) {
        this.cityBean[key]=value;
      },
      toolClick(index) {
        switch(index){
          case 0:
            if (this.isNull(this.cityBean.id)) {
              this.cityBean.parent_id=this.parent_id;
              this.post(2, 'settingController.api?insertCity', this.cityBean);
            } else {
              this.post(2, 'settingController.api?updateCity', this.cityBean);
            }
            break;
        }
      }
    }
  }
</script>
<style scoped>
</style>
