<template>
  <div class="class-root">
    <toolbar :title="title" :toolData="toolData" @toolClick="toolClick"></toolbar>
    <list v-bind="this.$data" :dataSource="cityBeans" @operationClick="operationClick"  @goPage="goPage"></list>
  </div>
</template>
<script type="text/ecmascript-6">
export default {
  data(){
    return{
      cityBeans:[],
      baseData:[],
      operationData:[],
      toolData:[{name:'添加'}],
      params:{},
      page:1,
      total:0,
      title:decodeURIComponent(this.$route.params.title),
      level:this.isNull(this.$route.params.level)?1:this.$route.params.level,
      parent_id:this.isNull(this.$route.params.id)?-1:this.$route.params.id
    }
  },
  beforeMount() {
    this.getCityList(1);
    this.baseData = [
      {key: 'id', name: 'ID'},
      {key: 'name', name: '名称',flex:2},
      {key: 'code', name: '区号',flex:1},
      {key: 'create_time', name: '创建时间'},
      {name: '操作',type:'operation',flex:2},
    ];
    this.operationData=[
      {name:(parseInt(this.level)+1)+'级子分类'},
      {name:'详情'},
      {name:'删除',type:'dialog',message:'确定删除？'}
    ]
    this.operationData[0].hide=this.level==4?true:false;
  },
  watch:{
    $route(to,from){
      this.parent_id=this.$route.params.id;
      this.level=this.$route.params.level;
      this.operationData[0].hide=this.level==3?true:false;
      this.page=1;
      this.title=decodeURIComponent(this.$route.params.title);
      this.operationData[0].name=(parseInt(this.level)+1)+'级子分类';
      this.getCityList(this.page);
    }
  },
  methods: {
    getCityList(page){
      this.params.page=page;
      this.params.parent_id=this.parent_id;
      this.post(1, 'settingController.api?getCityList',this.params,true);
    },
    doSuccess(index, data) {
      switch (index) {
        case 1:
          this.cityBeans = data.data;
          this.total=data.total;
          break;
        case 2:
          this.showTip('删除成功','success');
          this.getCityList(this.page);
          break;
      }
    },
    operationClick(index,rowId){
      switch(index){
        case 0:
          this.$router.push('/city/'+this.cityBeans[rowId].id+"/"+(++this.level)+"/"+encodeURIComponent(this.cityBeans[rowId].name));
          break;
        case 1:
          this.$router.push('/city_editor/'+this.cityBeans[rowId].id+'/'+this.parent_id);
          break;
        case 2:
          this.post(2,'settingController.api?deleteCity',{id:this.cityBeans[rowId].id});

          break;
      }
    },
    toolClick(index){
      switch (index){
        case 0:
          this.$router.push('/city_editor/'+0+"/"+this.parent_id);
          break;
      }
    },
    goPage(page){
      this.page=page;
      this.getCityList(page);
    }
  }
}
</script>
<style scoped>

</style>
