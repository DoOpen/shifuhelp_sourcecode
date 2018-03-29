<template>
  <div class="banner-root">
    <toolbar title="广告列表" :toolData="toolData" @toolClick="toolClick"></toolbar>
    <div class="search-content">
      <editor title="广告ID" filed="banner_id" @change="paramsChange"></editor>
      <editor title="标题" filed="banner_title" @change="paramsChange"></editor>
      <my-select title="广告类型" filed="banner_type" :dataSource="bannerTypeData" selectValue="value" showValue="key" initValue="" @change="paramsChange"></my-select>
      <my-select title="展示位置" filed="banner_position" :dataSource="bannerPositionData" selectValue="value" showValue="key" initValue="" @change="paramsChange"></my-select>
    </div>
    <list v-bind="this.$data" :dataSource="bannerBeans" @operationClick="operationClick"  @goPage="goPage"></list>
  </div>
</template>
<script type="text/ecmascript-6">
export default {
  data(){
    return{
      bannerBeans:[],
      baseData:[],
      operationData:[],
      toolData:[{name:'搜索'},{name:'添加'}],
      params:{},
      page:1,
      total:0,
    }
  },
  beforeMount() {
    this.getBannerList(1);
    this.baseData = [
      {key: 'banner_id', name: 'ID'},
      {key: 'banner_title', name: '标题',flex:2},
      {key: 'banner_img', name: '图片',type:'img',disable:true},
      {key: 'sort',name:'权重'},
      {key: 'banner_type_show', name: '广告类型'},
      {key: 'banner_position_show', name: '展示位置'},
      {key: 'create_time', name: '创建时间',flex:2},
      {name: '操作',type:'operation'},
    ];
    this.operationData=[
      {name:'详情'},
      {name:'删除',type:'dialog',message:'确定删除？'}
    ]
    this.bannerTypeData=[
      {
        key: '全部',
        value: ''
      },
      {
        key: '普通广告',
        value: 'common'
      },
      {
        key: '商品广告',
        value: 'goods'
      },
      {
        key: '外链广告',
        value: 'chain'
      }
    ]
    this.bannerPositionData=[
      {
        key: '全部',
        value: ''
      },
      {
        key: '所有主页',
        value: 'home'
      },
      {
        key: '微信主页',
        value: 'wechat_home'
      },
      {
        key: 'app主页',
        value: 'app_home'
      },
      {
        key: 'pc主页',
        value: 'pc_home'
      }
    ]
  },
  methods: {
    getBannerList(page){
      this.params.page=page;
      this.post(1, 'settingController.api?getBannerList',this.params,true);
    },
    doSuccess(index, data) {
      switch (index) {
        case 1:
          this.bannerBeans = data.data;
          this.total=data.total;
          break;
        case 2:
          this.showTip('删除成功','success');
          this.getBannerList(this.page);
          break;
      }
    },
    operationClick(index,rowId){
      switch(index){
        case 0:
          this.$router.push('/banner_editor/'+this.bannerBeans[rowId].banner_id);
          break;
        case 1:
          this.post(2,'settingController.api?deleteBanner',{banner_id:this.bannerBeans[rowId].banner_id});

          break;
      }
    },
    toolClick(index){
      switch (index){
        case 0:
          this.getBannerList(1);
          break;
        case 1:
          this.$router.push('/banner_editor/'+0);
          break;
      }
    },
    paramsChange(key,value){
      this.params[key]=value;
    },
    goPage(page){
      this.page=page;
      this.getBannerList(page);
    }
  }
}
</script>
<style scoped>

</style>
