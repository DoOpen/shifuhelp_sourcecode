<template>
  <div class="module-root">
    <toolbar :title="title" :toolData="toolData" @toolClick="toolClick"></toolbar>
    <list v-bind="this.$data" :dataSource="moduleBeans" @operationClick="operationClick"  @goPage="goPage"></list>
  </div>
</template>
<script type="text/ecmascript-6">
export default {

  data(){
    return{
      moduleBeans:[],
      baseData:[],
      operationData:[],
      toolData:[{name:'添加'}],
      page:1,
      total:0,
      title:decodeURIComponent(this.$route.params.title),
      level:this.isNull(this.$route.params.level)?1:this.$route.params.level,
      parent_id:this.isNull(this.$route.params.module_id)?-1:this.$route.params.module_id
    }
  },
  beforeMount() {
    this.getSystemModuleList(1);
    this.baseData = [
      {key: 'module_id', name: 'ID'},
      {key: 'module_name', name: '名称'},
      {key: 'sort', name: '权重'},
      {key: 'create_time', name: '创建时间'},
      {name: '操作',type:'operation'},
    ];
    this.operationData=[
      {name:(parseInt(this.level)+1)+'级子模块'},
      {name:'详情'},
      {name:'删除',type:'dialog',message:'确定删除？'}
    ]
    this.operationData[0].hide=this.level==2?true:false;
  },
  watch:{
    $route(to,from){
      this.parent_id=this.$route.params.module_id;
      this.level=this.$route.params.level;
      this.operationData[0].hide=this.level==2?true:false;
      this.page=1;
      this.title=decodeURIComponent(this.$route.params.title);
      this.operationData[0].name=(parseInt(this.level)+1)+'级子模块';
      this.getSystemModuleList(this.page);
    }
  },
  methods: {
    getSystemModuleList(page){
      this.post(1, 'systemController.api?getSystemModuleList',{page:page,parent_id:this.parent_id},true);
    },
    doSuccess(index, data) {
      switch (index) {
        case 1:
          this.moduleBeans = data.data;
          this.total=data.total;
          break;
        case 2:
          this.showTip('删除成功','success');
          this.getSystemModuleList(this.page);
          break;
      }
    },
    operationClick(index,rowId){
      switch(index){
        case 0:
          this.$router.push('/module/'+this.moduleBeans[rowId].module_id+"/"+(++this.level)+"/"+encodeURIComponent(this.moduleBeans[rowId].module_name));
          break;
        case 1:
          this.$router.push('/module_editor/'+this.moduleBeans[rowId].module_id+'/'+this.parent_id);
          break;
        case 2:
          this.post(2,'systemController.api?deleteSystemModule',{module_id:this.moduleBeans[rowId].module_id});

          break;
      }
    },
    checked(val){
      val.forEach((item,index)=>{
        this.check[index]=this.moduleBeans[index].module_id;
      })
    },
    toolClick(index){
      switch (index){
        case 0:
          this.$router.push('/module_editor/'+0+"/"+this.parent_id);
          break;
      }
    },
    goPage(page){
      this.page=page;
      this.getSystemModuleList(page);
    }
  }
}
</script>
<style scoped>

</style>
