<template>
  <div class="class-root">
    <toolbar :title="'线下商品分类'" :toolData="toolData" @toolClick="toolClick"></toolbar>
    <div class="search-content">
      <editor title="ID" filed="class_id" @change="paramsChange"></editor>
      <editor title="商家ID" filed="merchants_id" @change="paramsChange" :hide="hide"></editor>
    </div>
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
      params:{},
      toolData:[{name: '搜索'},{name:'添加'}],
      hide:false,
      page:1,
      total:0
    }
  },
  beforeMount() {
    this.getGoodsClassList(1);
    this.baseData = [
      {key: 'class_id', name: 'ID'},
      {key: 'class_name', name: '名称',flex:2},
      {key: 'merchants_id', name: '商家ID'},
      {key: 'class_img', name: '图片',type:'img',disable:true},
      {key: 'sort', name: '权重'},
      {key: 'create_time', name: '创建时间',flex:2},
      {name: '操作',type:'operation',flex:2},
    ];
    this.operationData=[
      {name:'详情'},
      {name:'删除',type:'dialog',message:'确定删除？'}
    ]
  },
  methods: {
    getGoodsClassList(page){
      if (!this.isNull(sessionStorage.systemAccountBean)) {
        let systemAccountBean = JSON.parse(sessionStorage.systemAccountBean);
        if(systemAccountBean.system_type=='merchants'){
          this.params.merchants_id=systemAccountBean.relation_id;
          this.hide=true;
        }
      }
      this.params.page=this.page;
      this.params.class_type='not_online';
      this.post(1, 'goodsController.api?getGoodsClassList',this.params,true);
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
          this.$router.push('/inline_goods_class_editor/'+this.goodsClassBeans[rowId].class_id);
          break;
        case 1:
          this.post(2,'goodsController.api?deleteGoodsClass',{class_id:this.goodsClassBeans[rowId].class_id});

          break;
      }
    },
    paramsChange(key, value) {
      this.params[key] = value;
    },
    toolClick(index){
      switch (index){
        case 0:
          this.getGoodsClassList(1);
          break;
        case 1:
          this.$router.push('/inline_goods_class_editor/'+0);
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
