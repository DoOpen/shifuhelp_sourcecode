<template>
  <div specification="editor-root">
    <toolbar title="商品规格详情" :toolData="toolData" @toolClick="toolClick"></toolbar>
    <detail :baseData="baseData" :dataSource="specificationBean" @change="change"></detail>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        baseData: [],
        specificationBean:{},
        toolData:[{name:'保存'}],
        parent_id:this.$route.params.parent_id
      }
    },
    beforeMount() {
      if(this.$route.params.specification_id!=0){
        this.post(1, 'goodsController.api?getSpecificationDetail', {specification_id: this.$route.params.specification_id});
      }
      this.baseData = [
        {key: 'specification_name', name: '名称'},
        {key: 'sort', name: '权重'},
        {key: 'create_time', name: '创建时间',type:'text'}
      ];
    },
    methods: {
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.specificationBean = data;
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
        this.specificationBean[key]=value;
      },
      toolClick(index) {
        switch(index){
          case 0:
            if (this.isNull(this.specificationBean.specification_id)) {
              this.specificationBean.parent_id=this.parent_id;
              this.post(2, 'goodsController.api?insertSpecification', this.specificationBean);
            } else {
              this.post(3, 'goodsController.api?updateSpecification', this.specificationBean);
            }
            break;
        }
      }
    }
  }
</script>
<style scoped>
</style>
