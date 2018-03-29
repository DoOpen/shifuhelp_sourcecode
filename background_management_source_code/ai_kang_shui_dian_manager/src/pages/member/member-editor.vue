<template>
  <div class="editor-root">
    <toolbar title="用户信息详情"/>
    <detail :baseData="baseData" :dataSource="memberBean" header="基本信息" @change="change"  :toolData="toolData" @toolClick="toolClick"/>
    <list  :baseData="addressBaseData" :dataSource="memberAddressBeans" header="收货地址" v-if="!isNull(memberAddressBeans)"/>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        baseData: [],
        addressBaseData:[],
        toolData:[{name:'保存'}],
        memberBean: {},
        memberAddressBeans:[]
      }
    },
    beforeMount() {
      if(this.$route.params.member_id!=0){
        this.post(1, 'memberController.api?getMemberDetail', {member_id: this.$route.params.member_id});
        this.post(3, 'memberController.api?getMemberAddressList', {member_id: this.$route.params.member_id});
      }
      this.baseData = [
        {key: 'member_nick_name', name: '昵称'},
        {
          key: 'member_sex',
          name: '性别',
          selectValue: 'value',
          showValue: 'key',
          type: 'select',
          dataSource: [
            {
              key: '男',
              value: '男'
            },
            {
              key: '女',
              value: '女'
            }
          ]
        },
        {key: 'member_head_image', name: '头像', type: 'img',path:'/images/member'},
        {key: 'member_integral', name: '积分',type:'text'},
        {key: 'member_create_time', name: '注册时间', type: 'text'}
      ];
      this.addressBaseData=[
        {key: 'address_mobile', name: '手机'},
        {key: 'address_name', name: '姓名'},
        {key: 'address_province', name: '省'},
        {key: 'address_city', name: '市'},
        {key: 'address_district', name: '区'},
        {key: 'address_detail', name: '详细地址'},
        {key: 'address_zip_code', name: '邮编'}

      ];
    },
    methods: {
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.memberBean = data;
            break;
          case 2:
            this.showTip('保存成功', 'success');
            this.$router.back();
            break;
          case 3:
            this.memberAddressBeans=data;
            break;
        }
      },
      change(key, value) {
        this.memberBean[key] = value;
      },
      toolClick(index) {
        switch (index){
          case 0:
            let params={};
            params.member_id=this.memberBean.member_id;
            params.member_nick_name=this.memberBean.member_nick_name;
            params.member_sex=this.memberBean.member_sex;
            params.member_head_image=this.memberBean.member_head_image;
            this.post(2, 'memberController.api?updateMember', params);
            break;
        }
      },
    }
  }
</script>
<style scoped>

</style>
