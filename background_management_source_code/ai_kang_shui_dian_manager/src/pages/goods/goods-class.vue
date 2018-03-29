<template>
  <div class="class-root">
    <toolbar :title="title" :toolData="toolData" @toolClick="toolClick"></toolbar>
    <list v-bind="this.$data" :dataSource="goodsClassBeans" @operationClick="operationClick"  @goPage="goPage"></list>
  </div>
</template>
<script type="text/ecmascript-6">
export default {
  data(){
    return{
      goodsClassBeans:[],
      baseData:[],
      operationData:[],
      toolData:[{name:'添加'}],
      page:1,
      total:0,
      title:decodeURIComponent(this.$route.params.title),
      level:this.isNull(this.$route.params.level)?1:this.$route.params.level,
      parent_id:this.isNull(this.$route.params.class_id)?-1:this.$route.params.class_id
    }
  },
  beforeMount() {
    this.getGoodsClassList(1);
    this.baseData = [
      {key: 'class_id', name: 'ID'},
      {key: 'class_name', name: '名称',flex:2},
      {key: 'class_img', name: '图片',type:'img',disable:true},
      {key: 'sort', name: '权重'},
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
      this.parent_id=this.$route.params.class_id;
      this.level=this.$route.params.level;
      this.operationData[0].hide=this.level==2?true:false;
      this.page=1;
      this.title=decodeURIComponent(this.$route.params.title);
      this.operationData[0].name=(parseInt(this.level)+1)+'级子分类';
      this.getGoodsClassList(this.page);
    }
  },
  methods: {
    getGoodsClassList(page){
      this.post(1, 'goodsController.api?getGoodsClassList',{page:page,parent_id:this.parent_id,class_type:'online'},true);
    },
    doSuccess(index, data) {
      switch (index) {
        case 1:
          this.goodsClassBeans = data.data;
          this.total=data.total;
          break;
        case 2:
          this.showTip('删除成功','success');
          this.getGoodsClassList(this.page);
          break;
      }
    },
    operationClick(index,rowId){
      switch(index){
        case 0:
          this.$router.push('/goods_class/'+this.goodsClassBeans[rowId].class_id+"/"+(++this.level)+"/"+encodeURIComponent(this.goodsClassBeans[rowId].class_name));
          break;
        case 1:
          this.$router.push('/goods_class_editor/'+this.goodsClassBeans[rowId].class_id+'/'+this.parent_id);
          break;
        case 2:
          this.post(2,'goodsController.api?deleteGoodsClass',{class_id:this.goodsClassBeans[rowId].class_id});

          break;
      }
    },
    checked(val){
      val.forEach((item,index)=>{
        this.check[index]=this.goodsClassBeans[index].class_id;
      })
    },
    toolClick(index){
      switch (index){
        case 0:
          this.$router.push('/goods_class_editor/'+0+"/"+this.parent_id);
          break;
      }
    },
    goPage(page){
      this.page=page;
      this.getGoodsClassList(page);
    }
  }
}
</script>
<style scoped>

</style>
