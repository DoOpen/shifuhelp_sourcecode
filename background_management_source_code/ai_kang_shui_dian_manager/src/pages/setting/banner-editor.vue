<template>
  <div class="editor-root">
    <toolbar title="广告详情" :toolData="toolData" @toolClick="toolClick"></toolbar>
    <detail :baseData="baseData" :dataSource="bannerBean" header="基本信息" @change="change"></detail>
    <wang-editor :url="bannerBean.banner_url" header="广告内容" filed="banner_url_content" @change="change" v-if="bannerBean.banner_type=='common'"></wang-editor>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        baseData: [],
        toolData:[{name:'保存'}],
        bannerBean: {}
      }
    },
    beforeMount() {
      if(this.$route.params.banner_id!=0){
        this.post(1, 'settingController.api?getBannerDetail', {banner_id: this.$route.params.banner_id});
      }
      this.post(3, 'goodsController.api?getGoodsList', {
        goods_name: this.bannerBean.goods_id == undefined ? '' : this.bannerBean.goods_id,
        limit: 20000
      });
      this.baseData = [
        {key: 'banner_title', name: '标题'},
        {key: 'banner_img', name: '图片',type:'img',path:'/images/banner'},
        {key: 'banner_desc', name: '简介',type:'textarea'},
        {key: 'sort', name: '权重'},
        {
          key: 'banner_type',
          name: '广告类型',
          selectValue: 'value',
          showValue: 'key',
          type: 'select',
          dataSource: [
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
        },
        {
          key: 'goods_id',
          name: '商品ID',
          selectValue: 'goods_id',
          showValue: 'goods_name',
          type: 'search',
          dataSource: []
        },
        {key: 'chain_url', name: '外链地址'},
        {
          key: 'banner_position',
          name: '展示位置',
          selectValue: 'value',
          showValue: 'key',
          type: 'select',
          dataSource: [
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
        {key: 'create_time', name: '创建时间',type:'text'},
      ];
    },
    methods: {
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.bannerBean = data;
            this.init();
            break;
          case 2:
            this.showTip('保存成功', 'success');
            this.$router.back();
            break;
          case 3:
            this.baseData[5].dataSource = data;
            break;
        }
      },
      init(){
        this.baseData[5].hide=this.bannerBean.banner_type=='goods'?false:true;
        this.baseData[6].hide=this.bannerBean.banner_type=='chain'?false:true;
      },
      change(key, value) {
        this.bannerBean[key] = value;
        if(key=='banner_type'){
          this.init();
        }
      },
      toolClick(index) {
        switch (index){
          case 0:
            if (this.isNull(this.bannerBean.banner_id)) {
              this.post(2, 'settingController.api?insertBanner', this.bannerBean);
            } else {
              this.post(2, 'settingController.api?updateBanner', this.bannerBean);
            }
            break;
        }
      },
    }
  }
</script>
<style scoped>

</style>
