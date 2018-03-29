<template>
  <div class="root">
    <toolbar title="活动列表" :toolData="toolData" @toolClick="toolClick"></toolbar>
    <div class="search-content">
      <editor title="活动ID" filed="activity_id" @change="paramsChange"></editor>
      <editor title="活动名称" filed="activity_name" @change="paramsChange"></editor>
    </div>
    <list v-bind="this.$data" :dataSource="activityBeans" @operationClick="operationClick" @goPage="goPage"></list>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        baseData: [],
        activityBeans: [],
        operationData:[],
        toolData:[{name:'搜索'},{name:'添加'}],
        params:{},
        page:1,
        total:0
      }
    },
    beforeMount() {
      this.getActivityList(1);
      this.baseData = [
        {key: 'activity_id', name: 'ID'},
        {key: 'activity_name', name: '名称'},
        {key: 'activity_img', name: '图片',type:'img',disable:true},
        {key: 'sort', name: '权重'},
        {key: 'create_time', name: '创建时间',flex:2},
        {name: '操作',type:'operation'},
      ];
      this.operationData=[
        {name:'详情'},
        {name:'删除',type:'dialog',message:'确定删除？'}
      ]
    },
    methods: {
      getActivityList(page){
        this.params.page=page;
        this.post(1, 'homeController.api?getActivityList',this.params,true);
      },
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.activityBeans = data.data;
            this.total=data.total;
            break;
          case 2:
            this.showTip('删除成功','success');
            this.getActivityList(this.page);
            break;
        }
      },
      operationClick(index,rowId){
        switch(index){
          case 0:
            this.$router.push('/activity_editor/'+this.activityBeans[rowId].activity_id);
            break;
          case 1:
            this.post(2,'homeController.api?deleteActivity',{activity_id:this.activityBeans[rowId].activity_id});
            break;
        }
      },
      paramsChange(key,value){
        this.params[key]=value;
      },
      toolClick(index){
        switch (index){
          case 0:
            this.getActivityList(1);
            break;
          case 1:
            this.$router.push('/activity_editor/'+0);
            break;
        }
      },
      goPage(page){
        this.page=page;
        this.getActivityList(page);
      }
    }
  }
</script>
<style scoped>

</style>
