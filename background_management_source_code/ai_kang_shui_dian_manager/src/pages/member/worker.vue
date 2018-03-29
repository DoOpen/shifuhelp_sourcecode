<template>
  <div class="root">
    <toolbar title="师傅列表" :toolData="toolData" @toolClick="toolClick"/>
    <div class="search-content">
      <editor title="账号" filed="member_account" @change="paramsChange"/>
      <editor title="姓名" filed="member_real_name" @change="paramsChange"/>
      <my-select title="师傅类型" filed="member_is_star" :dataSource="memberTypeData" selectValue="value" showValue="key" initValue="" @change="paramsChange"/>
      <my-select title="账号状态" filed="is_disable" :dataSource="isDisableData" selectValue="value" showValue="key" initValue="" @change="paramsChange"/>
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
        {key: 'member_sex', name: '性别'},
        {key: 'member_head_image', name: '头像',type:'img',disable:true},
        {key: 'member_integral', name: '积分'},
        {key: 'member_state_show', name: '审核状态'},
        {key: 'is_disable_show', name: '账号状态'},
        {key: 'member_create_time', name: '创建时间'},
        {name: '操作',type:'operation'},
      ];
      this.isDisableData=[
        {
          key: '全部',
          value: ''
        },
        {
          key: '启用',
          value: '0'
        },
        {
          key: '冻结',
          value: '1'
        }
      ];
      this.memberTypeData=[
        {
          key: '全部',
          value: ''
        },
        {
          key: '明星师傅',
          value: '1'
        },
        {
          key: '普通师傅',
          value: '0'
        }
      ];
      this.operationData=[
        {name:'详情'}
      ]
    },
    methods: {
      getMemberList(page){
        this.params.page=page;
        this.params.member_type='1';
        this.params.member_state=this.isNull(this.$route.params.member_state)?"":this.$route.params.member_state;
        this.post(1, 'memberController.api?getMemberList',this.params,true);
      },
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.memberBeans = data.data;
            this.total=data.total;
            break;
        }
      },
      operationClick(index,rowId){
        switch(index){
          case 0:
            this.$router.push('/worker_editor/'+this.memberBeans[rowId].member_id);
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
    },
    watch:{
      $route(to,from){
        this.page=1;
        this.getMemberList(this.page);
      }
    }
  }
</script>
<style scoped>

</style>
