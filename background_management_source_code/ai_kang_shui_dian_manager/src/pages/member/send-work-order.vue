<template>
  <div class="root">
    <toolbar title="师傅列表" :toolData="toolData" @toolClick="toolClick"/>
    <div class="search-content">
      <editor title="账号" filed="member_account" @change="paramsChange"/>
      <editor title="姓名" filed="member_real_name" @change="paramsChange"/>
      <my-select title="明星师傅" filed="member_is_star" :dataSource="memberStarData" selectValue="value" showValue="key" initValue="" @change="paramsChange"/>
    </div>
    <list v-bind="this.$data" :dataSource="memberBeans" @operationClick="operationClick" @goPage="goPage"/>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        baseData: [],
        memberBeans: [],
        operationData:[],
        toolData:[{name:'搜索'}],
        params:{},
        page:1,
        total:0
      }
    },
    beforeMount() {
      this.getMemberList(1);
      this.baseData = [
        {key: 'member_account', name: '账号'},
        {key: 'member_real_name', name: '姓名'},
        {key: 'member_head_image', name: '头像',type:'img',disable:true},
        {key: 'member_work_age', name: '工龄'},
        {key: 'member_work_type', name: '工种'},
        {key: 'special_skill', name: '特殊技能'},
        {key: 'member_service_number', name: '服务次数'},
        {key: 'member_good_rate', name: '好评率'},
        {key: 'assessment_star1', name: '服务'},
        {key: 'assessment_star2', name: '速度'},
        {key: 'assessment_star3', name: '做工'},
        {key: 'distance', name: '距离'},
        {name: '操作',type:'operation'},
      ];
      this.memberStarData=[
        {
          key: '全部',
          value: ''
        },
        {
          key: '是',
          value: '1'
        },
        {
          key: '否',
          value: '0'
        }
      ];
      this.operationData=[
        {name:'派单'}
      ]
    },
    methods: {
      getMemberList(page){
        this.params.page=page;
        this.params.order_id=this.$route.params.order_id;
        this.post(1, 'workOrderController.api?getSendWorkOrderWorkerList',this.params,true);
      },
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.memberBeans = data.data;
            this.total=data.total;
            break;
          case 2:
            this.showTip('派单成功','success');
            this.$router.back();
            break;
        }
      },
      operationClick(index,rowId){
        switch(index){
          case 0:
            this.post(2,'workOrderController.api?sendWorkOrder',{order_accept_id:this.memberBeans[rowId].member_id,order_id:this.$route.params.order_id});
            break;
        }
      },
      paramsChange(key,value){
        this.params[key]=value;
      },
      toolClick(index){
        switch (index){
          case 0:
            this.getMemberList(1);
            break;
        }
      },
      goPage(page){
        this.page=page;
        this.getMemberList(page);
      }
    }
  }
</script>
<style scoped>

</style>
