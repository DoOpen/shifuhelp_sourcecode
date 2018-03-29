<template>
  <div class="editor-root">
    <toolbar title="物流公司详情" :toolData="toolData" @toolClick="toolClick"></toolbar>
    <detail :baseData="baseData" :dataSource="logisticsBean" @change="change"></detail>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        baseData: [],
        toolData: [{name:'保存'}],
        logisticsBean: {}
      }
    },
    beforeMount() {
      if(this.$route.params.logistics_id!=0){
        this.post(1, 'orderController.api?getLogisticsDetail', {logistics_id: this.$route.params.logistics_id});
      }
      this.baseData = [
        {key: 'logistics_name', name: '公司名称'},
        {key: 'logistics_code', name: '公司编码'},
        {key: 'sort', name: '权重'},
        {key: 'create_time', name: '创建时间',type:'text'}
      ];
    },
    methods: {
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.logisticsBean = data;
            break;
          case 2:
            this.showTip('保存成功', 'success');
            this.$router.back();
            break;
        }
      },
      change(key, value) {
        this.logisticsBean[key] = value;
      },
      toolClick(index) {
        switch (index) {
          case 0:
            if(this.$route.params.logistics_id==0){
              this.post(2, 'orderController.api?insertLogistics', this.logisticsBean);
            }else{
              this.post(2, 'orderController.api?updateLogistics', this.logisticsBean);
            }
            break;
        }
      }
    }
  }
</script>
<style scoped>

</style>
