<template>
  <div class="editor-root">
    <toolbar title="优惠券信息详情" :toolData="toolData" @toolClick="toolClick"></toolbar>
    <detail :baseData="baseData" :dataSource="couponBean" @change="change"></detail>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        baseData: [],
        toolData:[{name:'保存'}],
        couponBean: {}
      }
    },
    beforeMount() {
      if(this.$route.params.coupon_id!=0){
        this.post(1, 'memberController.api?getCouponDetail', {coupon_id: this.$route.params.coupon_id});
      }
      this.post(3, 'goodsController.api?getGoodsList', {
        goods_name: this.couponBean.goods_id == undefined ? '' : this.couponBean.goods_id,
        limit: 2000
      });
      this.post(4, 'goodsController.api?getGoodsClassList', {
        class_name: this.couponBean.class_id == undefined ? '' : this.couponBean.class_id,
        limit: 2000
      });
      this.post(5, 'merchantsController.api?getMerchantsList', {
        merchants_name: this.couponBean.merchants_id == undefined ? '' : this.couponBean.merchants_id,
        limit: 2000
      });

      this.baseData = [
        {key: 'coupon_name', name: '名称'},
        {key: 'coupon_price', name: '优惠金额'},
        {key: 'coupon_full_price', name: '满减金额'},
        {key: 'coupon_desc', name: '简介',type:'textarea'},
        {key: 'start_time', name: '开始时间',type:'datetime'},
        {key: 'end_time', name: '结束时间',type:'datetime'},
        {key: 'valid_day', name: '有效天数'},
        {key: 'coupon_img', name: '图片', type: 'img'},
        {
          key: 'coupon_type',
          name: '类型',
          selectValue: 'value',
          showValue: 'key',
          type: 'select',
          dataSource: [
            {
              key: '商品',
              value: 'goods'
            },
            {
              key: '商家',
              value: 'merchants'
            },
            {
              key: '分类',
              value: 'class'
            },
            {
              key: '平台',
              value: 'platform'
            }
          ]
        },
        {
          key: 'coupon_postion',
          name: '展示位置',
          selectValue: 'value',
          showValue: 'key',
          type: 'select',
          dataSource: [
            {
              key: '领卷中心',
              value: 'coupon'
            },
            {
              key: '后台',
              value: 'system'
            }
          ]
        },
        {
          key: 'goods_id',
          name: '商品ID',
          selectValue: 'goods_id',
          showValue: 'goods_name',
          type: 'search',
          dataSource: []
        },
        {
          key: 'class_id',
          name: '商品类别ID',
          selectValue: 'class_id',
          showValue: 'class_name',
          type: 'search',
          dataSource: []
        },
        {
          key: 'merchants_id',
          name: '商家ID',
          selectValue: 'merchants_id',
          showValue: 'merchants_name',
          type: 'search',
          dataSource: []
        }
      ];
    },
    methods: {
      doSuccess(index, data) {
        switch (index) {
          case 1:
            this.couponBean = data;
            break;
          case 2:
            this.showTip('保存成功', 'success');
            this.$router.back();
            break;
          case 3:
            this.baseData[10].dataSource = data;
            break;
          case 4:
            this.baseData[11].dataSource = data;
            break;
          case 5:
            this.baseData[12].dataSource = data;
            break;
        }
      },
      change(key, value) {
        this.couponBean[key] = value;
      },
      toolClick(index) {
        switch (index){
          case 0:
            this.post(2, 'memberController.api?updateCoupon', this.couponBean);
            break;
        }
      },
    }
  }
</script>
<style scoped>

</style>
