<template>
  <div class="editor-root">
    <toolbar title="系统账号详情" :toolData="toolData" @toolClick="toolClick"/>
    <detail :baseData="baseData" :dataSource="systemAccountBean" @change="change"/>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {

    data() {
      return {
        baseData: [],
        toolData:[{name:'保存'}],
        systemAccountBean: {}
      }
    },
    beforeMount() {
      if(this.$route.params.account_id!=0){
        this.post(1, 'systemController.api?getSystemAccountDetail', {account_id: this.$route.params.account_id});
      }
      this.post(3,'systemController.api?getSystemRoleList',{limit:1000});
      this.baseData = [
        {key: 'system_account', name: '账号'},
        {key: 'system_name', name: '名称'},
        {key: 'system_old_password', name: '旧密码'},
        {key: 'system_password', name: '新密码'},
        {
          key: 'is_disable',
          name: '状态',
          selectValue: 'value',
          showValue: 'key',
          type: 'select',
          dataSource: [
            {
              key: '正常',
              value: '0'
            },
            {
              key: '冻结',
              value: '1'
            }
          ]
        },
        {
          key: 'system_type',
          name: '账号类型',
          selectValue: 'value',
          showValue: 'key',
          type: 'select',
          disable:'true',
          dataSource: [
            {
              key: '系统账号',
              value: 'system'
            },
            {
              key: '商家账号',
              value: 'merchants'
            }
          ]
        },
        {
          key: 'role_id',
          name: '角色',
          selectValue: 'role_id',
          showValue: 'role_name',
          type: 'select',
          dataSource: []
        },
        {key: 'district', name: '区域'},
        {key: 'system_img', name: '头像', type: 'img'},
        {key: 'create_time', name: '创建时间', type: 'text'}
      ];
      this.init();
    },
    methods: {
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.systemAccountBean = data;
            break;
          case 2:
            this.showTip('保存成功', 'success');
            this.$router.back();
            break;
          case 3:
            this.baseData[6].dataSource=data;
            break;
        }
      },
      init(){
        if(this.$route.params.account_id!=0){
          this.baseData[0].disable=true;
        }else{
          this.baseData[2].hide=true;
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
            if (this.isNull(this.systemAccountBean.account_id)) {
              this.post(2, 'systemController.api?insertSystemAccount', this.systemAccountBean);
            } else {
              this.post(2, 'systemController.api?updateSystemAccount', this.systemAccountBean);
            }
            break;
        }
      },
    }
  }
</script>
<style scoped>

</style>
