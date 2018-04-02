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
        toolData:[{name:'通过'},{name:'拒绝'},{name:'保存'},{name:'指派'},{name:'抢派'}],
        orderBean: {},
        orderServiceClassBeans:[]
      }
    },
    beforeMount() {
      if(this.$route.params.type=='cancel'){
        this.post(1, 'workOrderController.api?getRefundWorkOrderDetail', {order_id: this.$route.params.order_id});
      }else{
        this.post(1, 'workOrderController.api?getWorkOrderDetail', {order_id: this.$route.params.order_id});
      }
      this.post(4, 'workOrderController.api?getServiceClassList', {level: 5,class_parent_id:-1});
      this.post(3,'settingController.api?getCityListCache');
      this.post(5,'memberController.api?getMemberList',{member_type:1,limit:50000000});
      this.baseData = [
        {key: 'order_name', name: '姓名',disable:true},
        {key: 'order_phone', name: '电话',disable:true},
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
        {key: 'others_service_content', name: '其他服务内容', type: 'textarea'},
        {key: 'others_price', name: '其他价格', validate:'number'},
        {key: 'order_final_price', name: '最终支付价格'},
        {key: 'order_subscribe_content', name: '服务内容',disable:true},
        {key: 'work_area', name: '施工面积', validate:'number'},
        {
          key: 'work_way',
          name: '施工方式',
          selectValue: 'value',
          showValue: 'key',
          type: 'select',
          dataSource: [
            {
              key: '无',
              value: ''
            },
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
        {key: 'recommend_phone', name: '推荐人手机号', type: 'text'},
        {key: 'order_reality_content', name: '实际服务内容'},
        {key: 'order_hope_service_time', name: '期望服务时间'},
        {key: 'order_subscribe_img1', name: '预约图片1',type:'img',path:'/images/workOrder'},
        {key: 'order_subscribe_img2', name: '预约图片2',type:'img',path:'/images/workOrder'},
        {key: 'order_subscribe_img3', name: '预约图片3',type:'img',path:'/images/workOrder'},
        {key: 'order_complete_img1', name: '完工图片1',type:'img',path:'/images/workOrder'},
        {key: 'order_complete_img2', name: '完工图片2',type:'img',path:'/images/workOrder'},
        {key: 'order_complete_img3', name: '完工图片3',type:'img',path:'/images/workOrder'},
        {key: 'order_subscribe_note', name: '预约备注', type: 'textarea'},
        {key: 'order_complete_note', name: '完工备注', type: 'textarea'},
        {key: 'order_cancle_why', name: '退单原因', type: 'textarea'},
        {key: 'order_cancle_time', name: '退单时间', type: 'text'},
        {key: 'order_cancle_pass_time', name: '退单审核通过时间', type: 'text'},
        {key: 'order_evaluate_content', name: '评价内容', type: 'textarea'},
        {key: 'order_service_attitude', name: '服务态度',validate:'int'},
        {key: 'order_service_aging', name: '服务时效' ,validate:'int'},
        {key: 'order_service_quality', name: '服务质量',validate:'int'},
        {key: 'order_create_time', name: '创建时间', type: 'text'},
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
        '4,6,7,12'.split(',').forEach(item=>{
          if(item==this.orderBean.order_state){
            have=true;
          }
        });
        this.$set(this.toolData[0],'hide',!have);
        this.$set(this.toolData[1],'hide',!have);
        this.$set(this.toolData[2],'hide',this.orderBean.order_state==8||this.orderBean.order_state==9?true:false);
        if(this.orderBean.order_state==0){
          this.$set(this.toolData[1],'hide',false);
        }
        if(this.orderBean.order_state==7||this.orderBean.order_state==9){
          this.baseData.forEach((item)=>{
            this.$set(item,'disable',true);
          });
        }
        let flag=true;
        if(this.orderBean.order_state==0){
          flag=false;
        }
        this.$set(this.toolData[3],'hide',flag);
        this.$set(this.toolData[4],'hide',flag);
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
          case 4:
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
            if(!this.validate(this.baseData,this.orderBean)){
              return false;
            }
            if(this.orderBean.others_price<0||this.orderBean.order_final_price<0){
              this.showTip('价格不能小于0');
              return false;
            }
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
            params.others_service_content=this.orderBean.others_service_content;
            params.others_price=this.orderBean.others_price;
            params.order_final_price=this.orderBean.order_final_price;
            params.work_area=this.orderBean.work_area;
            params.work_way=this.orderBean.work_way;
            params.work_requirements=this.orderBean.work_requirements;
            params.order_subscribe_note=this.orderBean.order_subscribe_note;
            params.order_evaluate_content=this.orderBean.order_evaluate_content;
            params.order_complete_note=this.orderBean.order_complete_note;
            this.post(2,'workOrderController.api?updateOrder',params);
            break;
          case 3:
            this.$router.push('/send_work_order/'+this.orderBean.order_id);
            break;
        }
      },
    }
  }
</script>
<style scoped>

</style>
