<template>
  <div class="setting-root">
    <toolbar title="平台设置列表"/>
    <div class="search-content">
    </div>
    <list v-bind="this.$data" :dataSource="settingBeans" @operationClick="operationClick"  @goPage="goPage"/>
  </div>
</template>
<script type="text/ecmascript-6">
export default {

  data(){
    return{
      settingBeans:[],
      baseData:[],
      operationData:[],
      page:1,
      params:{},
      total:0
    }
  },
  beforeMount() {
    this.getSystemSettingList(1);
    this.baseData = [
      {key: 'setting_desc', name: '简介'},
      {key: 'setting_name', name: '标识'},
      {key: 'setting_value', name: '参数'},
      {key: 'setting_unit', name: '单位'},
      {key: 'create_time', name: '创建时间'},
      {name: '操作',type:'operation'},
    ];
    this.operationData=[
      {name:'详情'}
    ]
  },
  methods: {
    getSystemSettingList(page) {
      this.params.page=this.page;
      this.post(1, 'settingController.api?getSettingList',this.params,true);
    },
    doSuccess(index, data) {
      switch (index) {
        case 1:
          this.settingBeans = data.data;
          this.total = data.total;
          break;
        case 2:
          this.showTip('删除成功', 'success');
          this.getSystemSettingList(this.page);
          break;
      }
    },
    operationClick(index, rowId) {
      switch (index) {
        case 0:
          this.$router.push('/setting_editor/' + encodeURIComponent(JSON.stringify(this.settingBeans[rowId])));
          break;
      }
    },
    toolClick(index){
      switch (index){
        case 0:
          this.getSystemSettingList(1);
          break;
      }
    },
    paramsChange(key,value){
      this.params[key]=value;
    },
    goPage(page) {
      this.page = page;
      this.getSystemSettingList(page);
    }
  }
}
</script>
<style scoped>

</style>
