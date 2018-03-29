<template>
  <div class="root">
    <toolbar title="物流公司列表" :toolData="toolData" @toolClick="toolClick"></toolbar>
    <div class="search-content">
      <editor title="名称" filed="logistics_name" @change="paramsChange"></editor>
      <editor title="编码" filed="logistics_code" @change="paramsChange"></editor>
    </div>
    <list v-bind="this.$data" :dataSource="logisticsBeans" @operationClick="operationClick" @goPage="goPage"></list>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        baseData: [],
        logisticsBeans: [],
        operationData:[],
        toolData:[{name:'搜索'},{name:'添加'}],
        params:{},
        page:1,
        total:0
      }
    },
    beforeMount() {
      this.getLogisticsList(1);
      this.baseData = [
        {key: 'logistics_id', name: 'ID'},
        {key: 'logistics_name', name: '物流公司名称'},
        {key: 'logistics_code', name: '物流公司编码'},
        {key: 'sort', name: '权重'},
        {key: 'create_time', name: '创建时间'},
        {name: '操作',type:'operation',flex:2},
      ];
      this.operationData=[
        {name:'详情'},
        {name:'删除',type:'dialog',message:'确定删除？'}
      ];
    },
    methods: {
      getLogisticsList(page){
        this.params.page=page;
        this.post(1, 'orderController.api?getLogisticsList',this.params,true);

      },
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.logisticsBeans = data.data;
            this.total=data.total;
            break;
          case 2:
            this.showTip('删除成功','success');
            this.getLogisticsList(1);
            break;
        }
      },
      operationClick(index,rowId){
        switch(index){
          case 0:
            this.$router.push('/logistics_editor/'+this.logisticsBeans[rowId].logistics_id);
            break;
          case 1:
            this.post(2,'orderController.api?deleteLogistics',{logistics_id:this.logisticsBeans[rowId].logistics_id});
            break;
        }
      },
      toolClick(index){
        switch (index){
          case 0:
            this.getLogisticsList(1);
            break;
          case 1:
            this.$router.push('/logistics_editor/'+0);
            break;
        }
      },
      paramsChange(key,value){
        this.params[key]=value;
      },
      goPage(page){
        this.page=page;
        this.getLogisticsList(page);
      }
    }
  }
</script>
<style scoped>

</style>
