<template>
  <div class="reported-root">
    <toolbar title="报备信息列表" :toolData="toolData" @toolClick="toolClick"/>
    <div class="search-content">
      <editor title="师傅账号" filed="member_account" @change="paramsChange"/>
      <editor title="师傅姓名" filed="member_real_name" @change="paramsChange"/>
      <editor title="报备账号" filed="reported_phone" @change="paramsChange"/>
      <editor title="报备姓名" filed="reported_name" @change="paramsChange"/>
    </div>
    <list v-bind="this.$data" :dataSource="reportedBeans" @operationClick="operationClick"  @goPage="goPage"/>
  </div>
</template>
<script type="text/ecmascript-6">
export default {

  data(){
    return{
      reportedBeans:[],
      baseData:[],
      toolData:[{name:'搜索'}],
      operationData:[],
      page:1,
      params:{},
      total:0
    }
  },
  beforeMount() {
    this.getReportedList(1);
    this.baseData = [
      {key: 'member_account', name: '师傅账号'},
      {key: 'member_real_name', name: '师傅姓名'},
      {key: 'reported_phone', name: '报备账号'},
      {key: 'reported_name', name: '报备姓名'},
      {key: 'reported_img1', name: '报备图片',type:'img',disable:true},
      {key: 'create_time', name: '报备时间'},
      {key: 'address', name: '报备地址',flex:2},
      {key: 'reported_state_show', name: '审核状态'},
      {name: '操作',type:'operation'},
    ];
    this.operationData=[
      {name:'详情'}
    ]
  },
  methods: {
    getReportedList(page) {
      this.params.page=this.page;
      this.params.reported_state=this.isNull(this.$route.params.reported_state)?"":this.$route.params.reported_state;
      this.post(1, 'memberController.api?getReportedList',this.params,true);
    },
    doSuccess(index, data) {
      switch (index) {
        case 1:
          data.data.forEach((item)=>{
            item.address=item.province+item.city+item.district+item.detail;
          })
          this.reportedBeans = data.data;
          this.total = data.total;
          break;
        case 2:
          this.showTip('删除成功', 'success');
          this.getReportedList(this.page);
          break;
      }
    },
    operationClick(index, rowId) {
      switch (index) {
        case 0:
          this.$router.push('/reported_editor/' + encodeURIComponent(JSON.stringify(this.reportedBeans[rowId])));
          break;
      }
    },
    toolClick(index){
      switch (index){
        case 0:
          this.getReportedList(1);
          break;
      }
    },
    paramsChange(key,value){
      this.params[key]=value;
    },
    goPage(page) {
      this.page = page;
      this.getReportedList(page);
    }
  },
  watch:{
    $route(to,from){
      this.page=1;
      this.getReportedList(this.page);
    }
  }
}
</script>
<style scoped>

</style>
