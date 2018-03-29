<template>
  <div class="role-root">
    <toolbar title="角色列表" :toolData="toolData" @toolClick="toolClick"></toolbar>
    <list v-bind="this.$data" :dataSource="roleBeans" @operationClick="operationClick"  @goPage="goPage"></list>
  </div>
</template>
<script type="text/ecmascript-6">
export default {

  data(){
    return{
      roleBeans:[],
      baseData:[],
      operationData:[],
      toolData:[{name:'添加'}],
      page:1,
      total:0,
    }
  },
  beforeMount() {
    this.getSystemRoleList(1);
    this.baseData = [
      {key: 'role_id', name: 'ID'},
      {key: 'role_name', name: '名称'},
      {key: 'create_time', name: '创建时间'},
      {name: '操作',type:'operation'},
    ];
    this.operationData=[
      {name:'详情'},
      {name:'删除',type:'dialog',message:'确定删除？'}
    ]
  },
  methods: {
    getSystemRoleList(page){
      this.post(1, 'systemController.api?getSystemRoleList',{page:page},true);
    },
    doSuccess(index, data) {
      switch (index) {
        case 1:
          this.roleBeans = data.data;
          this.total=data.total;
          break;
        case 2:
          this.showTip('删除成功','success');
          this.getSystemRoleList(this.page);
          break;
      }
    },
    operationClick(index,rowId){
      switch(index){
        case 0:
          this.$router.push('/role_editor/'+this.roleBeans[rowId].role_id);
          break;
        case 1:
          this.post(2,'systemController.api?deleteSystemRole',{role_id:this.roleBeans[rowId].role_id});

          break;
      }
    },
    toolClick(index){
      switch (index){
        case 0:
          this.$router.push('/role_editor/'+0);
          break;
      }
    },
    goPage(page){
      this.page=page;
      this.getSystemRoleList(page);
    }
  }
}
</script>
<style scoped>

</style>
