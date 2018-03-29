<template>
  <div class="root">
    <toolbar title="提现统计" :toolData="toolData" @toolClick="toolClick"/>
    <div class="search-content">
      <editor title="账号" filed="member_account" @change="paramsChange"/>
      <editor title="姓名" filed="member_real_name" @change="paramsChange"/>
      <date-picker title="开始时间" filed="start_time" @change="paramsChange" type="date"/>
      <date-picker title="结束时间" filed="end_time" @change="paramsChange" type="date"/>
    </div>
    <list v-bind="this.$data" :dataSource="withdrawalBeans" @operationClick="operationClick" @goPage="goPage"/>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        baseData: [],
        withdrawalBeans: [],
        operationData:[],
        toolData:[{name:'搜索'},{name:'导出'}],
        params:{},
        page:1,
        total:0
      }
    },
    beforeMount() {
      this.getWithdrawalList(1);
      this.baseData = [
        {key: 'member_account', name: '用户账号'},
        {key: 'member_real_name', name: '用户姓名'},
        {key: 'withdrawal_price', name: '提现金额'},
        {key: 'member_extract_money', name: '用户余额'},
        {key: 'withdrawal_no', name: '打款账号',flex:2},
        {key: 'order_no', name: '流水号',flex:2},
        {key: 'create_time', name: '申请时间'},
        {key: 'withdrawal_way_show', name: '打款方式'},
        {name: '操作',type:'operation'},
      ];
      this.operationData=[
        {name:'详情'}
      ];
    },
    methods: {
      getWithdrawalList(page){
        this.params.page=page;
        this.params.withdrawal_state='end';
        this.post(1, 'memberController.api?getWithdrawalList',this.params,true);
      },
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.withdrawalBeans = data.data;
            this.total=data.total;
            break;
          case 2:
            window.location.href=this.homeUrl+data;
            break;
        }
      },
      operationClick(index,rowId){
        switch(index){
          case 0:
            this.$router.push('/withdrawal_over_editor/'+encodeURIComponent(JSON.stringify(this.withdrawalBeans[rowId])));
            break;
        }
      },
      toolClick(index){
        switch (index){
          case 0:
            this.getWithdrawalList(1);
            break;
          case 1:
            this.post(2,'memberController.api?exportWithdrawalExcel',this.params);
            break;
        }
      },
      paramsChange(key,value){
        this.params[key]=value;
      },
      goPage(page){
        this.page=page;
        this.getWithdrawalList(page);
      }
    }
  }
</script>
<style scoped>

</style>
