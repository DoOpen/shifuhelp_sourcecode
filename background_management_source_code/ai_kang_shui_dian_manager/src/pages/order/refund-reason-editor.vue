<template>
  <div class="editor-root">
    <toolbar title="退款原因详情" :toolData="toolData" @toolClick="toolClick"></toolbar>
    <detail :baseData="baseData" :dataSource="reasonBean" @change="change"></detail>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        baseData: [],
        reasonBean:{},
        toolData:[{name:'保存'}],
      }
    },
    beforeMount() {
      if(this.$route.params.reason_id!=0){
        this.post(1, 'orderController.api?getRefundReasonDetail', {reason_id: this.$route.params.reason_id});
      }
      this.baseData = [
        {key: 'reason_name', name: '名称'},
        {key: 'sort', name: '权重'},
        {key: 'create_time', name: '创建时间',type:'text'}
      ];
    },
    methods: {
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.reasonBean = data;
            break;
          case 2:
            this.showTip('保存成功', 'success');
            this.$router.back();
            break;
        }
      },
      change(key, value) {
        this.reasonBean[key]=value;
      },
      toolClick(index) {
        switch(index){
          case 0:
            if (this.isNull(this.reasonBean.reason_id)) {
              this.post(2, 'orderController.api?insertRefundReason', this.reasonBean);
            } else {
              this.post(2, 'orderController.api?updateRefundReason', this.reasonBean);
            }
            break;
        }
      },
    }
  }
</script>
<style scoped>

</style>
