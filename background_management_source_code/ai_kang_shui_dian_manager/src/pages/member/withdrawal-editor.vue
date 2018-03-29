<template>
  <div class="editor-root">
    <toolbar title="提现信息详情"/>
    <detail :baseData="baseData" :dataSource="withdrawalBean" @change="change" :toolData="toolData" @toolClick="toolClick"/>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        baseData: [],
        toolData:[{name:'保存'}],
        withdrawalBean: JSON.parse(decodeURIComponent(this.$route.params.withdrawalBean))
      }
    },
    beforeMount() {
      this.baseData = [
        {key: 'withdrawal_way_show', name: '提现类型',type:'text'},
        {key: 'member_account', name: '账号',type:'text'},
        {key: 'member_real_name', name: '姓名',type:'text'},
        {key: 'withdrawal_price', name: '提现金额',type:'text'},
        {key: 'member_extract_money', name: '用户余额',type:'text'},
        {key: 'bank_name', name: '银行名称',type:'text'},
        {key: 'bank_open_name', name: '开户行名称',type:'text'},
        {key: 'bank_user_name', name: '开户人姓名',type:'text'},
        {key: 'bank_open_mobile', name: '开户人手机号',type:'text'},
        {key: 'withdrawal_no', name: '打款账号',type:'text'},
        {key: 'order_no', name: '流水号',type:'text'},
        {key: 'pay_time', name: '打款时间', type: 'text'},
        {key: 'create_time', name: '申请时间', type: 'text'},
        {
          key: 'withdrawal_state',
          name: '提现状态',
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
              key: '已打款',
              value: 'end'
            }
          ]
        },
        {key: 'refuse_note', name: '拒绝原因',type:'textarea'}
      ];
      this.baseData[13].disable=this.withdrawalBean.withdrawal_state=='refuse'||this.withdrawalBean.withdrawal_state=='end'?true:false;
      this.baseData[5].hide=this.baseData[6].hide=this.baseData[7].hide=this.baseData[8].hide=this.withdrawalBean.withdrawal_way=='bank'?false:true;
      this.init();
    },
    methods: {
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.showTip('保存成功', 'success');
            this.$router.back();
            break;
        }
      },
      init(){
        this.baseData[14].hide=this.withdrawalBean.withdrawal_state=='refuse'?false:true;
      },
      change(key, value) {
        this.withdrawalBean[key] = value;
        if(key=='withdrawal_state'){
          this.init();
        }
      },
      toolClick(index) {
        switch (index){
          case 0:
            let params={};
            params.withdrawal_id=this.withdrawalBean.withdrawal_id;
            params.withdrawal_state=this.withdrawalBean.withdrawal_state;
            params.refuse_note=this.withdrawalBean.refuse_note;
            this.post(1, 'memberController.api?updateWithdrawal', params);
            break;
        }
      },
    }
  }
</script>
<style scoped>

</style>
