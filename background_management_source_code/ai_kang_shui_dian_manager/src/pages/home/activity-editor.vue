<template>
  <div class="editor-root">
    <toolbar title="活动信息详情" :toolData="toolData" @toolClick="toolClick"></toolbar>
    <detail :baseData="activityData" :dataSource="activityBean" header="基本信息" @change="change"></detail>
    <detail :baseData="activityClassData" :dataSource="activityClassBean" header="活动分类信息" @change="activityClassChange"></detail>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        activityData: [],
        toolData: [{name: '保存'}],
        activityBean: {},
        activityClassData: [],
        activityClassBean: {},
        goodsClassBeans: []
      }
    },
    beforeMount() {
      if (this.$route.params.activity_id != 0) {
        this.post(1, 'homeController.api?getActivityDetail', {activity_id: this.$route.params.activity_id});
      }
      this.post(3, 'goodsController.api?getGoodsClassList', {parent_id: -1, limit: 2000});
      this.activityData = [
        {key: 'activity_name', name: '名称'},
        {key: 'activity_img', name: '展示图片', type: 'img'},
        {key: 'sort', name: '权重'},
        {key: 'create_time', name: '创建时间', type: 'text'}
      ];
      this.activityClassData = [
        {
          key:'class_id0',
          name:'一号分类',
          type:'level',
          lables:'商品分类',
          result:'one',
          childName:'goodsClassBeans',
          selectValue:'class_id',
          showValue:'class_name',
          dataSource:[]
        },
        {
          key:'class_id1',
          name:'二号分类',
          type:'level',
          lables:'商品分类',
          result:'one',
          childName:'goodsClassBeans',
          selectValue:'class_id',
          showValue:'class_name',
          dataSource:[]
        },
        {
          key:'class_id2',
          name:'三号分类',
          type:'level',
          lables:'商品分类',
          result:'one',
          childName:'goodsClassBeans',
          selectValue:'class_id',
          showValue:'class_name',
          dataSource:[]
        },
        {
          key:'class_id3',
          name:'四号分类',
          type:'level',
          lables:'商品分类',
          result:'one',
          childName:'goodsClassBeans',
          selectValue:'class_id',
          showValue:'class_name',
          dataSource:[]
        }
      ]
    },
    methods: {
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.activityBean = data;
            if (!this.isNull(data.goodsClassBeans)) {
              data.goodsClassBeans.forEach((item, index) => {
                this.activityClassData[index].name=item.class_name;
                this.activityClassBean['class_id' + index] = item.class_id;
              });
            }
            break;
          case 2:
            this.showTip('保存成功', 'success');
            this.$router.back();
            break;
          case 3:
            this.activityClassData.forEach((item)=>{
              item.dataSource=data;
            });
            break;
        }
      },
      change(key, value) {
        this.activityBean[key] = value;
      },
      activityClassChange(key, value) {
        this.activityClassBean[key] = value;
      },
      toolClick(index) {
        this.activityBean.class_ids = this.activityClassBean.class_id0 + ',' + this.activityClassBean.class_id1 + ',' + this.activityClassBean.class_id2 + ','
          + this.activityClassBean.class_id3;
        let params={};
        params.activity_id=this.activityBean.activity_id;
        params.activity_name=this.activityBean.activity_name;
        params.activity_img=this.activityBean.activity_img;
        params.sort=this.activityBean.sort;
        params.class_ids=this.activityClassBean.class_id0 + ',' + this.activityClassBean.class_id1 + ',' + this.activityClassBean.class_id2 + ','
          + this.activityClassBean.class_id3;
        switch (index) {
          case 0:
            if (this.$route.params.activity_id == 0) {
              this.post(2, 'homeController.api?insertActivity', params);
            } else {
              this.post(2, 'homeController.api?updateActivity', params);
            }
            break;
        }
      },
    }
  }
</script>
<style scoped>

</style>
