<template>
  <div class="editor-root">
    <toolbar title="订单信息详情" :toolData="toolData" @toolClick="toolClick"></toolbar>
    <detail :baseData="baseData" :dataSource="orderBean" header="基本信息" @change="change"></detail>
    <detail :baseData="logisticsData" :dataSource="orderBean" header="物流信息" @change="change"></detail>
    <detail :baseData="invoiceData" :dataSource="orderBean" header="发票信息" v-if="orderBean.invoice_state!='wait_apply'" @change="change"></detail>
    <list :baseData="orderGoodsData" :dataSource="orderBean.orderGoodsBeans" header="商品信息" @change="change"></list>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        baseData: [],
        toolData: [{name: '发货'},{name:'保存'}],
        orderBean: {},
        logisticsData:[],
        invoiceData:[],
        orderGoodsData:[]
      }
    },
    beforeMount() {
      this.getOrderDetail();
      this.post(4, 'orderController.api?getLogisticsList', {limit: 50000});
      this.baseData = [
        {key: 'member_id', name: '用户ID',type:'text'},
        {key: 'order_no', name: '订单号',type:'text'},
        {key: 'order_total_price', name: '订单总价',type:'text'},
        {key: 'order_actual_price', name: '支付价格',type:'text'},
        {key: 'order_refund_price', name: '退款金额',type:'text'},
        {key: 'order_state_show', name: '订单状态',type:'text'},
        {key: 'pay_way_show', name: '支付方式',type:'text'},
        {key: 'pay_no', name: '付款单号',type:'text'},
        {key: 'ping_no', name: 'ping++单号',type:'text'},
        {key: 'create_time', name: '下单时间',type:'text'},
        {key: 'cancel_time', name: '取消时间',type:'text'},
        {key: 'pay_time', name: '付款时间',type:'text'},
        {key: 'send_time', name: '发货时间',type:'text'},
        {key: 'receive_time', name: '收货时间',type:'text'},
        {key: 'assessment_time', name: '评价时间',type:'text'}
      ];
      this.logisticsData = [
        {key: 'address_mobile', name: '手机',type:'text'},
        {key: 'address_name', name: '姓名',type:'text'},
        {key: 'address_province', name: '物流-省',type:'text'},
        {key: 'address_city', name: '物流-市',type:'text'},
        {key: 'address_district', name: '物流-区',type:'text'},
        {key: 'address_detail', name: '详细地址',type:'text'},
        {key: 'address_zip_code', name: '邮编',type:'text'},
        {key: 'logistics_no', name: '物流单号'},
        {
          key: 'logistics_id',
          name: '物流公司',
          selectValue: 'logistics_id',
          showValue: 'logistics_name',
          type: 'select',
          dataSource: []
        }
      ];
      this.invoiceData = [
        {
          key: 'invoice_state',
          name: '发票状态',
          selectValue: 'value',
          showValue: 'key',
          type: 'select',
          dataSource: [
            {
              key: '等待审核',
              value: 'wait_accept'
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
              key: '已开票',
              value: 'end'
            }
          ]
        },
        {key: 'invoice_type', name: '发票类型',type:'text'},
        {key: 'invoice_rise', name: '发票抬头',type:'text'},
        {key: 'invoice_no', name: '纳税人识别号',type:'text'},
        {key: 'invoice_desc', name: '发票内容',type:'text'},
        {key: 'invoice_price', name: '发票金额',type:'text'},
        {key: 'invoice_mobile', name: '手机号',type:'text'},
        {key: 'invoice_name', name: '物流-省',type:'text'},
        {key: 'invoice_province', name: '物流-省',type:'text'},
        {key: 'invoice_city', name: '物流-市',type:'text'},
        {key: 'invoice_district', name: '物流-区',type:'text'},
        {key: 'invoice_address_detail', name: '详细地址',type:'text'},
        {key: 'invoice_time', name: '申请时间',type:'text'}
      ];
      this.orderGoodsData=[
        {key: 'order_goods_id', name: 'ID'},
        {key: 'order_id', name: '订单ID'},
        {key: 'goods_id', name: '商品ID'},
        {key: 'goods_num', name: '数量'},
        {key: 'goods_name', name: '商品名称',flex:2},
        {key: 'goods_img', name: '商品图片',type:'img',disable:true},
        {key: 'specification_id', name: '规格ID'},
        {key: 'specification_ids', name: '规格组合'},
        {key: 'specification_names', name: '规格名称',flex:2},
        {key: 'specification_img', name: '规格图片',type:'img',disable:true},
        {key: 'specification_price', name: '单价'}
      ];
    },
    methods: {
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.orderBean = data;
            this.init();
            break;
          case 2:
            this.showTip('发货成功', 'success');
            this.getOrderDetail();
            break;
          case 3:
            this.showTip('保存成功', 'success');
            break;
          case 4:
            this.logisticsData[8].dataSource=data;
            break;
        }
      },
      getOrderDetail(){
        this.post(1, 'orderController.api?getOrderDetail', {order_id: this.$route.params.order_id});
      },
      init(){
        if(this.orderBean.order_state!='wait_send'){
          this.$set(this.toolData[0],'hide',true);
        }
      },
      change(key, value) {
        this.orderBean[key] = value;
      },
      toolClick(index) {
        let params={};
        switch (index) {
          case 0:
            if(this.isNull(this.orderBean.logistics_no)){
              this.showTip('请填写物流单号','danger');
              return;
            }
            params.logistics_no=this.orderBean.logistics_no;
            params.logistics_id=this.orderBean.logistics_id;
            params.order_id=this.orderBean.order_id;
            this.post(2, 'orderController.api?sendOrder', params);
            break;
          case 1:
            params.order_id=this.orderBean.order_id;
            params.invoice_state=this.orderBean.invoice_state;
            this.post(3, 'orderController.api?updateOrder', params);
            break;
        }
      }
    }
  }
</script>
<style scoped>

</style>
