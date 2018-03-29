<template>
  <div class="root">
    <toolbar title="订单列表" :toolData="toolData" @toolClick="toolClick"></toolbar>
    <div class="search-content">
      <editor title="订单ID" filed="order_id" @change="paramsChange"></editor>
      <editor title="用户ID" filed="member_id" @change="paramsChange"></editor>
      <editor title="订单号" filed="order_no" @change="paramsChange"></editor>
      <date-picker title="开始时间" filed="start_time" @change="paramsChange" type="date"></date-picker>
      <date-picker title="结束时间" filed="end_time" @change="paramsChange" type="date"></date-picker>
    </div>
    <list v-bind="this.$data" :dataSource="orderBeans" @operationClick="operationClick" @goPage="goPage"></list>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        baseData: [],
        orderBeans: [],
        operationData:[],
        toolData:[{name:'搜索'},{name:'导出'}],
        orderStateData:[],
        params:{},
        page:1,
        total:0
      }
    },
    beforeMount() {
      this.getOrderList(1);
      this.baseData = [
        {key: 'order_id', name: 'ID'},
        {key: 'member_id', name: '用户ID'},
        {key: 'order_no', name: '订单号',flex:2},
        {key: 'order_total_price', name: '订单总价'},
        {key: 'order_actual_price', name: '支付价格'},
        {key: 'logistics_no', name: '物流单号',flex:2},
        {key: 'invoice_state_show', name: '发票状态'},
        {key: 'order_state_show', name: '订单状态'},
        {name: '操作',type:'operation',flex:2},
      ];
      this.operationData=[
        {name:'详情'},
        {name:'删除',type:'dialog',message:'确定删除？'}
      ];
    },
    methods: {
      getOrderList(page){
        this.params.page=page;
        this.params.order_state=this.isNull(this.$route.params.order_state)?"":this.$route.params.order_state;
        if (!this.isNull(sessionStorage.systemAccountBean)) {
          let systemAccountBean = JSON.parse(sessionStorage.systemAccountBean);
          if(systemAccountBean.system_type=='merchants') {
            this.params.merchants_id = systemAccountBean.relation_id;
          }
        }
        this.post(1, 'orderController.api?getOrderList',this.params,true);

      },
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.orderBeans = data.data;
            this.total=data.total;
            break;
          case 2:
            this.showTip('删除成功','success');
            this.getOrderList(1);
            break;
          case 3:
            window.location.href=this.homeUrl+data;
            break;
        }
      },
      operationClick(index,rowId){
        switch(index){
          case 0:
            this.$router.push('/order_editor/'+this.orderBeans[rowId].order_id);
            break;
          case 1:
            this.post(2,'orderController.api?deleteOrder',{order_id:this.orderBeans[rowId].order_id});
            break;
        }
      },
      toolClick(index){
        switch (index){
          case 0:
            this.getOrderList(1);
            break;
          case 1:
            this.post(3,'orderController.api?exportOrderExcel',this.params);
            break;
        }
      },
      paramsChange(key,value){
        this.params[key]=value;
      },
      goPage(page){
        this.page=page;
        this.getOrderList(page);
      }
    },
    watch:{
      $route(to,from){
        this.page=1;
        this.getOrderList(this.page);
      }
    }
  }
</script>
<style scoped>

</style>
