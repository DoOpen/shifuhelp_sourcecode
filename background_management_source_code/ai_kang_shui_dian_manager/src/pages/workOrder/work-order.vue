<template>
  <div class="root">
    <toolbar title="工单列表" :toolData="toolData" @toolClick="toolClick"/>
    <div class="search-content">
      <editor title="姓名" filed="order_name" @change="paramsChange"/>
      <editor title="电话" filed="order_phone" @change="paramsChange"/>
      <my-select title="状态" filed="type" :dataSource="orderStateData" selectValue="value" showValue="key" :initValue="this.$route.params.type" @change="paramsChange"/>
      <date-picker title="开始时间" filed="order_create_time" @change="paramsChange" type="date"/>
      <date-picker title="结束时间" filed="order_update_time" @change="paramsChange" type="date"/>
    </div>
    <list v-bind="this.$data" :dataSource="orderBeans" @operationClick="operationClick" @goPage="goPage"/>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        baseData: [],
        orderBeans: [],
        operationData:[],
        toolData:[{name:'搜索'},{name:'创建工单'},{name:'导出'}],
        orderStateData:[],
        params:{},
        page:1,
        total:0
      }
    },
    beforeMount() {
      this.getOrderList(1);
      this.baseData = [
        {key: 'order_name', name: '姓名'},
        {key: 'order_phone', name: '电话'},
        {key: 'worker_name', name: '服务师傅'},
        {key: 'order_subscribe_content', name: '服务内容',flex:2},
        {key: 'service_address', name: '服务地址',flex:2},
        {key: 'order_final_price', name: '订单金额'},
        {key: 'order_state_show', name: '订单状态'},
        {key: 'order_update_time', name: '流转记录'},
        {name: '操作',type:'operation',flex:2},
      ];
      this.operationData=[
        {name:'详情'},
        {name:'作废',type:'dialog',message:'作废后不可恢复，是否作废？'},
        {name:'锁定',flag:'work_order_lock'},
        {name:'派单',flag:'send_work_order'}
      ];
      this.orderStateData=[
        {
          key: '全部',
          value: ''
        },
        {
          key: '待审核',
          value: 'wait_audit'
        },
        {
          key: '待派单',
          value: 'loss'
        },
        {
          key: '待接单',
          value: 'wait_accept'
        },
        {
          key: '已接单',
          value: 'complete_accept'
        },
        {
          key: '已退单',
          value: 'cancel'
        },
        {
          key: '已完成',
          value: 'complete'
        },
        {
          key: '异常',
          value: 'exception'
        },
        {
          key: '作废',
          value: 'nullify'
        }
      ]
    },
    methods: {
      getOrderList(page){
        this.params.page=page;
        this.params.order_is_delete=this.params.type=='nullify'?1:0;
        this.params.type=this.isNull(this.$route.params.type)?"":this.$route.params.type;
        this.post(1, 'workOrderController.api?getOrderList',this.params,true);
      },
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.orderBeans = data.data;
            this.orderBeans.forEach((item)=>{
              item.service_address=item.order_address_province+'-'+item.order_address_city+'-'+item.order_address_district+'-'+item.order_address_detail;
            })
            this.total=data.total;
            break;
          case 2:
            this.showTip('删除成功','success');
            this.getOrderList(1);
            break;
          case 3:
            this.showTip(data,'success');
            this.getOrderList(this.page);
            break;
          case 4:
            window.location.href=this.homeUrl+data;
            break;
        }
      },
      operationClick(index,rowId){
        switch(index){
          case 0:
            this.$router.push('/work_order_editor/'+this.orderBeans[rowId].order_id+'/'+this.$route.params.type);
            break;
          case 1:
            this.post(2,'orderController.api?deleteOrder',{order_id:this.orderBeans[rowId].order_id});
            break;
          case 2:
            this.post(3,'workOrderController.api?changeLockState',{order_id:this.orderBeans[rowId].order_id});
            break;
          case 3:
            this.$router.push('/send_work_order/'+this.orderBeans[rowId].order_id);
            break;
        }
      },
      toolClick(index){
        switch (index){
          case 0:
            this.getOrderList(1);
            break;
          case 1:
            this.$router.push('/work_order_create');
            break;
          case 2:
            this.post(4, 'workOrderController.api?exportWorkOrderExcel',this.params);
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
