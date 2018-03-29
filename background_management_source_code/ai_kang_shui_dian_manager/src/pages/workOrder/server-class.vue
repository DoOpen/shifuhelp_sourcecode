<template>
  <div class="class-root">
    <toolbar :title="title" :toolData="toolData" @toolClick="toolClick"/>
    <list v-bind="this.$data" :dataSource="serviceClassBeans" @operationClick="operationClick"  @goPage="goPage"></list>
  </div>
</template>
<script type="text/ecmascript-6">
export default {
  data(){
    return{
      serviceClassBeans:[],
      baseData:[],
      operationData:[],
      toolData:[{name:'添加'}],
      page:1,
      total:0,
      title:decodeURIComponent(this.$route.params.title),
      level:this.isNull(this.$route.params.level)?1:this.$route.params.level,
      class_parent_id:this.isNull(this.$route.params.class_id)?-1:this.$route.params.class_id
    }
  },
  beforeMount() {
    this.getServiceClassList(1);
    this.baseData = [
      {key: 'class_id', name: 'ID'},
      {key: 'class_name', name: '名称',flex:2},
      {key: 'class_img', name: '图标',type:'img',disable:true},
      {key: 'class_price', name: '价格'},
      {key: 'class_unit', name: '单位'},
      {name: '操作',type:'operation',flex:2},
    ];
    this.operationData=[
      {name:(parseInt(this.level)+1)+'级子分类'},
      {name:'详情'},
      {name:'删除',type:'dialog',message:'确定删除？'}
    ]
    this.operationData[0].hide=this.level==3?true:false;
  },
  watch:{
    $route(to,from){
      this.class_parent_id=this.$route.params.class_id;
      this.level=this.$route.params.level;
      this.operationData[0].hide=this.level==4?true:false;
      this.page=1;
      this.title=decodeURIComponent(this.$route.params.title);
      this.operationData[0].name=(parseInt(this.level)+1)+'级子分类';
      this.getServiceClassList(this.page);
    }
  },
  methods: {
    getServiceClassList(page){
      this.post(1, 'workOrderController.api?getServiceClassList',{page:page,class_parent_id:this.class_parent_id},true);
    },
    doSuccess(index, data) {
      switch (index) {
        case 1:
          this.serviceClassBeans = data.data;
          this.total=data.total;
          break;
        case 2:
          this.showTip('删除成功','success');
          this.getServiceClassList(this.page);
          break;
      }
    },
    operationClick(index,rowId){
      switch(index){
        case 0:
          this.$router.push('/service_class/'+this.serviceClassBeans[rowId].class_id+"/"+(++this.level)+"/"+encodeURIComponent(this.serviceClassBeans[rowId].class_name));
          break;
        case 1:
          this.$router.push('/service_class_editor/'+this.serviceClassBeans[rowId].class_id+'/'+this.class_parent_id);
          break;
        case 2:
          this.post(2,'workOrderController.api?deleteServiceClass',{class_id:this.serviceClassBeans[rowId].class_id});

          break;
      }
    },
    toolClick(index){
      switch (index){
        case 0:
          this.$router.push('/service_class_editor/'+0+"/"+this.class_parent_id);
          break;
      }
    },
    goPage(page){
      this.page=page;
      this.getServiceClassList(page);
    }
  }
}
</script>
<style scoped>

</style>
