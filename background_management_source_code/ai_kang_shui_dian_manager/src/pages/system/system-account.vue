<template>
  <div class="root">
    <toolbar title="系统账号列表" :toolData="toolData" @toolClick="toolClick"/>
    <div class="search-content">
      <editor title="ID" filed="account_id" @change="paramsChange"/>
      <editor title="账号" filed="system_account" @change="paramsChange"/>
      <editor title="名称" filed="system_name" @change="paramsChange"/>
      <my-select title="角色" filed="role_id" :dataSource="roleBeans" selectValue="role_id" showValue="role_name" initValue="" @change="paramsChange"/>
      <my-select title="状态" filed="is_disable" :dataSource="stateBeans" selectValue="value" showValue="key" initValue="" @change="paramsChange"/>
    </div>
    <list v-bind="this.$data" :dataSource="systemAccountBeans" @operationClick="operationClick" @goPage="goPage"></list>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {

    data() {
      return {
        baseData: [],
        systemAccountBeans: [],
        roleBeans:[],
        stateBeans:[],
        operationData:[],
        toolData:[{name:'搜索'},{name:'添加'}],
        params:{},
        check:[],
        page:1,
        total:0
      }
    },
    beforeMount() {
      this.getSystemAccountList(1);
      this.post(3,'systemController.api?getSystemRoleList',{limit:1000});
      this.baseData = [
        {key: 'account_id', name: 'ID'},
        {key: 'system_account', name: '账号'},
        {key: 'system_name', name: '名称'},
        {key: 'system_type_show', name: '账号类型'},
        {key: 'role_name', name: '角色名称'},
        {key: 'is_disable_show', name: '状态'},
        {key: 'district', name: '区域'},
        {key: 'system_img', name: '头像',type:'img',disable:true},
        {name: '操作',type:'operation'},
      ];
      this.stateBeans=[
        {
          key: '全部',
          value: ''
        },
        {
          key: '正常',
          value: '0'
        },
        {
          key: '冻结',
          value: '1'
        }
        ]
      this.operationData=[
        {name:'详情'},
        {name:'删除',type:'dialog',message:'确定删除？'}
      ]
    },
    methods: {
      getSystemAccountList(page){
        this.params.page=page;
        this.post(1, 'systemController.api?getSystemAccountList',this.params,true);
      },
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.systemAccountBeans = data.data;
            this.total=data.total;
            break;
          case 2:
            this.showTip('删除成功','success');
            this.getSystemAccountList(this.page);
            break;
          case 3:
            this.roleBeans.push({role_name:'全部'});
            this.roleBeans=this.roleBeans.concat(data);
            break;
        }
      },
      operationClick(index,rowId){
        switch(index){
          case 0:
            this.$router.push('/system_account_editor/'+this.systemAccountBeans[rowId].account_id);
            break;
          case 1:
            this.post(2,'systemController.api?deleteSystemAccount',{account_id:this.systemAccountBeans[rowId].account_id});
            break;
        }
      },
      checked(val){
        this.check=val;
      },
      paramsChange(key,value){
        this.params[key]=value;
      },
      toolClick(index){
        switch (index){
          case 0:
            this.getSystemAccountList(1);
            break;
          case 1:
            this.$router.push('/system_account_editor/'+0);
            break;
        }
      },
      goPage(page){
        this.page=page;
        this.getSystemAccountList(page);
      }
    }
  }
</script>
<style scoped>

</style>
