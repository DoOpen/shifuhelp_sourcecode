<template>
  <div class="editor-root">
    <toolbar title="工单服务分类详情" :toolData="toolData" @toolClick="toolClick"/>
    <detail :baseData="baseData" :dataSource="serviceClassBean" @change="change"/>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        baseData: [],
        serviceClassBean:{},
        toolData:[{name:'保存'}],
        class_parent_id:this.$route.params.class_parent_id
      }
    },
    beforeMount() {
      if(this.$route.params.class_id!=0){
        this.post(1, 'workOrderController.api?getServiceClassDetail', {class_id: this.$route.params.class_id});
      }
      this.baseData = [
        {key: 'class_name', name: '名称'},
        {key: 'class_img', name: '图标',type:'img',path:'/images/service'},
        {key: 'class_price', name: '价格'},
        {key: 'class_unit', name: '单位'},
        {key: 'class_desc', name: '简介',type:'textarea'},
      ];
    },
    methods: {
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.serviceClassBean = data;
            break;
          case 2:
            this.showTip('保存成功', 'success');
            this.$router.back();
            break;
        }
      },
      change(key, value) {
        this.serviceClassBean[key]=value;
      },
      toolClick(index) {
        switch(index){
          case 0:
            if (this.isNull(this.serviceClassBean.class_id)) {
              this.serviceClassBean.class_parent_id=this.class_parent_id;
              this.post(2, 'workOrderController.api?insertServiceClass', this.serviceClassBean);
            } else {
              this.post(2, 'workOrderController.api?updateServiceClass', this.serviceClassBean);
            }
            break;
        }
      }
    }
  }
</script>
<style scoped>
</style>
