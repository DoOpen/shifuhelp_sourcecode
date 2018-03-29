<template>
  <div class="reason-root">
    <toolbar title="退款原因列表" :toolData="toolData" @toolClick="toolClick"></toolbar>
    <list v-bind="this.$data" :dataSource="reasonBeans" @operationClick="operationClick"  @goPage="goPage"></list>
  </div>
</template>
<script type="text/ecmascript-6">
export default {
  data(){
    return{
      reasonBeans:[],
      baseData:[],
      operationData:[],
      toolData:[{name:'添加'}],
      page:1,
      total:0,
    }
  },
  beforeMount() {
    this.getRefundReasonList(1);
    this.baseData = [
      {key: 'reason_id', name: 'ID'},
      {key: 'reason_name', name: '名称'},
      {key: 'sort', name: '权重'},
      {key: 'create_time', name: '创建时间'},
      {name: '操作',type:'operation'},
    ];
    this.operationData=[
      {name:'详情'},
      {name:'删除',type:'dialog',message:'确定删除？'}
    ]
  },
  methods: {
    getRefundReasonList(page){
      this.post(1, 'orderController.api?getRefundReasonList',{page:page},true);
    },
    doSuccess(index, data) {
      switch (index) {
        case 1:
          this.reasonBeans = data.data;
          this.total=data.total;
          break;
        case 2:
          this.showTip('删除成功','success');
          this.getRefundReasonList(this.page);
          break;
      }
    },
    operationClick(index,rowId){
      switch(index){
        case 0:
          this.$router.push('/refund_reason_editor/'+this.reasonBeans[rowId].reason_id);
          break;
        case 1:
          this.post(2,'orderController.api?deleteRefundReason',{reason_id:this.reasonBeans[rowId].reason_id});

          break;
      }
    },
    toolClick(index){
      switch (index){
        case 0:
          this.$router.push('/refund_reason_editor/'+0);
          break;
      }
    },
    goPage(page){
      this.page=page;
      this.getRefundReasonList(page);
    }
  }
}
</script>
<style scoped>

</style>
