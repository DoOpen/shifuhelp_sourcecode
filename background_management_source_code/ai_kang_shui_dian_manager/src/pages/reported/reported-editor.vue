<template>
  <div class="editor-root">
    <toolbar title="报备信息详情"/>
    <detail :baseData="baseData" :dataSource="reportedBean" @change="change"  :toolData="toolData" @toolClick="toolClick"/>
  </div>
</template>
<script type="text/ecmascript-6">

  export default {

    data() {
      return {
        baseData: [],
        reportedBean:JSON.parse(decodeURIComponent(this.$route.params.reportedBean)),
        toolData:[{name:'保存'}],
      }
    },
    beforeMount() {
      this.baseData = [
        {key: 'member_account', name: '师傅账号',disable:true},
        {key: 'member_real_name', name: '师傅姓名',disable:true},
        {key: 'reported_name', name: '报备人姓名',disable:true},
        {key: 'reported_phone', name: '报备人账号',disable:true},
        {key: 'province', name: '省',disable:true},
        {key: 'city', name: '市',disable:true},
        {key: 'district', name: '区',disable:true},
        {key: 'detail', name: '详情地址',disable:true},
        {key: 'create_time', name: '报备时间',disable:true},
        {key: 'reported_img1', name: '报备图片1',type:'img',disable:true},
        {key: 'reported_img2', name: '报备图片2',type:'img',disable:true},
        {key: 'reported_img3', name: '报备图片3',type:'img',disable:true},
        {
          key: 'reported_state',
          name: '审核状态',
          selectValue: 'value',
          showValue: 'key',
          type: 'select',
          dataSource: [
            {
              key: '待审核',
              value: 'wait_audit'
            },
            {
              key: '已受理',
              value: 'accept'
            },
            {
              key: '成功',
              value: 'success'
            },
            {
              key: '拒绝',
              value: 'refuse'
            }
          ]
        },
        {key: 'refuse_note', name: '拒绝原因',type:'textarea'},
      ];
      this.baseData[12].disable=this.reportedBean.reported_state=='success'?true:false;
      this.init();
    },
    methods: {
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.showTip('保存成功', 'success');
            this.$router.back();
            break;
        }
      },
      init(){
        this.baseData[13].hide=this.reportedBean.reported_state=='refuse'?false:true;
      },
      change(key, value) {
        this.reportedBean[key]=value;
        if(key=='reported_state'){
          this.init();
        }
      },
      toolClick(index) {
        switch(index){
          case 0:
            this.post(1, 'memberController.api?updateReported', this.reportedBean);
            break;
        }
      },
    }
  }
</script>
<style scoped>

</style>
