<template>
  <div class="editor-root">
    <toolbar title="提现信息详情" />
    <detail :baseData="baseData" :dataSource="withdrawalBean" />
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        baseData: [],
        withdrawalBean: JSON.parse(decodeURIComponent(this.$route.params.withdrawalBean))
      }
    },
    beforeMount() {
      this.baseData = [
        {key: 'withdrawal_way_show', name: '提现类型',type:'text'},
        {key: 'member_account', name: '账号',type:'text'},
        {key: 'member_real_name', name: '姓名',type:'text'},
        {key: 'withdrawal_price', name: '提现金额'},
        {key: 'member_extract_money', name: '用户余额',type:'text'},
        {key: 'bank_name', name: '银行名称',type:'text'},
        {key: 'bank_open_name', name: '开户行名称',type:'text'},
        {key: 'bank_user_name', name: '开户人姓名',type:'text'},
        {key: 'bank_open_mobile', name: '开户人手机号',type:'text'},
        {key: 'withdrawal_no', name: '打款账号',type:'text'},
        {key: 'order_no', name: '流水号',type:'text'},
        {key: 'pay_time', name: '打款时间', type: 'text'},
        {key: 'create_time', name: '申请时间', type: 'text'}
      ];
      this.baseData[4].hide=this.baseData[5].hide=this.baseData[6].hide=this.baseData[7].hide=this.withdrawalBean.withdrawal_way=='bank'?false:true;
    }
  }
</script>
<style scoped>

</style>
