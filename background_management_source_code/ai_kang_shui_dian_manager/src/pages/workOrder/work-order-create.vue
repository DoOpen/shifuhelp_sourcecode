<template>
  <div class="editor-root">
    <toolbar title="创建工单" :toolData="toolData" @toolClick="toolClick"/>
    <detail :baseData="baseData" :dataSource="orderBean" @change="change"/>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        baseData: [],
        toolData:[{name:'保存'}],
        orderBean: {},
        memberList:[],
        serviceClassBeans:[]
      }
    },
    beforeMount() {
      this.post(2, 'workOrderController.api?getServiceClassList', {level: 5,class_parent_id:-1});
      this.post(3,'settingController.api?getCityListCache');
      this.post(4,'memberController.api?getMemberList',{member_type:0,limit:50000000});
      this.post(5,'memberController.api?getMemberList',{member_type:1,limit:50000000});
      this.baseData = [
        {key: 'order_name', name: '姓名'},
        {
          key: 'order_member_id',
          name: '电话',
          selectValue: 'member_id',
          showValue: 'member_phone',
          type: 'search',
          validate:'null,int',
          dataSource: []
        },
        {
          key:'address',
          name:'省/市/区',
          type:'level',
          lables:'省,市,区',
          childName:'cityBeans',
          selectValue:'name',
          showValue:'name',
          validate:'null',
          dataSource:[]
        },
        {key: 'order_address_detail', name: '详细地址'},
        {
          key: 'order_type',
          name: '工单类型',
          selectValue: 'value',
          showValue: 'key',
          type: 'select',
          dataSource: [
            {
              key: '水电安装',
              value: '0'
            },
            {
              key: '水电维修',
              value: '1'
            }
          ]
        },
        {
          key: 'work_way',
          name: '施工方式',
          selectValue: 'value',
          showValue: 'key',
          type: 'select',
          dataSource: [
            {
              key: '清工',
              value: '清工'
            },
            {
              key: '包工包料',
              value: '包工包料'
            }
          ]
        },
        {key: 'work_area', name: '施工面积'},
        {key: 'work_requirements', name: '施工要求',type:'textarea'},
        {
          key:'order_class_id',
          name:'服务分类',
          type:'level',
          lables:'一,二,三,四',
          result:'many',
          childName:'serviceClassBeans',
          selectValue:'class_id',
          showValue:'class_name',
          validate:'null',
          dataSource:[]
        },
        {
          key: 'order_accept_id',
          name: '服务师傅',
          selectValue: 'member_id',
          showValue: 'member_account',
          type: 'search',
          dataSource: []
        },
        {key: 'order_hope_service_time', name: '期望服务时间', type: 'datetime'},
        {key: 'order_subscribe_note', name: '预约备注', type: 'textarea'},
        {key: 'recommend_phone', name: '推荐人手机'},
      ];
    },
    methods: {
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.showTip('保存成功', 'success');
            this.$router.back();
            break;
          case 2:
            this.baseData[8].dataSource=data;
            this.serviceClassBeans=data;
            break;
          case 3:
            this.baseData[2].dataSource=data;
            break;
          case 4:
            this.baseData[1].dataSource=data;
            this.memberList=data;
            break;
          case 5:
            data.forEach((item)=>{
              item.member_account+=' '+item.member_real_name;
            });
            this.baseData[9].dataSource=data;
          break;
        }
      },
      change(key, value) {
        this.orderBean[key] = value;
        if(key=='address'){
          let address=value.split('-');
          this.orderBean.order_address_province=address[0];
          this.orderBean.order_address_city=address[1];
          this.orderBean.order_address_district=address[2];
        }
      },
      buildClassContent(){
        let class_ids=this.orderBean.order_class_id.split('-');
        let serviceClassBeans=this.serviceClassBeans;
        let content='';
        class_ids.forEach((class_id,index)=>{
          serviceClassBeans.forEach((item)=>{
            if(item.class_id==class_id){
              serviceClassBeans=item.serviceClassBeans;
              content+=item.class_name;
            }
          });
        });
        return content;
      },
      toolClick(index) {
        let params={};
        switch (index){
          case 0:
            if(!this.validate(this.baseData,this.orderBean)){
              return;
            }
            params.order_name=this.orderBean.order_name;
            params.order_member_id=this.orderBean.order_member_id;
            for(let i=0;i<this.memberList.length;i++){
              if(this.memberList[i].member_id==this.orderBean.order_member_id){
                params.order_phone=this.memberList[i].member_phone;
                break;
              }
            }
            params.order_address_province=this.orderBean.order_address_province;
            params.order_address_city=this.orderBean.order_address_city;
            params.order_address_district=this.orderBean.order_address_district;
            params.order_address_detail=this.orderBean.order_address_detail;
            params.order_class_id=this.orderBean.order_class_id.split('-')[this.orderBean.order_class_id.split('-').length-1];
            params.order_subscribe_content=this.buildClassContent()
            params.order_accept_id=this.orderBean.order_accept_id;
            params.order_hope_service_time=this.orderBean.order_hope_service_time;
            params.order_subscribe_note=this.orderBean.order_subscribe_note;
            params.work_requirements=this.orderBean.work_requirements;
            params.work_area=this.orderBean.work_area;
            params.work_way=this.orderBean.work_way;
            params.recommend_phone=this.orderBean.recommend_phone;
            this.post(1,'workOrderController.api?createWorkOrder',params);
            break;
        }
      },
    }
  }
</script>
<style scoped>

</style>
