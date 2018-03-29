<template>
  <div class="root">
    <toolbar title="优惠券列表" :toolData="toolData" @toolClick="toolClick"></toolbar>
    <list v-bind="this.$data" :dataSource="couponBeans" @operationClick="operationClick" @goPage="goPage"></list>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        baseData: [],
        couponBeans: [],
        operationData:[],
        toolData:[{name:'添加'}],
        page:1,
        total:0
      }
    },
    beforeMount() {
      this.getCouponList(1);
      this.baseData = [
        {key: 'coupon_id', name: 'ID'},
        {key: 'coupon_name', name: '名称',flex:2},
        {key: 'coupon_price', name: '优惠金额'},
        {key: 'coupon_full_price', name: '满减金额'},
        {key: 'coupon_type_show', name: '类型'},
        {key: 'coupon_img', name: '图片',type:'img',disable:true},
        {key: 'valid_day', name: '有效天数'},
        {name: '操作',type:'operation',flex:2},
      ];
      this.operationData=[
        {name:'详情'},
        {name:'群发',type:'input',message:'请输入用户ID，英文逗号分隔!'},
        {name:'全发',type:'dialog',message:'确定向所有用户分配该优惠券？'},
        {name:'删除',type:'dialog',message:'确定删除？'}
      ]
    },
    methods: {
      getCouponList(page){
        this.post(1, 'memberController.api?getCouponList',{page:page},true);
      },
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.couponBeans = data.data;
            this.total=data.total;
            break;
          case 2:
            this.showTip('删除成功','success');
            this.getCouponList(this.page);
            break;
          case 4:
            this.showTip('优惠券分配成功','success');
            break;

        }
      },
      operationClick(index,rowId,val){
        switch(index){
          case 0:
            this.$router.push('/coupon_editor/'+this.couponBeans[rowId].coupon_id);
            break;
          case 1:
            this.post(4,'memberController.api?allocationCoupon',{coupon_id:this.couponBeans[rowId].coupon_id,member_ids:val});
            break;
          case 2:
            this.post(4,'memberController.api?allocationAllMemberCoupon',{coupon_id:this.couponBeans[rowId].coupon_id});
            break;
          case 3:
            this.post(2,'memberController.api?deleteCoupon',{coupon_id:this.couponBeans[rowId].coupon_id});
            break;
        }
      },
      toolClick(index){
        switch (index){
          case 0:
            this.$router.push('/coupon_editor/'+0);
            break;
        }
      },
      goPage(page){
        this.page=page;
        this.getCouponList(page);
      }
    }
  }
</script>
<style scoped>

</style>
