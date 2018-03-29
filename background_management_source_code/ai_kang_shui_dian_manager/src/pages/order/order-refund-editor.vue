<template>
  <div class="editor-root">
    <toolbar title="售后信息详情" :toolData="toolData" @toolClick="toolClick"/>
    <detail :baseData="baseData" :dataSource="refundBean" @change="change" header="基本信息"/>
    <div class="splitter">售后图片</div>
    <div class="img_parent">
      <div class="img_child">
        <my-image :disable="true" :initValue="refundBean.refund_img1" options="width:150px;height:150px;"/>
        <my-image :disable="true" :initValue="refundBean.refund_img2" options="width:150px;height:150px;"/>
        <my-image :disable="true" :initValue="refundBean.refund_img3" options="width:150px;height:150px;"/>
      </div>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        baseData: [],
        toolData: [{name: '保存'}],
        refundBean: {}
      }
    },
    beforeMount() {
      this.post(1, 'orderController.api?getRefundDetail', {refund_id: this.$route.params.refund_id});
      this.baseData = [
        {key: 'member_id', name: '用户ID', type: 'text'},
        {key: 'order_id', name: '订单ID', type: 'text'},
        {key: 'order_no', name: '订单号', type: 'text'},
        {key: 'order_goods_id', name: '商品ID', type: 'text'},
        {key: 'refund_type_show', name: '售后类型', type: 'text'},
        {key: 'refund_no', name: '售后单号', type: 'text'},
        {key: 'refund_count', name: '退款数量', type: 'text'},
        {key: 'reason_name', name: '退款原因', type: 'text'},
        {key: 'refund_desc', name: '买家备注', type: 'textarea'},
        {
          key: 'refund_state',
          name: '退款状态',
          selectValue: 'value',
          showValue: 'key',
          type: 'select',
          dataSource: [
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
              key: '已退款',
              value: 'end'
            },
            {
              key: '取消',
              value: 'cancel'
            }
          ]
        },
        {key: 'refuse_note', name: '拒绝原因', type: 'textarea'},
        {key: 'logistics_no', name: '物流单号', type: 'text'},
        {key: 'logistics_name', name: '物流公司', type: 'text'},
        {key: 'create_time', name: '申请时间', type: 'text'}
      ];
    },
    methods: {
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.refundBean = data;
            if (data.refund_state != 'wait_review') {
              this.baseData[9].disable = true;
            }
            break;
          case 2:
            this.showTip('保存成功', 'success');
            this.$router.back();
            break;
        }
      },
      change(key, value) {
        this.refundBean[key] = value;
        if(key=='refund_state'){
          this.baseData[10].hide=this.refundBean.refund_state=='refuse'?false:true;
        }
      },
      toolClick(index) {
        let params = {};
        switch (index) {
          case 0:
            this.post(2, 'orderController.api?updateRefund', params);
            if(this.refundBean.refund_state=='accept'){
              params.refund_id=this.refundBeans[rowId].refund_id;
              this.post(2,'orderController.api?refundOrder',params);
            }else{
              params.refund_state = this.refundBean.refund_state;
              params.refuse_note = this.refundBean.refuse_note;
              params.refund_id = this.refundBean.refund_id;
            }
            break;
        }
      }
    }
  }
</script>
<style scoped lang="less">
.img_parent{
  display: flex;
  flex-flow: row nowrap;
  justify-content: flex-start;
  .img_child{
    margin: 10px 0 10px 10px;
    >div{
     display: inline-block;
      margin-left:20px;
    }
  }
}
</style>
