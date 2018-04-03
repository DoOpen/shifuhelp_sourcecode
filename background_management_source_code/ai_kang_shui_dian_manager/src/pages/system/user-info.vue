<template>
  <div class="editor-root">
    <toolbar title="个人信息" :toolData="toolData" @toolClick="toolClick"/>
    <detail :baseData="baseData" :dataSource="systemAccountBean" @change="change"/>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        baseData: [],
        toolData:[{name:'保存'}],
        systemAccountBean: {},
      }
    },
    beforeMount() {
      this.systemAccountBean=JSON.parse(sessionStorage['systemAccountBean']);
      this.baseData = [
        {key: 'system_account', name: '账号',type:'text'},
        {key: 'system_name', name: '名称'},
        {key: 'system_type_show', name: '账号类型',type:'text'},
        {key: 'role_name', name: '角色',type:'text'},
        {key: 'system_img', name: '头像', type: 'img'},
        {key: 'create_time', name: '创建时间', type: 'text'},
        {key: 'system_old_password', name: '原密码'},
        {key: 'system_password', name: '新密码'}
      ];
    },
    methods: {
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.showTip('保存成功', 'success');
            sessionStorage['systemAccountBean']=JSON.stringify(data);
            this.$router.back();
            break;
          case 2:
            this.baseData[8].dataSource=data;
            break;
        }
      },
      change(key, value) {
        this.systemAccountBean[key] = value;
      },
      toolClick(index) {
        switch (index){
          case 0:
            if(!this.isNull(this.systemAccountBean.system_password)){
              if(!/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{1,50}$/.test(this.systemAccountBean.system_password)){
                this.showTip('新密码必须字母加数字组合');
                return;
              }
            }
            this.post(1, 'systemController.api?updateSystemAccount', this.systemAccountBean);
            break;
        }
      },
    }
  }
</script>
<style scoped>

</style>
