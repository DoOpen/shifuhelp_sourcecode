<template>
  <div class="editor-root">
    <toolbar title="师傅信息详情" :toolData="toolData" @toolClick="toolClick"/>
    <detail :baseData="baseData" :dataSource="memberBean" header="基本信息" @change="change"/>
    <wang-editor v-if="memberBean.member_is_star==1" :url="memberBean.star_worker_info" filed="star_worker_info_content" header="师傅风采" path="/images/member" @change="change"/>
    <wang-editor :url="memberBean.member_certificate" filed="member_certificate_content" header="师傅证书" path="/images/member" @change="change"/>
    <wang-editor :url="memberBean.process_show" filed="process_show_content" header="工艺展示" path="/images/member" @change="change"/>
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
        cityBeans:[],
        memberAddressBeans:[]
      }
    },
    beforeMount() {
      if(this.$route.params.member_id!=0){
        this.post(1, 'memberController.api?getMemberDetail', {member_id: this.$route.params.member_id});
        this.post(3, 'memberController.api?getMemberAddressList', {member_id: this.$route.params.member_id});
        this.post(4,'settingController.api?getCityListCache');
      }
      this.baseData = [
        {
          key: 'is_disable',
          name: '是否禁用',
          selectValue: 'value',
          showValue: 'key',
          type: 'select',
          dataSource: [
            {
              key: '启用',
              value: '0'
            },
            {
              key: '禁用',
              value: '1'
            }
          ]
        },
        {key: 'disable_note', name: '禁用原因',type:'textarea'},
        {key: 'member_account', name: '账号',type:'text'},
        {key: 'member_real_name', name: '姓名'},
        {key: 'member_head_image', name: '头像', type: 'img',path:'/images/member'},
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
        {
          key: 'member_is_star',
          name: '明星师傅',
          selectValue: 'value',
          showValue: 'key',
          type: 'select',
          dataSource: [
            {
              key: '是',
              value: '1'
            },
            {
              key: '否',
              value: '0'
            }
          ]
        },
        {
          key:'address',
          name:'服务地址',
          type:'level',
          lables:'省,市,区',
          childName:'cityBeans',
          selectValue:'name',
          showValue:'name',
          dataSource:[]
        },
        {key: 'member_service_detail', name: '详细地址'},
        {key: 'member_service_name', name: '服务姓名'},
        {key: 'member_service_phone', name: '服务电话'},
        {key: 'member_integral', name: '积分',type:'text'},
        {key: 'member_freeze_money', name: '冻结金额',type:'text'},
        {key: 'member_extract_money', name: '可提现',type:'text'},
        {key: 'member_deposit_money', name: '押金',type:'text'},
        {key: 'member_age', name: '年龄',type:'text'},
        {key: 'work_type', name: '工种',type:'text'},
        {key: 'member_work_age', name: '工龄',type:'text'},
        {key: 'id_number', name: '身份证号',type:'text'},
        {key: 'special_skill', name: '特殊技能',type:'textarea',disable:true},
        {key: 'member_good_rate', name: '好评率',type:'text'},
        {key: 'member_service_number', name: '服务次数',type:'text'},
        {key: 'assessment_star1', name: '服务满意度',type:'text'},
        {key: 'assessment_star2', name: '速度满意度',type:'text'},
        {key: 'assessment_star3', name: '做工满意度',type:'text'},
        {key: 'member_create_time', name: '注册时间', type: 'text'},
        {key: 'audit_pass_time', name: '审核时间', type: 'text'},
        {
          key: 'member_state',
          name: '审核状态',
          selectValue: 'value',
          showValue: 'key',
          type: 'select',
          dataSource: [
            {
              key: '待审核',
              value: '-1'
            },
            {
              key: '审核中',
              value: '0'
            },
            {
              key: '通过',
              value: '1'
            },
            {
              key: '拒绝',
              value: '2'
            }
          ]
        },
        {key: 'custome_refuse_note', name: '拒绝原因',type:'textarea'},
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
            this.baseData[27].disable=this.memberBean.member_state==1?true:false;
            this.memberBean.address=this.memberBean.member_service_province+this.memberBean.member_service_city+this.memberBean.member_service_district;
            this.init();
            break;
          case 2:
            this.showTip('保存成功', 'success');
            this.$router.back();
            break;
          case 3:
            this.memberAddressBeans=data;
            break;
          case 4:
            this.baseData[7].dataSource=data;
            break;
        }
      },
      change(key, value) {
        this.memberBean[key] = value;
        if(key=='address'){
          let address=value.split('-');
          this.memberBean.member_service_province=address[0];
          this.memberBean.member_service_city=address[1];
          this.memberBean.member_service_district=address[2];
        }
        this.init();
      },
      init(){
        this.baseData[28].hide=this.memberBean.member_state==2?false:true;
        this.baseData[1].hide=this.memberBean.is_disable==1?false:true;
      },
      toolClick(index) {
        switch (index){
          case 0:
            this.memberBean.memberAddressBeans=[];
            let params={};
            params.member_id=this.memberBean.member_id;
            params.member_real_name=this.memberBean.member_real_name;
            params.member_sex=this.memberBean.member_sex;
            params.member_state=this.memberBean.member_state;
            params.member_head_image=this.memberBean.member_head_image;
            params.custome_refuse_note=this.memberBean.custome_refuse_note;
            params.is_disable=this.memberBean.is_disable;
            params.disable_note=this.memberBean.disable_note;
            params.member_service_province=this.memberBean.member_service_province;
            params.member_service_city=this.memberBean.member_service_city;
            params.member_service_district=this.memberBean.member_service_district;
            params.member_service_detail=this.memberBean.member_service_detail;
            params.member_service_name=this.memberBean.member_service_name;
            params.member_service_phone=this.memberBean.member_service_phone;
            params.star_worker_info_content=this.memberBean.star_worker_info_content;
            params.member_certificate_content=this.memberBean.member_certificate_content;
            params.member_certificate_content=this.memberBean.member_certificate_content;
            params.process_show_content=this.memberBean.process_show_content;
            params.star_worker_info=this.memberBean.star_worker_info;
            params.member_certificate=this.memberBean.member_certificate;
            params.member_is_star=this.memberBean.member_is_star;
            this.post(2, 'memberController.api?updateMember', params);
            break;
        }
      },
    }
  }
</script>
<style scoped>

</style>
