<template>
  <div class="editor-root">
    <toolbar title="版本控制" :toolData="toolData" @toolClick="toolClick"/>
    <detail :baseData="baseData" :dataSource="versionBean" @change="change"/>
  </div>
</template>
<script type="text/ecmascript-6">

  export default {

    data() {
      return {
        baseData: [],
        versionBean:{},
        params:{},
        toolData:[{name:'保存'}],
      }
    },
    beforeMount() {
      this.getAppVersionDetail();
      this.baseData = [
        {key: 'version_name', name: '名称',type:'textarea'},
        {key: 'update_content', name: '更新内容',type:'textarea'},
        {key: 'download_address', name: '下载地址',type:'textarea'},
        {key: 'server_address', name: '上传安装包',type:'img',path:'/apk'},
        {key: 'server_address', name: '下载地址',type:'textarea',disable:true},
        {key: 'version_no', name: '版本号'},
        {
          key: 'must_update',
          name: '强制更新',
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
        {
          key: 'server_update',
          name: '更新方式',
          selectValue: 'value',
          showValue: 'key',
          type: 'select',
          dataSource: [
            {
              key: '市场更新',
              value: '0'
            },
            {
              key: '服务器更新',
              value: '1'
            }
          ]
        },
        {key: 'update_time', name: '修改时间',disable:true}
      ];
    },
    methods: {
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.versionBean=data;
            break;
          case 2:
            this.showTip('保存成功', 'success');
            this.$router.back();
            break;
        }
      },
      getAppVersionDetail(){
        this.params.version_type=this.$route.params.version_type;
        this.post(1,'settingController.api?getAppVersionDetail',this.params);
      },
      change(key, value) {
        this.versionBean[key]=value;
      },
      toolClick(index) {
        switch(index){
          case 0:
            this.post(2, 'settingController.api?updateAppVersion', this.versionBean);
            break;
        }
      },
    },
    watch:{
      $route(to,from){
        this.getAppVersionDetail();
      }
    }
  }
</script>
<style scoped>

</style>
