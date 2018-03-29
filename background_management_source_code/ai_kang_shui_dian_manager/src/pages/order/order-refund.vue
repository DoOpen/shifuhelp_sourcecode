<template>
  <div class="root">
    <toolbar title="售后订单列表" :toolData="toolData" @toolClick="toolClick"></toolbar>
    <div class="search-content">
      <editor title="售后ID" filed="refund_id" @change="paramsChange"></editor>
      <editor title="用户ID" filed="member_id" @change="paramsChange"></editor>
      <editor title="订单号" filed="refund_no" @change="paramsChange"></editor>
      <my-select title="售后类型" filed="refund_type" :dataSource="refundTypeData" selectValue="value" showValue="key" initValue="" @change="paramsChange"></my-select>
      <my-select title="售后状态" filed="refund_state" :dataSource="refundStateData" selectValue="value" showValue="key" initValue="" @change="paramsChange"></my-select>
      <date-picker title="开始时间" filed="start_time" @change="paramsChange" type="date"></date-picker>
      <date-picker title="结束时间" filed="end_time" @change="paramsChange" type="date"></date-picker>
    </div>
    <list v-bind="this.$data" :dataSource="refundBeans" @operationClick="operationClick"  @goPage="goPage"></list>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {

    data() {
      return {
        baseData: [],
        refundBeans: [],
        operationData:[],
        toolData:[{name:'搜索'}],
        refundStateData:[],
        refundTypeData:[],
        params:{},
        page:1,
        total:0
      }
    },
    beforeMount() {
      this.getRefundList(1);
      this.baseData = [
        {key: 'refund_id', name: 'ID'},
        {key: 'member_nick_name', name: '用户昵称'},
        {key: 'order_no', name: '订单号',flex:2},
        {key: 'goods_name', name: '商品名称'},
        {key: 'refund_type_show', name: '退款类型'},
        {key: 'refund_no', name: '退款号',flex:2},
        {key: 'refund_count', name: '数量',flex:0.5},
        {key: 'refund_price', name: '退款金额'},
        {key: 'refund_state_show', name: '退款状态'},
        {name: '操作',type:'operation',flex:2},
      ];
      this.operationData=[
        {name:'详情'},
        {name:'接受'},
        {name:'拒绝'}
      ];
      this.refundStateData=[
        {
          key: '全部',
          value: ''
        },
        {
          key: '待审核',
          value: 'wait_review'
        },
        {
          key: '接受',
          value: 'accept'
        },
        {
          key: '拒绝',
          value: 'refuse'
        },
        {
          key: '退款成功',
          value: 'end'
        },
        {
          key: '取消',
          value: 'cancel'
        }
      ]
      this.refundTypeData=[
        {
          key: '全部',
          value: ''
        },
        {
          key: '仅退款',
          value: '1'
        },
        {
          key: '退货退款',
          value: '2'
        }
      ]
    },
    methods: {
      getRefundList(page){
        this.params.page=page;
        if (!this.isNull(sessionStorage.systemAccountBean)) {
          let systemAccountBean = JSON.parse(sessionStorage.systemAccountBean);
          if(systemAccountBean.system_type=='merchants') {
            this.params.merchants_id = systemAccountBean.relation_id;
          }
        }
        this.post(1, 'orderController.api?getRefundList',this.params,true);
      },
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.refundBeans = data.data;
            this.total=data.total;
            break;
          case 2:
                this.showTip('操作成功','success');
                this.getRefundList(this.page);
            break;
        }
      },
      operationClick(index,rowId){
        let params={};
        switch(index){
          case 0:
            this.$router.push('/order_refund_editor/'+this.refundBeans[rowId].refund_id);
            break;
          case 1:
            params.refund_id=this.refundBeans[rowId].refund_id;
            this.post(2,'orderController.api?refundOrder',params);
            break;
          case 2:
            params.refund_id=this.refundBeans[rowId].refund_id;
            params.refund_state='refuse';
            this.post(2,'orderController.api?updateRefund',params);
            break;
        }
      },
      toolClick(index){
        switch (index){
          case 0:
            this.getRefundList(1);
            break;
        }
      },
      paramsChange(key,value){
        this.params[key]=value;
      },
      goPage(page){
        this.page=page;
        this.getRefundList(page);
      }
    }
  }
</script>
<style scoped>

</style>
