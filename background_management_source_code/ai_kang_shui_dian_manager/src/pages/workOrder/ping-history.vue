<template>
  <div class="root">
    <toolbar title="押金支付记录" :toolData="toolData" @toolClick="toolClick"/>
    <div class="search-content">
      <editor title="师傅姓名" filed="member_real_name" @change="paramsChange"/>
      <editor title="师傅账号" filed="member_account" @change="paramsChange"/>
      <editor title="用户昵称" filed="member_nick_name" @change="paramsChange"/>
      <date-picker title="开始时间" filed="start_time" @change="paramsChange" type="date"/>
      <date-picker title="结束时间" filed="end_time" @change="paramsChange" type="date"/>
    </div>
    <list v-bind="this.$data" :dataSource="pingHistoryBeans" @goPage="goPage"/>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        baseData: [],
        pingHistoryBeans: [],
        toolData:[{name:'搜索'},{name:'导出'}],
        pingTypeData:[],
        params:{},
        page:1,
        total:0
      }
    },
    beforeMount() {
      this.getPingHistoryList(1);
      this.baseData = [
        {key: 'member_real_name', name: '师傅姓名'},
        {key: 'member_account', name: '师傅账号'},
        {key: 'member_nick_name', name: '用户昵称'},
        {key: 'price', name: '支付金额'},
        {key: 'create_time', name: '支付时间'}
      ];
    },
    methods: {
      getPingHistoryList(page){
        this.params.page=page;
        this.params.ping_type=this.isNull(this.$route.params.ping_type)?"":this.$route.params.ping_type;
        this.post(1, 'workOrderController.api?getPingHistoryList',this.params,true);
      },
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.pingHistoryBeans = data.data;
            this.total=data.total;
            break;
          case 2:
            window.location.href=this.homeUrl+data;
            break;
        }
      },
      toolClick(index){
        switch (index){
          case 0:
            this.getPingHistoryList(1);
            break;
          case 1:
            this.post(2,'workOrderController.api?exportPingHistoryExcel',this.params);
            break;
        }
      },
      paramsChange(key,value){
        this.params[key]=value;
      },
      goPage(page){
        this.page=page;
        this.getPingHistoryList(page);
      }
    },
    watch:{
      $route(to,from){
        this.page=1;
        this.getPingHistoryList(this.page);
      }
    }
  }
</script>
<style scoped>

</style>
