<template>
  <div class="root">
    <toolbar title="用户列表" :toolData="toolData" @toolClick="toolClick"/>
    <div class="search-content">
      <editor title="昵称" filed="member_nick_name" @change="paramsChange"/>
      <editor title="手机号" filed="member_phone" @change="paramsChange"/>
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
        {key: 'member_id', name: '用户ID'},
        {key: 'member_nick_name', name: '昵称'},
        {key: 'member_phone', name: '手机号'},
        {key: 'member_head_image', name: '头像',type:'img',disable:true},
        {key: 'member_integral', name: '积分'},
        {key: 'member_create_time', name: '创建时间'},
        {name: '操作',type:'operation'},
      ];
      this.operationData=[
        {name:'详情'}
      ]
    },
    methods: {
      getMemberList(page){
        this.params.page=page;
        this.params.member_type='0';
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
            this.$router.push('/member_editor/'+this.memberBeans[rowId].member_id);
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
