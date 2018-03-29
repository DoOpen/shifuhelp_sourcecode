<template>
  <div class="root">
    <toolbar title="商品列表" :toolData="toolData" @toolClick="toolClick"/>
    <div class="search-content">
      <editor title="商品名称" filed="goods_name" @change="paramsChange"/>
      <my-select title="展示位置" filed="show_type" :dataSource="showTypeData" selectValue="value" showValue="key" initValue="" @change="paramsChange"/>
      <my-select title="购买方式" filed="buy_type" :dataSource="buyTypeData" selectValue="value" showValue="key" initValue="" @change="paramsChange"/>
      <date-picker title="开始时间" filed="starttime" @change="paramsChange" type="date"/>
      <date-picker title="结束时间" filed="endtime" @change="paramsChange" type="date"/>
    </div>
    <list v-bind="this.$data" :dataSource="goodsBeans" @operationClick="operationClick"  @goPage="goPage"></list>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        baseData: [],
        goodsBeans: [],
        operationData:[],
        toolData:[{name:'搜索'},{name:'添加'}],
        params:{},
        page:1,
        total:0
      }
    },
    beforeMount() {
      this.getGoodsList(1);
      this.baseData = [
        {key: 'goods_id', name: 'ID'},
        {key: 'goods_name', name: '商品名称',flex:2},
        {key: 'class_name', name: '分类',flex:2},
        {key: 'goods_now_price', name: '现价',flex:2},
        {key: 'show_type_show', name: '展示位置'},
        {key: 'buy_type_show', name: '购买方式'},
        {key: 'actual_sales', name: '实际销量'},
        {key: 'goods_stock', name: '库存'},
        {key: 'goods_img', name: '图片',type:'img',disable:true},
        {name: '操作',type:'operation',flex:2},
      ];
      this.operationData=[
        {name:'详情'},
        {name:'删除',type:'dialog',message:'确定删除？'}
      ]
      this.showTypeData=[
        {
          key:'全部',
          value:''
        },
        {
          key:'师傅端',
          value:'app'
        },
        {
          key:'微信端',
          value:'wx'
        }
      ];
      this.buyTypeData=[
        {
          key:'全部',
          value:''
        },
        {
          key:'余额',
          value:'money'
        },
        {
          key:'积分',
          value:'integral'
        }
      ]
    },
    methods: {
      getGoodsList(page){
        this.params.page=page;
        this.params.merchants_id=JSON.parse(sessionStorage['systemAccountBean']).relation_id;
        this.post(1, 'goodsController.api?getGoodsList',this.params,true);

      },
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.goodsBeans = data.data;
            this.total=data.total;
            break;
          case 2:
            this.showTip('删除成功','success');
            this.getGoodsList(this.page);
            break;
        }
      },
      operationClick(index,rowId){
        switch(index){
          case 0:
            this.$router.push('/goods_editor/'+this.goodsBeans[rowId].goods_id);
            break;
          case 1:
            this.post(2,'goodsController.api?deleteGoods',{goods_id:this.goodsBeans[rowId].goods_id});
            break;
        }
      },
      toolClick(index){
        switch (index){
          case 0:
            this.getGoodsList(1);
            break;
          case 1:
            this.$router.push('/goods_editor/'+0);
            break;
        }
      },
      paramsChange(key,value){
        this.params[key]=value;
      },
      goPage(page){
        this.page=page;
        this.getGoodsList(page);
      }
    }
  }
</script>
<style scoped>

</style>
