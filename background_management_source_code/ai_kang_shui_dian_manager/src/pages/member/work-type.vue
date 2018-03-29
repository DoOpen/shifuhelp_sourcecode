<template>
  <div class="type-root">
    <toolbar title="工种列表" :toolData="toolData" @toolClick="toolClick"/>
    <div class="search-content">
      <editor title="名称" filed="type_name" @change="paramsChange"/>
    </div>
    <list v-bind="this.$data" :dataSource="typeBeans" @operationClick="operationClick"  @goPage="goPage"/>
  </div>
</template>
<script type="text/ecmascript-6">
export default {

  data(){
    return{
      typeBeans:[],
      baseData:[],
      toolData:[{name:'搜索'},{name:'添加'}],
      operationData:[],
      page:1,
      params:{},
      total:0
    }
  },
  beforeMount() {
    this.getWorkTypeList(1);
    this.baseData = [
      {key: 'type_name', name: '名称'},
      {key: 'sort', name: '权重'},
      {key: 'create_time', name: '创建时间'},
      {name: '操作',type:'operation'},
    ];
    this.operationData=[
      {name:'详情'},
      {name:'删除',type:'dialog',message:'确定删除？'}
    ]
  },
  methods: {
    getWorkTypeList(page) {
      this.params.page=this.page;
      this.post(1, 'memberController.api?getWorkTypeList',this.params,true);
    },
    doSuccess(index, data) {
      switch (index) {
        case 1:
          this.typeBeans = data.data;
          this.total = data.total;
          break;
        case 2:
          this.showTip('删除成功', 'success');
          this.getWorkTypeList(this.page);
          break;
      }
    },
    operationClick(index, rowId) {
      switch (index) {
        case 0:
          this.$router.push('/work_type_editor/' + encodeURIComponent(JSON.stringify(this.typeBeans[rowId])));
          break;
        case 1:
          this.post(2,'memberController.api?deleteWorkType',{type_id:this.typeBeans[rowId].type_id});
          break;
      }
    },
    toolClick(index){
      switch (index){
        case 0:
          this.getWorkTypeList(1);
          break;
        case 1:
          this.$router.push('/work_type_editor');
          break;
      }
    },
    paramsChange(key,value){
      this.params[key]=value;
    },
    goPage(page) {
      this.page = page;
      this.getWorkTypeList(page);
    }
  }
}
</script>
<style scoped>

</style>
