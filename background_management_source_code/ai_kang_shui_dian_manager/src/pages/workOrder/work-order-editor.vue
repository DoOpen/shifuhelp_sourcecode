<template>
  <div class="editor-root">
    <toolbar title="工单详情"/>
    <detail :baseData="baseData" :dataSource="orderBean" @change="change"  :toolData="toolData" @toolClick="toolClick"/>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        baseData: [],
        toolData:[{name:'通过'},{name:'拒绝'},{name:'保存'}],
        orderBean: {},
        orderServiceClassBeans:[]
      }
    },
    beforeMount() {
      this.post(1, 'workOrderController.api?getWorkOrderDetail', {order_id: this.$route.params.order_id});
      this.post(4, 'workOrderController.api?getServiceClassList', {level: 5,class_parent_id:-1});
      this.post(3,'settingController.api?getCityListCache');
      this.post(5,'memberController.api?getMemberList',{member_type:1,limit:50000000});
      this.baseData = [
        {key: 'order_name', name: '姓名'},
        {key: 'order_phone', name: '电话'},
        {
          key:'order_class_id',
          name:'服务分类',
          type:'level',
          lables:'一,二,三,四',
          childName:'serviceClassBeans',
          selectValue:'class_id',
          showValue:'class_name',
          dataSource:[]
        },
        {
          key:'address',
          name:'省/市/区',
          type:'level',
          lables:'省,市,区',
          childName:'cityBeans',
          selectValue:'name',
          showValue:'name',
          dataSource:[]
        },
        {key: 'order_address_detail', name: '详细地址'},
        {
          key: 'order_accept_id',
          name: '服务师傅',
          selectValue: 'member_id',
          showValue: 'member_real_name',
          type: 'search',
          dataSource: []
        },
        {key: 'others_service_content', name: '其他服务内容', type: 'textarea',disable:true},
        {key: 'others_price', name: '其他价格', type: 'text'},
        {key: 'order_final_price', name: '最终支付价格', type: 'text'},
        {key: 'order_subscribe_content', name: '服务内容', type: 'text'},
        {key: 'work_area', name: '施工面积', type: 'text'},
        {key: 'work_way', name: '施工方式', type: 'text'},
        {key: 'recommend_phone', name: '推荐人手机号', type: 'text'},
        {key: 'order_reality_content', name: '实际服务内容', type: 'text'},
        {key: 'order_subscribe_note', name: '预约备注', type: 'textarea',disable:true},
        {key: 'order_complete_note', name: '完工备注', type: 'textarea',disable:true},
        {key: 'order_complete_img1', name: '完工图片',type:'img',disable:true},
        {key: 'order_complete_img2', name: '完工图片',type:'img',disable:true},
        {key: 'order_complete_img3', name: '完工图片',type:'img',disable:true},
        {key: 'order_create_time', name: '创建时间', type: 'text'},
        {key: 'order_hope_service_time', name: '期望服务时间', type: 'text'},
        {key: 'order_accept_time', name: '接单时间', type: 'text'},
        {key: 'order_service_time', name: '上门时间', type: 'text'},
        {key: 'order_complete_time', name: '完工时间', type: 'text'}
      ];
    },
    methods: {
      doSuccess(index, data) {
        switch (index) {
          case 1:
            data.address=data.order_address_province+'-'+data.order_address_city+'-'+data.order_address_district;
            this.orderBean = data;
            this.init();
            break;
          case 2:
            this.showTip('保存成功', 'success');
            this.$router.back();
            break;
          case 3:
            this.baseData[3].dataSource=data;
            break;
          case 4:
            this.baseData[2].dataSource=data;
            this.orderServiceClassBeans=data;
            break;
          case 5:
            this.baseData[5].dataSource=data;
            break;
        }
      },
      init(){
        let have=false;
        '0,4,6,7,12'.split(',').forEach(item=>{
          if(item==this.orderBean.order_state){
            have=true;
          }
        });
        this.$set(this.toolData[0],'hide',!have);
        this.$set(this.toolData[1],'hide',!have);
        this.baseData[5].disable=this.orderBean.order_state=='0'?true:false;
        this.$set(this.toolData[2],'hide',this.orderBean.order_state==8||this.orderBean.order_state==9?true:false);
      },
      change(key, value) {
        this.orderBean[key] = value;
        if(key=='address'){
          let address=value.split('-');
          this.orderBean.order_address_province=address[0];
          this.orderBean.order_address_city=address[1];
          this.orderBean.order_address_district=address[2];
        }else if(key=='order_class_id'){
          this.orderBean.order_subscribe_content='';
          let address=value.split('-');
          let temp=this.orderServiceClassBeans;
          address.forEach((item,index)=>{
            temp.forEach((classItem,classIndex)=>{
              if(item==classItem.class_id){
                this.orderBean.order_subscribe_content+=index==0?'':'-';
                this.orderBean.order_subscribe_content+=classItem.class_name;
                temp=classItem.serviceClassBeans;
              }
            })
          })
          this.orderBean.order_class_id=address[address.length];
        }
      },
      toolClick(index) {
        let params={};
        switch (index){
          case 0:
            params.type='pass';
            params.order_id=this.orderBean.order_id;
            params.order_state=this.orderBean.order_state;
            this.post(2, 'workOrderController.api?updateOrderAuditState',params);
            break;
          case 1:
            params.type='not_pass';
            params.order_id=this.orderBean.order_id;
            this.post(2, 'workOrderController.api?updateOrderAuditState',params);
            break;
          case 2:
            params.order_id=this.orderBean.order_id;
            params.order_name=this.orderBean.order_name;
            params.order_phone=this.orderBean.order_phone;
            params.order_subscribe_content=this.orderBean.order_subscribe_content;
            params.order_accept_id=this.orderBean.order_accept_id;
            params.order_address_province=this.orderBean.order_address_province;
            params.order_address_city=this.orderBean.order_address_city;
            params.order_address_district=this.orderBean.order_address_district;
            params.order_address_detail=this.orderBean.order_address_detail;
            params.order_class_id=this.orderBean.order_class_id;
            this.post(2,'workOrderController.api?updateOrder',params);
            break;
        }
      },
    }
  }
</script>
<style scoped>

</style>
