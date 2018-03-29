<template>
  <div specification="specification-root">
    <toolbar :title="title" :toolData="toolData" @toolClick="toolClick"></toolbar>
    <list v-bind="this.$data" :dataSource="specificationBeans" @operationClick="operationClick"  @goPage="goPage"></list>
  </div>
</template>
<script type="text/ecmascript-6">
export default {

  data(){
    return{
      specificationBeans:[],
      baseData:[],
      operationData:[],
      toolData:[{name:'添加'}],
      page:1,
      total:0,
      title:decodeURIComponent(this.$route.params.title),
      level:this.isNull(this.$route.params.level)?1:this.$route.params.level,
      parent_id:this.isNull(this.$route.params.specification_id)?-1:this.$route.params.specification_id
    }
  },
  beforeMount() {
    this.getSpecificationList(1);
    this.baseData = [
      {key: 'specification_id', name: 'ID'},
      {key: 'specification_name', name: '名称'},
      {key: 'sort', name: '权重'},
      {key: 'create_time', name: '创建时间'},
      {name: '操作',type:'operation'},
    ];
    this.operationData=[
      {name:(parseInt(this.level)+1)+'级子规格'},
      {name:'详情'},
      {name:'删除',type:'dialog',message:'确定删除？'}
    ]
  },
  watch:{
    $route(to,from){
      this.parent_id=this.$route.params.specification_id;
      this.level=this.$route.params.level;
      this.operationData[0].hide=this.level==2?true:false;
      this.page=1;
      this.title=decodeURIComponent(this.$route.params.title);
      this.operationData[0].name=(parseInt(this.level)+1)+'级子规格';
      this.getSpecificationList(this.page);
    }
  },
  methods: {
    getSpecificationList(page){
      this.post(1, 'goodsController.api?getSpecificationList',{page:page,parent_id:this.parent_id},true);
    },
    doSuccess(index, data) {
      switch (index) {
        case 1:
          this.specificationBeans = data.data;
          this.total=data.total;
          break;
        case 2:
          this.showTip('删除成功','success');
          this.getSpecificationList(this.page);
          break;
      }
    },
    operationClick(index,rowId){
      switch(index){
        case 0:
          this.$router.push('/specification/'+this.specificationBeans[rowId].specification_id+"/"+(++this.level)+"/"+encodeURIComponent(this.specificationBeans[rowId].specification_name));
          break;
        case 1:
          this.$router.push('/specification_editor/'+this.specificationBeans[rowId].specification_id+'/'+this.parent_id);
          break;
        case 2:
          this.post(2,'goodsController.api?deleteSpecification',{specification_id:this.specificationBeans[rowId].specification_id});

          break;
      }
    },
    toolClick(index){
      switch (index){
        case 0:
          this.$router.push('/specification_editor/'+0+"/"+this.parent_id);
          break;
      }
    },
    goPage(page){
      this.page=page;
      this.getSpecificationList(page);
    }
  }
}
</script>
<style scoped>

</style>
