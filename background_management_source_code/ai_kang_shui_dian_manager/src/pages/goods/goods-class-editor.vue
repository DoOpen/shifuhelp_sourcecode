<template>
  <div class="editor-root">
    <toolbar title="商品分类详情" :toolData="toolData" @toolClick="toolClick"></toolbar>
    <detail :baseData="baseData" :dataSource="goodsClassBean" @change="change"></detail>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        baseData: [],
        goodsClassBean:{},
        toolData:[{name:'保存'}],
        parent_id:this.$route.params.parent_id
      }
    },
    beforeMount() {
      if(this.$route.params.class_id!=0){
        this.post(1, 'goodsController.api?getGoodsClassDetail', {class_id: this.$route.params.class_id});
      }
      this.baseData = [
        {key: 'class_name', name: '名称'},
        {key: 'class_img', name: '图标',type:'img',path:'/images/goods'},
        {
          key: 'is_recommend',
          name: '首页推荐',
          selectValue: 'value',
          showValue: 'key',
          type: 'select',
          dataSource: [
            {
              key: '否',
              value: '0'
            },
            {
              key: '是',
              value: '1'
            }
          ]
        },
        {key: 'sort', name: '权重'},
        {key: 'class_desc', name: '介绍'}
      ];
    },
    methods: {
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.goodsClassBean = data;
            break;
          case 2:
            this.showTip('保存成功', 'success');
            this.$router.back();
            break;
          case 3:
            this.showTip('保存成功', 'success');
            this.$router.back();
            break;
        }
      },
      change(key, value) {
        this.goodsClassBean[key]=value;
      },
      toolClick(index) {
        switch(index){
          case 0:
            if (this.isNull(this.goodsClassBean.class_id)) {
              this.goodsClassBean.parent_id=this.parent_id;
              this.post(2, 'goodsController.api?insertGoodsClass', this.goodsClassBean);
            } else {
              this.post(3, 'goodsController.api?updateGoodsClass', this.goodsClassBean);
            }
            break;
        }
      }
    }
  }
</script>
<style scoped>
</style>
